package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.Model.Account;
import com.example.capstone_3.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HexFormat;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final AccountRepository accountRepository;
    private final BrevoEmailService brevoEmailService;
    private final SecureRandom secureRandom = new SecureRandom();

    @Value("${app.public-base-url}")
    private String publicBaseUrl;

    @Transactional
    public void sendVerificationEmail(Integer accountId) {

        Account account = accountRepository.findAccountForVerification(accountId);

        if (account == null) {
            throw new ApiException("Account not found");
        }

        if (!"ACTIVE".equals(account.getStatus())) {
            throw new ApiException("Account is suspended or blocked");
        }

        if (Boolean.TRUE.equals(account.getEmailVerified())) {
            throw new ApiException("Email is already verified");
        }

        LocalDateTime now = LocalDateTime.now();

        if (account.getEmailVerificationSentAt() != null && now.isBefore(account.getEmailVerificationSentAt().plusSeconds(60))) {
            throw new ApiException("Please wait 60 seconds before requesting another email");
        }

        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        String token = HexFormat.of().formatHex(randomBytes);

        account.setEmailVerificationHash(hash(token));
        account.setEmailVerificationExpiresAt(now.plusHours(24));
        account.setEmailVerificationSentAt(now);

        String verificationLink = publicBaseUrl.replaceAll("/+$", "") + "/api/v1/account/verify-email?accountId=" + accountId + "&token=" + token;

        brevoEmailService.sendVerificationEmail(account.getEmail(), verificationLink);

        accountRepository.save(account);
    }

    @Transactional
    public void verifyEmail(Integer accountId, String token) {

        if (token == null || !token.matches("^[a-f0-9]{64}$")) {
            throw new ApiException("Invalid verification link");
        }

        Account account = accountRepository.findAccountForVerification(accountId);

        if (account == null) {
            throw new ApiException("Invalid verification link");
        }

        if (!"ACTIVE".equals(account.getStatus())) {
            throw new ApiException("Account is suspended or blocked");
        }

        if (Boolean.TRUE.equals(account.getEmailVerified())) {
            throw new ApiException("Email is already verified");
        }

        if (account.getEmailVerificationHash() == null || account.getEmailVerificationExpiresAt() == null) {
            throw new ApiException("Invalid verification link");
        }

        if (!LocalDateTime.now().isBefore(account.getEmailVerificationExpiresAt())) {
            throw new ApiException("Verification link expired. Request a new email");
        }

        byte[] expectedHash = account.getEmailVerificationHash().getBytes(StandardCharsets.UTF_8);
        byte[] receivedHash = hash(token).getBytes(StandardCharsets.UTF_8);

        if (!MessageDigest.isEqual(expectedHash, receivedHash)) {
            throw new ApiException("Invalid verification link");
        }

        account.setEmailVerified(true);
        account.setEmailVerificationHash(null);
        account.setEmailVerificationExpiresAt(null);

        accountRepository.save(account);
    }

    private String hash(String token) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return HexFormat.of().formatHex(digest.digest(token.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("SHA-256 is unavailable", exception);
        }
    }
}