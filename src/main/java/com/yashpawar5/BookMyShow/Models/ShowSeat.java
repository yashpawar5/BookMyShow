package com.yashpawar5.BookMyShow.Models;

import com.yashpawar5.BookMyShow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showSeatId;
    private String seatNo;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    private Boolean isBooked;
    private Boolean isFoodAttached;

    @JoinColumn
    @ManyToOne
    private Show show;



}
