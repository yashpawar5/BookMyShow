package com.yashpawar5.BookMyShow.Controllers;

import com.yashpawar5.BookMyShow.Requests.AddTheaterRequest;
import com.yashpawar5.BookMyShow.Requests.AddTheaterSeatsRequest;
import com.yashpawar5.BookMyShow.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;
    
    @PostMapping("add")
    public ResponseEntity addTheater(@RequestBody AddTheaterRequest addTheaterRequest){

        String response = theaterService.addTheater(addTheaterRequest);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @PutMapping("associateSeats")
    public ResponseEntity associateSeats(@RequestBody AddTheaterSeatsRequest theaterSeatsRequest){

        String response = theaterService.associateTheaterSeats(theaterSeatsRequest);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("theaterRevenue")
    public ResponseEntity getTheaterRevenue(@RequestParam Integer theaterId,
                                            @RequestParam LocalDate localDate){
        String response = theaterService.getTheaterRevenue(theaterId, localDate);
        return new ResponseEntity(response,HttpStatus.OK);
    }

}
