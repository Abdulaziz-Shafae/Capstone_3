package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.DtoIn.ExchangeDtoIn;
import com.example.capstone_3.Service.ExchangeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exchange")
public class ExchangeController {

    private final ExchangeService exchangeService;


    @GetMapping("/get")
    public ResponseEntity<?> get(){
        return ResponseEntity.status(200).body(exchangeService.get());
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid ExchangeDtoIn exchangeDtoIn){
        exchangeService.add(exchangeDtoIn);
        return ResponseEntity.status(200).body(new ApiResponse("exchange added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid ExchangeDtoIn exchangeDtoIn){
        exchangeService.update(id, exchangeDtoIn);
        return ResponseEntity.status(200).body(new ApiResponse("exchange updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        exchangeService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("exchange deleted"));
    }

}