package spring.boot.integration.test.controller;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import spring.boot.dao.entity.Book;
import spring.boot.service.model.BookContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource({ "classpath:application.properties" })
public class BookControllerIT {
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int tomcatPortNo;
	
	@Test
	@Order(1)
	public void testRestTemplate() {
		
		ResponseEntity<String> response = restTemplate.getForEntity("https://www.google.com", String.class);
		
		System.out.println(tomcatPortNo);
		
		Assert.assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		Assert.assertTrue(response.getBody().length() > 0);
	}
	
	@Test
	@Order(2)
	public void findBookById() {
		
		String url = prepareBookRestServiceRootUrl() + "book/2";
		
		ResponseEntity<Book> response = restTemplate.getForEntity(url, Book.class);
		
		Book book = response.getBody();
		
		Assert.assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		Assert.assertTrue(2 == book.getBookId());
	}
	
	@Test
	@Order(3)
	public void findBookByName() {
		
		String url = prepareBookRestServiceRootUrl() + "book/İnsan ne İle Yaşar";
		
		ResponseEntity<Book> response = restTemplate.getForEntity(url, Book.class);
		
		Book book = response.getBody();
		
		Assert.assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		Assert.assertTrue("İnsan ne İle Yaşar" == book.getBookName());
	}
	
	@Test
	@Order(4)
	public void saveBook() {
		
		String url = prepareBookRestServiceRootUrl() + "book";
		
		BookContext bookContext = new BookContext();
		bookContext.setBookName("Kitap1");
		bookContext.setBookEditionDate("2000");
		bookContext.setBookDescription("Kitap Açıklama1");
		
		ResponseEntity<Integer> response = restTemplate.postForEntity(url, bookContext, Integer.class);
		
		Integer bookId = response.getBody();
		
		Assert.assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		Assert.assertNotNull(bookId);
	}
	
	private String prepareBookRestServiceRootUrl() {
		
		return "http://localhost:" + tomcatPortNo + "/myLibrary/";
	}
}
