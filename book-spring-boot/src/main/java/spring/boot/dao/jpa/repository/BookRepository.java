package spring.boot.dao.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import spring.boot.dao.entity.Author;
import spring.boot.dao.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
	
	@Query(value = "SELECT b from Book b")
	public List<Book> getAllBookList();
	
	@Query(value = "SELECT a from Author a")
	public List<Author> getAllAuthorList();
	
	@Query(value = "FROM Book b WHERE b.bookId = :bookId")
	public Book findWithBookId(@Param("bookId") Integer bookId);
	
	@Query(value = "Select b FROM Book b WHERE b.bookName LIKE '%' || :bookName || '%'")
	public List<Book> findWithBookName(@Param("bookName") String bookName);
	
	@Query(value = "SELECT MAX(b.bookId) FROM Book b")
	public int findMaxId();
	

	
}
