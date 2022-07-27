package com.example.l40springdatalive.dao.books;

import com.example.l40springdatalive.dao.authors.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface BooksRepository extends JpaRepository<BookEntity, Long> {

    Stream<BookEntity> findAllByAuthorId(Long id);

}
