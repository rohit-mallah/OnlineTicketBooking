package com.sapient.onlineticket.repository;

import com.sapient.onlineticket.entity.ScreenPrimaryKey;
import com.sapient.onlineticket.entity.Screens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScreenRepository extends JpaRepository<Screens, ScreenPrimaryKey> {

    @Query(value = "select * from screens S join theater_partner T on T.theater_id = S.theater_id and T.city = :city and S.movie_id = :movieId",  nativeQuery = true)
    public List<Screens> fetchTheaterByCityandMovie(@Param("city") String city, @Param("movieId") Long movieId);

    @Query(value = "select * from screens S join theater_partner T on T.theater_id = S.theater_id and T.city = :city and S.movie_id = :movieId and DATE(S.screen_Date) = :date ",  nativeQuery = true)
    public List<Screens> fetchTheaterByCityMovieAndDate(@Param("city") String city, @Param("movieId") Long movieId, @Param("date") Date date);

}
