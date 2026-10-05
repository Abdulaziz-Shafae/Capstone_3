package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.DtoIn.SessionParticipantDtoIn;
import com.example.capstone_3.Service.SessionParticipantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/session-participant")
public class SessionParticipantController {

    private final SessionParticipantService sessionParticipantService;

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        return ResponseEntity.status(200).body(sessionParticipantService.get());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid SessionParticipantDtoIn dto) {
        sessionParticipantService.add(dto);
        return ResponseEntity.status(200).body(new ApiResponse("session participant added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid SessionParticipantDtoIn dto) {
        sessionParticipantService.update(id, dto);
        return ResponseEntity.status(200).body(new ApiResponse("session participant updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        sessionParticipantService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("session participant deleted"));
    }
}

