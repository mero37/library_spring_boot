package spring.boot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.dao.entity.Author;
import spring.boot.dao.entity.Book;
import spring.boot.service.AuthorService;
import spring.boot.service.BookService;
import spring.boot.service.model.BookContext;


@RestController
@RequestMapping("/myLibrary")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public Book findByBookId(@PathVariable("id") Integer id) {
		
		return bookService.findByBookId(id);
	}
	
	@RequestMapping(value = "/bookName/{name}", method = RequestMethod.GET)
	public List<Book> findByBookName(@PathVariable("name") String name) {
		
		return bookService.findByBookName(name);
	}
	
	@RequestMapping(value = "/book/list", method = RequestMethod.GET)
	public List<Book> getAllBookList() {
		
		return bookService.getAllBookList();
	}
	
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public int save(@RequestBody BookContext bookContext) {
		
		return bookService.save(bookContext);
		
	}
	

}
