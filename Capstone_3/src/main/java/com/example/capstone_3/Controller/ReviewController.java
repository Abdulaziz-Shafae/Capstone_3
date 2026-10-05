package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.DtoIn.ReviewDtoIn;
import com.example.capstone_3.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        return ResponseEntity.status(200).body(reviewService.get());
    }

    @GetMapping("/get/exchange/{exchangeId}")
    public ResponseEntity<?> getByExchangeId(@PathVariable Integer exchangeId) {
        return ResponseEntity.status(200).body(reviewService.getByExchangeId(exchangeId));
    }

    @GetMapping("/get/account/{accountId}")
    public ResponseEntity<?> getByReviewedAccountId(@PathVariable Integer accountId) {
        return ResponseEntity.status(200).body(reviewService.getByReviewedAccountId(accountId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid ReviewDtoIn dto) {
        reviewService.add(dto);
        return ResponseEntity.status(200).body(new ApiResponse("review added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid ReviewDtoIn dto) {
        reviewService.update(id, dto);
        return ResponseEntity.status(200).body(new ApiResponse("review updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        reviewService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("review deleted"));
    }
}

