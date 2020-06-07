package spring.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.dao.entity.Author;
import spring.boot.service.AuthorService;

@RestController
@RequestMapping("/myLibrary")
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	
	@RequestMapping(value = "/author/list", method = RequestMethod.GET)
	public List<Author> getAllAuthorList() {
		
		return authorService.getAllAuthorList();
	}
	
}
