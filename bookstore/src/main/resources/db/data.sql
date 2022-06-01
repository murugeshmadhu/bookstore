-- BOOKS

INSERT INTO BOOKS (id, name, price, authors, isbn, publisher, published_on) 
	VALUES (1, 'The Lord of the Rings', '99.99', 'J. R. R. Tolkien', '9780261103207', 'Allen & Unwin', '1954-07-29');
	
INSERT INTO BOOKS (id, name, price, authors, isbn, publisher, published_on) 
	VALUES (2, 'Harry Potter and the Half-Blood Prince', '55.00', 'J. K. Rowling', '9780439784542', 'Bloomsbury Publishing (UK)', '2005-07-16');
	
INSERT INTO BOOKS (id, name, price, authors, isbn, publisher, published_on) 
	VALUES (3, 'The Da Vinci Code', '250.89', 'Dan Brown', '0385504209', 'Doubleday', '2003-04-02');
	
INSERT INTO BOOKS (id, name, price, authors, isbn, publisher, published_on) 
	VALUES (4, 'Fifty Shades of Grey', '26.95', 'E. L. James', '9781612130286', 'Vintage Books', '2012-04-17');


-- USERS

INSERT INTO USERS (username, password, enabled) 
	VALUES ('ognjen', '{noop}ognjen', 1);
	

-- AUTHORITIES

INSERT INTO AUTHORITIES (username, authority) 
	VALUES ('ognjen', 'ADMIN');