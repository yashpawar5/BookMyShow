package com.yashpawar5.BookMyShow.Services;

import com.yashpawar5.BookMyShow.Models.Movie;
import com.yashpawar5.BookMyShow.Models.Show;
import com.yashpawar5.BookMyShow.Models.Theater;
import com.yashpawar5.BookMyShow.Models.Ticket;
import com.yashpawar5.BookMyShow.Repositories.MovieRepository;
import com.yashpawar5.BookMyShow.Requests.AddMovieRequest;
import com.yashpawar5.BookMyShow.Requests.UpdateMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(AddMovieRequest movieRequest) {

        //From my Request Entry I am creating the entity : bcz entity saves into the DB
        Movie movie = new Movie();
        movie.setMovieName(movieRequest.getMovieName());
        movie.setDuration(movieRequest.getDuration());
        movie.setLanguage(movieRequest.getLanguage());
        movie.setRating(movieRequest.getRating());
        movie.setReleaseDate(movieRequest.getReleaseDate());

        movie = movieRepository.save(movie);
        return "Movie has been added to the DB with movieId "+movie.getMovieId();
    }

    public List getTheaters(int movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        List<Show> shows = movie.getShowsList();
        List<Theater> theaters = new ArrayList<>();

        for (Show show : shows) {
            if(show.getMovie().getMovieId() == movieId) {
                theaters.add(show.getTheater());
            }
        }
        return theaters;
    }

    public String getMovieRevenue(int movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        List<Ticket> ticketList = movie.getTicketList();
        Integer movieRevenue = 0;
        for (Ticket ticket : ticketList) {
            if(ticket.getMovieName().equals(movie.getMovieName())){
                movieRevenue += ticket.getTotalAmount();
            }

        }
        return "Total revenue for movie: "+movieId+" is "+movieRevenue;
    }

    public String updateMovieAttributes(UpdateMovieRequest movieRequest){

        //Get the movie Entity
        Movie movie = movieRepository.findMovieByMovieName(movieRequest.getMovieName());

        //Get value from hashmap

        //Update the new attributes
        movie.setLanguage(movieRequest.getNewLanguage());
        movie.setRating(movieRequest.getNewRating());

        //update the value

        // Save it back to DB
        movieRepository.save(movie);

        //put it back to HM
        return "Movie Attributes have been updated ";
    }
}
