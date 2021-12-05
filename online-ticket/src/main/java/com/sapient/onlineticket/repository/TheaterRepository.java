package com.sapient.onlineticket.repository;

import com.sapient.onlineticket.ResponseMessage.BookingResponse;
import com.sapient.onlineticket.entity.Screens;
import com.sapient.onlineticket.entity.TheaterPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<TheaterPartner,Long> {

    @Query(value = "select S.* from screens S join theater_partner T on T.theater_id = S.theater_id and T.city = :city and S.movie_id = :movieId",  nativeQuery = true)
    public List<Screens> fetchTheaterByCityandMovie(@Param("city") String city, @Param("movieId") Long movieId);

}
