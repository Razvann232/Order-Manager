package dao;

import connection.ConnectionFactory;
import model.Order;
import model.Product;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Handles the connectivity in regard with the objects of type Order:
 * selecting, deleting ,finding, updating from the database.
 * @author Pinzariu Razvan-George
 */
public class OrderDao {

    private static final String insertStatementString = "INSERT INTO orders (clientId)"
            + " VALUES (?)";
    private final static String findStatementString = "SELECT * FROM orders where id = ?";
    private final static String deleteStatementString = "DELETE FROM orders WHERE id = ?";
    private final static String insertProductStatementString = "INSERT INTO products_order (idProduct, idOrder, quantity) VALUES(?,?,?)";
    private final static String generateBillStatementString = "SELECT idProduct as Product_ID, name as Product_Name, " +
            "products_order.quantity as Quantity, products_order.price as Price " +
            "FROM products_order " +
            "JOIN products ON idProduct = id " +
            "WHERE idOrder = ?";

    /**
     * Uses the given id to find an order in the database.
     * Creates a new order with the found data and returns it.
     * @param orderId
     * The unique id of the order in the database;
     * @return
     * The found order.
     */
    public static Order findById(int orderId) {
        Order toReturn;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, orderId);
            rs = findStatement.executeQuery();
            rs.next();

            int clientId = rs.getInt("clientId");
            float price = rs.getFloat("price");
            toReturn = new Order(clientId, price);
        } catch (SQLException e) {
            return null;
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    /**
     * Inserts a given order to a database. First the data from the object is
     * extracted, then an update query is executed.
     * @param order
     * The order which will be added.
     * @return
     * If the insert was successful, the new id of the order is returned.
     * In case of failure, -1 is returned.
     */
    public static int insert(Order order) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, order.getClientId());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            return -1;
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**
     * Deletes a given order from the database.
     * The id is extracted, then an update query is executed.
     * @param order
     * The order to be deleted.
     * @return
     * True or false depending whether the order was found or not.
     */
    public static boolean delete(Order order){
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, order.getOrderId());
            deleteStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    /**
     * Adds a product to an order. The operation is done by inserting an element in the
     * junction table products_order in the database.
     * @param order
     * The order to which the product belongs.
     * @param product
     * The purchased product.
     * @param quantity
     * Its given quantity.
     * @return
     * The new id of the product_order row, -1 in case of failure.
     */
    public static int insertProductOrder(Order order, Product product, int quantity) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertProductStatement = null;
        int insertedId = -1;
        try {
            insertProductStatement = dbConnection.prepareStatement(insertProductStatementString, Statement.RETURN_GENERATED_KEYS);
            insertProductStatement.setInt(1, product.getId());
            insertProductStatement.setInt(2, order.getOrderId());
            insertProductStatement.setInt(3, quantity);
            insertProductStatement.executeUpdate();

            ResultSet rs = insertProductStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            return -1;
        } finally {
            ConnectionFactory.close(insertProductStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**
     * Generates a .txt file containing the products purchased, their quantity and respective price
     * and the price of the entire order.
     * @param order
     * The order whose bill is generated.
     * @return
     * True or not depending on whether the bill was generated successfully or not.
     */
    public static boolean generateBill(Order order){
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement generateBillStatement = null;
        try {
            generateBillStatement = dbConnection.prepareStatement(generateBillStatementString, Statement.RETURN_GENERATED_KEYS);
            generateBillStatement.setInt(1, order.getOrderId());
            ResultSet rs = generateBillStatement.executeQuery();

            File billFile = new File("bill" + order.getOrderId() + ".txt");   // pentru scrierea in fisier
            FileWriter fw = new FileWriter(billFile);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("Product_ID | Product_Name | Quantity | Price");
            while(rs.next()) {
                pw.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getInt(3) + " | " + rs.getFloat(4));
            }
            pw.println("Total price: " + order.getPrice());
            pw.close();

            return true;
        } catch (SQLException | IOException e) {
            return false;
        } finally {
            ConnectionFactory.close(generateBillStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
