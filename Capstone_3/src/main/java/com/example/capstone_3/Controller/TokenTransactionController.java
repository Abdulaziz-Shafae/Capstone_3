package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.DtoIn.TokenTransactionDtoIn;
import com.example.capstone_3.Service.TokenTransactionService;
import jakarta.validation.Valid;
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
}

