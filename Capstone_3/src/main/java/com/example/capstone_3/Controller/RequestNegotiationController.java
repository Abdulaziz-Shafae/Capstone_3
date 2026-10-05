package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.DtoIn.RequestNegotiationDtoIn;
import com.example.capstone_3.Service.RequestNegotiationService;
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


    @PostMapping("/add/{requestId}/{senderAccountId}")
    public ResponseEntity<?> add(@PathVariable Integer requestId, @PathVariable Integer senderAccountId, @RequestBody @Valid RequestNegotiationDtoIn requestNegotiationDtoIn){
        requestNegotiationService.add(requestId, senderAccountId, requestNegotiationDtoIn);
        return ResponseEntity.status(200).body(new ApiResponse("request negotiation added"));
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

}