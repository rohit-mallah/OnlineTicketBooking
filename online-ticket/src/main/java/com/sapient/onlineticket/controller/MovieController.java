package com.sapient.onlineticket.controller;

import com.sapient.onlineticket.entity.Movie;
import com.sapient.onlineticket.repository.MovieRepository;
import com.sapient.onlineticket.services.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieServiceImpl movieService;

    /***
     * Method description - this method is to register the movie to the database.
     *
     * @param movie
     * @return
     */
    @PostMapping("/movie")
    public Movie registerMovie(@RequestBody Movie movie){
        return movieService.registerMovie(movie);
    }

    @GetMapping("/movie")
    public List<Movie> fetchAllMovies()
    {
        return movieService.fetchAllMovies();
    }
}
