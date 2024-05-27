package com.yashpawar5.BookMyShow.Models;

import com.yashpawar5.BookMyShow.Enums.Genre;
import com.yashpawar5.BookMyShow.Enums.Language;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(unique = true)
    private String movieName;

    private Double duration;

    private LocalDate releaseDate;

    private Genre genre;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    private Double rating;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Show> showsList = new ArrayList<>();

    @OneToMany(mappedBy = "movies",cascade = CascadeType.ALL)
    private List<Ticket> ticketList = new ArrayList<>();
}
