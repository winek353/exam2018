package uj.jwzp.exam2018.controllers;

import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uj.jwzp.exam2018.model.Author;
import uj.jwzp.exam2018.model.Book;
import uj.jwzp.exam2018.services.AuthorService;
import uj.jwzp.exam2018.services.BookService;

@Controller
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;

    @RequestMapping("/authors")
    public @ResponseBody Author [] getAuthors() {
        return authorService.getAuthors();
    }

    @RequestMapping("/authors/{id}")
    public ResponseEntity<?>  getAuthor(@PathVariable("id") int id) {
        Author author = authorService.getAuthor(id);
        if(author == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else
            return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @RequestMapping(value="/authors", method = RequestMethod.POST)
    public  ResponseEntity<?> addAuthor(@RequestBody Author author){
        author = authorService.addAuthor(author);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @RequestMapping(value="/authors/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?>  deleteAuthor(@PathVariable("id") int id) {
        Author author = authorService.getAuthor(id);

        if(authorService.deleteAuthor(author))
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @RequestMapping(value="/authors/{authorId}/books", method = RequestMethod.POST)//do dokończenia
    public ResponseEntity<?>  addBook(@PathVariable("authorId") int id,
                                      @RequestBody Book book) {
        Author author = authorService.getAuthor(id);
        book = bookService.addBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
}
