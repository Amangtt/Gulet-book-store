USE BookStore

/*------------------------------------------------------------------------------------------------------------------------------------
															PROCEDURE
-------------------------------------------------------------------------------------------------------------------------------------*/
GO
--1 PROCEDURE
	CREATE PROC Search_Employee--search employee using first name
		@fName VARCHAR(50)
	AS
	BEGIN
		SELECT * 
		FROM UDFsearchEmployee(@fName)
	END
GO
--2 PROCEDURE
	CREATE PROC GetAll_Employee-- selects all the employee information
	AS
	BEGIN
		SELECT * 
		FROM UDFgetAllEmployee()
	END
GO
--3 PROCEDURE
	CREATE PROC Authenticate-- selects user name and password
	@userName VARCHAR(MAX), @password VARCHAR(MAX)
	AS
	BEGIN
		SELECT * 
		FROM UDFautenticate(@userName, @password)
	END
GO
--4 PROCEDURE
	CREATE PROC Delete_Employee--deletes employee
	@id INT
	AS
	BEGIN
		DELETE FROM Employee
		WHERE id = @id
	END
GO
--5 PROCEDURE
	CREATE PROC Inser_Employee--inserts data into employee
		@fName VARCHAR(MAX), @lName VARCHAR(50), @gender CHAR(1), @salary FLOAT, @managerId INT, 
		@typeofUser VARCHAR(10), @userName VARCHAR(MAX), @password VARCHAR(MAX), @tel1 VARCHAR(15), @tel2 VARCHAR(15)
	AS
	BEGIN
		DECLARE @id INT
		--INSERTING INTO EMPLOYEE
		INSERT INTO Employee (fName, lName, gender, salary, managerId, typeOfUser) VALUES (@fName, @lName, @gender, @salary, @managerId, @typeofUser)
		SET @id = @@IDENTITY--GETTING THE EMPLOYEE ID
		--INSERTING INTO USER
		INSERT INTO [User] (userName, [password], employeeId) VALUES (@userName, @password, @id)
		--INSERTING INTO EMPLOYEE TEL
		INSERT INTO EmployeeTel VALUES(@tel1, @tel2, @id)
	END
GO
-- 6 PROCEDURE
	CREATE PROC Update_Employee--updates empl0yee
		@fName VARCHAR(MAX), @lName VARCHAR(50), @gender CHAR(1), @salary FLOAT, @managerId INT, @typeofUser VARCHAR(10), 
		@userName VARCHAR(MAX), @password VARCHAR(MAX), @tel1 VARCHAR(15), @tel2 VARCHAR(15), @id INT
	AS
	BEGIN
		DECLARE @t1 VARCHAR(15), @t2 VARCHAR(15), @counter INT = 1
		IF(@managerId = 0)
			BEGIN
				SET @managerId = NULL
			END
		UPDATE Employee
		SET fName = @fName, lName = @lName, gender = @gender, salary = @salary, managerId = @managerId, typeOfUser = @typeofUser
		WHERE id = @id

		UPDATE [User]
		SET userName = @userName, [password] = @password
		WHERE employeeId = @id

		UPDATE EmployeeTel
		SET tel = @tel1, tel2 = @tel2
		WHERE employeeId = @id
	END
GO
/*------------------------------------------------------------------------------------------------------------------------------------
															FUNCTION
-------------------------------------------------------------------------------------------------------------------------------------*/
--1 FUNCTION
GO
	CREATE FUNCTION UDFgetAllEmployee()
	RETURNS TABLE
	AS
	RETURN(
		SELECT Employee.id, fName, lName, gender, salary, managerId, typeOfUser, [status], tel, tel2, userName, [password]
		FROM Employee, EmployeeTel, [User]
		WHERE Employee.id = EmployeeTel.employeeId
				AND Employee.id = [User].employeeId
				AND [status] = 1
	)
	--2 FUNCTION
GO
	CREATE FUNCTION UDFsearchEmployee(@fName VARCHAR(50))
	RETURNS TABLE
	AS
	RETURN(
		SELECT Employee.id, fName, lName, gender, salary, managerId, typeOfUser, [status], tel, tel2, userName, [password]
		FROM Employee, EmployeeTel, [User]
		WHERE Employee.id = EmployeeTel.employeeId
				AND Employee.id = [User].employeeId
				AND fName LIKE '%'+@fName+'%'
	)
	--3 FUNCTION
GO
	CREATE FUNCTION UDFautenticate(@userName VARCHAR(50), @password VARCHAR(MAX))
	RETURNS TABLE
	AS
	RETURN(
		SELECT Employee.id, fName, lName, gender, salary, managerId, typeOfUser, [status], tel, tel2, userName, [password]
		FROM Employee, [User], EmployeeTel
		WHERE	Employee.id = EmployeeTel.employeeId 
			    AND Employee.id = [User].employeeId
				AND userName = @userName
				AND [password] = @password
	)
	--4 FUNCTION
GO
	CREATE FUNCTION UDFupdateFname(@name VARCHAR(50))
	RETURNS VARCHAR(50)
	AS
	BEGIN
		SET @name = LTRIM(RTRIM(@name))
		SET @name = UPPER(SUBSTRING(@name, 1,1)) + SUBSTRING(@name, 2, LEN(@name))
		RETURN @name
	END
	--5 FUNCTION
GO
	CREATE FUNCTION UDFtotalSalary()
	RETURNS FLOAT
	AS
	BEGIN
		DECLARE @total FLOAT = 0;

		SELECT @total = SUM(salary)
		FROM Employee

		RETURN @total
	END
GO
/*------------------------------------------------------------------------------------------------------------------------------------
															TRIGGER
-------------------------------------------------------------------------------------------------------------------------------------*/