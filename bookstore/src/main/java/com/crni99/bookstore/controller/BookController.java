package com.crni99.bookstore.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crni99.bookstore.book.Book;
import com.crni99.bookstore.service.BookService;

@Controller
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/book")
	public String getAllBooks(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		return page(null, model, page, size);
	}

	@GetMapping("/book/search")
	public String searchBooks(@RequestParam("term") String term, Model model,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		if (StringUtils.isEmpty(term)) {
			return "redirect:/book";
		}
		return page(term, model, page, size);
	}

	private String page(@RequestParam("term") String term, Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);

		Page<Book> bookPage;

		if (term == null) {
			bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize), null);
		} else {
			bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize), term);
		}
		model.addAttribute("bookPage", bookPage);

		int totalPages = bookPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "list";
	}

	@GetMapping("/book/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "form";
	}

	@PostMapping("/book/save")
	public String saveBook(@Valid Book book, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "form";
		}
		bookService.save(book);
		redirect.addFlashAttribute("successMessage", "Saved book successfully!");
		return "redirect:/book";
	}

	@GetMapping("/book/{id}/edit")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", bookService.findBookById(id));
		return "form";
	}

	@GetMapping("/book/{id}/delete")
	public String deleteBook(@PathVariable Long id, RedirectAttributes redirect) {
		bookService.delete(id);
		redirect.addFlashAttribute("successMessage", "Deleted book successfully!");
		return "redirect:/book";
	}

}
