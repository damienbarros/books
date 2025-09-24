package com.example.bookshop.service;

import com.example.bookshop.domain.Book;
import com.example.bookshop.repo.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> list() {
        return repo.findAll();
    }

    public Book get(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Book create(Book b) {
        b.setId(null);
        return repo.save(b);
    }

    public Book update(Long id, Book b) {
        b.setId(id);
        return repo.save(b);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Book> searchTitle(String q) {
        return repo.findByTitleContainingIgnoreCase(q);
    }

    public List<Book> searchAuthor(String q) {
        return repo.findByAuthorContainingIgnoreCase(q);
    }
}
