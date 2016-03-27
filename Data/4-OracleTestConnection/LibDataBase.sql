DROP TABLE Authors;
CREATE TABLE Authors
(
	Author_Id CHAR(4),
	Name CHAR(25),
	Fname CHAR(25),
	PRIMARY KEY (Author_Id)
);

INSERT INTO Authors VALUES ('ALEX', 'Alexander', 'Christopher');
INSERT INTO Authors VALUES ('BROO', 'Brooks', 'Frederick P.');
INSERT INTO Authors VALUES ('CORM', 'Cormen', 'Thomas H.');
INSERT INTO Authors VALUES ('DATE', 'Date', 'C. J.');
INSERT INTO Authors VALUES ('DARW', 'Darwen', 'Hugh');
INSERT INTO Authors VALUES ('FEIN', 'Feiner', 'Steven K.');
INSERT INTO Authors VALUES ('FLAN', 'Flanagan', 'David');
INSERT INTO Authors VALUES ('FOLE', 'Foley', 'James D.');
INSERT INTO Authors VALUES ('GAMM', 'Gamma', 'Erich');
INSERT INTO Authors VALUES ('GARF', 'Garfinkel', 'Simson');
INSERT INTO Authors VALUES ('HEIN', 'Hein', 'Trent R.');
INSERT INTO Authors VALUES ('HELM', 'Helm', 'Richard');
INSERT INTO Authors VALUES ('HOPC', 'Hopcroft', 'John E.');
INSERT INTO Authors VALUES ('HUGH', 'Hughes', 'John F.');
INSERT INTO Authors VALUES ('ISHI', 'Ishikawa', 'Sara');
INSERT INTO Authors VALUES ('JOHN', 'Johnson', 'Ralph');
INSERT INTO Authors VALUES ('KAHN', 'Kahn', 'David');
INSERT INTO Authors VALUES ('KERN', 'Kernighan', 'Brian');
INSERT INTO Authors VALUES ('KIDD', 'Kidder', 'Tracy');
INSERT INTO Authors VALUES ('KNUT', 'Knuth', 'Donald E.');
INSERT INTO Authors VALUES ('LEIS', 'Leiserson', 'Charles E.');
INSERT INTO Authors VALUES ('MOTW', 'Motwani', 'Rajeev');
INSERT INTO Authors VALUES ('NEME', 'Nemeth', 'Evi');
INSERT INTO Authors VALUES ('RAYM', 'Raymond', 'Eric');
INSERT INTO Authors VALUES ('RITC', 'Ritchie', 'Dennis');
INSERT INTO Authors VALUES ('RIVE', 'Rivest', 'Ronald R.');
INSERT INTO Authors VALUES ('SCHN', 'Schneier', 'Bruce');
INSERT INTO Authors VALUES ('SEEB', 'Seebass', 'Scott');
INSERT INTO Authors VALUES ('SILV', 'Silverstein', 'Murray');
INSERT INTO Authors VALUES ('SNYD', 'Snyder', 'Garth');
INSERT INTO Authors VALUES ('STEI', 'Stein', 'Clifford E.');
INSERT INTO Authors VALUES ('STOL', 'Stoll', 'Clifford');
INSERT INTO Authors VALUES ('STRA', 'Strassmann', 'Steven');
INSERT INTO Authors VALUES ('STRO', 'Stroustrup', 'Bjarne');
INSERT INTO Authors VALUES ('ULLM', 'Ullman', 'Jeffrey D.');
INSERT INTO Authors VALUES ('VAND', 'van Dam', 'Andries');
INSERT INTO Authors VALUES ('VLIS', 'Vlissides', 'John');
INSERT INTO Authors VALUES ('WEIS', 'Weise', 'Daniel');

select * from Authors;

DROP TABLE Books;
CREATE TABLE Books
(
	Title CHAR(60),
	ISBN CHAR(13),
	Publisher_Id CHAR(6),
	Price DECIMAL(10,2),
	PRIMARY KEY (Title)
);

