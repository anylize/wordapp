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

    //如果需要，代表原密码
    private String passwordY;
    private String password;
    private String repassword;

    private String userPic;

    private String token;

}
