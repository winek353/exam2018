package uj.jwzp.exam2018.controllers;

import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uj.jwzp.exam2018.model.Author;
import uj.jwzp.exam2018.services.AuthorService;

@Controller
public class AuthorController {
    @Autowired
    AuthorService authorService;

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
//        System.out.println("tutaj " + author);
    }

    @RequestMapping(value="/authors/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?>  deleteAuthor(@PathVariable("id") int id) {
        Author author = authorService.getAuthor(id);

        if(author == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else
            return new ResponseEntity<>(author, HttpStatus.OK);
    }
}
