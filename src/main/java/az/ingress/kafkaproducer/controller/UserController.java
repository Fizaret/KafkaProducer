package az.ingress.kafkaproducer.controller;

import az.ingress.kafkaproducer.dto.UserDto;
import az.ingress.kafkaproducer.dto.UserResponseDto;
import az.ingress.kafkaproducer.dto.UserUpdateBalanceDto;
import az.ingress.kafkaproducer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> publish(@RequestBody UserDto userDto){
        userService.register(userDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/balance/{id}")
    public UserResponseDto addBalance(@PathVariable Long id, @RequestBody UserUpdateBalanceDto balance){
        return userService.addBalance(id,balance);
    }


}
