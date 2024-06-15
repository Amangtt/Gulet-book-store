USE BookStore1

/*------------------------------------------------------------------------------------------------------------------------------------
															PROCEDURE
-------------------------------------------------------------------------------------------------------------------------------------*/
--1 PROCEDURE
GO
	CREATE PROC SearchSupplier
	@search VARCHAR(MAX)
	AS
	BEGIN
		SELECT *
		FROM UDFsearchSupplier(@search)
	END
GO
--2 PROCEDURE
	CREATE PROC GetAllSupplier
	AS
	BEGIN
		SELECT *
		FROM UDFgetAllSupplier()
	END
GO
--3 PROCEDURE
	CREATE PROC InsertIntoSupplier
		@company_name varchar(MAX)
		,@tin_number varchar(20)
		,@address varchar(MAX)
		,@email VARCHAR(MAX)
		,@tel1 VARCHAR(15)
		,@tel2 VARCHAR(15)

	AS
	BEGIN
		
		DECLARE @id INT

		INSERT INTO Supplier (companyName, tinNumber, [address]) VALUES(@company_name ,@tin_number, @address)

		SET @id = @@IDENTITY
		INSERT INTO supplierTel VALUES (@tel1, @tel2, @id)

		INSERT INTO SupplierEmail values (@email, @id)
	END
GO
--4 PROCEDURE
	CREATE PROC UpdateSupplier
		@id INT
		,@company_name varchar(MAX)
		,@tin_number varchar(20)
		,@address varchar(MAX)
		,@email VARCHAR(MAX)
		,@tel1 VARCHAR(15)
		,@tel2 VARCHAR(15)
	AS
	BEGIN
		UPDATE Supplier 
		SET companyName = @company_name, tinNumber = @tin_number, [address] = @address
		WHERE id=@id

		UPDATE SupplierTel
		SET tel = @tel1, tel2 = @tel2
		WHERE supplierId = @id

		UPDATE SupplierEmail
		SET email = @email
		WHERE supplierId = @id
	END
	
GO
--5 PROCEDURE
	CREATE PROC DeleteSupplier
	@id INT
	AS
	BEGIN
		DELETE FROM Supplier
		WHERE Id=@id
	END
GO
/*------------------------------------------------------------------------------------------------------------------------------------
															FUNCTION
-------------------------------------------------------------------------------------------------------------------------------------*/
GO
--1 FUNCTION
	CREATE FUNCTION UDFsearchSupplier(@search VARCHAR(MAX))
	RETURNS TABLE
	AS
	RETURN(
		
		SELECT supplier.id, companyName, tinNumber, [address], tel, tel2, email
		FROM Supplier, supplierTel, supplierEmail
		WHERE Supplier.id = SupplierTel.supplierId
			  AND Supplier.id = SupplierEmail.supplierId
			  AND companyName LIKE '%' + @search +'%'
	)
GO
--2 FUNCTION
	CREATE FUNCTION UDFgetAllSupplier()
	RETURNS TABLE
	AS
	RETURN(
		SELECT supplier.id, companyName, tinNumber, [address], tel, tel2, email
		FROM Supplier, supplierTel, supplierEmail
		WHERE Supplier.id = SupplierTel.supplierId
			  AND Supplier.id = SupplierEmail.supplierId
	)
GO


-----------------------------------------------------------------------------------
SELECT * FROM SUPPLIER
insert INTO supplier values('My company', '1234567890', 'ezi sefer', default)

insert into supplierTel values ('0901945054', '0927006263', 5)

insert into supplierEmail values ('email', 5)

select * 
from supplier, supplierTel, supplierEmail
where supplier.id = supplierTel.supplierId
	  AND supplier.id = supplierEmail.supplierId

select * from supplierEmail
---------------------------------------------------------------------------------