INSERT INTO Books VALUES ('A Guide to the SQL Standard', '0-201-96426-0', '0201', 47.95);
INSERT INTO Books VALUES ('A Pattern Language: Towns, Buildings, Construction', '0-19-501919-9', '019', 65.00);
INSERT INTO Books VALUES ('Applied Cryptography', '0-471-11709-9', '0471', 60.00);
INSERT INTO Books VALUES ('Computer Graphics: Principles and Practice', '0-201-84840-6', '0201', 79.99);
INSERT INTO Books VALUES ('Cuckoo''s Egg', '0-7434-1146-3', '07434', 13.95);
INSERT INTO Books VALUES ('Design Patterns', '0-201-63361-2', '0201', 54.99);
INSERT INTO Books VALUES ('Introduction to Algorithms', '0-262-03293-7', '0262', 80.00);
INSERT INTO Books VALUES ('Introduction to Automata Theory, Languages, and Computation', '0-201-44124-1', '0201', 105.00);
INSERT INTO Books VALUES ('JavaScript: The Definitive Guide', '0-596-00048-0', '0596', 44.95);
INSERT INTO Books VALUES ('The Art of Computer Programming vol. 1', '0-201-89683-4', '0201', 59.99);
INSERT INTO Books VALUES ('The Art of Computer Programming vol. 2', '0-201-89684-2', '0201', 59.99);
INSERT INTO Books VALUES ('The Art of Computer Programming vol. 3', '0-201-89685-0', '0201', 59.99);
INSERT INTO Books VALUES ('The C Programming Language', '0-13-110362-8', '013', 42.00);
INSERT INTO Books VALUES ('The C++ Programming Language', '0-201-70073-5', '0201', 64.99);
INSERT INTO Books VALUES ('The Cathedral and the Bazaar', '0-596-00108-8', '0596', 16.95);
INSERT INTO Books VALUES ('The Codebreakers', '0-684-83130-9', '07434', 70.00);
INSERT INTO Books VALUES ('The Mythical Man-Month', '0-201-83595-9', '0201', 29.95);
INSERT INTO Books VALUES ('The Soul of a New Machine', '0-679-60261-5', '0679', 18.95);
INSERT INTO Books VALUES ('The UNIX Hater''s Handbook', '1-56884-203-1', '0471', 16.95);
INSERT INTO Books VALUES ('UNIX System Administration Handbook', '0-13-020601-6', '013', 68.00);

select * from Books;

