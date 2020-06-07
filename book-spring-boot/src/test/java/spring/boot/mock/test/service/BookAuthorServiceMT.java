package spring.boot.mock.test.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import spring.boot.dao.entity.BookAuthor;
import spring.boot.dao.jpa.repository.BookAuthorRepository;
import spring.boot.service.BookAuthorService;
import spring.boot.service.model.BookContext;

@RunWith(MockitoJUnitRunner.class)
public class BookAuthorServiceMT {

	
	@Mock
    private BookAuthorRepository bookAuthorRepository;
	
	@InjectMocks
	private BookAuthorService bookAuthorService;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void queryBookAuthor() {
		
		BookAuthor bookAuthor1 = new BookAuthor();
		bookAuthor1.setBookAuthorId(101);
		bookAuthor1.setBookId(2);
	  	bookAuthor1.setAuthorId(5);
	  	
		BookAuthor bookAuthor2 = new BookAuthor();
		bookAuthor2.setBookAuthorId(102);
		bookAuthor2.setBookId(3);
		bookAuthor2.setAuthorId(6);
		 
	    Mockito
	    	.when(bookAuthorRepository.findWithBookAuthorId(101))
	    	.thenReturn(bookAuthor1);
	    
	    Mockito
	    	.when(bookAuthorRepository.findWithBookAuthorId(102))
	    	.thenReturn(bookAuthor2);
	    
		
		BookAuthor bookAuthor3 = bookAuthorRepository.findWithBookAuthorId(102);
		
		Assert.assertNotNull(bookAuthor3);
		Assert.assertTrue(bookAuthor3.getBookAuthorId() > 0);
	}
	
	@Test
	public void queryAllEmployees() {
		
		BookAuthor bookAuthor1 = new BookAuthor();
		bookAuthor1.setBookAuthorId(101);
		bookAuthor1.setBookId(2);
	  	bookAuthor1.setAuthorId(5);
	  	
		BookAuthor bookAuthor2 = new BookAuthor();
		bookAuthor2.setBookAuthorId(102);
		bookAuthor2.setBookId(3);
		bookAuthor2.setAuthorId(6);
	    
	    
		List<BookAuthor> bookAuthors = Arrays.asList(bookAuthor1, bookAuthor2);
		
		Mockito
			.when(bookAuthorRepository.getAllBookAuthorList())
			.thenReturn(bookAuthors);
		
		
		
		List<BookAuthor> bookAuthor3 = bookAuthorRepository.getAllBookAuthorList();
		
		Assert.assertNotNull(bookAuthor3);
		Assert.assertTrue(bookAuthor3.size() > 0);
	}
	
	@Test
	public void saveBook() {
		
		BookContext bookContext = new BookContext();
		bookContext.setAuthorId(Arrays.asList(3,5));
		
	    int maxBookAuthorId = 100;
	    int bookId = 2;

		BookAuthor bookAuthor = new BookAuthor();
		bookAuthor.setBookAuthorId(maxBookAuthorId++);
		bookAuthor.setBookId(bookId);
		bookAuthor.setAuthorId(bookContext.getAuthorId().get(0));
	    
		
	    Mockito
			.when(bookAuthorRepository.findMaxId())
			.thenReturn(maxBookAuthorId);
	    
		Mockito
			.when(bookAuthorRepository.save(bookAuthor))
			.thenReturn(bookAuthor);
		
		
		int bookAuthorId = bookAuthorService.save(bookContext.getAuthorId().get(0));
		
		Assert.assertEquals(101, bookAuthorId);
	}
	
}
