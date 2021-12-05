package com.sapient.onlineticket.repository;

import com.sapient.onlineticket.entity.Screens;
import com.sapient.onlineticket.entity.Shows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Shows, Long> {

    @Query(value = "select * from shows S join theater_partner T on T.theater_id = S.theater_id and T.city = :city and S.movie_id = :movieId and DATE(S.show_date) = :date ",  nativeQuery = true)
    public List<Shows> fetchTheaterByCityMovieAndDate(@Param("city") String city, @Param("movieId") Long movieId, @Param("date") Date date);

    @Query(value = "select * from shows S S.movie_id = :movieId and DATE(S.show_date) = :date ",  nativeQuery = true)
    public List<Shows> fetchTheaterByMovieAndDate(@Param("movieId") Long movieId, @Param("date") Date date);

}