DROP TABLE BooksAuthors;
CREATE TABLE BooksAuthors
(
    ISBN CHAR(13),
	Author_Id CHAR(4),
	Seq_No INT
);
INSERT INTO BooksAuthors VALUES ('0-201-96426-0', 'DATE', 1);
INSERT INTO BooksAuthors VALUES ('0-201-96426-0', 'DARW', 2);
INSERT INTO BooksAuthors VALUES ('0-19-501919-9', 'ALEX', 1);
INSERT INTO BooksAuthors VALUES ('0-19-501919-9', 'ISHI', 2);
INSERT INTO BooksAuthors VALUES ('0-19-501919-9', 'SILV', 3);
INSERT INTO BooksAuthors VALUES ('0-471-11709-9', 'SCHN', 1);
INSERT INTO BooksAuthors VALUES ('0-201-84840-6', 'FOLE', 1);
INSERT INTO BooksAuthors VALUES ('0-201-84840-6', 'VAND', 2);
INSERT INTO BooksAuthors VALUES ('0-201-84840-6', 'FEIN', 3);
INSERT INTO BooksAuthors VALUES ('0-201-84840-6', 'HUGH', 4);
INSERT INTO BooksAuthors VALUES ('0-7434-1146-3', 'STOL', 1);
INSERT INTO BooksAuthors VALUES ('0-201-63361-2', 'GAMM', 1);
INSERT INTO BooksAuthors VALUES ('0-201-63361-2', 'HELM', 2);
INSERT INTO BooksAuthors VALUES ('0-201-63361-2', 'JOHN', 3);
INSERT INTO BooksAuthors VALUES ('0-201-63361-2', 'VLIS', 4);
INSERT INTO BooksAuthors VALUES ('0-262-03293-7', 'CORM', 1);
INSERT INTO BooksAuthors VALUES ('0-262-03293-7', 'LEIS', 2);
INSERT INTO BooksAuthors VALUES ('0-262-03293-7', 'RIVE', 3);
INSERT INTO BooksAuthors VALUES ('0-262-03293-7', 'STEI', 4);
INSERT INTO BooksAuthors VALUES ('0-201-44124-1', 'HOPC', 1);
INSERT INTO BooksAuthors VALUES ('0-201-44124-1', 'ULLM', 2);
INSERT INTO BooksAuthors VALUES ('0-201-44124-1', 'MOTW', 3);
INSERT INTO BooksAuthors VALUES ('0-596-00048-0', 'FLAN', 1);
INSERT INTO BooksAuthors VALUES ('0-201-89683-4', 'KNUT', 1);
INSERT INTO BooksAuthors VALUES ('0-201-89684-2', 'KNUT', 1);
INSERT INTO BooksAuthors VALUES ('0-201-89685-0', 'KNUT', 1);
INSERT INTO BooksAuthors VALUES ('0-13-110362-8', 'KERN', 1);
INSERT INTO BooksAuthors VALUES ('0-13-110362-8', 'RITC', 2);
INSERT INTO BooksAuthors VALUES ('0-201-70073-5', 'STRO', 1);
INSERT INTO BooksAuthors VALUES ('0-596-00108-8', 'RAYM', 1);
INSERT INTO BooksAuthors VALUES ('0-684-83130-9', 'KAHN', 1);
INSERT INTO BooksAuthors VALUES ('0-201-83595-9', 'BROO', 1);
INSERT INTO BooksAuthors VALUES ('0-679-60261-5', 'KIDD', 1);
INSERT INTO BooksAuthors VALUES ('1-56884-203-1', 'GARF', 1);
INSERT INTO BooksAuthors VALUES ('1-56884-203-1', 'WEIS', 2);
INSERT INTO BooksAuthors VALUES ('1-56884-203-1', 'STRA', 3);
INSERT INTO BooksAuthors VALUES ('0-13-020601-6', 'NEME', 1);
INSERT INTO BooksAuthors VALUES ('0-13-020601-6', 'SNYD', 2);
INSERT INTO BooksAuthors VALUES ('0-13-020601-6', 'SEEB', 3);
INSERT INTO BooksAuthors VALUES ('0-13-020601-6', 'HEIN', 4);

select * from BooksAuthors;

DROP TABLE Publishers;
CREATE TABLE Publishers
(
	Publisher_Id CHAR(6),
	Name CHAR(30),
	URL CHAR(80),
	PRIMARY KEY (Publisher_Id)
);
INSERT INTO Publishers VALUES ('0201', 'Addison-Wesley', 'www.aw-bc.com');
INSERT INTO Publishers VALUES ('0471', 'John Wiley & Sons', 'www.wiley.com');
INSERT INTO Publishers VALUES ('0262', 'MIT Press', 'mitpress.mit.edu');
INSERT INTO Publishers VALUES ('0596', 'O''Reilly', 'www.ora.com');
INSERT INTO Publishers VALUES ('019', 'Oxford University Press', 'www.oup.co.uk');
INSERT INTO Publishers VALUES ('013', 'Prentice Hall', 'www.phptr.com');
INSERT INTO Publishers VALUES ('0679', 'Random House', 'www.randomhouse.com');
INSERT INTO Publishers VALUES ('07434', 'Simon & Schuster', 'www.simonsays.com');

select * from Publishers;
