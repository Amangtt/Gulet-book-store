USE BookStore

/*------------------------------------------------------------------------------------------------------------------------------------
															PROCEDURE
-------------------------------------------------------------------------------------------------------------------------------------*/
--1 PROCEDURE
GO
	CREATE PROC GetAllPurchase
	AS
	BEGIN
		SELECT PurchaseOrder.id AS [orderId], purchaseOrderDetail.id, quantity, supplierId, bookId, invoice, employeeId
		FROM PurchaseOrder, PurchaseOrderDetail
		WHERE PurchaseOrder.id = purchaseId
	END
GO
--2 PROCEDUER
	CREATE PROC SearchPurchaseByInvocie
	@invoice INT
	AS
	BEGIN
  
		SELECT PurchaseOrder.id AS [orderId], purchaseOrderDetail.id, quantity, supplierId, bookId, invoice, employeeId
		FROM PurchaseOrder, PurchaseOrderDetail
		WHERE PurchaseOrder.id = purchaseId
			  AND invoice = @invoice
	END
GO
--3 PROCEDURE
	CREATE PROC SearchPurchaseByDay
	@day INT
	AS
	BEGIN
		SELECT PurchaseOrder.id AS [orderId], purchaseOrderDetail.id, quantity, supplierId, bookId, invoice, employeeId
		FROM PurchaseOrder, PurchaseOrderDetail
		WHERE PurchaseOrder.id = purchaseId
			  AND DAY([day]) = @day
	END
GO
--4 PROCEDURE
	CREATE PROC InsertIntoPurchase
	@employeeId INT
	,@supplierId INT
	,@bookId INT
	,@quantity INT
	,@price INT 
	,@firstTime INT
	,@return INT OUTPUT
	AS
	BEGIN
		DECLARE @exist INT
		SELECT @exist = bookId
		FROM purchaseList
		IF(@exist IS NOT NULL)
			BEGIN
				IF(@firstTime = 0)
					BEGIN
						INSERT INTO PurchaseOrder (employeeId, supplierId) VALUES (@employeeId, @supplierId)
						DECLARE @id INT
						SET @id = @@IDENTITY
						INSERT INTO PurchaseOrderDetail(purchaseId, bookId, quantity) VALUES (@id, @bookId, @quantity)
						UPDATE Book
						SET price = @price, copies = @quantity
						WHERE id = @bookId
					END
				ELSE
					BEGIN
						DECLARE @purchaseId INT
						SELECT @purchaseId = id
						FROM PurchaseOrder
						ORDER BY id ASC
						INSERT INTO PurchaseOrderDetail (purchaseId, bookId, quantity) VALUES (@purchaseId, @bookId, @quantity)
						UPDATE Book
						SET price = @price, copies = @quantity
						WHERE id = @bookId
					END
				SET @return = 1
			END
		ELSE
			BEGIN
				SET @return = 0
			END
	END
GO
--5 PROCEDURE
	CREATE PROC UpdatePurchase
	@id INT
	,@bookId INT
	,@quantity INT
	,@price INT 
	,@return INT OUTPUT
	AS
	BEGIN
		DECLARE @oldBookId INT
		SELECT @oldBookId = bookId
		FROM PurchaseOrderDetail

		UPDATE Book
		SET copies -= @quantity, price = 0
		WHERE id = @oldBookId

		UPDATE Book
		SET copies += @quantity, price = @price
		WHERE id = @bookId

		UPDATE PurchaseOrderDetail
		SET quantity = @quantity
		WHERE id = @id
	END
GO
--6 PROCEDURE
	CREATE PROC DeletePurchase
	@id INT
	AS
	BEGIN
		DELETE FROM PurchaseOrderDetail
		WHERE id = @id
	END
GO
/*------------------------------------------------------------------------------------------------------------------------------------
															FUNCTION
-------------------------------------------------------------------------------------------------------------------------------------*/
--1 FUNCTION
GO
	CREATE FUNCTION UDFpurchaseInvoice()
	RETURNS INT
	AS
	BEGIN
		DECLARE @invoice INT
		DECLARE invoice_cursor  CURSOR SCROLL FOR

		SELECT invoice FROM purchaseOrder
		ORDER BY invoice ASC

		OPEN invoice_cursor

		FETCH LAST FROM invoice_cursor INTO @invoice
			
		IF(@invoice IS NULL)
			BEGIN
				SET @invoice = 100001
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
	CREATE FUNCTION UDFpriceOfItemPurchased(@id INT)
	RETURNS FLOAT
	AS
	BEGIN
		DECLARE @total FLOAT = 0

		SELECT @total = (price * quantity)
		FROM book, PurchaseOrderDetail
		WHERE book.id = PurchaseOrderDetail.bookId

		RETURN @total
	END
GO
--3 FUNCTION
	CREATE FUNCTION UDFtotalPurchase( @purchaseOrderId INT)
	RETURNS FLOAT
	AS
	BEGIN
		DECLARE @total FLOAT = 0

		SELECT @total = SUM(dbo.UDFpriceOfItemPurchased(bookId))
		FROM PurchaseOrder, PurchaseOrderDetail, book
		WHERE PurchaseOrder.id = @purchaseOrderId
				AND PurchaseOrder.id = purchaseId

		RETURN @total
	END
GO
--4 FUNCTION
	CREATE FUNCTION UDFcalculateVatForPurchased(@purchaseOrderId INT)
	RETURNS FLOAT
	AS
	BEGIN
		DECLARE @vat FLOAT = 0

		SET @vat = 0.15 * dbo.UDFtotalPurchase(@purchaseOrderId)

		RETURN @vat
	END
GO
--5 FUNCTION
	CREATE FUNCTION UDFgrandTotal(@purchaseOrderId INT)

	RETURNS FLOAT
	AS
	BEGIN
		DECLARE @total FLOAT = 0

		SET @total = dbo.UDFtotalPurchased(@purchaseOrderId) + dbo.UDFcalculateVatPurchased(@purchaseOrderId)
			
		RETURN @total
	END
GO
/*------------------------------------------------------------------------------------------------------------------------------------
															TRIGGER
-------------------------------------------------------------------------------------------------------------------------------------*/