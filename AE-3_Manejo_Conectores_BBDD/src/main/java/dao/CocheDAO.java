package dao;

import database.DBConnection;
import database.DBSchema;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CocheDAO {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean addCoche(Coche coche) throws SQLException {

        connection = new DBConnection().getConnection();

        String query = String.format("INSERT INTO %s (%s, %s) VALUES (?,?)",
                DBSchema.TAB_COCHES,
                DBSchema.MARCA, DBSchema.MATRICULA);

        ps = connection.prepareStatement(query);
        ps.setString(1, coche.getMarca());
        ps.setString(2, coche.getMatricula());

        boolean hecho = ps.execute();

        new DBConnection().closeConnection();

        return hecho;

    }

    public boolean eliminarPorId(int id) throws SQLException {

        connection = new DBConnection().getConnection();

        String query = String.format("DELETE FROM %s WHERE %s = ?",
                DBSchema.TAB_COCHES, DBSchema.ID);

        ps = connection.prepareStatement(query);
        ps.setInt(1, id);


        boolean hecho = ps.execute();

        new DBConnection().closeConnection();

        return  hecho;
    }

    public Coche consultarPorId(int id) throws SQLException {

        connection = new DBConnection().getConnection();

        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                DBSchema.TAB_COCHES, DBSchema.ID);

        ps = connection.prepareStatement(query);
        ps.setInt(1, id);

        rs = ps.executeQuery();

        Coche coche = null;
        while (rs.next()) {

            int identificador = rs.getInt(DBSchema.ID);
            String marca = rs.getString(DBSchema.MARCA);
            String matricula = rs.getString(DBSchema.MATRICULA);

            coche = new Coche(identificador, marca, matricula);
        }

        new DBConnection().closeConnection();

        return coche;
    }

    public void consultarCoches() throws SQLException {

        connection = new DBConnection().getConnection();

        String query = String.format("SELECT * FROM %s",
                DBSchema.TAB_COCHES);

        ps = connection.prepareStatement(query);

        rs = ps.executeQuery();

        while (rs.next()) {

            int identificador = rs.getInt(DBSchema.ID);
            String marca = rs.getString(DBSchema.MARCA);
            String matricula = rs.getString(DBSchema.MATRICULA);
            Coche coche = new Coche(identificador, marca, matricula);
            System.out.println(coche);
        }

        new DBConnection().closeConnection();

    }

    public boolean actualizarMatricula(int id, String matricula) throws SQLException {

        connection = new DBConnection().getConnection();

        String query = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
                DBSchema.TAB_COCHES,
                DBSchema.MATRICULA,
                DBSchema.ID);

        ps = connection.prepareStatement(query);
        ps.setString(1, matricula);
        ps.setInt(2, id);

        boolean hecho = ps.execute();

        new DBConnection().closeConnection();

        return hecho;
    }

    public boolean actualizarMarca(int id, String marca) throws SQLException {

        connection = new DBConnection().getConnection();

        String query = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
                DBSchema.TAB_COCHES,
                DBSchema.MARCA,
                DBSchema.ID);

        ps = connection.prepareStatement(query);
        ps.setString(1, marca);
        ps.setInt(2, id);

        boolean hecho = ps.execute();

        new DBConnection().closeConnection();

        return hecho;
    }
}
