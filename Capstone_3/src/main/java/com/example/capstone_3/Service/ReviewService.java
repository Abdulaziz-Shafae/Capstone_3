package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DtoIn.ReviewDtoIn;
import com.example.capstone_3.Model.Account;
import com.example.capstone_3.Model.Exchange;
import com.example.capstone_3.Model.Review;
import com.example.capstone_3.Repository.AccountRepository;
import com.example.capstone_3.Repository.ExchangeRepository;
import com.example.capstone_3.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ExchangeRepository exchangeRepository;
    private final AccountRepository accountRepository;

    public List<Review> get() {
        return reviewRepository.findAll();
    }

    public List<Review> getByExchangeId(Integer exchangeId) {
        return reviewRepository.findReviewsByExchange_Id(exchangeId);
    }

    public List<Review> getByReviewedAccountId(Integer accountId) {
        if (accountRepository.findAccountById(accountId) == null) {
            throw new ApiException("No account found");
        }

        return reviewRepository.findReviewsByReviewedAccount_Id(accountId);
    }

    public Double getAverageRating(Integer accountId) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("No account found");
        }

        List<Review> reviews = reviewRepository.findReviewsByReviewedAccount_Id(accountId);
        return reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
    }

    public void add(Integer exchangeId, Integer reviewerId, ReviewDtoIn dto) {
        Exchange exchange = exchangeRepository.findExchangeById(exchangeId);

        if (exchange == null) {
            throw new ApiException("No exchange found");
        }

        Account reviewer = accountRepository.findAccountById(reviewerId);
        if (reviewer == null) {
            throw new ApiException("No reviewer account found");
        }

        Account reviewed = accountRepository.findAccountById(dto.getReviewedAccountId());
        if (reviewed == null) {
            throw new ApiException("No reviewed account found");
        }

        if (reviewer.getId().equals(reviewed.getId())) {
            throw new ApiException("You cannot review your own account");
        }

        Review existingReview = reviewRepository.findReviewByExchange_IdAndReviewerAccount_Id(exchangeId, reviewerId);

        if (existingReview != null) {
            throw new ApiException("You have already reviewed this exchange");
        }

        Review review = new Review();
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setExchange(exchange);
        review.setReviewerAccount(reviewer);
        review.setReviewedAccount(reviewed);

        reviewRepository.save(review);
    }

    public void update(Integer id, ReviewDtoIn dto) {
        Review oldReview = reviewRepository.findReviewById(id);

        if (oldReview == null) {
            throw new ApiException("No review found");
        }

        Exchange exchange = exchangeRepository.findExchangeById(dto.getExchangeId());
        if (exchange == null) {
            throw new ApiException("No exchange found");
        }

        Account reviewer = accountRepository.findAccountById(dto.getReviewerAccountId());
        if (reviewer == null) {
            throw new ApiException("No reviewer account found");
        }

        Account reviewed = accountRepository.findAccountById(dto.getReviewedAccountId());
        if (reviewed == null) {
            throw new ApiException("No reviewed account found");
        }

        if (reviewer.getId().equals(reviewed.getId())) {
            throw new ApiException("You cannot review your own account");
        }

        Review existingReview = reviewRepository.findReviewByExchange_IdAndReviewerAccount_Id(dto.getExchangeId(), dto.getReviewerAccountId());
        if (existingReview != null && !existingReview.getId().equals(id)) {
            throw new ApiException("You have already reviewed this exchange");
        }

        oldReview.setRating(dto.getRating());
        oldReview.setComment(dto.getComment());
        oldReview.setExchange(exchange);
        oldReview.setReviewerAccount(reviewer);
        oldReview.setReviewedAccount(reviewed);

        reviewRepository.save(oldReview);
    }

    public void delete(Integer id) {
        Review review = reviewRepository.findReviewById(id);
        if (review == null) {
            throw new ApiException("No review found");
        }

        reviewRepository.delete(review);
    }
}
