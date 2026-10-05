package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Review findReviewById(Integer id);

    List<Review> findReviewsByExchange_Id(Integer exchangeId);

    Review findReviewByExchange_IdAndReviewerAccount_Id(
            Integer exchangeId,
            Integer reviewerAccountId
    );

    List<Review> findReviewsByReviewedAccount_Id(Integer accountId);
}

