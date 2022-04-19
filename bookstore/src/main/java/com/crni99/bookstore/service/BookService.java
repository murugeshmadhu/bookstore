package com.crni99.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crni99.bookstore.book.Book;
import com.crni99.bookstore.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Iterable<Book> findAll() {
		return bookRepository.findAll();
	}

	public List<Book> search(String term) {
		return bookRepository.findByNameContaining(term);
	}

	public Optional<Book> findOne(Long id) {
		Optional<Book> book = bookRepository.findById(id);
		return book;
	}

	public void save(Book book) {
		bookRepository.save(book);
	}

	public void delete(Long id) {
		bookRepository.deleteById(id);
	}

}
