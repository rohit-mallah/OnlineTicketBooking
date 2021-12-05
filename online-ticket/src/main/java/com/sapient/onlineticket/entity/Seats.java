package com.sapient.onlineticket.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sapient.onlineticket.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SeatPk.class)
public class Seats {

    @Id
    private Long movieId;
    @Id
    private Long theaterId;
    @Id
    private Long showId;
    @Id
    private String rowNo;
    @Id
    private int seatNumber;

    private boolean booked;
    private SeatType seatType;
    private int price;

}
