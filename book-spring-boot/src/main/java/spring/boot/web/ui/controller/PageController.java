package spring.boot.web.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.boot.dao.entity.Author;
import spring.boot.dao.entity.Book;
import spring.boot.service.AuthorService;
import spring.boot.service.BookAuthorService;
import spring.boot.service.BookService;
import spring.boot.service.model.BookContext;

@Controller
@RequestMapping("/pages")
public class PageController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private BookAuthorService bookAuthorService;
	
	
	@RequestMapping(value = "/book_list.html", method = RequestMethod.GET)
	public String getBooks(Model model) {
		
		List<Book> books = bookService.getAllBookList();
		model.addAttribute("book", books);
		
		return "book_list";
	}
	
	@RequestMapping(value = "/book_list.html/search", method = RequestMethod.GET)
	public String getSearchBooks(String bookName, Model model) {
		
		List<Book> books = bookService.findByBookName(bookName);
		model.addAttribute("book", books);
		
		return "book_list";
	}
	
	@RequestMapping(value = "/book_save.html", method = RequestMethod.GET)
	public String getEmployeeSavePage(Model model) {
		 
	    BookContext form = new BookContext();
	    model.addAttribute("bookContext", form);
	 
	    List<Author> list = authorService.getAllAuthorList();
	    model.addAttribute("authors", list);
	 
	    return "book_save";
	}
	@RequestMapping(value = "/book", method = RequestMethod.POST)
    public String save(BookContext bookContext, BindingResult result, Model model) throws InterruptedException {
		 
		bookService.save(bookContext);
        
		for(int authorId:bookContext.getAuthorId()) {
			bookAuthorService.save(authorId);
		}
		
		List<Book> books = bookService.getAllBookList();
		model.addAttribute("book", books);
	 
		return "redirect:/pages/book_list.html";
	}
}