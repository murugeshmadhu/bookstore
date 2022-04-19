package com.crni99.bookstore.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	public String list(Model model) {
		model.addAttribute("books", bookService.findAll());
		return "list";
	}

	@GetMapping("/book/search")
	public String search(@RequestParam("term") String term, Model model) {
		if (StringUtils.isEmpty(term)) {
			return "redirect:/book";
		}
		model.addAttribute("books", bookService.search(term));
		return "list";
	}

	@GetMapping("/book/add")
	public String add(Model model) {
		model.addAttribute("book", new Book());
		return "form";
	}

	@GetMapping("/book/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", bookService.findOne(id));
		return "form";
	}

	@PostMapping("/book/save")
	public String save(@Validated Book book, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "form";
		}
		bookService.save(book);
		redirect.addFlashAttribute("successMessage", "Saved book successfully!");
		return "redirect:/book";
	}

	@GetMapping("/book/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes redirect) {
		bookService.delete(id);
		redirect.addFlashAttribute("successMessage", "Deleted book successfully!");
		return "redirect:/book";
	}

}
