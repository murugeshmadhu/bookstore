package com.crni99.bookstore.book;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@Column(name = "authors", nullable = false)
	private String authors;

	@Column(name = "isbn", nullable = false)
	private String isbn;

	@Column(name = "publisher", nullable = false)
	private String publisher;

	@Column(name = "published_on", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate publishedOn;

	public Book(Long id, String name, BigDecimal price, String authors, String isbn, String publisher,
			LocalDate publishedOn) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.authors = authors;
		this.isbn = isbn;
		this.publisher = publisher;
		this.publishedOn = publishedOn;
	}

	public Book() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public LocalDate getPublishedOn() {
		return publishedOn;
	}

	public void setPublishedOn(LocalDate publishedOn) {
		this.publishedOn = publishedOn;
	}

}
