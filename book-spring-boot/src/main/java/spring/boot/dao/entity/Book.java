package spring.boot.dao.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
	
	@Id
	@org.springframework.data.annotation.Id
	@Column(name = "book_id")
	private int bookId;
	
	@Column(name = "book_name")
	private String bookName;
	
	@Column(name = "book_date")
	private String bookEditionDate;
	
	@Column(name = "book_desc")
	private String bookDescription;
	
	/*@Column(name = "author_name")
	private String authorName;*/

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "book_author",
			joinColumns = { @JoinColumn(name = "book_id") },
			inverseJoinColumns =  { @JoinColumn(name = "author_id") }
	)
	private List<Author> authors;
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookEditionDate() {
		return bookEditionDate;
	}

	public void setBookEditionDate(String bookEditionDate) {
		this.bookEditionDate = bookEditionDate;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + ((bookDescription == null) ? 0 : bookDescription.hashCode());
		result = prime * result + ((bookEditionDate == null) ? 0 : bookEditionDate.hashCode());
		result = prime * result + bookId;
		result = prime * result + ((bookName == null) ? 0 : bookName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (bookDescription == null) {
			if (other.bookDescription != null)
				return false;
		} else if (!bookDescription.equals(other.bookDescription))
			return false;
		if (bookEditionDate == null) {
			if (other.bookEditionDate != null)
				return false;
		} else if (!bookEditionDate.equals(other.bookEditionDate))
			return false;
		if (bookId != other.bookId)
			return false;
		if (bookName == null) {
			if (other.bookName != null)
				return false;
		} else if (!bookName.equals(other.bookName))
			return false;
		return true;
	}

	/*public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}*/
	
	

	

}
