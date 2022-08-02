package com.example.l40springdatalive.web.books;

import com.example.l40springdatalive.service.books.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> findAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }


   @GetMapping("/filter")
    public Page<BookDTO> findAll(@RequestBody BookFilterDTO filter, Pageable pageable) {
        return bookService.findAllByFilter(filter, pageable);
    }

    @GetMapping("/search")
    public List<BookDTO> findByParameters(@RequestParam("author_id") Long authorId ){
        return bookService.findByAuthor(authorId);
    }


    @GetMapping("/{id}")
    public BookDTO findOne(@PathVariable Long id) throws Throwable {
        return bookService.findOne(id);
    }

    @PostMapping
    public BookDTO save(@RequestBody SaveBookDTO saveAuthor) throws Throwable {
        return bookService.save(saveAuthor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

}
