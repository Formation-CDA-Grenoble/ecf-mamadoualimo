package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.example.api.entity.Books;
import com.example.api.repository.BooksRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private BooksRepository booksRepository;

    @GetMapping("")
    public List<Books> getAllArticles() {
        return booksRepository.findAll();
    }

    @GetMapping("/{id}")
    public Books getArticle(@PathVariable(value = "id") long id) {
        return booksRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Books does not exist")
        );
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Books createBooks(@Valid @RequestBody Books books) {
        if (books.getTitle() == null) {
            books.setTitle(new Date());
        }
        return booksRepository.save(books);
    }

    @PutMapping("/{id}")
    public Books updateArticle(
        @Valid @RequestBody Books newBooks,
        @PathVariable(value = "id") long id
    ) {
        Books books = booksRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Books does not exist")
        );
        if (newBooks.getId() != 0 && newBooks.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Books id does not match requested resource id");
        }
        books.setTitle(newBooks.getTitle());
        books.setContent(newBooks.getContent());
        books.setLikes(newBooks.getLikes());
        books.setChapitre(newBooks.getChapitre());
        return booksRepository.save(books);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable(value = "id") long id) {
        booksRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Books does not exist")
        );
        booksRepository.deleteById(id);
    }
}
