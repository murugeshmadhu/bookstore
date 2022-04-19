package com.crni99.bookstore.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crni99.bookstore.book.Book;
import com.crni99.bookstore.repository.BookRepository;

class BookServiceTest {

	private static final Long ID_1 = 1L;
	private static final String NAME_1 = "The Lord of the Rings";
	private static final BigDecimal PRICE_1 = new BigDecimal(99.99);
	private static final String AUTHORS_1 = "J. R. R. Tolkien";
	private static final String ISBN_1 = "978-0-261-10320-7";
	private static final String PUBLISHER_1 = "Allen & Unwin";
	private static final LocalDate DOB_1 = LocalDate.of(1954, 07, 29);

	private static final Long ID_2 = 2L;
	private static final String NAME_2 = "The Da Vinci Code";
	private static final BigDecimal PRICE_2 = new BigDecimal(250.89);
	private static final String AUTHORS_2 = "Dan Brown";
	private static final String ISBN_2 = "0-385-50420-9";
	private static final String PUBLISHER_2 = "Doubleday";
	private static final LocalDate DOB_2 = LocalDate.of(2003, 04, 02);

	@Mock
	private BookRepository bookRepository;

	private BookService bookService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		bookService = new BookService(bookRepository);
	}

	@Test
	void test() {

	}

	@Test
	void shouldFindAllBooks() {
		List<Book> books = new ArrayList<>();
		books.add(new Book(ID_1, NAME_1, PRICE_1, AUTHORS_1, ISBN_1, PUBLISHER_1, DOB_1));
		books.add(new Book(ID_2, NAME_2, PRICE_2, AUTHORS_2, ISBN_2, PUBLISHER_2, DOB_2));

		when(bookRepository.findAll()).thenReturn(books);

		assertThat(bookService.findAll()).isEqualTo(books);
	}

	@Test
	void shouldSaveBook() {
		Book book = new Book(ID_1, NAME_1, PRICE_1, AUTHORS_1, ISBN_1, PUBLISHER_1, DOB_1);
		bookService.save(book);

		ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
		verify(bookRepository).save(bookArgumentCaptor.capture());

		Book capturedBook = bookArgumentCaptor.getValue();
		assertThat(capturedBook).isEqualTo(book);
	}

	@Test
	void shouldFindBookByTerm() {
		List<Book> books = new ArrayList<>();
		Book book1 = new Book(ID_1, NAME_1, PRICE_1, AUTHORS_1, ISBN_1, PUBLISHER_1, DOB_1);
		Book book2 = new Book(ID_2, NAME_1, PRICE_2, AUTHORS_2, ISBN_2, PUBLISHER_2, DOB_2);
		books.add(book1);
		books.add(book2);

		when(bookRepository.findByNameContaining(NAME_1)).thenReturn(books);

		assertThat(bookService.search(NAME_1)).isEqualTo(books);
	}

	@Test
	void shouldFindBookByID() {
		Book book = new Book(ID_1, NAME_1, PRICE_1, AUTHORS_1, ISBN_1, PUBLISHER_1, DOB_1);

		when(bookRepository.findById(ID_1)).thenReturn(Optional.of(book));

		assertThat(bookService.findBookById(ID_1)).isEqualTo(Optional.of(book));
	}

	@Test
	void shouldDeleteBook() {
		Book book = new Book(ID_1, NAME_1, PRICE_1, AUTHORS_1, ISBN_1, PUBLISHER_1, DOB_1);

		when(bookRepository.findById(ID_1)).thenReturn(Optional.of(book));
		bookService.delete(ID_1);
		verify(bookRepository).deleteById(ID_1);
	}

}
