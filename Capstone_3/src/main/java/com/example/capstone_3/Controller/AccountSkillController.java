package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.Model.AccountSkill;
import com.example.capstone_3.Service.AccountSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account-skill")
@RequiredArgsConstructor
public class AccountSkillController {

    private final AccountSkillService accountSkillService;

    @GetMapping("/get")
    public ResponseEntity<?> getAccountSkills() {
        return ResponseEntity.status(200).body(accountSkillService.getAccountSkills());
    }

    @PostMapping("/add/{accountId}/{skillId}")
    public ResponseEntity<?> addAccountSkill(@PathVariable Integer accountId, @PathVariable Integer skillId) {
        accountSkillService.addAccountSkill(accountId, skillId);
        return ResponseEntity.status(200).body(new ApiResponse("Skill added to account"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAccountSkill(@PathVariable Integer id, @RequestBody @Valid AccountSkill accountSkill) {
        accountSkillService.updateAccountSkill(id, accountSkill);
        return ResponseEntity.status(200).body(new ApiResponse("Account skill updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccountSkill(@PathVariable Integer id) {
        accountSkillService.deleteAccountSkill(id);
        return ResponseEntity.status(200).body(new ApiResponse("Account skill deleted"));
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<?> getSkillsByAccount(@PathVariable Integer accountId) {
        return ResponseEntity.status(200).body(accountSkillService.getSkillsByAccount(accountId));
    }

    @GetMapping("/verified/{accountId}")
    public ResponseEntity<?> getVerifiedSkillsOfAccount(@PathVariable Integer accountId) {
        return ResponseEntity.status(200).body(accountSkillService.getVerifiedSkillsOfAccount(accountId));
    }
}