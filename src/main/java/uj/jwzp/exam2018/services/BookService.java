package uj.jwzp.exam2018.services;

import org.springframework.stereotype.Service;
import uj.jwzp.exam2018.model.Author;
import uj.jwzp.exam2018.model.Book;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();
    private static int idHolder= 1;

    public Book[] getBooks(){
        return books.toArray(new Book[0]);
    }

    public Book getBook(int id){
        for(Book book: books){
            if(book.getId() == id)
                return book;
        }
        return null;
    }

    public Book addBook(Book book){ //do poprawy id
        if(book.getId() == 0){
            book.setId(idHolder++);
        }
        books.add(book);
        return book;
    }
}
