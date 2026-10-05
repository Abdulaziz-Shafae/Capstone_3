package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.DtoIn.ExchangeDtoIn;
import com.example.capstone_3.Service.ExchangeService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exchange")
public class ExchangeController {

    private final ExchangeService exchangeService;


    @GetMapping("/get")
    public ResponseEntity<?> get(){
        return ResponseEntity.status(200).body(exchangeService.get());
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid ExchangeDtoIn exchangeDtoIn){
        exchangeService.add(exchangeDtoIn);
        return ResponseEntity.status(200).body(new ApiResponse("exchange added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid ExchangeDtoIn exchangeDtoIn){
        exchangeService.update(id, exchangeDtoIn);
        return ResponseEntity.status(200).body(new ApiResponse("exchange updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        exchangeService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("exchange deleted"));
    }

    @PostMapping("/create/{requestId}/{offerId}")
    public ResponseEntity<?> createExchange(@PathVariable Integer requestId, @PathVariable Integer offerId, HttpSession session) {
        exchangeService.createExchange((Integer) session.getAttribute("accountId"), requestId, offerId);
        return ResponseEntity.status(200).body(new ApiResponse("Exchange created successfully"));
    }

    @GetMapping("/{exchangeId}")
    public ResponseEntity<?> getExchangeDetails(@PathVariable Integer exchangeId, HttpSession session) {
        return ResponseEntity.status(200).body(exchangeService.getExchangeDetails((Integer) session.getAttribute("accountId"), exchangeId));
    }

    @PutMapping("/{exchangeId}/accept")
    public ResponseEntity<?> acceptExchange(@PathVariable Integer exchangeId, HttpSession session) {
        exchangeService.acceptExchange((Integer) session.getAttribute("accountId"), exchangeId);
        return ResponseEntity.status(200).body(new ApiResponse("Exchange accepted successfully"));
    }

    @PutMapping("/{exchangeId}/cancel")
    public ResponseEntity<?> cancelExchange(@PathVariable Integer exchangeId, HttpSession session) {
        exchangeService.cancelExchange((Integer) session.getAttribute("accountId"), exchangeId);
        return ResponseEntity.status(200).body(new ApiResponse("Exchange cancelled successfully"));
    }

    @GetMapping("/account")
    public ResponseEntity<?> getAccountExchanges(HttpSession session) {
        return ResponseEntity.status(200).body(exchangeService.getAccountExchanges((Integer) session.getAttribute("accountId")));
    }


}