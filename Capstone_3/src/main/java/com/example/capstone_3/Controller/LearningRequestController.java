package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.Model.LearningRequest;
import com.example.capstone_3.Service.LearningRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.capstone_3.DtoIn.CreateLearningRequestDtoIn;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/learning-request")
@RequiredArgsConstructor
public class LearningRequestController {

    private final LearningRequestService learningRequestService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllLearningRequests() {
        return ResponseEntity.status(200).body(learningRequestService.getAllLearningRequests());
    }

    @PostMapping("/create/{offerId}")
    public ResponseEntity<?> addLearningRequest(@PathVariable Integer offerId, @RequestBody @Valid CreateLearningRequestDtoIn dtoIn, HttpSession session) {
        learningRequestService.addLearningRequest((Integer) session.getAttribute("accountId"), offerId, dtoIn);
        return ResponseEntity.status(200).body(new ApiResponse("Learning request created successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLearningRequest(@PathVariable Integer id, @RequestBody @Valid LearningRequest learningRequest) {
        learningRequestService.updateLearningRequest(id, learningRequest);
        return ResponseEntity.status(200).body(new ApiResponse("Learning request updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLearningRequest(@PathVariable Integer id) {
        learningRequestService.deleteLearningRequest(id);
        return ResponseEntity.status(200).body(new ApiResponse("Learning request deleted"));
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<?> getLearningRequestById(@PathVariable Integer requestId, HttpSession session) {
        return ResponseEntity.status(200).body(learningRequestService.getLearningRequestById(requestId, (Integer) session.getAttribute("accountId")));
    }
}