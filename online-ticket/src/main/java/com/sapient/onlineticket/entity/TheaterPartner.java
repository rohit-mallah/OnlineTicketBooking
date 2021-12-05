package com.sapient.onlineticket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterPartner {

    @Id
    @SequenceGenerator(
            name = "theater_sequence",
            sequenceName = "theater_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "theater_sequence"
    )
    private Long theaterId;
    @NotBlank(message = "Address can not be blank.")
    private String address;
    @NotBlank(message = "City can not be blank.")
    private String city;
    private String state;
    private String country;
    private String name;


}
