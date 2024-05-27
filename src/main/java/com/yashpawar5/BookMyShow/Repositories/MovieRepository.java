package com.yashpawar5.BookMyShow.Repositories;

import com.yashpawar5.BookMyShow.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    public Movie findMovieByMovieName(String name);
    public List<Movie> getMoviesByGenre(Enum Genre);
}
