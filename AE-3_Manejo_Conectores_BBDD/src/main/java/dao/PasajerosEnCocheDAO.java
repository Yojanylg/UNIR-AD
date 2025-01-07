package dao;

import database.DBConnection;
import database.DBSchema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasajerosEnCocheDAO {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean agregarPasajeroCoche (int idPasajero, int idCoche) throws SQLException {

        connection = new DBConnection().getConnection();

        String query = String.format("INSERT (%s, %s) INTO %s VALUES (?,?)",
                DBSchema.ID_COCHE, DBSchema.ID_PASAJERO,
                DBSchema.TAB_PASAJEROS_COCHE);

        ps = connection.prepareStatement(query);
        ps.setInt(1, idCoche);
        ps.setInt(2, idPasajero);

        boolean hecho = ps.execute();

        new DBConnection().closeConnection();

        return hecho;
    }
}
