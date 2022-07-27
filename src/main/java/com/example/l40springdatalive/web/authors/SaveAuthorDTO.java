package com.example.l40springdatalive.web.authors;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SaveAuthorDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;
}
