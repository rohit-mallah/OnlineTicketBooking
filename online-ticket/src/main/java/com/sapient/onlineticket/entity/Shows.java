package com.sapient.onlineticket.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "shows",
uniqueConstraints = @UniqueConstraint(columnNames = {"theater_id", "movie_id", "show_starts_at","show_date"})
)
public class Shows {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long showId;

    @Column(name = "theater_id")
    @NotBlank(message= "theaterId shouldn't be blank")
    private String theaterId;
    @Column(name ="movie_id")
    @NotBlank(message = "movieId shouldn't be blank")
    private String movieId;
    @Column(name = "show_starts_at")
    private String showStartsAt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "show_date", nullable = false)
    @FutureOrPresent(message = "Show Dates cant be past date.")
    private Date showDate;
    private String endsAt;

}
