package com.sapient.onlineticket.repository;

import com.sapient.onlineticket.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    public Optional<Movie> findByMovieName(String name);

}
