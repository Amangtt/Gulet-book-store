--INSERT INTO BOOK
USE BookStore1
INSERT INTO Book VALUES ('Art of War', 'Zen Lessons', 'Startegy', 189.99, 'Anubha Printers', 3, '0987654330', 10)
						,('Simplified English Grammer', 'Workneh Kebede','Eductional', 27.50, 'Mega Publishing & Distribution', 5,'0987654322', 15)
						,('Emegua', 'Alemayehu Eshete', 'Fiction', 85.00, 'TZ publishing', 1,'0987654324', 13)
						,('Cash Flow', 'Robert Kiyosaki', 'Financial', 175.00, 'Plata publishing', 2, '0987654325', 18)
						,('Atomic Habits', 'James Clear', 'Financial', 170.00, 'Candace Edutaiment', 2,'0987654326', 18)
						,('Think Like A Man', 'Steve Harvey', 'Relationship', 160.00, 'Mega Publisher', 2,'0987654327', 7)
						,('Hand Book of Applied Mathematics II With Dynamics', 'Begashaw Moltot', 'Educational', 140.00, 'Adevert Publisher', 5,'0987654328', 9)
						,('Physic', 'Solomon Beneberu', 'Educational', 69.99, 'Branna Printing Enterprise', 4, '0987654329' , 9)
						,('Rich Dad Poor Dad', 'Robert Kiyosaki', 'Financial', 174.99, 'Plata publishing', 3,'0987654320',  17 )
--INSERT INTO EMPLOYEE
INSERT INTO Employee VALUES ('Simon', 'G/hiwot','M', 6000.00, NULL,'Admin', 1)
--INSERT INTO EMPLOYEE TEL
INSERT INTO EmployeeTel (tel, tel2, employeeId)VALUES ('0901945054','0927006263',1)
--INSERT INTO USER
INSERT INTO [User](userName,[password], employeeId) VALUES ('Admin', 'Admin', 1)
--INSERT INTO supplier
insert INTO supplier values('My company', '1234567890', 'ezi sefer', default)
--INSERT INTO SUPPPLIER TEL
INSERT INTO supplierTel VALUES ('0901945054', '0927006263', 1)
--INSERT INTO SUPPLIER
INSERT INTO supplierEmail VALUES ('email', 1)
--INSERT INTO SALES ORDER
INSERT INTO SalesOrder (employeeId, customer) VALUES (1, 'Simon')
--INSERT INTO SALES ORDER DETAIL
INSERT INTO SalesOrderDetail(salesId, bookId, quantity, discount) VALUES(1, 1, 2, 0.1)
--INSERT INTO PURCHASE ORDER
INSERT INTO PurchaseOrder (supplierId, employeeId) VALUES (1, 1)
--INSERT INTO PURCHASE ORDER DETAIL
INSERT INTO PurchaseOrderDetail (purchaseId, bookId, quantity) VALUES (1,1, 4)