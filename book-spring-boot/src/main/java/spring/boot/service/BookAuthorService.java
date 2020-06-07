package spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.annotation.MethodRunningTime;
import spring.boot.dao.entity.Author;
import spring.boot.dao.entity.Book;
import spring.boot.dao.entity.BookAuthor;
import spring.boot.dao.jpa.repository.BookAuthorRepository;
import spring.boot.dao.jpa.repository.BookRepository;
import spring.boot.service.model.BookContext;

@Service
public class BookAuthorService {

	
	@Autowired
	private BookAuthorRepository bookAuthorRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@MethodRunningTime(active = true)
	public List<BookAuthor> getAllBookAuthorList() {
		
		return bookAuthorRepository.getAllBookAuthorList();
	}
	
	@MethodRunningTime(active = true)
	public BookAuthor findByBookAuthorId(int bookId) {
		
		return bookAuthorRepository.findWithBookAuthorId(bookId);
	}
	
	@MethodRunningTime(active = true)
	@Transactional
	public int save(int authorId) {
		
		int maxId = bookAuthorRepository.findMaxId() + 1;
		int bookId = bookRepository.findMaxId();
		BookAuthor bookAuthor = new BookAuthor();
		bookAuthor.setBookAuthorId(maxId);
		bookAuthor.setBookId(bookId);
		bookAuthor.setAuthorId(authorId);
		
		bookAuthor = bookAuthorRepository.save(bookAuthor);
		
		return bookAuthor.getBookAuthorId();
	}

}
