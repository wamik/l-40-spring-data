package com.example.l40springdatalive.web.books;

import lombok.Data;

import java.time.Year;


@Data
public class BookFilterDTO {

    private  String title;

    private Year year;

    private String authorFirstName;

    private String authorLastName;
}
