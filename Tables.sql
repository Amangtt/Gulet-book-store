CREATE DATABASE BookStore
GO
USE BookStore
GO
CREATE TABLE Employee(

	id INT PRIMARY KEY IDENTITY
	,fName VARCHAR(10) NOT NULL
	,lName VARCHAR (10) NOT NULL
	,gender VARCHAR(1)
	,salary FLOAT NOT NULL
	,managerId INT
	,typeOfUser VARCHAR(10) NOT NULL
	,[status] INT NOT NULL DEFAULT 1
)
CREATE TABLE EmployeeTel(

	tel VARCHAR(15) NOT NULL
	,tel2 VARCHAR(15) NOT NULL
	,employeeId INT NOT NULL 
	,CONSTRAINT fk_employee_tel FOREIGN KEY(employeeId) REFERENCES Employee(id)
	,PRIMARY KEY(tel, employeeId)
)
CREATE TABLE [User](

	userName VARCHAR(15) UNIQUE NOT NULL
	,[password] VARCHAR(16) NOT NULL
	,id INT PRIMARY KEY IDENTITY 
	,employeeId INT NOT NULL 
	,CONSTRAINT fk_user FOREIGN KEY(employeeId) REFERENCES Employee(id)
)
CREATE TABLE Book(

	id INT PRIMARY KEY IDENTITY
	,title VARCHAR(200) NOT NULL
	,author VARCHAR(50) NOT NULL
	,category VARCHAR(50) NOT NULL
	,price FLOAT NOT NULL DEFAULT 0
	,publisher VARCHAR(200) NOT NULL
	,edition INT NOT NULL
	,isbn VARCHAR(50) UNIQUE NOT NULL
	,copies INT NOT NULL DEFAULT 0

)
CREATE TABLE Supplier(
	
	id INT PRIMARY KEY IDENTITY
	,companyName VARCHAR(200) NOT NULL 
	,tinNumber VARCHAR(20) NOT NULL UNIQUE
	,[address] VARCHAR(200) NOT NULL
	,[status] INT NOT NULL DEFAULT 1
)
CREATE TABLE SupplierTel(

	tel VARCHAR(15) NOT NULL
	,tel2 VARCHAR(15) NOT NULL
	,supplierId INT NOT NULL
	,CONSTRAINT fk_supplier FOREIGN KEY(supplierId) REFERENCES Supplier(id)
	,PRIMARY KEY(tel, supplierId)
)
CREATE TABLE SupplierEmail(

	email VARCHAR(20) NOT NULL
	,supplierId INT NOT NULL
	,CONSTRAINT fk_supplier_email FOREIGN KEY(supplierId) REFERENCES Supplier(id)
	,PRIMARY KEY(email, supplierId)
)
CREATE TABLE SalesOrder(
	
	id INT PRIMARY KEY IDENTITY
	,invoice INT UNIQUE NULL
	,employeeId INT NOT NULL
	,customer VARCHAR(50) NOT NULL
	,[day] AS(GETDATE())
	,CONSTRAINT fk_sales_Order FOREIGN KEY (employeeId) REFERENCES Employee(id)
)
CREATE TABLE SalesOrderDetail(

	id INT PRIMARY KEY IDENTITY
	,salesId INT NOT NULL
	,bookId INT NOT NULL
	,quantity INT NOT NULL
	,discount FLOAT NOT NULL
	,CONSTRAINT fk_book_sales FOREIGN KEY (bookId) REFERENCES Book(id)
	,CONSTRAINT fk_sales_Order_Detail FOREIGN KEY (salesId) REFERENCES SalesOrder(id)
)
CREATE TABLE PurchaseOrder(
	
	id INT PRIMARY KEY IDENTITY
	,invoice INT UNIQUE NULL
	,employeeId INT NOT NULL
	,supplierId INT NOT NULL
	,[day] AS(GETDATE())
	,CONSTRAINT fk_purchase_Order FOREIGN KEY (employeeId) REFERENCES Employee(id)
	,CONSTRAINT fk_supplier_id FOREIGN KEY(supplierId) REFERENCES Supplier(id)
)
CREATE TABLE PurchaseOrderDetail(

	id INT PRIMARY KEY IDENTITY
	,purchaseId INT NOT NULL
	,bookId INT NOT NULL
	,quantity INT NOT NULL
	,CONSTRAINT fk_book_purchase FOREIGN KEY (bookId) REFERENCES Book(id)
	,CONSTRAINT fk_purchase_Order_Detail FOREIGN KEY (purchaseId) REFERENCES PurchaseOrder(id)
)
CREATE TABLE purchaseList(
	
	id INT PRIMARY KEY IDENTITY
	,bookId INT NOT NULL
	,CONSTRAINT purchase_list FOREIGN KEY(bookId) REFERENCES Book(id)
)
ALTER TABLE Employee
ADD CONSTRAINT fk_employee
FOREIGN KEY(managerId) REFERENCES Employee(id)