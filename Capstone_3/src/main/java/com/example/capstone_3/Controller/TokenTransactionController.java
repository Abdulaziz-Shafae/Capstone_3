package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.DtoIn.TokenTransactionDtoIn;
import com.example.capstone_3.Service.TokenTransactionService;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/token-transaction")
public class TokenTransactionController {

    private final TokenTransactionService tokenTransactionService;

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        return ResponseEntity.status(200).body(tokenTransactionService.get());
    }

    @GetMapping("/get/account/{accountId}")
    public ResponseEntity<?> getByAccountId(@PathVariable Integer accountId) {
        return ResponseEntity.status(200).body(tokenTransactionService.getByAccountId(accountId));
    }

    @GetMapping("/get/exchange/{exchangeId}")
    public ResponseEntity<?> getByExchangeId(@PathVariable Integer exchangeId) {
        return ResponseEntity.status(200).body(tokenTransactionService.getByExchangeId(exchangeId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid TokenTransactionDtoIn dto) {
        tokenTransactionService.add(dto);
        return ResponseEntity.status(200).body(new ApiResponse("token transaction added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid TokenTransactionDtoIn dto) {
        tokenTransactionService.update(id, dto);
        return ResponseEntity.status(200).body(new ApiResponse("token transaction updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        tokenTransactionService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("token transaction deleted"));
    }
    // ================= Token endpoints =================

    // #43 Get token balance
    @GetMapping("/account/balance")
    public ResponseEntity<?> getBalance(HttpSession session) {
        Integer balance = tokenTransactionService.getBalance((Integer) session.getAttribute("accountId"));
        return ResponseEntity.status(200).body(new ApiResponse("Balance is " + balance + " tokens"));
    }

    // #44 Get token transaction history
    @GetMapping("/account/history")
    public ResponseEntity<?> getHistory(HttpSession session) {
        return ResponseEntity.status(200).body(tokenTransactionService.getHistory((Integer) session.getAttribute("accountId")));
    }

    // #45 Give bonus tokens (every 5 completed teachings)
    @PostMapping("/bonus")
    public ResponseEntity<?> giveBonus(HttpSession session) {
        tokenTransactionService.giveBonus((Integer) session.getAttribute("accountId"));
        return ResponseEntity.status(200).body(new ApiResponse("Bonus tokens added"));
    }

    // #46 Refund tokens for exchange
    @PostMapping("/refund/{exchangeId}")
    public ResponseEntity<?> refundExchange(@PathVariable Integer exchangeId, HttpSession session) {
        tokenTransactionService.refundExchange((Integer) session.getAttribute("accountId"), exchangeId);
        return ResponseEntity.status(200).body(new ApiResponse("Tokens refunded"));
    }

    // #47 Purchase tokens using money
    @PostMapping("/purchase/{amount}")
    public ResponseEntity<?> purchaseTokens(@PathVariable Integer amount, HttpSession session) {
        Integer price = tokenTransactionService.purchaseTokens((Integer) session.getAttribute("accountId"), amount);
        return ResponseEntity.status(200).body(new ApiResponse("Simulated purchase: added " + amount + " tokens for " + price + " SAR. No real payment processed"));
    }

    // #48 Redeem tokens into money
    @PostMapping("/redeem/{amount}")
    public ResponseEntity<?> redeemTokens(@PathVariable Integer amount, HttpSession session) {
        Integer money = tokenTransactionService.redeemTokens((Integer) session.getAttribute("accountId"), amount);
        return ResponseEntity.status(200).body(new ApiResponse("Redeemed " + amount + " tokens for " + money + " SAR"));
    }
}

