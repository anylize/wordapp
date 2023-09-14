package com.sangeng.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Word {

    private int id;
    private String word;
    private String meaning;

    private int count;
    public int state;

    //用与开始时提取count和state
    private int count1;
    public int state1;
    private int count2;
    public int state2;
    private int count3;
    public int state3;



}
