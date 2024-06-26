package com.yashpawar5.BookMyShow.Services;

import com.yashpawar5.BookMyShow.Enums.SeatType;
import com.yashpawar5.BookMyShow.Models.Show;
import com.yashpawar5.BookMyShow.Models.Theater;
import com.yashpawar5.BookMyShow.Models.TheaterSeat;
import com.yashpawar5.BookMyShow.Models.Ticket;
import com.yashpawar5.BookMyShow.Repositories.*;
import com.yashpawar5.BookMyShow.Requests.AddTheaterRequest;
import com.yashpawar5.BookMyShow.Requests.AddTheaterSeatsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private TheaterSeatsRepository theaterSeatsRepository;

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private ShowRepository showRepository;

    public String addTheater(AddTheaterRequest addTheaterRequest){

        Theater theater = Theater.builder().noOfScreens(addTheaterRequest.getNoOfScreens())
                .name(addTheaterRequest.getName())
                .address(addTheaterRequest.getAddress())
                .build();

        theater = theaterRepository.save(theater);
        return "Theater has been saved to the DB with theaterId "+theater.getTheaterId();
    }

    public String getTheaterRevenue(Integer theaterId, LocalDate localDate){
        Theater theater = theaterRepository.findById(theaterId).get();
        List<Ticket> ticketList = theater.getTicketList();
        List<Ticket> dayTicketList = new ArrayList<>();

        for(Ticket ticket : ticketList){
            if(ticket.getShowDate().equals(localDate) ){
                dayTicketList.add(ticket);
            }
        }
        Integer totalRevenue = 0;
        for(Ticket ticket : dayTicketList){
            if(ticket.getTheaterName().equals(theater.getName()) ){
                totalRevenue += ticket.getTotalAmount();
            }
        }
        theater.setTheaterRevenue(totalRevenue);
        theaterRepository.save(theater);
        return "The total revenue generated by Theater:"+theaterId+" on " +localDate+" is "+totalRevenue;
    }

    public List getMovieList(Integer theaterId){
        Theater theater = theaterRepository.findById(theaterId).get();
        List<Show> showList = theater.getShowList();
        List<String> movieList = new ArrayList<>();

        for(Show show : showList){
            if(show.getTheater().getTheaterId().equals(theaterId) && (!movieList.contains(show.getMovie().getMovieName()))){
                movieList.add(show.getMovie().getMovieName());
            }
        }
        return movieList;
    }


    public String associateTheaterSeats(AddTheaterSeatsRequest theaterSeatsRequest) {

        int theaterId = theaterSeatsRequest.getTheaterId();
        int noOfClassicSeats = theaterSeatsRequest.getNoOfClassicSeats();
        int noOfPremiumSeats = theaterSeatsRequest.getNoOfPremiumSeats();

        List<TheaterSeat> theaterSeatList = new ArrayList<>();

        //1. Get the theaterEntity from DB
        Theater theater = theaterRepository.findById(theaterId).get();

        //2. Generate those seatNos through for Classic Seats

        int noOfRowsOfClassicSeats = noOfClassicSeats/5; //Complete rows that i can build
        int noOfSeatsInLastRowClassic = noOfClassicSeats%5;
        int row;
        for(row= 1; row<=noOfRowsOfClassicSeats; row++) {

            for(int j=1;j<=5;j++) {

                char ch = (char)('A'+j-1);
                String seatNo = "" + row + ch;

                TheaterSeat theaterSeat = TheaterSeat.builder().seatNo(seatNo)
                        .seatType(SeatType.CLASSIC)
                        .theater(theater)
                        .build();
                theaterSeatList.add(theaterSeat);
            }
        }

        //For the last row
        for(int j=1;j<=noOfSeatsInLastRowClassic;j++) {
            char ch = (char)('A'+j-1);
            String seatNo = "" + row + ch;
            TheaterSeat theaterSeat = TheaterSeat.builder().seatNo(seatNo)
                    .seatType(SeatType.CLASSIC)
                    .theater(theater)
                    .build();
            theaterSeatList.add(theaterSeat);
        }

        //Same logic for the premium seats
        int noOfRowsInPremiumSeats = noOfPremiumSeats/5;
        int noOfSeatsInLastRowPremium = noOfPremiumSeats%5;

        int currentRow = row;
        if(noOfSeatsInLastRowClassic>0){
            currentRow++;
        }
        for(row=currentRow;row<=noOfRowsInPremiumSeats+currentRow-1; row++) {
            for(int j=1;j<=5;j++) {
                char ch = (char)('A'+j-1);
                String seatNo = "" + row + ch;
                TheaterSeat theaterSeat = TheaterSeat.builder().seatNo(seatNo)
                        .seatType(SeatType.PREMIUM)
                        .theater(theater)
                        .build();
                theaterSeatList.add(theaterSeat);
            }
        }
        //For the last row
        for(int j=1;j<=noOfSeatsInLastRowPremium;j++){
            char ch = (char)('A'+j-1);
            String seatNo = "" + row + ch;
            TheaterSeat theaterSeat = TheaterSeat.builder().seatNo(seatNo)
                    .seatType(SeatType.PREMIUM)
                    .theater(theater)
                    .build();
            theaterSeatList.add(theaterSeat);
        }

        theater.setTheaterSeatList(theaterSeatList);
        theaterRepository.save(theater);

        //Save all the generated Theater seats into the DB
        theaterSeatsRepository.saveAll(theaterSeatList);
        return "The theater seats have been associated";
    }
}
