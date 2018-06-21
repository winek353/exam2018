package uj.jwzp.exam2018.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uj.jwzp.exam2018.model.Author;
import uj.jwzp.exam2018.model.Book;
import uj.jwzp.exam2018.services.BookService;

@Controller
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/books")
    public @ResponseBody
    Book[] getBooks() {
        return bookService.getBooks();
    }

    @RequestMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable("id") int id) {
        Book book = bookService.getBook(id);
        if(book == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else
            return new ResponseEntity<>(book, HttpStatus.OK);
    }

}
