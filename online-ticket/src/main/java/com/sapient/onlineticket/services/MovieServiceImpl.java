package com.sapient.onlineticket.services;

import com.sapient.onlineticket.entity.Movie;
import com.sapient.onlineticket.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl {

    @Autowired
    private MovieRepository movieRepository;

    public Movie registerMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> fetchAllMovies() {
        return movieRepository.findAll();
    }
}
