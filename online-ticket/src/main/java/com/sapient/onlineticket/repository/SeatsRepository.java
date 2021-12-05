package com.sapient.onlineticket.repository;

import com.sapient.onlineticket.entity.SeatPk;
import com.sapient.onlineticket.entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatsRepository extends JpaRepository<Seats, SeatPk> {
}
