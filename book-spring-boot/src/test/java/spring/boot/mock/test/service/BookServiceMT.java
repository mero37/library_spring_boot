package spring.boot.mock.test.service;

import java.util.Arrays;
import java.util.Date;
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

import spring.boot.controller.BookController;
import spring.boot.dao.entity.Book;
import spring.boot.dao.jpa.repository.BookRepository;
import spring.boot.service.BookService;
import spring.boot.service.model.BookContext;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceMT {
	
	@Mock
    private BookRepository bookRepository;
	
	@InjectMocks
	private BookService bookService;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void queryBook() {
		
		Book book1 = new Book();
		book1.setBookId(101);
		book1.setBookName("Kitap1");
		book1.setBookEditionDate("2001");
		book1.setBookDescription("Açıklama1");

	    Book book2 = new Book();
	    book2.setBookId(102);
	    book2.setBookName("Kitap2");
	    book2.setBookEditionDate("2005");
	    book2.setBookDescription("Açıklama1");
		 
	    Mockito
	    	.when(bookRepository.findWithBookId(101))
	    	.thenReturn(book1);
	    
	    Mockito
	    	.when(bookRepository.findWithBookId(102))
	    	.thenReturn(book2);
	    
		
		Book book3 = bookRepository.findWithBookId(102);
		
		Assert.assertNotNull(book3);
		Assert.assertTrue(book3.getBookId() > 0);
	}
	
	@Test
	public void queryAllEmployees() {
		
		Book book1 = new Book();
		book1.setBookId(101);
		book1.setBookName("Kitap1");
		book1.setBookEditionDate("2001");
		book1.setBookDescription("Açıklama1");
	    
		Book book2 = new Book();
	    book2.setBookId(102);
	    book2.setBookName("Kitap2");
	    book2.setBookEditionDate("2005");
	    book2.setBookDescription("Açıklama1");
	    
	    
		List<Book> books = Arrays.asList(book1, book2);
		
		Mockito
			.when(bookRepository.getAllBookList())
			.thenReturn(books);
		
		
		
		List<Book> book3 = bookRepository.getAllBookList();
		
		Assert.assertNotNull(book3);
		Assert.assertTrue(book3.size() > 0);
	}
	
	@Test
	public void saveBook() {
		
		BookContext bookContext = new BookContext();
		bookContext.setBookName("Kitap1");
		bookContext.setBookEditionDate("2008");
		bookContext.setBookDescription("Açıklamaaa");
	    int maxBookId = 100;
	    
		Book book = new Book();
		book.setBookId(maxBookId++);
		book.setBookName(bookContext.getBookName());
		book.setBookEditionDate(bookContext.getBookEditionDate());
		book.setBookDescription(bookContext.getBookDescription());
	    
		
	    Mockito
			.when(bookRepository.findMaxId())
			.thenReturn(maxBookId);
	    
		Mockito
			.when(bookRepository.save(book))
			.thenReturn(book);
		
		
		int bookId = bookService.save(bookContext);
		
		Assert.assertEquals(101, bookId);
	}
	
	@Test
	private void prepareMockTestRuleBookQueryByBookId() {
		
		Book book = new Book();
		book.setBookId(10);
		book.setBookName("Kitap1");
		book.setBookEditionDate("2001");
		book.setBookDescription("Açıklama1");;
		 
	    Mockito
	    	.when(bookRepository.findWithBookId(10))
	    	.thenReturn(book);
	}
	
	@Test
	private void prepareMockTestRuleBookQueryByBookName() {
		
		Book book = new Book();
		book.setBookId(10);
		book.setBookName("Kitap1");
		book.setBookEditionDate("2001");
		book.setBookDescription("Açıklama1");;
		 
	    Mockito
	    	.when(bookRepository.findWithBookName("kit"))
	    	.thenReturn((List<Book>) book);
	}

}
