USE BookStore

/*------------------------------------------------------------------------------------------------------------------------------------
															PROCEDURE
-------------------------------------------------------------------------------------------------------------------------------------*/
GO
--1 PROCEDURE
	CREATE PROC GetAllSale
	AS
	BEGIN
		SELECT SalesOrder.id AS [orderId], SalesOrderDetail.id, discount, quantity, customer, employeeId, bookId, invoice
		FROM SalesOrder, SalesOrderDetail
		WHERE SalesOrder.id = salesId
	END
GO
--2 PROCEDRE
	CREATE PROC SearchByInvoice 
	@invoice INT
	AS
	BEGIN
  
		SELECT  SalesOrder.id AS [orderId], SalesOrderDetail.id, discount, quantity, customer, employeeId, bookId, invoice
		FROM SalesOrder, SalesOrderDetail
		WHERE SalesOrder.id = salesId
			  AND invoice = @invoice
	END
GO
--3 PROCEDURE
	CREATE PROC SearchByDay
	@day INT
	AS
	BEGIN
		SELECT SalesOrder.id AS [orderId], SalesOrderDetail.id, discount, quantity, customer, employeeId, bookId, invoice
		FROM SalesOrder, SalesOrderDetail
		WHERE SalesOrder.id = salesId
			  AND DAY([day]) = @day
	END
GO
--4 PROCEDURE
	CREATE PROC InsertIntoSale
	@employeeId INT
	,@customer VARCHAR(50)
	,@bookId INT
	,@quantity INT
	,@discount INT
	,@firstTime INT
	,@return INT OUTPUT
	AS
	BEGIN
		DECLARE @salesId INT, @amount INT
		
		SELECT @amount = copies
		FROM Book

		IF(@firstTime = 0)
			BEGIN
				IF(@quantity >= @amount)
					BEGIN
						SET @return = 0
					END
				ELSE
					BEGIN
						INSERT INTO SalesOrder (employeeId, customer)VALUES(@employeeId, @customer)
						SET @salesId = @@IDENTITY
						INSERT INTO SalesOrderDetail (salesId, bookId, quantity, discount) VALUES (@salesId, @bookId, @quantity, @discount)
						SET @return = 1
					END
			END
		ELSE
			BEGIN
				SELECT @salesId = id
				FROM SalesOrder
				ORDER BY id ASC
				IF(@quantity >= @amount)
					BEGIN
						SET @return = 0
					END 
				ELSE
					BEGIN
						INSERT INTO SalesOrderDetail (salesId, bookId, quantity, discount) VALUES (@salesId, @bookId, @quantity, @discount)
						SET @return = 1						
					END
			END
	END
GO
--5 PROCEDURE
	CREATE PROC UpdateSale
	@salesOrderId INT 
	,@bookId INT
	,@quanity FLOAT
	,@discount FLOAT
	,@return INT OUTPUT
	AS
	BEGIN
		
		DECLARE @amount INT, @id INT, @copies INT

		SELECT @amount = quantity, @id = bookId
		FROM SalesOrderDetail
		WHERE id = @salesOrderId

		UPDATE Book
		SET copies += @amount
		WHERE id = @id
		
		SELECT @copies = copies
		FROM Book
		WHERE id = @bookId 

		IF(@copies < @quanity)
			BEGIN
				SET @return = 0
			END
		ELSE
			BEGIN
				UPDATE SalesOrderDetail
				SET quantity = @quanity, bookId = @bookId, discount = @discount
				WHERE id = @salesOrderId

				UPDATE Book
				SET copies -= @quanity
				WHERE id = @bookId
				SET @return = 1
			END
	END
GO
--6 PROCEDURE
	CREATE PROC DeleteSale
	@id INT
	AS
	BEGIN
		DELETE FROM SalesOrderDetail
		WHERE id = @id
	END
GO
--7 PROCEDURE
	CREATE PROC Inventory
	AS
	BEGIN
		SELECT *
		FROM UDFgetSaleAndPurchase()
	END
GO
--8 PROCEDURE 
	CREATE PROCEDURE GetTotalSaleOfDay
	AS
	BEGIN
		SELECT *
		FROM UDFtotalSaleofDay()
	END
GO
/*------------------------------------------------------------------------------------------------------------------------------------
															FUNCTION
-------------------------------------------------------------------------------------------------------------------------------------*/
--1 FUNCTION
	CREATE FUNCTION UDFsaleInvoice()
	RETURNS INT
	AS
	BEGIN
		DECLARE @invoice INT
		DECLARE invoice_cursor  CURSOR SCROLL FOR

		SELECT invoice FROM salesOrder
		ORDER BY invoice ASC

		OPEN invoice_cursor

		FETCH LAST FROM invoice_cursor INTO @invoice
			
		IF(@invoice IS NULL)
			BEGIN
				SET @invoice = 10000001
			END
		ELSE
			BEGIN
				SET @invoice += 1
			END
		CLOSE invoice_cursor
		DEALLOCATE invoice_cursor
		RETURN @invoice
	END
GO
--2 FUNCTION
	CREATE FUNCTION UDFpriceOfItem(@id INT)
	RETURNS FLOAT
	AS
	BEGIN
		DECLARE @total FLOAT = 0

		SELECT @total = (price * quantity) - (price * quantity * discount)
		FROM book, SalesOrderDetail
		WHERE book.id = SalesOrderDetail.bookId

		RETURN @total
	END
GO
--3 FUNCTION
	CREATE FUNCTION UDFtotalSale(@salesOrderId INT)
	RETURNS FLOAT
	AS
	BEGIN
		DECLARE @total FLOAT = 0

		SELECT @total = ( SUM(dbo.UDFpriceOfItem(bookId)) + SUM(dbo.UDFpriceOfItem(bookId)) * 0.15)
		FROM SalesOrder, SalesOrderDetail, book
		WHERE SalesOrder.id = @salesOrderId
				AND SalesOrder.id = salesId

		RETURN @total
	END
GO
--4 FUNCTION
	CREATE FUNCTION UDFtotalSaleofDay()
	RETURNS TABLE
	AS
	RETURN(
		SELECT dbo.UDFtotalSale(id) AS [Before VAT], dbo.UDFcalculateVAT(dbo.UDFtotalSale(id)) AS VAT , dbo.UDFcalculateVAT(dbo.UDFtotalSale(id)) + dbo.UDFtotalSale(id) AS [Grand Total]
		FROM SalesOrder
		WHERE [day] = DAY(GETDATE())
	)
	GO
--5 FUNCTION
	CREATE FUNCTION UDFcalculateVAT(@totalBeforeVAT FLOAT)
	RETURNS FLOAT
	AS
	BEGIN
		DECLARE @VAT FLOAT
		SET @VAT = @totalBeforeVAT * 0.15
		RETURN @VAT 
	END

GO
/*------------------------------------------------------------------------------------------------------------------------------------
															TRIGGER
-------------------------------------------------------------------------------------------------------------------------------------*/