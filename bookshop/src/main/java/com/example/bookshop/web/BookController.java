package com.example.bookshop.web;

import com.example.bookshop.domain.Book;
import com.example.bookshop.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
  private final BookService service;
  public BookController(BookService service) { this.service = service; }

  @GetMapping
  public List<Book> list() { return service.list(); }

  @GetMapping("/{id}")
  public Book get(@PathVariable Long id) { return service.get(id); }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Book create(@Valid @RequestBody Book b) { return service.create(b); }

  @PutMapping("/{id}")
  public Book update(@PathVariable Long id, @Valid @RequestBody Book b) { return service.update(id, b); }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) { service.delete(id); }

  @GetMapping("/search")
  public List<Book> search(@RequestParam(required = false) String title,
                           @RequestParam(required = false) String author) {
    if (title != null) return service.searchTitle(title);
    if (author != null) return service.searchAuthor(author);
    return service.list();
  }
}
