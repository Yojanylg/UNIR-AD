package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    public Connection getConnection() {

        if (connection == null){
            createConnection();
        }

        return connection;
    }

    private void createConnection(){

        String url = String.format("jdbc:mysql://%s:%s/%s",
                DBSchema.HOST, DBSchema.PORT, DBSchema.DATABASE);
        try {
            connection = DriverManager.getConnection(url, "root", "");
        } catch (SQLException e) {
            System.out.println("Error en la conexion con la BD");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }

    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("error al cerrar la conexion");
        } finally {
            connection = null;
        }
    }

}
