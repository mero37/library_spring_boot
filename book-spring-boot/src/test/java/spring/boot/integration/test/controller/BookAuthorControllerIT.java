package spring.boot.integration.test.controller;

import java.util.List;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import spring.boot.dao.entity.Book;
import spring.boot.dao.entity.BookAuthor;
import spring.boot.service.model.BookContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource({ "classpath:application.properties" })
public class BookAuthorControllerIT {
	
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
	public void findBookAuthorById() {
		
		String url = prepareBookRestServiceRootUrl() + "bookAuthor/2";
		
		ResponseEntity<BookAuthor> response = restTemplate.getForEntity(url, BookAuthor.class);
		
		BookAuthor bookAuthor = response.getBody();
		
		Assert.assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		Assert.assertTrue(2 == bookAuthor.getBookAuthorId());
	}
	
	@Test
	@Order(4)
	public void saveBookAuthor() {
		
		String url = prepareBookRestServiceRootUrl() + "bookAuthor";
		
		BookContext bookContext = new BookContext();
	    Integer id[] = new Integer[]{1,3,5};

	    List<Integer> idList = Arrays.asList(id);
	      
		bookContext.setAuthorId(idList);
		
		ResponseEntity<Integer> response = restTemplate.postForEntity(url, bookContext, Integer.class);
		
		Integer bookAuthorId = response.getBody();
		
		Assert.assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		Assert.assertNotNull(bookAuthorId);
	}
	
	private String prepareBookRestServiceRootUrl() {
		
		return "http://localhost:" + tomcatPortNo + "/myLibrary/";
	}
}
