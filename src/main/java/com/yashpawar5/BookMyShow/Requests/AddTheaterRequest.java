package com.yashpawar5.BookMyShow.Requests;

import lombok.Data;

@Data
public class AddTheaterRequest {
    private Integer noOfScreens;
    private String name;
    private String address;
}
