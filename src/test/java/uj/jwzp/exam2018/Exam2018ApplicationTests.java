package uj.jwzp.exam2018;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uj.jwzp.exam2018.TestUtil.jsonContent;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Exam2018ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

    @Test
    public void checkGreetingMessage() throws Exception {
        mockMvc
            .perform(get("/greeting"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Hello World")))
            .andDo(print());
    }

    //Test do zaliczenia na 3.0
    @Test
    public void checkOneAuthorFlow() throws Exception {
        checkAuthors("empty-array");
        checkAuthor404(1);
        addAuthor("author-1");
        checkAuthors("author-1-array");
        checkAuthorById(1);
        deleteAuthor(1);
        checkAuthors("empty-array");
        checkAuthor404(1);
    }

    //Test do zaliczenia na 4
    @Test
    @Ignore
    public void checkOneAuthorOneBookFlow() throws Exception {
	    checkAuthors("empty-array");
	    checkAuthor404(1);
	    addAuthor("author-1");
	    checkAuthors("author-1-array");
	    checkAuthorById(1);

	    checkBooks("empty-array");
        checkBook404(1);
//	    addBook(1, "book-1");
//	    checkBooks("book-1-array");
//	    checkBookById(1);
//	    checkBooksByAuthor(1, "book-1-array");
//	    deleteBook(1);
//        checkBooks("empty-array");
//        checkBook404(1);
//        checkBooksByAuthor(1, "empty-array");
//        deleteAuthor(1);
//        checkAuthors("empty-array");
//        checkAuthor404(1);
    }

    //Test do zaliczenia na 5
    @Test
    @Ignore
    public void checkBeforePersistence() throws Exception {
        checkAuthors("empty-array");
        checkAuthor404(1);
        addAuthor("author-1");
        checkAuthors("author-1-array");
        checkAuthorById(1);
        checkBooks("empty-array");
        checkBook404(1);
        addBook(1, "book-1");
        checkBooks("book-1-array");
        checkBookById(1);
        checkBooksByAuthor(1, "book-1-array");
    }

    //Test do zaliczenia na 4 i więcej
    @Test
    @Ignore
    public void checkAfterPersistence() throws Exception {
        checkAuthorById(1);
        checkBooks("book-1-array");
        checkBookById(1);
        checkBooksByAuthor(1, "book-1-array");
    }

    private void checkAuthors(String fileName) throws Exception {
        mockMvc
            .perform(get("/authors"))
            .andExpect(status().isOk())
            .andExpect(content().json(jsonContent(fileName)))
            .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"))
            .andDo(print());
    }

    private void checkBooks(String fileName) throws Exception {
        mockMvc
            .perform(get("/books"))
            .andExpect(status().isOk())
            .andExpect(content().json(jsonContent(fileName)))
            .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"))
            .andDo(print());
    }

    private void addAuthor(String fileName) throws Exception {
        mockMvc
            .perform(post("/authors").content(jsonContent(fileName)).contentType("application/json"))
            .andExpect(status().isCreated())
            .andExpect(content().json(jsonContent(fileName + "-with-id")))
            .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"))
            .andDo(print());
    }

    private void checkAuthorById(int id) throws Exception {
        mockMvc
            .perform(get("/authors/" + id))
            .andExpect(status().isOk())
            .andExpect(content().json(jsonContent("author-" + id + "-with-id")))
            .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"))
            .andDo(print());
    }

    private void addBook(int authorId, String fileName) throws Exception {
        mockMvc
            .perform(post("/authors/ " + authorId + "/books").content(jsonContent(fileName)).contentType("application/json"))
            .andExpect(status().isCreated())
            .andExpect(content().json(jsonContent(fileName + "-with-id")))
            .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"))
            .andDo(print());
    }

    private void checkBookById(int id) throws Exception {
        mockMvc
            .perform(get("/books/" + id))
            .andExpect(status().isOk())
            .andExpect(content().json(jsonContent("book-" + id + "-with-id")))
            .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"))
            .andDo(print());
    }

    private void checkBooksByAuthor(int id, String fileName) throws Exception {
        mockMvc
            .perform(get("/authors/" + id + "/books"))
            .andExpect(status().isOk())
            .andExpect(content().json(jsonContent(fileName)))
            .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"))
            .andDo(print());
    }

    private void deleteBook(int id) throws Exception {
        mockMvc
            .perform(delete("/books/" + id))
            .andExpect(status().isOk())
            .andDo(print());
    }

    private void deleteAuthor(int id) throws Exception {
        mockMvc
            .perform(delete("/authors/" + id))
            .andExpect(status().isOk())
            .andDo(print());
    }

    private void checkAuthor404(int id) throws Exception {
        mockMvc
            .perform(get("/authors/" + id))
            .andExpect(status().isNotFound())
            .andDo(print());
    }

    private void checkBook404(int id) throws Exception {
        mockMvc
            .perform(get("/books/" + id))
            .andExpect(status().isNotFound())
            .andDo(print());
    }
}
