package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    public void createConnection () throws SQLException {
        String url = String.format("jdbc:mysql://%s:3306/%s", DBSchema.HOST, DBSchema.DATABASE);
        //JDBC -> jdbc:mysql://localhost/database
        connection = DriverManager.getConnection(url, "root", "");
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                createConnection();
            } catch (SQLException e) {
                System.out.println("Error al crear la conexion a la BD");
            }
        }

        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexion a la BD");        }
    }

}
