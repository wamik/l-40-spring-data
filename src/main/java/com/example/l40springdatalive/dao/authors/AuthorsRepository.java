package com.example.l40springdatalive.dao.authors;

import com.example.l40springdatalive.dao.books.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorsRepository extends JpaRepository<AuthorEntity, Long> {



    Page<AuthorEntity> findByFirstName(String firstName, Pageable pageable);

    Slice<AuthorEntity> findByFirstName1(String firstName, Pageable pageable);

    List<AuthorEntity> findByLastname(String lastname, Sort sort);

    List<AuthorEntity> findByLastname(String lastname, Pageable pageable);


}
