package spring.boot.service.model;

import java.util.List;
import java.io.Serializable;


public class BookContext implements Serializable {

		private static final long serialVersionUID = -2225749367577811809L;
		
		private String bookName;
		private String bookEditionDate;
		private String bookDescription;
		private List<Integer> authorId;
		
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
		public List<Integer> getAuthorId() {
			return authorId;
		}
		public void setAuthorId(List<Integer> authorId) {
			this.authorId = authorId;
		}
		
		
		
		
}
