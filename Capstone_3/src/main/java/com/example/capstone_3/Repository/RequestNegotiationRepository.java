package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.RequestNegotiation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequestNegotiationRepository extends JpaRepository<RequestNegotiation, Integer> {
    RequestNegotiation findRequestNegotiationById(Integer id);
    List<RequestNegotiation> findByLearningRequest_IdOrderByCreatedAtAscIdAsc(Integer requestId);
    RequestNegotiation findFirstByLearningRequest_IdOrderByCreatedAtDescIdDesc(Integer requestId);

    @Query(value = "SELECT learning_request_id FROM request_negotiation WHERE id = :negotiationId", nativeQuery = true)
    Integer findRequestIdByNegotiationId(@Param("negotiationId") Integer negotiationId);

}
