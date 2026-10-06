package com.example.capstone_3.Controller;

import com.example.capstone_3.Service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    //52 Find providers by skill done
    @GetMapping("/providers/{skillId}")
    public ResponseEntity<?> findProvidersBySkill(@PathVariable Integer skillId) {
        return ResponseEntity.status(200).body(searchService.findProvidersBySkill(skillId));
    }

    //53 Find requests by skill done
    @GetMapping("/requests/{skillId}")
    public ResponseEntity<?> findRequestsBySkill(@PathVariable Integer skillId) {
        return ResponseEntity.status(200).body(searchService.findRequestsBySkill(skillId));
    }
}