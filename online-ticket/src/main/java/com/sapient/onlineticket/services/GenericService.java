package com.sapient.onlineticket.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.onlineticket.ResponseMessage.ErrorResponse;
import com.sapient.onlineticket.Util.OnlineBookingException;
import com.sapient.onlineticket.entity.*;
import com.sapient.onlineticket.repository.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class GenericService {

    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private SeatsRepository seatsRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;

    public JSONObject getAllShowsByCity(String city, Long movieId )
            throws JsonProcessingException {

        List<Screens> screens = screenRepository.fetchTheaterByCityandMovie(city,movieId);
        JSONObject result = new JSONObject();
        JSONArray screensArray = new JSONArray();
        for (Screens screen : screens) {
            ObjectMapper mapper = new ObjectMapper();
            String screenJsonObj = mapper.writeValueAsString(screen);
            JSONObject obj = new JSONObject(screenJsonObj);
            screensArray.put(obj);
        }
        result.put(String.valueOf(movieId), screensArray);
        return result;
    }

    public JSONObject getAllShowsByCityMovieAndDate(String city, Long movieId, Date date )
            throws JsonProcessingException {

        String movieDetails = new String();
        Optional<Movie> movie =  this.movieRepository.findById(movieId);
        if(movie.isPresent())
        {
            movieDetails = movie.get().getMovieName() + " (" + movie.get().getLanguage() + ")";
        }else
        {
            throw new OnlineBookingException("Movie is not Present");
        }

        List<Shows> shows = showRepository.fetchTheaterByCityMovieAndDate(city,movieId,date);
        JSONObject result = new JSONObject();
        JSONArray showsArrays = new JSONArray();
        for (Shows shows1 : shows) {
            ObjectMapper mapper = new ObjectMapper();
            String screenJsonObj = mapper.writeValueAsString(shows1);
            JSONObject obj = new JSONObject(screenJsonObj);
            showsArrays.put(obj);
        }
        result.put(movieDetails, showsArrays);
        return result;
    }

    public JSONObject getAllShowsByCityMovieNameAndDate(String city, String movieName, Date date )
            throws JsonProcessingException {

        String movieDetails = new String();
        Optional<Movie> movie =  this.movieRepository.findByMovieName(movieName);
        Long movieId;
        if(movie.isPresent())
        {
            movieDetails = movie.get().getMovieName() + " (" + movie.get().getLanguage() + ")";
            movieId = movie.get().getMovieId();
        }else
        {
            throw new OnlineBookingException("Movie is not Present");
        }

        List<Shows> shows = showRepository.fetchTheaterByCityMovieAndDate(city,movieId,date);
        JSONObject result = new JSONObject();
        JSONArray showsArrays = new JSONArray();
        for (Shows shows1 : shows) {
            ObjectMapper mapper = new ObjectMapper();
            String screenJsonObj = mapper.writeValueAsString(shows1);
            JSONObject obj = new JSONObject(screenJsonObj);
            showsArrays.put(obj);
        }
        result.put(movieDetails, showsArrays);
        return result;
    }

    public JSONObject bookSeats(List<Seats> seats, String mobileNumber )
            throws JsonProcessingException {

        StringBuilder sb = new StringBuilder();

        for (Seats toBookseat : seats) {
            SeatPk seatPk = SeatPk.builder().movieId(toBookseat.getMovieId())
                    .theaterId(toBookseat.getTheaterId())
                    .showId(toBookseat.getShowId())
                    .seatNumber(toBookseat.getSeatNumber())
                    .rowNo(toBookseat.getRowNo())
                    .build();
            Optional<Seats> seatFromDB = this.seatsRepository.findById(seatPk);

            if (seatFromDB.isPresent()) {
                Seats seats1 = seatFromDB.get();
                if (seats1.isBooked()) {
                    throw new OnlineBookingException("This seat is already booked --" + seats1.getRowNo() + seats1.getSeatNumber());
                } else {
                    seats1.setBooked(true);
                    this.seatsRepository.save(seats1);
                    sb.append(seats1.getRowNo() + seats1.getSeatNumber() + ",");
                }
            }else
            {
                throw new OnlineBookingException("This seat is not available");
            }
        }

        String seatNumbers = sb.toString();
        seatNumbers = (seatNumbers.endsWith(",")) ? seatNumbers.substring(0, seatNumbers.length() - 2)
                : seatNumbers;

        Seats seat = seats.get(0);
        Booking booking = Booking.builder().createdOn(LocalDateTime.now())
                .movieId(seat.getMovieId())
                .seatNumbers(seatNumbers)
                .theaterId(seat.getTheaterId())
                .showId(seat.getShowId())
                .userId(mobileNumber).build();

        this.bookingRepository.save(booking);

        JSONObject result = new JSONObject();
            JSONObject userDetails = new JSONObject();
            User user = this.userRepository.findById(mobileNumber).get();
            userDetails.put("bookedBy", user.getFirstName() + " " + user.getLastName());
            result.put("userDtails", userDetails);

            JSONObject movieDetails = new JSONObject();
            Optional<Movie> movie = this.movieRepository.findById(booking.getMovieId());
            Movie movieFromDb = movie.get();
            movieDetails.put("movieName", movieFromDb.getMovieName());
            result.put("movieDetails", movieDetails);

            JSONObject theaterDetails = new JSONObject();
            Optional<TheaterPartner> theater = this.theaterRepository.findById(booking.getTheaterId());
            TheaterPartner theaterFromDb = theater.get();
            theaterDetails.put("theaterName", theaterFromDb.getName());
            theaterDetails.put("theaterAddress", theaterFromDb.getAddress());
            result.put("theaterDetails", theaterDetails);

            JSONObject showDetails = new JSONObject();
            Optional<Shows> showFromDb = this.showRepository.findById(booking.getShowId());
            Shows show = showFromDb.get();
            showDetails.put("show starts at ", show.getShowDate() + " : " + show.getShowStartsAt());
            result.put("showDetails", showDetails);

            JSONObject ticketDetails = new JSONObject();
            ticketDetails.put("ticketNumbers", booking.getSeatNumbers());
            ticketDetails.put("totalPrice", booking.getTotalPrice());
            result.put("ticketDetails", ticketDetails);

            return result;

    }


}
