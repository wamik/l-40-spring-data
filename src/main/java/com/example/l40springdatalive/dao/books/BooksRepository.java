package com.example.l40springdatalive.dao.books;

import com.example.l40springdatalive.dao.authors.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.time.Year;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface BooksRepository extends JpaRepository<BookEntity, Long> {

    Stream<BookEntity> findAllByAuthorId(Long id);


//    @Query(value = """
//                       SELECT a FROM BookEntity a
//                       WHERE (:title IS NULL OR LOWER(a.title) LIKE CONCAT('%', LOWER(TRIM(:title)), '%'))
//                       AND (DATE(:year) IS NULL OR a.yearOfIssue = :year)
//                       AND (:authorFirstName IS NULL OR LOWER(a.author.firstName) LIKE CONCAT('%', LOWER(TRIM(:authorFirstName)), '%'))
//                       AND (:authorLastName IS NULL OR LOWER(a.author.lastName) LIKE CONCAT('%', LOWER(TRIM(:authorLastName)), '%'))
//            """)

    @Query(value = """
                       SELECT a FROM BookEntity a
                       WHERE :strings IS EMPTY OR (a.author.firstName IN :strings OR a.author.lastName IN :strings)
            """)
    Page<BookEntity> findAllByFilter(
            Collection<String> strings); // John, Ronald, Rowen, Tolkien

// John Ronald Rowen    fisrtName
// Tolkien              lastName


}
