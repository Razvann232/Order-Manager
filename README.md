# Order-Manager
Simulating a warehouse application with database operations via an UI.

The database must be created first, so that the application may work properly. The files for the database are included in the 'sql' folder. The connexion
is designed for a MySql database.

Clients can order products from the warehouse. The user (similar to a cashier) must select the client from the database (or register him if he is ordering for the first time) and the products the client desired. If all the products are available in the quantities desired by the client, the order will be placed and the database will be updated accordingly. A receipt will be printed, then another order can be placed.

All the information is taken from or updated into a MySql database. The receipt will be printed into a "bill.txt" file in the project directory.

A detailed JavaDoc is available by accessing the file javaDoc/index.html.

More information and details can be found in the documentation.

