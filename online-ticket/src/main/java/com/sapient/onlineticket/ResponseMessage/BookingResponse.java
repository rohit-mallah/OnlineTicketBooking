package com.sapient.onlineticket.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingResponse {

    private String bookedBy;
    private String movieName;
    private String theaterName;
    private String screenName;
    private String showName;
    private String date;
}
