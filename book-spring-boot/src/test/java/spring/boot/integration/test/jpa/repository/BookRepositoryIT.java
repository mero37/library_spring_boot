package spring.boot.integration.test.jpa.repository;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.PageRequest;
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
public class BookRepositoryIT {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	@Order(1)
	public void selectBookById() {
		
		int maxId = bookRepository.findMaxId();
		Book book = bookRepository.findWithBookId(maxId);
		
		Assert.assertNotNull(book);
		Assert.assertTrue(book.getBookId() > 0);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	@Order(2)
	public void saveBook() {
		
		int maxId = bookRepository.findMaxId();
		int newBookId = maxId + 1;
		
		Book book = new Book();
		book.setBookId(newBookId);
		book.setBookName("Üç Şilahşörler");
		book.setBookEditionDate("2001");
		book.setBookDescription("Kitap açıklaması.");
		bookRepository.save(book);
		
		book = bookRepository.findWithBookId(newBookId);
		
		Assert.assertNotNull(book);
		Assert.assertTrue(book.getBookId() > 0);
	}

	
}