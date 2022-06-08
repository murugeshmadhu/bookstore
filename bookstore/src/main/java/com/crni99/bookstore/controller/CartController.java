package com.crni99.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crni99.bookstore.book.Book;
import com.crni99.bookstore.service.BookService;
import com.crni99.bookstore.service.ShoppingCartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	private final BookService bookService;
	private final ShoppingCartService shoppingCartService;

	public CartController(BookService bookService, ShoppingCartService shoppingCartService) {
		this.bookService = bookService;
		this.shoppingCartService = shoppingCartService;
	}

	@GetMapping(value = { "", "/" })
	public String shoppingCart(Model model) {

		model.addAttribute("books", shoppingCartService.productsInCart());
		model.addAttribute("total", shoppingCartService.totalPrice());
		return "cart";
	}

	@GetMapping("/add/{id}")
	public String addToCart(@PathVariable("id") Long id, RedirectAttributes redirect) {

		Book book = bookService.findBookById(id).get();
		if (book != null) {
			shoppingCartService.addProduct(book);
		}
		redirect.addFlashAttribute("successMessage", "Added book successfully!");
		return "redirect:/";
	}

	@GetMapping("/remove/{id}")
	public String removeFromCart(@PathVariable("id") Long id, RedirectAttributes redirect) {

		Book book = bookService.findBookById(id).get();
		if (book != null) {
			shoppingCartService.removeProduct(book);
		}
		redirect.addFlashAttribute("successMessage", "Removed book successfully!");
		return "redirect:/cart";
	}
	
	@GetMapping("/remove/all")
	public String removeFromCart() {

		shoppingCartService.removeAll();
		return "redirect:/cart";
	}

}
