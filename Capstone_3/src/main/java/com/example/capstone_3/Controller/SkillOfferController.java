package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.Model.SkillOffer;
import com.example.capstone_3.Service.SkillOfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/skill-offer")
@RequiredArgsConstructor
public class SkillOfferController {

    private final SkillOfferService skillOfferService;

    @GetMapping("/get")
    public ResponseEntity<?> getSkillOffer() {
        return ResponseEntity.status(200).body(skillOfferService.getSkillOffer());
    }

    @PostMapping("/add/{accountId}/{skillId}")
    public ResponseEntity<?> addOffer(@PathVariable Integer accountId, @PathVariable Integer skillId, @RequestBody @Valid SkillOffer skillOffer) {
        skillOfferService.addOffer(accountId, skillId, skillOffer);
        return ResponseEntity.status(200).body(new ApiResponse("Skill offer added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSkillOffer(@PathVariable Integer id, @RequestBody @Valid SkillOffer skillOffer) {
        skillOfferService.updateSkillOffer(id, skillOffer);
        return ResponseEntity.status(200).body(new ApiResponse("Skill offer updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSkillOffer(@PathVariable Integer id) {
        skillOfferService.deleteSkillOffer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Skill offer deleted"));
    }
}