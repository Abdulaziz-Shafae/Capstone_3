package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.DtoIn.SessionDtoIn;
import com.example.capstone_3.Service.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/session")
public class SessionController {

    private final SessionService sessionService;

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        return ResponseEntity.status(200).body(sessionService.get());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid SessionDtoIn sessionDtoIn) {
        sessionService.add(sessionDtoIn);
        return ResponseEntity.status(200).body(new ApiResponse("session added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid SessionDtoIn sessionDtoIn) {
        sessionService.update(id, sessionDtoIn);
        return ResponseEntity.status(200).body(new ApiResponse("session updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        sessionService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("session deleted"));
    }
}

