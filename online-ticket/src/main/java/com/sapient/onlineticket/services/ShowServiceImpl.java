package com.sapient.onlineticket.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.onlineticket.Util.OnlineBookingException;
import com.sapient.onlineticket.entity.Movie;
import com.sapient.onlineticket.entity.Shows;
import com.sapient.onlineticket.repository.MovieRepository;
import com.sapient.onlineticket.repository.ShowRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShowServiceImpl {

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;

    public Shows saveShows(Shows show)
    {
        return this.showRepository.save(show);
    }

    public String deleteShow(Long showId)
    {
        Optional<Shows> show = this.showRepository.findById(showId);
                if(show.isPresent())
                {
                    showRepository.delete(show.get());
                }else
                {
                    throw new OnlineBookingException("Show is not Present");
                }
        return "Show Deleted Successfully";
    }

    public JSONObject getAllShowsByMovieAndDate(Long movieId, Date date) throws JsonProcessingException {

        String movieDetails = new String();
        Optional<Movie> movie =  this.movieRepository.findById(movieId);
        if(movie.isPresent())
        {
            movieDetails = movie.get().getMovieName() + " (" + movie.get().getLanguage() + ")";
        }else
        {
            throw new OnlineBookingException("Movie is not Present");
        }

        List<Shows> shows = showRepository.fetchTheaterByMovieAndDate(movieId,date);
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

    public Shows updateShow(Shows show) {

        Optional<Shows> showFromDB = this.showRepository.findById(show.getShowId());
        if(!showFromDB.isPresent())
        {
           throw new OnlineBookingException("Show is not available in database.");
        }
            Shows showsDB = showFromDB.get();
            showsDB.setShowDate(show.getShowDate());
            showsDB.setShowStartsAt(show.getShowStartsAt());
            showsDB.setMovieId(show.getMovieId());
            showsDB.setEndsAt(show.getEndsAt());

            return this.showRepository.save(showsDB);

    }

    public Shows getShow(Long showId) {
        Optional<Shows> showFromDB = this.showRepository.findById(showId);
        if(!showFromDB.isPresent())
        {
            throw new OnlineBookingException("Show is not present in DB");
        }
        return showFromDB.get();
    }
}
