package com.example.l40springdatalive.service.authors;


import com.example.l40springdatalive.dao.authors.AuthorEntity;
import com.example.l40springdatalive.dao.authors.AuthorsRepository;
import com.example.l40springdatalive.errors.ResourceNotFoundException;
import com.example.l40springdatalive.web.authors.AuthorDTO;
import com.example.l40springdatalive.web.authors.SaveAuthorDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

public interface AuthorService {

    List<AuthorDTO> findAll(Pageable pageable);

    AuthorDTO findOne(Long id) throws Throwable;

    AuthorDTO save(SaveAuthorDTO saveAuthor);

    void delete(Long id);
}

@Service
class AuthorServiceImpl implements AuthorService {

    private AuthorsRepository authorRepository;

    public AuthorServiceImpl(AuthorsRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDTO> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable)
                .stream()
                .map(AuthorEntity::toDto)
                .toList();
    }

    @Override
    public AuthorDTO findOne(Long id) throws Throwable {
        return authorRepository.findById(id)
                .map(AuthorEntity::toDto)
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundException("Author with id %s not found".formatted(id)));
    }

    @Override
    public AuthorDTO save(SaveAuthorDTO saveAuthor) {
        AuthorEntity saved = authorRepository.save(
                new AuthorEntity(
                        saveAuthor.getId(),
                        saveAuthor.getFirstName(),
                        saveAuthor.getLastName(),
                        saveAuthor.getDateOfBirth(),
                        null,
                        null
                )
        );
        return saved.toDto();
    }

    @Override
    public void delete(Long id) {
        // check if books exists
        authorRepository.deleteById(id);
    }
}


