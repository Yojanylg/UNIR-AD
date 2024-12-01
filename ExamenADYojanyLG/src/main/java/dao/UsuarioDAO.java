package dao;

import database.DBConnection;
import database.DBSchema;
import model.Usuario;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;

public class UsuarioDAO {


    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    // insertar
    public boolean insertarUsuario (Usuario usuario) throws SQLException {

        connection = new DBConnection().getConnection();

        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                                            DBSchema.TAB_USUARIO,
                                            DBSchema.COL_NOMBRE, DBSchema.COL_APELLIDO, DBSchema.COL_CORREO, DBSchema.COL_PASS);

        preparedStatement = connection.prepareStatement(query);
         preparedStatement.setString(1, usuario.getNombre());
         preparedStatement.setString(2, usuario.getApellido());
         preparedStatement.setString(3, usuario.getCorreo());
         preparedStatement.setString(4, usuario.getPass());

         return preparedStatement.execute();
    }

    // listar

    public void listarUsuarios() throws SQLException {

        connection = new DBConnection().getConnection();
        String query = String.format("SELECT * FROM %s", DBSchema.TAB_USUARIO);
        preparedStatement = connection.prepareStatement(query);

        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            String nombre = resultSet.getString(DBSchema.COL_NOMBRE);
            String apellidos = resultSet.getString(DBSchema.COL_APELLIDO);
            String correo = resultSet.getString(DBSchema.COL_CORREO);
            String pass = resultSet.getString(DBSchema.COL_PASS);

            System.out.println("Usuario: " + nombre + " " + apellidos + " Correo: " + correo + "la pass no te la muestro");
        }
    }

    public boolean login(String correo, String pass) throws SQLException {
        connection = new DBConnection().getConnection();

        preparedStatement = connection.prepareStatement(String.format("SELECT %s FROM %s WHERE %s=? AND %s=?",
                                                                        DBSchema.COL_CORREO, DBSchema.TAB_USUARIO, DBSchema.COL_CORREO , DBSchema.COL_PASS));
        preparedStatement.setString(1,correo);
        preparedStatement.setString(2,pass);
        resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }

    // este m'etodo hay que hacerlo de otra forma mejor

    public void exportarUsuarios(){
        File file = new File("src/main/java/resursos/usuarios.obj");
        ObjectOutputStream objectOutputStream = null;
        connection = new DBConnection().getConnection();
        String query = String.format("SELECT * FROM %s", DBSchema.TAB_USUARIO);

        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String nombre = resultSet.getString(DBSchema.COL_NOMBRE);
                String apellidos = resultSet.getString(DBSchema.COL_APELLIDO);
                String correo = resultSet.getString(DBSchema.COL_CORREO);
                String pass = resultSet.getString(DBSchema.COL_PASS);

                Usuario usuario = new Usuario(nombre, apellidos, correo, pass);
                objectOutputStream.writeObject(usuario);
            }


        } catch (IOException e) {
            System.out.println("Error al guardar objetos");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error al cerrar");
            }
        }

    }
}
