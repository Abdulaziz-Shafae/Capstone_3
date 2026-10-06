package com.example.capstone_3.Controller;

import com.example.capstone_3.Service.AIService;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/match/{skillId}")
    public ResponseEntity<?> match(@PathVariable Integer skillId, HttpSession session) {
        return ResponseEntity.ok(aiService.calculateMatch((Integer) session.getAttribute("accountId"), skillId));
    }

    @GetMapping("/match/{providerId}/{skillId}/explanation")
    public ResponseEntity<?> matchExplanation(@PathVariable Integer providerId, @PathVariable Integer skillId, HttpSession session) {
        return ResponseEntity.ok(aiService.explainMatch((Integer) session.getAttribute("accountId"), providerId, skillId));
    }

    @PostMapping(value = "/cv/extract-skills", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> extractSkills(@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        return ResponseEntity.ok(aiService.extractSkills((Integer) session.getAttribute("accountId"), file));
    }

    @GetMapping("/cv/suggest-offers")
    public ResponseEntity<?> suggestOffers(HttpSession session) {
        return ResponseEntity.ok(aiService.suggestOffers((Integer) session.getAttribute("accountId")));
    }
}
