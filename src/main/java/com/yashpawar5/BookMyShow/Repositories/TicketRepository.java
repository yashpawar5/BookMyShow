package com.yashpawar5.BookMyShow.Repositories;

import com.yashpawar5.BookMyShow.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
    public Ticket findTicketsByTheaterName(String theaterName);
}
