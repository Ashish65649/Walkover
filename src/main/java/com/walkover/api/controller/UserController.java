package com.walkover.api.controller;

import com.walkover.api.entity.User;
import com.walkover.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService _service ;

    @PostMapping(path = "/register")
    public ResponseEntity<?> signUpUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(this._service.signUpUser(user), HttpStatus.ACCEPTED);
    }

    @GetMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        return new ResponseEntity<>(this._service.validateUser(email,password),HttpStatus.OK);
    }

    @PutMapping("/update-password")
    public ResponseEntity<?> changePassword(@RequestParam("email") String email, @RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass) {
        return new ResponseEntity<>(this._service.updatePassword(email, oldPass, newPass),HttpStatus.ACCEPTED);
    }

    @GetMapping("/all-users")
    public ResponseEntity<?> getUsers(@RequestParam("pageNo") int pageNo,@RequestParam("noOfItems") int pageSize) {
        return new ResponseEntity<>(this._service.getAllUsers(pageNo,pageSize),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUser(@RequestParam("email") String email,@RequestParam("password") String password) {
        return new ResponseEntity<>(this._service.deleteUser(email,password),HttpStatus.OK);
    }
}