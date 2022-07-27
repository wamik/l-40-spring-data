package com.example.l40springdatalive.dao.books;

import com.example.l40springdatalive.dao.authors.AuthorEntity;
import com.example.l40springdatalive.web.books.BookDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @Column(name = "year_of_issue")
    private LocalDate yearOfIssue;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public BookDTO toDto() {
        return new BookDTO(
                this.id,
                this.title,
                this.author.getFirstName() + " " + this.getAuthor().getLastName(),
                this.yearOfIssue
        );

    }
}
