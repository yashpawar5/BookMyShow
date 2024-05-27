package com.yashpawar5.BookMyShow.Services;

import com.yashpawar5.BookMyShow.Models.Ticket;
import com.yashpawar5.BookMyShow.Models.User;
import com.yashpawar5.BookMyShow.Repositories.UserRepository;
import com.yashpawar5.BookMyShow.Requests.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
;import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public List getBookingHistory(int userId) {
        User user = userRepository.findById(userId).get();
        List<Ticket> ticketList = user.getTicketList();
        List<String> bookingHistory  = new ArrayList();
        for (Ticket ticket : ticketList) {
            if(ticket.getUser().getUserId().equals(userId)) {
                String response = "Ticket Id:"+ticket.getTicketId()+", Movie:"+ticket.getMovieName()+
                        ", Show Date:"+ticket.getShowDate()+", Show Time:"+ticket.getShowTime()+
                        ", Theater:"+ticket.getTheaterName()+", Total Amount:"+ticket.getTotalAmount();
                bookingHistory.add(response);
            }
        }
        return bookingHistory;
    }

    public String addUser(AddUserRequest userRequest){

        User user = User.builder().age(userRequest.getAge())
                .emailId(userRequest.getEmailId())
                .name(userRequest.getName())
                .mobileNo(userRequest.getMobileNo())
                .build();


        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(userRequest.getEmailId());
        mailMessage.setFrom("masterchief000e@gmail.com");
        mailMessage.setSubject("Welcome to Book My Show Application !!");

        String body = "Hi "+userRequest.getName()+" !" +
                "Welcome to Book My Show Application, Enjoy WELCOME10 to get 10% off on tickets";
        mailMessage.setText(body);

        javaMailSender.send(mailMessage);

        user = userRepository.save(user);
        return "User has been saved to the DB with userId "+user.getUserId();
    }
}
