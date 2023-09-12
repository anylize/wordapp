package com.sangeng.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordUser_enroll {
    private Integer id;
    private String username;
    private String password;
    private String repassword;
}
