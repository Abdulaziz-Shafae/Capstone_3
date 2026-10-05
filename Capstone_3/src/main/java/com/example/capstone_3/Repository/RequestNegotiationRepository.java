package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.RequestNegotiation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequestNegotiationRepository extends JpaRepository<RequestNegotiation, Integer> {
    RequestNegotiation findRequestNegotiationById(Integer id);
    List<RequestNegotiation> findByLearningRequest_IdOrderByCreatedAtAscIdAsc(Integer requestId);
    RequestNegotiation findFirstByLearningRequest_IdOrderByCreatedAtDescIdDesc(Integer requestId);
}
