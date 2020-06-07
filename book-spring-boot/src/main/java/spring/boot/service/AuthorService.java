package spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.boot.annotation.MethodRunningTime;
import spring.boot.dao.entity.Author;
import spring.boot.dao.jpa.repository.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	
	@MethodRunningTime(active = true)
	public List<Author> getAllAuthorList() {
		
		return authorRepository.getAllAuthorList();
	}

}
