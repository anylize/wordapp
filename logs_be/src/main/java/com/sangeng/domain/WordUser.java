package com.sangeng.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordUser {

    private Integer id;
    private String username;

    //如果需要，代表修改后密码
    private String password;

    public String nickname;
    public String email;

    private int count;
    public String state;

    private String token;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setState(String state) {
        this.state = state;
    }


}
