package com.arun.ProdReadyFeature.app.controllers;

import com.arun.ProdReadyFeature.app.dto.UserDto;
import com.arun.ProdReadyFeature.app.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //Get All Users

    @GetMapping
    public List<UserDto> getAllUsers(){

        return userService.getAllUsers();
    }

    //Get User By Id

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable  Long id){
        return userService.getUserById(id);
    }

    //Creating a User

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    //Updating a User

    @PutMapping("/{id}")
    public UserDto updateUser(@RequestBody UserDto userDto,@PathVariable Long id){

        return userService.updateUser(id,userDto);
    }

    //Deleting a User

    @DeleteMapping("/{id}")
    public Boolean deleteUserById(@PathVariable Long id){
        return userService.deleteUser(id);
    }





}
