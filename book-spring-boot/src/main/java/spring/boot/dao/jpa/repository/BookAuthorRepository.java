package spring.boot.dao.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import spring.boot.dao.entity.Author;
import spring.boot.dao.entity.Book;
import spring.boot.dao.entity.BookAuthor;

public interface BookAuthorRepository extends CrudRepository<BookAuthor, Integer> {
	
	@Query(value = "SELECT ba from BookAuthor ba")
	public List<BookAuthor> getAllBookAuthorList();
	
	@Query(value = "SELECT MAX(ba.bookAuthorId) from BookAuthor ba")
	public int findMaxId();

	@Query(value = "FROM BookAuthor ba WHERE ba.bookAuthorId = :bookAuthorId")
	public BookAuthor findWithBookAuthorId(@Param("bookAuthorId") Integer bookAuthorId);

	
}