package com.korit.library.web.api;


import com.korit.library.aop.annotation.ValidAspect;
import com.korit.library.service.AccountService;
import com.korit.library.web.dto.CMRespDto;
import com.korit.library.web.dto.UserDto;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/account")
public class AccountApi {

    @Autowired
    private AccountService accountService;
    @ValidAspect
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDto userDto, BindingResult bindingResult){

        accountService.duplicateUsername(userDto.getUsername());
        accountService.compareToPassword(userDto.getPassword(),userDto.getRepassword());

        UserDto user = accountService.registerUser(userDto); //회원가입된 유저


        return ResponseEntity
                .created(URI.create("/api/account/user/" + user.getUserId()))
                .body(new CMRespDto<>(HttpStatus.CREATED.value(),"Create a new User",user));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable int userId){
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.CREATED.OK.value(),"Success", accountService.getUser(userId)));
    }


}
