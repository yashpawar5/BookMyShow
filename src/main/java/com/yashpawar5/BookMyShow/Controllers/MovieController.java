package com.yashpawar5.BookMyShow.Controllers;

import com.yashpawar5.BookMyShow.Models.Movie;
import com.yashpawar5.BookMyShow.Models.Theater;
import com.yashpawar5.BookMyShow.Requests.AddMovieRequest;
import com.yashpawar5.BookMyShow.Requests.UpdateMovieRequest;
import com.yashpawar5.BookMyShow.Services.MovieService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("add")
    public ResponseEntity addMovie(@RequestBody AddMovieRequest movieRequest){

        String response = movieService.addMovie(movieRequest);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity updateMovieAttributes(@RequestBody UpdateMovieRequest updateMovieRequest){

        String response = movieService.updateMovieAttributes(updateMovieRequest);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("theaters")
    public ResponseEntity getTheaters(@RequestParam int movieId){
        List response = movieService.getTheaters(movieId);
        return new ResponseEntity(response,HttpStatus.OK);
    }



}
