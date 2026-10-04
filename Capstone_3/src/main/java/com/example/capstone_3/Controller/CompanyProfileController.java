package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.DtoIn.CompanyProfileDtoIn;
import com.example.capstone_3.Service.CompanyProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company-profile")
public class CompanyProfileController {

    private final CompanyProfileService companyProfileService;


    @GetMapping("/get")
    public ResponseEntity<?> get(){
        return ResponseEntity.status(200).body(companyProfileService.get());
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid CompanyProfileDtoIn companyProfileDtoIn){
        companyProfileService.add(companyProfileDtoIn);
        return ResponseEntity.status(200).body(new ApiResponse("company profile added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid CompanyProfileDtoIn companyProfileDtoIn){
        companyProfileService.update(id, companyProfileDtoIn);
        return ResponseEntity.status(200).body(new ApiResponse("company profile updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        companyProfileService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("company profile deleted"));
    }

}