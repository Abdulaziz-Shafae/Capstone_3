package com.example.capstone_3.Controller;

import com.example.capstone_3.Service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;

    @GetMapping("/match/{learnerId}/{skillId}")
    public ResponseEntity<?> match(@PathVariable Integer learnerId, @PathVariable Integer skillId) {
        return ResponseEntity.ok(aiService.calculateMatch(learnerId, skillId));
    }

    @GetMapping("/match/{learnerId}/{providerId}/{skillId}/explanation")
    public ResponseEntity<?> matchExplanation(@PathVariable Integer learnerId, @PathVariable Integer providerId, @PathVariable Integer skillId) {
        return ResponseEntity.ok(aiService.explainMatch(learnerId, providerId, skillId));
    }

    @PostMapping(value = "/cv/extract-skills/{accountId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> extractSkills(@PathVariable Integer accountId, @RequestParam("file") MultipartFile file
    ) throws IOException {

        return ResponseEntity.ok(aiService.extractSkills(accountId, file));
    }

    @GetMapping("/cv/suggest-offers/{accountId}")
    public ResponseEntity<?> suggestOffers(@PathVariable Integer accountId) {
        return ResponseEntity.ok(aiService.suggestOffers(accountId));
    }
}
