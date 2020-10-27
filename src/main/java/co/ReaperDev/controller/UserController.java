package co.ReaperDev.controller;

import co.ReaperDev.dto.UserDTO;
import co.ReaperDev.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService c){
        this.service = c;
    }

    @PostMapping(value = "/createUser", consumes = "application/json")
    public ResponseEntity createUser(@RequestBody UserDTO userDTO) {
        log.info("createUser CALLED");
        service.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/userLogin", consumes = "application/json")
    public ResponseEntity userLogIn(@RequestBody UserDTO userDTO) {
        log.info("USER LOGGING IN");
        return ResponseEntity.status(HttpStatus.OK).body(service.userLogin(userDTO));
    }

    @GetMapping(value = "/getAllUsers")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllUsers());
    }
}
