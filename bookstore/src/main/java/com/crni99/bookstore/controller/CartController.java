package com.crni99.bookstore.controller;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crni99.bookstore.book.Book;
import com.crni99.bookstore.service.BookService;

@Controller
@RequestMapping("/cart")
public class CartController {

	private static ArrayList<Book> books = new ArrayList<Book>();
	private static BigDecimal total = BigDecimal.ZERO;

	private final BookService bookService;

	public CartController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping(value = { "", "/" })
	public String shoppingCart(Model model) {

		model.addAttribute("total", total);
		model.addAttribute("books", books);
		return "cart";
	}

	@GetMapping("/{id}/addToCart")
	public String addToCart(@PathVariable("id") Long id, RedirectAttributes redirect) {

		Book book = bookService.findBookById(id).get();
		if (book != null) {
			books.add(book);
			total = total.add(book.getPrice());
		}
		redirect.addFlashAttribute("successMessage", "Added book successfully!");
		return "redirect:/";
	}

	@GetMapping("/{id}/removeFromCart")
	public String removeFromCart(@PathVariable("id") Long id, RedirectAttributes redirect) {

		Book book = bookService.findBookById(id).get();
		if (book != null) {
			books.remove(book);
			total = total.subtract(book.getPrice());
		}
		redirect.addFlashAttribute("successMessage", "Removed book successfully!");
		return "redirect:/cart";
	}

}
