package com.example.l40springdatalive.service.books;


import com.example.l40springdatalive.dao.authors.AuthorEntity;
import com.example.l40springdatalive.dao.authors.AuthorsRepository;
import com.example.l40springdatalive.dao.books.BookEntity;
import com.example.l40springdatalive.dao.books.BooksRepository;
import com.example.l40springdatalive.errors.ResourceNotFoundException;
import com.example.l40springdatalive.web.books.BookDTO;
import com.example.l40springdatalive.web.books.BookFilterDTO;
import com.example.l40springdatalive.web.books.SaveBookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public interface BookService {

    List<BookDTO> findAll(Pageable pageable);

    Page<BookDTO> findAllByFilter(BookFilterDTO filter, Pageable pageable);

    BookDTO findOne(Long id) throws Throwable;

    BookDTO save(SaveBookDTO saveAuthor) throws Throwable;

    void delete(Long id);

    List<BookDTO> findByAuthor(Long authorId);

}

@Service
class BookServiceImpl implements BookService {

    private BooksRepository bookRepository;

    private AuthorsRepository authorRepository;

    public BookServiceImpl(BooksRepository bookRepository, AuthorsRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookDTO> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .stream()
                .map(BookEntity::toDto)
                .toList();
    }

    @Override
    public Page<BookDTO> findAllByFilter(BookFilterDTO filter, Pageable pageable) {

        LocalDate yearDate = null;

        if (filter.getYear() != null)
            yearDate = LocalDate.of(filter.getYear().getValue(), 1, 1);

        Page<BookEntity> foundBooks = bookRepository.findAllByFilter(
                filter.getTitle(),
                yearDate,
                filter.getAuthorFirstName(),
                filter.getAuthorLastName(),
                pageable);

        return foundBooks
                .map(BookEntity::toDto);
    }

    @Override
    public BookDTO findOne(Long id) throws Throwable {
        return bookRepository.findById(id)
                .map(BookEntity::toDto)
                .orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("Book with id %s not found".formatted(id)));
    }

    @Override
    @Transactional
    public BookDTO save(SaveBookDTO saveAuthor) throws Throwable {
        AuthorEntity authorEntity = authorRepository.findById(saveAuthor.getAuthorId())
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundException("Cannot persist book. Author with id %s does not exist".formatted(saveAuthor.getAuthorId())));

        BookEntity saved = bookRepository.save(
                new BookEntity(
                        saveAuthor.getId(),
                        saveAuthor.getTitle(),
                        authorEntity,
                        saveAuthor.getDateOfIssue(),
                        null
                ));

        // update

        // update


        return saved.toDto();

    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> findByAuthor(Long authorId) {
//        ArrayList<BookDTO> list = new ArrayList<>();
//
//        authorRepository.findById(authorId)
//                .ifPresent(
//                        author -> list
//                                .addAll(
//                                        bookRepository.findAllByAuthor(author).map(BookEntity::toDto).toList()
//                                )
//                );
//
//        return list;


        return bookRepository.findAllByAuthorId(authorId).map(BookEntity::toDto).toList();
    }
}
