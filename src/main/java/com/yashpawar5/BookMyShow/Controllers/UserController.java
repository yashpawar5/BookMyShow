package com.yashpawar5.BookMyShow.Controllers;

import com.yashpawar5.BookMyShow.Requests.AddUserRequest;
import com.yashpawar5.BookMyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("addUser")
    public String addUser(@RequestBody AddUserRequest userRequest){

        return userService.addUser(userRequest);
    }

    @GetMapping("bookingHistory")
    public ResponseEntity bookingHistory(@RequestParam int userId){
        List response = userService.getBookingHistory(userId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
