package com.sangeng.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelfTest {

    private int id;

    private String question;

    private String trueAnswer;

    public List<String> answer;
}
