package com.sapient.onlineticket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatPk implements Serializable {

    private Long movieId;
    private Long theaterId;
    private Long showId;
    private int seatNumber;
    private String rowNo;
}
