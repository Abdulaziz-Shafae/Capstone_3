package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.Model.LearningRequest;
import com.example.capstone_3.Service.LearningRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/learning-request")
@RequiredArgsConstructor
public class LearningRequestController {

    private final LearningRequestService learningRequestService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllLearningRequests() {
        return ResponseEntity.status(200).body(learningRequestService.getAllLearningRequests());
    }

    @PostMapping("/add/{accountId}/{skillId}/{providerAccountId}")
    public ResponseEntity<?> addLearningRequest(@PathVariable Integer accountId, @PathVariable Integer skillId, @PathVariable Integer providerAccountId, @RequestBody @Valid LearningRequest learningRequest){
        learningRequestService.addLearningRequest(accountId, skillId, providerAccountId, learningRequest);
        return ResponseEntity.status(200).body(new ApiResponse("Learning request added"));
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
}