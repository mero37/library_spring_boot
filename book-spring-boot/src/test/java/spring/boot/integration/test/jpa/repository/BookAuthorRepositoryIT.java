package spring.boot.integration.test.jpa.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.dao.entity.Book;
import spring.boot.dao.entity.BookAuthor;
import spring.boot.dao.jpa.repository.BookAuthorRepository;
import spring.boot.dao.jpa.repository.BookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource({ "classpath:application.properties" })
public class BookAuthorRepositoryIT {

	@Autowired
	private BookAuthorRepository bookAuthorRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	@Order(1)
	public void selectBookAuthorById() {
		
		int maxId = bookAuthorRepository.findMaxId();
		BookAuthor bookAuthor = bookAuthorRepository.findWithBookAuthorId(maxId);
		
		Assert.assertNotNull(bookAuthor);
		Assert.assertTrue(bookAuthor.getBookAuthorId() > 0);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	@Order(2)
	public void saveBookAuthor() {
		
		int maxId = bookAuthorRepository.findMaxId();
		int newBookAuthorId = maxId + 1;
		
		int bookId = bookRepository.findMaxId();
		
		BookAuthor bookAuthor = new BookAuthor();
		bookAuthor.setBookAuthorId(newBookAuthorId);
		bookAuthor.setBookId(bookId);
		bookAuthor.setBookId(2);
		bookAuthorRepository.save(bookAuthor);
		
		bookAuthor = bookAuthorRepository.findWithBookAuthorId(newBookAuthorId);
		
		Assert.assertNotNull(bookAuthor);
		Assert.assertTrue(bookAuthor.getBookAuthorId() > 0);
	}

	
}
