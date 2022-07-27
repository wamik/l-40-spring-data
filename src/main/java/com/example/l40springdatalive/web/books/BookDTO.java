package com.example.l40springdatalive.web.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDTO {

    private Long id;

    private String title;

    private String authorName;

    private LocalDate yearOfIssue;

}
