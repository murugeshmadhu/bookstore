package com.crni99.bookstore.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.crni99.bookstore.book.Book;

@Service
public class ShoppingCartService {

	private static ArrayList<Book> books = new ArrayList<>();
	private static BigDecimal total;

	public ArrayList<Book> productsInCart() {
		return books;
	}

	public BigDecimal totalPrice() {
		total = BigDecimal.ZERO;
		for (Book book : books) {
			total = total.add(book.getPrice());
		}
		return total;
	}

	public void addProduct(Book book) {
		books.add(book);
	}

	public void removeProduct(Book book) {

		for (int j = 0; j < books.size(); j++) {
			Long id = books.get(j).getId();
			if (book.getId() == id) {
				books.remove(j);
			}
		}
	}

	public void removeAll() {
		total = BigDecimal.ZERO;
		books.removeAll(books);
	}
}
