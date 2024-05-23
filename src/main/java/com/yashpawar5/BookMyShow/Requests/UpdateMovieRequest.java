package com.yashpawar5.BookMyShow.Requests;

import com.yashpawar5.BookMyShow.Enums.Language;
import lombok.Data;

@Data
public class UpdateMovieRequest {
    private String movieName;
    private Language newLanguage;
    private Double newRating;
}
