package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    long countByReviewedAccount_Id(Integer accountId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.reviewedAccount.id = :accountId")
    Double findAverageRating(@Param("accountId") Integer accountId);

}

