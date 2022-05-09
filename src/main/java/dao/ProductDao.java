package dao;

import connection.ConnectionFactory;
import model.Product;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the connectivity in regard with the objects of type Products:
 * selecting, deleting ,finding, updating from the database.
 * @author Pinzariu Razvan-George
 */
public class ProductDao {

    private static final String insertStatementString = "INSERT INTO products (name,quantity,weight,price)" // the statements which will be used in the query
            + " VALUES (?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM products WHERE id = ?";
    private final static String deleteStatementString = "DELETE FROM products WHERE id = ?";
    private final static String updateStatementString = "UPDATE products SET name = ?, quantity = ?, weight = ?, price = ? WHERE id = ?";
    private final static String countStatementString = "SELECT COUNT(*) FROM products";
    private final static String selectStatementString = "SELECT * FROM products";

    /**
     * Uses the given id to find a product in the database.
     * Creates a new product with the found data and returns it.
     * @param productId
     * The unique id of the product in the database;
     * @return
     * The found product.
     */
    public static Product findById(int productId) {
        Product toReturn;

        Connection dbConnection = ConnectionFactory.getConnection();    // receives the connection
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString); // prepares the statement
            findStatement.setLong(1, productId);    // sets unknown values
            rs = findStatement.executeQuery();  // executes the query
            rs.next();

            String name = rs.getString("name"); // receives the results
            int quantity = rs.getInt("quantity");
            float weight = rs.getFloat("weight");
            float price = rs.getFloat("price");
            toReturn = new Product(productId, name, quantity, weight, price);   // returns a new object with the found data
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
     * Inserts a given product to a database. First the data from the object is
     * extracted, then an update query is executed.
     * @param product
     * The product which will be added.
     * @return
     * If the insert was successful, the new id of the product is returned.
     * In case of failure, -1 is returned.
     */
    public static int insert(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, product.getName());
            insertStatement.setInt(2, product.getQuantity());
            insertStatement.setFloat(3, product.getWeight());
            insertStatement.setFloat(4, product.getPrice());
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
     * Deletes a given product from the database.
     * The id is extracted, then an update query is executed.
     * @param Product
     * The product to be deleted.
     * @return
     * True or false depending whether the product was found or not.
     */
    public static boolean delete(Product Product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, Product.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return true;
    }

    /**
     * Updates the product in the database. An auxiliary product is used that contains the new data.
     * All data fields are replaced.
     * @param pr
     * The product which will be updated
     * @param aux
     * An auxiliary product, containing the new data which will replace the old one.
     * @return
     * True or false depending on whether the update was successful or not.
     */
    public static boolean update(Product pr, Product aux){
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement updateStatement = null;
        try{
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, aux.getName());
            updateStatement.setInt(2, aux.getQuantity());
            updateStatement.setFloat(3, aux.getWeight());
            updateStatement.setFloat(4, aux.getPrice());
            updateStatement.setInt(5, pr.getId());
            updateStatement.executeUpdate();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * Selects all the products from the database.
     * @return
     * A list of objects containing all the products.
     */
    public static List<Object> selectAll(){
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement selectStatement;

        try{
            selectStatement = dbConnection.prepareStatement(selectStatementString, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = selectStatement.executeQuery();
            return createObject(rs);    // returns a list of object made using the found data
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Takes the result of a search query and creates an product for each
     * row found in the database.
     * @param rs
     * The result set of a select query.
     * @return
     * A list containing the found products.
     */
    private static List<Object> createObject(ResultSet rs){
        List<Object> list = new ArrayList<>();  // the list which will be returned
        Class c = Product.class;   // the class of the product
        Constructor[] ctors = c.getDeclaredConstructors();  // the constructors of the class
        Constructor ctor = null;

        for (Constructor constructor : ctors) {
            ctor = constructor;
            if (ctor.getGenericParameterTypes().length == 0) {  // we find the constructor with no parameters
                break;
            }
        }
        try{
            while(rs.next()){
                assert ctor != null;
                ctor.setAccessible(true);
                Product cl = (Product) ctor.newInstance();  // create a new Product
                for(Field field : c.getDeclaredFields()){
                    String fieldName = field.getName();
                    Object value = rs.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, c);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(cl, value);   // sets the found field with its value
                }
                list.add(cl);   // adds a new client to the list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Counts the number of products in the database.
     * @return
     * -1 in case of failure, the number of products in case of success
     */
    public static int countProducts(){
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement countStatement;

        try{
            countStatement = dbConnection.prepareStatement(countStatementString, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = countStatement.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
}
