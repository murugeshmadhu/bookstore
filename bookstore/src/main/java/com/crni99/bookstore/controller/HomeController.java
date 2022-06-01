package com.crni99.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crni99.bookstore.service.BookService;

@Controller
public class HomeController {

	private final BookService bookService;

	public HomeController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("/search")
	public String searchBooks(@RequestParam("term") String term, Model model) {
		if (StringUtils.isEmpty(term)) {
			return "redirect:/";
		}
		model.addAttribute("books", bookService.search(term));
		return "index";
	}
}
