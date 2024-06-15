USE BookStore
GO
--1 TRIGGER 
CREATE TRIGGER ChangeStatusOfEmployee ON Employee
INSTEAD OF DELETE
AS
BEGIN
	DECLARE @id INT
	SELECT @id = D.id
	FROM DELETED D

	UPDATE Employee
	SET [status] = 0
	WHERE id = @id
END
GO
--2 TRIGGER
CREATE TRIGGER InsertInvoiceIntoSale ON SalesOrder
AFTER INSERT
AS
BEGIN
	DECLARE @id INT
	SELECT @id = I.id
	FROM INSERTED I
	
	UPDATE SalesOrder
	SET invoice = dbo.UDFsaleInvoice()
	WHERE id = @id
END
GO
--3 TRIGGER
CREATE TRIGGER InsertIntoPurchaseList ON Book
AFTER INSERT
AS
BEGIN
	DECLARE @id INT
	SELECT @id = I.id
	FROM INSERTED I

	INSERT INTO purchaseList VALUES (@id)
END
GO
--4 TRIGGER
CREATE TRIGGER [DELETE_ON_SUPPLIER] ON SUPPLIER
INSTEAD OF DELETE
AS 
BEGIN
	DECLARE @id INT
	SELECT @id = D.id
	FROM DELETED D

	UPDATE Supplier
	SET [status] = 0
	WHERE id=@id

END
GO
--5 TRIGGER
CREATE TRIGGER [INSERT_INVOICE] ON PurchaseOrder
AFTER INSERT
AS
BEGIN

	DECLARE @id INT
	SELECT @id = I.id
	FROM INSERTED I

	UPDATE PurchaseOrder
	SET invoice = dbo.UDFpurchaseInvoice()
	WHERE id = @id

END