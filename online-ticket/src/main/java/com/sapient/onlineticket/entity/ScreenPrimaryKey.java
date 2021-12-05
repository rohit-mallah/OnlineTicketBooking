package com.sapient.onlineticket.entity;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreenPrimaryKey implements Serializable {

    private String theaterId;
    private String movieId;
    private String screenStartsAt;
    private Date screenDate;

}
