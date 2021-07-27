package com.example.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "users")
@Entity
@Data
@ToString
@NoArgsConstructor
public class UserEntity {

    @Id
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

}
