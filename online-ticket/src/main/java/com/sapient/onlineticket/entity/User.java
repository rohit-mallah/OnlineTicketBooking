package com.sapient.onlineticket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String mobileNumber;
    @Column(name="email_id", nullable = false)
    @Email
    private String emailId;
    private String firstName;
    private String lastName;

}
