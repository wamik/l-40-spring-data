package com.example.l40springdatalive.web.authors;


import com.example.l40springdatalive.service.authors.AuthorService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/authors")
public class AuthorsController {


    private AuthorService authorService;

    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorDTO> findAll(Pageable pageable) {
        return authorService.findAll(pageable);
    }


    @GetMapping("/{id}")
    public AuthorDTO findOne(@PathVariable Long id) throws Throwable {
        return authorService.findOne(id);
    }

    @PostMapping
    public AuthorDTO save(@RequestBody SaveAuthorDTO saveAuthor) {
        return authorService.save(saveAuthor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }


}
