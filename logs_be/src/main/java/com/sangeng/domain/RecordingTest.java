package com.sangeng.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordingTest {

    private int id;

    private String date;

    private String book;

    private int number;

    private int scores;
}
