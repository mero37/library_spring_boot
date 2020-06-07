package spring.boot.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_author")
public class BookAuthor {
	
	@Id
	@Column(name = "bookauthor_id")
	private int bookAuthorId;
	
	@Column(name = "book_id")
	private int bookId;
	
	@Column(name = "author_id")
	private int authorId;

	public int getBookAuthorId() {
		return bookAuthorId;
	}

	public void setBookAuthorId(int bookAuthorId) {
		this.bookAuthorId = bookAuthorId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	
}
