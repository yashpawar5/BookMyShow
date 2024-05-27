package com.yashpawar5.BookMyShow.Controllers;

import com.yashpawar5.BookMyShow.Requests.AddUserRequest;
import com.yashpawar5.BookMyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("addUser")
    public String addUser(@RequestBody AddUserRequest userRequest){

        return userService.addUser(userRequest);
    }
}
