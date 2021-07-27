package com.example.test.beans;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserValueBean {
    private int id;
    private String username;
    private String lastname;
}
