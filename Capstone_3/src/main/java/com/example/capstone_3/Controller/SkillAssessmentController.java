package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.Model.SkillAssessment;
import com.example.capstone_3.Service.SkillAssessmentService;
import jakarta.servlet.http.HttpSession;
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

    // 10 (login required)
    @PostMapping("/take/{accountSkillId}")
    public ResponseEntity<?> takeSkillAssessment(HttpSession session, @PathVariable Integer accountSkillId,
                                                 @RequestBody @Valid SkillAssessment skillAssessment) {
        skillAssessmentService.addSkillAssessment((Integer) session.getAttribute("accountId"), accountSkillId, skillAssessment);
        return ResponseEntity.status(200).body(new ApiResponse("Skill assessment added"));
    }


    @GetMapping("/history/{accountSkillId}")
    public ResponseEntity<?> getAssessmentHistory(@PathVariable Integer accountSkillId, HttpSession session) {
        return ResponseEntity.status(200).body(skillAssessmentService.getAssessmentHistory((Integer) session.getAttribute("accountId"), accountSkillId));
    }

    @GetMapping("/latest/{accountSkillId}")
    public ResponseEntity<?> getLatestAssessment(@PathVariable Integer accountSkillId, HttpSession session) {
        return ResponseEntity.status(200).body(skillAssessmentService.getLatestAssessment((Integer) session.getAttribute("accountId"), accountSkillId));
    }

    // Update and Delete are not allowed: assessment results are a permanent record (admin only if needed)
}