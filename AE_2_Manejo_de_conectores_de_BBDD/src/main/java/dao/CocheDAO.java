package dao;

import database.DBConnection;
import database.SchenaDB;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CocheDAO {

    public boolean addCoche(Coche coche){

        String matricula = coche.getMatricula();
        String marca = coche.getMarca();
        boolean hecho = false;

        Connection connection = new DBConnection().getConnection();

        String query = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                SchenaDB.TAB_COCHES,
                SchenaDB.COL_MARCA, SchenaDB.COL_MATRICULA);

        try {

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, marca);
            ps.setString(2, matricula);

            hecho = ps.execute();

        } catch (SQLException e) {

            System.out.println("Error en la conexion");

        }

        return hecho;

    }

    public void borrarPorId(int id){

        Connection connection = new DBConnection().getConnection();

        String query = String.format("DELETE FROM %s WHERE %s = ?",
                SchenaDB.TAB_COCHES,
                SchenaDB.COL_ID);

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            int row = ps.executeUpdate();

            System.out.println("Registros eliminados: "+ row);

        } catch (SQLException e) {
            System.out.println("Error al ejecutar query");
        }


    }

    public Coche buscarPorId(int id){

        Connection connection = new DBConnection().getConnection();
        Coche coche = null;

        String query = String.format("SELECT %s, %s FROM %s WHERE %s = ?",
                                        SchenaDB.COL_MARCA, SchenaDB.COL_MATRICULA,
                                        SchenaDB.TAB_COCHES,
                                        SchenaDB.COL_ID);

        try {

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();

            coche = new Coche(resultSet.getString(SchenaDB.COL_MATRICULA), resultSet.getString(SchenaDB.COL_MARCA));

        } catch (SQLException e) {
            System.out.println("Error en la consulta");
        }

        return coche;

    }

    /*
    public void modificarCochePorId(int id){

    }

    public void listarCoches(){

    }*/
}
