package dao;

import database.DBConnection;
import database.DBSchema;
import model.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasajeroDAO {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean addPasajero(Pasajero pasajero) throws SQLException {

        connection = new DBConnection().getConnection();

        String query = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?,?,?)",
                DBSchema.TAB_PASAJEROS,
                DBSchema.NOMBRE, DBSchema.EDAD, DBSchema.PESO);

        ps = connection.prepareStatement(query);
        ps.setString(1, pasajero.getNombre());
        ps.setInt(2, pasajero.getEdad());
        ps.setDouble(3, pasajero.getPeso());


        boolean hecho = ps.execute();

        new DBConnection().closeConnection();

        return hecho;

    }

    public boolean eliminarPasajeroPorId(int id) throws SQLException {

        connection = new DBConnection().getConnection();

        String query = String.format("DELETE FROM %s WHERE %s = ?",
                DBSchema.TAB_PASAJEROS, DBSchema.ID);

        ps = connection.prepareStatement(query);
        ps.setInt(1, id);


        boolean hecho = ps.execute();

        new DBConnection().closeConnection();

        return  hecho;
    }

    public Pasajero consultarPasajeroPorId(int id) throws SQLException {

        connection = new DBConnection().getConnection();

        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                DBSchema.TAB_PASAJEROS, DBSchema.ID);

        ps = connection.prepareStatement(query);
        ps.setInt(1, id);

        rs = ps.executeQuery();

        Pasajero pasajero = null;
        while (rs.next()) {

            int identificador = rs.getInt(DBSchema.ID);
            String nombre = rs.getString(DBSchema.NOMBRE);
            int edad= rs.getInt(DBSchema.EDAD);
            double peso = rs.getDouble(DBSchema.PESO);

            pasajero = new Pasajero(identificador, nombre, edad, peso);
        }

        new DBConnection().closeConnection();

        return pasajero;
    }

    public void consultarPasajeros() throws SQLException {

        connection = new DBConnection().getConnection();

        String query = String.format("SELECT * FROM %s",
                DBSchema.TAB_PASAJEROS);

        ps = connection.prepareStatement(query);

        rs = ps.executeQuery();

        while (rs.next()) {

            int identificador = rs.getInt(DBSchema.ID);
            String nombre = rs.getString(DBSchema.NOMBRE);
            int edad = rs.getInt(DBSchema.EDAD);
            double peso = rs.getDouble(DBSchema.PESO);
            Pasajero pasajero = new Pasajero(identificador, nombre, edad, peso);
            System.out.println(pasajero);
        }

        new DBConnection().closeConnection();

    }

    public boolean actualizarEdad(int id, int edad) throws SQLException {

        connection = new DBConnection().getConnection();

        String query = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
                DBSchema.TAB_PASAJEROS,
                DBSchema.EDAD,
                DBSchema.ID);

        ps = connection.prepareStatement(query);
        ps.setInt(1, edad);
        ps.setInt(2, id);

        boolean hecho = ps.execute();

        new DBConnection().closeConnection();

        return hecho;
    }

    public boolean actualizarPeso(int id, double peso) throws SQLException {

        connection = new DBConnection().getConnection();

        String query = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
                DBSchema.TAB_PASAJEROS,
                DBSchema.PESO,
                DBSchema.ID);

        ps = connection.prepareStatement(query);
        ps.setDouble(1, peso);
        ps.setInt(2, id);

        boolean hecho = ps.execute();

        new DBConnection().closeConnection();

        return hecho;
    }
}
