package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.Model.Skill;
import com.example.capstone_3.Service.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/skill")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllSkills() {
        return ResponseEntity.status(200).body(skillService.getAllSkills());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSkill(@RequestBody @Valid Skill skill) {
        skillService.addSkill(skill);
        return ResponseEntity.status(200).body(new ApiResponse("Skill added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSkill(@PathVariable Integer id, @RequestBody @Valid Skill skill) {
        skillService.updateSkill(id, skill);
        return ResponseEntity.status(200).body(new ApiResponse("Skill updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable Integer id) {
        skillService.deleteSkill(id);
        return ResponseEntity.status(200).body(new ApiResponse("Skill deleted"));
    }
}