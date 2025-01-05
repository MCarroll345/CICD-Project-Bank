package ie.atu.bam;

import java.sql.*;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager(String host, String user, String password, String database) {
        try {
            // Establish connection
            String url = "jdbc:mysql://" + host + "/" + database;
            this.connection = DriverManager.getConnection(url, user, password);
            //System.out.println("Connected to MySQL database");
        } catch (SQLException e) {
            System.err.println("Error connecting to MySQL database: " + e.getMessage());
        }
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                //System.out.println("Disconnected from MySQL database");
            } catch (SQLException e) {
                System.err.println("Error disconnecting from MySQL database: " + e.getMessage());
            }
        }
    }

    public ResultSet selectTable(String tableName) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM " + tableName);
    }

    public ResultSet selectColumns(String tableName, String... columns) throws SQLException {
        Statement statement = connection.createStatement();
        String columnsStr = String.join(", ", columns);
        return statement.executeQuery("SELECT " + columnsStr + " FROM " + tableName);


    }

}
