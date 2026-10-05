package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.DtoIn.AccountDtoIn;
import com.example.capstone_3.DtoIn.LoginDtoIn;
import com.example.capstone_3.Service.AccountService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;


    @GetMapping("/get")
    public ResponseEntity<?> get(){
        return ResponseEntity.status(200).body(accountService.get());
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid AccountDtoIn accountDtoIn){
        accountService.add(accountDtoIn);
        return ResponseEntity.status(200).body(new ApiResponse("account added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid AccountDtoIn accountDtoIn){
        accountService.update(id, accountDtoIn);
        return ResponseEntity.status(200).body(new ApiResponse("account updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        accountService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("account deleted"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDtoIn loginDtoIn, HttpSession session){

        Integer accountId = accountService.login(loginDtoIn);

        session.setAttribute("accountId", accountId);

        return ResponseEntity.status(200).body(new ApiResponse("Logged in successfully"));
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session){

        if((Integer) session.getAttribute("accountId") == null){
            throw new ApiException("Already logged out");
        }

        session.invalidate();

        return ResponseEntity.status(200).body(new ApiResponse("Logged out successfully"));
    }

}