package controller;

import dao.UsuarioDAO;
import model.Usuario;

import java.sql.SQLException;
import java.util.Scanner;

public class Entrada {

    private static Scanner scan = new Scanner(System.in);
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public static void main(String[] args) {
        int opcion = 0;

        do {
            menu();
            opcion = scan.nextInt();

            switch (opcion){
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    try {
                        usuarioDAO.listarUsuarios();
                    } catch (SQLException e) {
                        System.out.println("Error al acceder a los datos");                    }
                    break;
                case 3:
                    System.out.println("Introduce corre");
                    String correo = scan.next();
                    System.out.println("Introduce contrasena");
                    String pass = scan.next();
                    try {
                        boolean existe = usuarioDAO.login(correo, pass);

                        if (existe){
                            System.out.println("Todo ok");
                        } else {
                            System.out.println("No existes");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error al comprobar datos");
                    }
                    break;
                case 4:
                    usuarioDAO.exportarUsuarios();
                    break;
            }

        } while (opcion < 1 || opcion > 4);

    }

    private static void menu(){



        System.out.println("------------------------");
        System.out.println("----------MENU----------");
        System.out.println("------------------------");
        System.out.println("1. Registrar Usuario");
        System.out.println("2. Listar Usuarios");
        System.out.println("3. Comprobar credenciales");
        System.out.println("4. Exportar usuario");
        System.out.println("5. Salir");
        System.out.println("------------------------");

    }

    public static void registrarUsuario(){
        System.out.println("Vamos a registrar un Usuario");
        System.out.println("Nombre: ");
        String nombre = scan.next();
        System.out.println("Apellido: ");
        String apellido = scan.next();
        System.out.println("correo: ");
        String correo = scan.next();
        System.out.println("Pass: ");
        String pass = scan.next();

        Usuario usuario = new Usuario(nombre, apellido, correo, pass);

        try {
            usuarioDAO.insertarUsuario(usuario);
        } catch (SQLException e) {
            System.out.println("Error al insertar usuario");
        }


    }
}
