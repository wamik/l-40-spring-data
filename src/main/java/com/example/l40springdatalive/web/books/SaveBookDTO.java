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
public class SaveBookDTO {

    private Long id;

    private String title;

    private Long authorId;

    private LocalDate dateOfIssue;

}
