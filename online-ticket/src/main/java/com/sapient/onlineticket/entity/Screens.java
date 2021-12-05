package com.sapient.onlineticket.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ScreenPrimaryKey.class)
public class Screens {

   @Id
   private String theaterId;
   @Id
   private String movieId;
   @Id
   private String screenStartsAt;
   @Id
   @JsonFormat(pattern = "yyyy-MM-dd")
   private Date screenDate;
   private String endsAt;

}
