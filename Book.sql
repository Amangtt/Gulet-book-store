USE BookStore

/*------------------------------------------------------------------------------------------------------------------------------------
															PROCEDURE
-------------------------------------------------------------------------------------------------------------------------------------*/
GO
--1 PROCEDURE
	CREATE PROC GetAllBook
	AS
	BEGIN
		SELECT * 
		FROM UDFgetAllBook()
	END
GO
--2 PROCEDURE
	CREATE PROC SearchBook
	@search VARCHAR(MAX)
	AS
	BEGIN
		SELECT *
		FROM UDFsearchBook (@search)
	END
	GO
	
--3 PROCEDURE
	CREATE PROC GetSoledAndPurchased
	AS
	BEGIN
		SELECT *
		FROM UDFgetSaleAndPurchase()
	END
	GO
--4 PROCEDURE
	CREATE PROC UpdateBook
		@id	INT 
		,@title VARCHAR(200) 
		,@author VARCHAR(50) 
		,@category VARCHAR(50)
		,@publisher VARCHAR(200)
		,@edition INT 
		,@isbn VARCHAR(MAX)
	AS
	BEGIN
		UPDATE book
		SET title = @title, author = @author, category = @category , publisher = @publisher,edition = @edition
		WHERE id = @id
	END
GO
--5 PROCEDURE
	CREATE PROC InsertIntoBook
		@title VARCHAR(200) 
		,@author VARCHAR(50) 
		,@category VARCHAR(50)
		,@publisher VARCHAR(200)
		,@edition INT 
		,@isbn VARCHAR(MAX)
	AS
	BEGIN
		INSERT INTO book (title, author, category, publisher, edition, isbn) VALUES (@title, @author,@category,@publisher,@edition, @isbn)
	END
	
	GO
--6 PROCEDURE
	CREATE PROC DeleteBook
	@id INT
	AS
	BEGIN
		DELETE FROM purchaseList 
		WHERE bookId=@id

		DELETE FROM PurchaseOrderDetail
		WHERE bookId=@id

		DELETE FROM SalesOrderDetail
		WHERE bookId=@id

		DELETE FROM Book
		WHERE id=@id
	END	
	GO
/*------------------------------------------------------------------------------------------------------------------------------------
															FUNCTION
-------------------------------------------------------------------------------------------------------------------------------------*/
--1 FUNCTION
GO
	CREATE FUNCTION UDFsearchBook (@search VARCHAR(MAX))
	RETURNS TABLE
	AS
	RETURN(
		SELECT *
		FROM book
		WHERE title LIKE '%'+@search +'%'
				OR author LIKE '%'+@search +'%'
				OR category LIKE '%'+@search +'%'
	)
	GO
--2 FUNCTION
	CREATE FUNCTION  UDFgetAllBook()
	RETURNS TABLE
	AS
	RETURN(
		SELECT * FROM book
	)
GO
-- 3 FUNCTION
	CREATE FUNCTION UDFgetSaleAndPurchase()
	RETURNS TABLE
	AS
	RETURN(
		SELECT book.id, SUM(SalesOrderDetail.quantity) AS Soled, SUM(PurchaseOrderDetail.quantity) AS Purchased
		FROM book, SalesOrderDetail, PurchaseOrderDetail
		WHERE SalesOrderDetail.bookId = book.id
				AND PurchaseOrderDetail.bookId = book.id
				AND SalesOrderDetail.quantity IS NOT NULL
				AND PurchaseOrderDetail.quantity IS NOT NULL
		GROUP BY book.id
	)
GO
--4 FUNCTION
	CREATE FUNCTION UDFgroupByCategory()
	RETURNS TABLE
	AS
	RETURN(
		SELECT category, COUNT(title) AS [Number of Book]
		FROM book
		GROUP BY category
	)
GO
--5 FUNCTION
	CREATE FUNCTION UDFgetExpensiveBook()
	RETURNS INT
	AS
	BEGIN
		DECLARE @expensive INT
		SELECT @expensive = MAX(price)
		FROM book
		
		RETURN @expensive
	END
GO
/*------------------------------------------------------------------------------------------------------------------------------------
															TRIGGER
-------------------------------------------------------------------------------------------------------------------------------------*/