package com.yashpawar5.BookMyShow.Requests;

import com.yashpawar5.BookMyShow.Enums.Language;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AddMovieRequest {

    private String movieName;
    private Language language;
    private Double rating;
    private Double duration;
    private LocalDate releaseDate;

}
