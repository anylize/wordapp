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
    private String password;

    public String nickname;
}
