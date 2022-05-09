package dao;

import connection.ConnectionFactory;
import model.Client;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Handles the connectivity in regard with the objects of type Client:
 * selecting, deleting ,finding, updating from the database.
 * @author Pinzariu Razvan-George
 */
public class ClientDao {

    private static final String insertStatementString = "INSERT INTO clients (name,address,email,age)"
            + " VALUES (?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM clients where id = ?";
    private final static String deleteStatementString = "DELETE FROM clients WHERE id = ?";
    private final static String updateStatementString = "UPDATE clients SET name = ?, address = ?, email = ?, age = ? WHERE id = ?";
    private final static String selectStatementString = "SELECT * FROM clients";
    private final static String countStatementString = "SELECT COUNT(*) FROM clients";

    /**
     * Uses the given id to find a client in the database.
     * Creates a new client with the found data and returns it.
     * @param clientId
     * The unique id of the client in the database;
     * @return
     * The found client.
     */
    public static Client findById(int clientId) {
        Client toReturn;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, clientId);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("name");
            String address = rs.getString("address");
            String email = rs.getString("email");
            int age = rs.getInt("age");
            toReturn = new Client(clientId, name, address, email, age);
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
     * Inserts a given client to a database. First the data from the object is
     * extracted, then an update query is executed.
     * @param client
     * The client which will be added.
     * @return
     * If the insert was successful, the new id of the client is returned.
     * In case of failure, -1 is returned.
     */
    public static int insert(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, client.getName());
            insertStatement.setString(2, client.getAddress());
            insertStatement.setString(3, client.getEmail());
            insertStatement.setInt(4, client.getAge());
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
     * Deletes a given client from the database.
     * The id is extracted, then an update query is executed.
     * @param client
     * The client to be deleted.
     * @return
     * True or false depending whether the client was found or not.
     */
    public static boolean delete(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, client.getId());
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
     * Updates the client in the database. An auxiliary client is used that contains the new data.
     * All data fields are replaced.
     * @param cl
     * The client which will be updated
     * @param aux
     * An auxiliary client, containing the new data which will replace the old one.
     * @return
     * True or false depending on whether the update was successful or not.
     */
    public static boolean update(Client cl, Client aux){
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement updateStatement = null;
        try{
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, aux.getName());
            updateStatement.setString(2, aux.getAddress());
            updateStatement.setString(3, aux.getEmail());
            updateStatement.setInt(4, aux.getAge());
            updateStatement.setInt(5, cl.getId());
            updateStatement.executeUpdate();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * Selects all the clients from the database.
     * @return
     * A list of objects containing all the clients.
     */
    public static List<Object> selectAll(){
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement selectStatement = null;

        try{
            selectStatement = dbConnection.prepareStatement(selectStatementString, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = selectStatement.executeQuery();
            return createObject(rs);
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
     * A list containing the found clients.
     */
    private static List<Object> createObject(ResultSet rs){
        List<Object> list = new ArrayList<>();
        Class<Client> c = Client.class;
        Constructor[] ctors = c.getDeclaredConstructors();
        Constructor ctor = null;

        for (Constructor constructor : ctors) {
            ctor = constructor;
            if (ctor.getGenericParameterTypes().length == 0) {
                break;
            }
        }
        try{
            while(rs.next()){
                assert ctor != null;
                ctor.setAccessible(true);
                Client cl = (Client) ctor.newInstance();
                for(Field field : c.getDeclaredFields()){
                    String fieldName = field.getName();
                    Object value = rs.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, c);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(cl, value);   //cl.setfieldName(value)
                }
                list.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Counts the number of clients in the database.
     * @return
     * -1 in case of failure, the number of clients in case of success
     */
    public static int countClients(){
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
