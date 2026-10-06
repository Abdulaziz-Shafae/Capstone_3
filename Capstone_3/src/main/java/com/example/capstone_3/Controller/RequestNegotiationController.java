package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.DtoIn.RequestNegotiationDtoIn;
import com.example.capstone_3.Service.RequestNegotiationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/request-negotiation")
public class RequestNegotiationController {

    private final RequestNegotiationService requestNegotiationService;


    @GetMapping("/get")
    public ResponseEntity<?> get(){
        return ResponseEntity.status(200).body(requestNegotiationService.get());
    }


    @PostMapping("/respond/{requestId}")
    public ResponseEntity<?> respond(@PathVariable Integer requestId, @RequestBody @Valid RequestNegotiationDtoIn dtoIn, HttpSession session) {
        requestNegotiationService.respond((Integer) session.getAttribute("accountId"), requestId, dtoIn);
        return ResponseEntity.status(200).body(new ApiResponse("Response sent successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid RequestNegotiationDtoIn requestNegotiationDtoIn){
        requestNegotiationService.update(id, requestNegotiationDtoIn);
        return ResponseEntity.status(200).body(new ApiResponse("request negotiation updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        requestNegotiationService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("request negotiation deleted"));
    }

    @GetMapping("/request/{requestId}")
    public ResponseEntity<?> getNegotiationHistory(@PathVariable Integer requestId, HttpSession session) {
        return ResponseEntity.status(200).body(requestNegotiationService.getNegotiationHistory((Integer) session.getAttribute("accountId"), requestId));
    }

    @GetMapping("/latest/{requestId}")
    public ResponseEntity<?> getLatestNegotiation(@PathVariable Integer requestId, HttpSession session) {
        return ResponseEntity.status(200).body(requestNegotiationService.getLatestNegotiation((Integer) session.getAttribute("accountId"), requestId));
    }

    @GetMapping("/{requestId}/calculate-urgency/{negotiationId}")
    public ResponseEntity<?> getNegotiationProposal(@PathVariable Integer requestId, @PathVariable Integer negotiationId, HttpSession session) {
        return ResponseEntity.status(200).body(requestNegotiationService.getNegotiationProposal((Integer) session.getAttribute("accountId"), requestId, negotiationId));
    }

    @PutMapping("/{negotiationId}/accept")
    public ResponseEntity<?> acceptProposal(@PathVariable Integer negotiationId, HttpSession session) {
        requestNegotiationService.acceptProposal((Integer) session.getAttribute("accountId"), negotiationId);
        return ResponseEntity.status(200).body(new ApiResponse("Proposal accepted successfully"));
    }

}
