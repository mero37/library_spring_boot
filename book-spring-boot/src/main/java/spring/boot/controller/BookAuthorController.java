package spring.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.dao.entity.Author;
import spring.boot.dao.entity.Book;
import spring.boot.dao.entity.BookAuthor;
import spring.boot.service.AuthorService;
import spring.boot.service.BookAuthorService;
import spring.boot.service.model.BookContext;

@RestController
@RequestMapping("/myLibrary")
public class BookAuthorController {

	@Autowired
	private BookAuthorService bookAuthorService;
	
	@RequestMapping(value = "/bookauthor/list", method = RequestMethod.GET)
	public List<BookAuthor> getAllAuthorList() {
		
		return bookAuthorService.getAllBookAuthorList();
	}
	
	@RequestMapping(value = "/bookAuthor/{id}", method = RequestMethod.GET)
	public BookAuthor findByBookId(@PathVariable("id") Integer id) {
		
		return bookAuthorService.findByBookAuthorId(id);
	}
	
	@RequestMapping(value = "/bookAuthor", method = RequestMethod.POST)
	public int save(@RequestBody int authorId) {
		
		return bookAuthorService.save(authorId);
		
	}
}
