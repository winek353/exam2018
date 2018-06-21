package uj.jwzp.exam2018.services;

import org.springframework.stereotype.Service;
import uj.jwzp.exam2018.model.Author;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    private List<Author> authors = new ArrayList<>();
    static int idHolder= 1;

    public Author[] getAuthors(){
            return authors.toArray(new Author[0]);
    }

    public Author getAuthor(int id){
        for(Author author: authors){
            if(author.getId() == id)
                return author;
        }
        return null;
    }

    public Author addAuthor(Author author){ //do poprawy id
        if(author.getId() == 0){
            author.setId(idHolder++);
        }
        authors.add(author);
        return author;
    }
    public boolean deleteAuthor(Author author){
        return authors.remove(author);
    }

//    public boolean isIdcorrect(int id){
//
//    }
}
