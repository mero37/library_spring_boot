package spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.annotation.MethodRunningTime;
import spring.boot.dao.entity.Author;
import spring.boot.dao.entity.Book;
import spring.boot.dao.jpa.repository.BookRepository;
import spring.boot.service.model.BookContext;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	
	@MethodRunningTime(active = true)
	public List<Book> getAllBookList() {
		
		return bookRepository.getAllBookList();
	}

	@MethodRunningTime(active = true)
	public Book findByBookId(int bookId) {
		
		return bookRepository.findWithBookId(bookId);
	}
	
	@MethodRunningTime(active = true)
	public List<Book> findByBookName(String bookName) {
		
		return bookRepository.findWithBookName(bookName);
	}
	
	@MethodRunningTime(active = true)
	@Transactional
	public int save(BookContext bookContext) {
		
		int maxId = bookRepository.findMaxId() + 1;
		
		Book book = new Book();
		book.setBookId(maxId);
		book.setBookName(bookContext.getBookName());
		book.setBookEditionDate(bookContext.getBookEditionDate());
		book.setBookDescription(bookContext.getBookDescription());
		book = bookRepository.save(book);
		
		
		return book.getBookId();
	}
	

}
