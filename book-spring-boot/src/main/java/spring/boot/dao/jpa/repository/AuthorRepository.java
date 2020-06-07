package spring.boot.dao.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import spring.boot.dao.entity.Author;
import spring.boot.dao.entity.Book;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
	
	@Query(value = "SELECT a from Author a")
	public List<Author> getAllAuthorList();
	
}
