package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.Model.SkillAssessment;
import com.example.capstone_3.Service.SkillAssessmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/skill-assessment")
@RequiredArgsConstructor
public class SkillAssessmentController {

    private final SkillAssessmentService skillAssessmentService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllSkillAssessments() {
        return ResponseEntity.status(200).body(skillAssessmentService.getAllSkillAssessments());
    }

    @PostMapping("/take/{accountSkillId}")
    public ResponseEntity<?> takeSkillAssessment(@PathVariable Integer accountSkillId, @RequestBody @Valid SkillAssessment skillAssessment) {
        skillAssessmentService.addSkillAssessment(accountSkillId, skillAssessment);
        return ResponseEntity.status(200).body(new ApiResponse("Skill assessment added"));
    }

    // Update and Delete are not allowed: assessment results are a permanent record (admin only if needed)
}