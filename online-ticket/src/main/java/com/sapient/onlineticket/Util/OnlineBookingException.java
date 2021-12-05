package com.sapient.onlineticket.Util;

public class OnlineBookingException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public OnlineBookingException(String errorMsg) {
        super(errorMsg);
    }
}
