package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.DtoIn.IndividualProfileDtoIn;
import com.example.capstone_3.Service.IndividualProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/individual-profile")
public class IndividualProfileController {

    private final IndividualProfileService individualProfileService;


    @GetMapping("/get")
    public ResponseEntity<?> get(){
        return ResponseEntity.status(200).body(individualProfileService.get());
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid IndividualProfileDtoIn individualProfileDtoIn){
        individualProfileService.add(individualProfileDtoIn);
        return ResponseEntity.status(200).body(new ApiResponse("individual profile added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid IndividualProfileDtoIn individualProfileDtoIn){
        individualProfileService.update(id, individualProfileDtoIn);
        return ResponseEntity.status(200).body(new ApiResponse("individual profile updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        individualProfileService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("individual profile deleted"));
    }

}