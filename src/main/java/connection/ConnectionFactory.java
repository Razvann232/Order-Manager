package connection;

import java.sql.*;
import java.util.logging.Logger;

/**
 * Handles the main connection to the database.
 * @author Pinzariu Razvan-George
 */
public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/warehousedb";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static final ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * A private constructor - used only by this ConnectionFactory to create a single instance
     */
    private ConnectionFactory(){
        try{
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Creates a new connection based on the predefined data in the singleton instance.
     * @return
     * The new connection.
     */
    private Connection createConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Requests a new connection. (getter)
     * @return
     * The new connection.
     */
    public static Connection getConnection(){
            return singleInstance.createConnection();
    }

    /**
     * Closes the given connection.
     * @param connection
     * Connection to be closed.
     */
    public static void close(Connection connection){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Closes the given statement.
     * @param statement
     * Statement to be closed.
     */
    public static void close(Statement statement){
        try {
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Closes the given result set.
     * @param resultSet
     * Result set to be closed.
     */
    public static void close(ResultSet resultSet){
        try {
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
