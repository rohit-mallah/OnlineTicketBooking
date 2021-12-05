package com.sapient.onlineticket.ResponseMessage;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ErrorResponse implements Serializable {

    private HttpStatus statusCode;
    private String errorMessage;

}
