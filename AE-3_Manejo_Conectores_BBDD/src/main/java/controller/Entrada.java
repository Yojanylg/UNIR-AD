package controller;

import dao.CocheDAO;
import dao.PasajeroDAO;
import dao.PasajerosEnCocheDAO;
import database.DBSchema;
import model.Coche;
import model.Pasajero;

import java.sql.SQLException;
import java.util.Scanner;

public class Entrada {

    private static Scanner scanner = new Scanner(System.in);
    private static CocheDAO cocheDAO = new CocheDAO();
    private static PasajeroDAO pasajeroDAO = new PasajeroDAO();
    private static PasajerosEnCocheDAO pasajerosEnCocheDAO = new PasajerosEnCocheDAO();

    public static void main(String[] args) {
        int opcion;

        do{

            System.out.println("Bienvenido al gestor de viajes");
            System.out.println("1 - Insertar Coche");
            System.out.println("2 - Eliminar Coche por ID");
            System.out.println("3 - Consultar Coche por ID");
            System.out.println("4 - Modificar Coche por ID");
            System.out.println("5 - Listar coches");
            System.out.println("6 - Gestionar pasajeros");
            System.out.println("7 - Salir");

            System.out.println("Elige opcion: ");
            opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    agregarCoche();
                    break;
                case 2:
                    eliminarCochePorId();
                    break;
                case 3:
                    buscarCochePorId();
                    break;
                case 4:
                    modificarCochePorId();
                    break;
                case 5:
                    listarCoches();
                    break;
                case 6:
                    gestionarPasajeros();
                    break;
            }

        }while (opcion != 7);

    }

    private static void gestionarPasajeros() {

        int opcion;

        do{

            System.out.println("Bienvenido al gestor de pasajeros");
            System.out.println("1 - Insertar Pasajero");
            System.out.println("2 - Eliminar Pasajero por ID");
            System.out.println("3 - Consultar Pasajero por ID");
            System.out.println("4 - Listar pasajeros");
            System.out.println("5 - Agregar pasajero a coche");
            System.out.println("6 - Salir del menu pasajero");

            System.out.println("Elige opcion: ");

            opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    agregarPasajero();
                    break;
                case 2:
                    eliminarPasajeroPorId();
                    break;
                case 3:
                    buscarPasajeroPorId();
                    break;
                case 4:
                    listarPasajeros();
                    break;
                case 5:
                    agregarPasajeroCoche();
                    break;
            }

        }while (opcion != 5);

    }

    private static void agregarPasajeroCoche() {

        listarCoches();
        System.out.println("Selecciona Coche");
        int idCoche = scanner.nextInt();

        listarPasajeros();
        System.out.println("Selecciona Pasajero");
        int idPasajero = scanner.nextInt();

        try {
            pasajerosEnCocheDAO.agregarPasajeroCoche(idPasajero, idCoche);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private static void listarPasajeros() {

        try {
            pasajeroDAO.consultarPasajeros();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void buscarPasajeroPorId() {
        System.out.println("Introduce id a buscar");
        int id = scanner.nextInt();
        try {
            Pasajero pasajero = pasajeroDAO.consultarPasajeroPorId(id);
            if (pasajero != null){
                System.out.println(pasajero.toString());
            } else {
                System.out.println("Pasajero no encontrado");
            }
        } catch (SQLException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    private static void eliminarPasajeroPorId() {
        System.out.println("Introduce id a eliminar");
        try {
            pasajeroDAO.eliminarPasajeroPorId(scanner.nextInt());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void agregarPasajero() {
        try {
            pasajeroDAO.addPasajero(obtenerPasajero());
            System.out.println("Pasajero agragado correctamente");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static Pasajero obtenerPasajero() {
        String nombre;
        int edad;
        double peso;

        System.out.println("Vamos a introducir un Nuevo Pasajero");
        System.out.println("Introduce nombre");
        nombre = scanner.next();
        System.out.println("Introduce edad");
        edad = scanner.nextInt();

        while (true) {
            System.out.print("Introduce el peso: ");
            if (scanner.hasNextDouble()) {
                peso = scanner.nextDouble();
                break;
            } else {
                System.out.println("Error: Debes introducir un número decimal válido.");
                scanner.next(); // Consumir entrada inválida
            }
        }

        return new Pasajero(nombre, edad, peso);
    }

    private static void listarCoches() {

        try {
            cocheDAO.consultarCoches();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void modificarCochePorId() {

        // Mejoras: consultar si existe el id
        //          traerme el coche como objeto, realizar las modificaciones
        //          luego persistirlo en la bbdd

        System.out.println("Introduce id a modificar");
        int id = scanner.nextInt();
        System.out.println("Quieres modificar: Matricula (1) o Marca (2)");
        int opcion = scanner.nextInt();

        if (opcion == 1 ){

            System.out.println("Introduce nueva matricula");
            String nuevaMatricula = scanner.next();
            try {
                boolean hecho = cocheDAO.actualizarMatricula(id, nuevaMatricula);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        if (opcion == 2 ){

            System.out.println("Introduce nueva marca");
            String nuevaMarca = scanner.next();
            try {
                boolean hecho = cocheDAO.actualizarMarca(id, nuevaMarca);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }

    private static void buscarCochePorId() {
        System.out.println("Introduce id a buscar");
        int id = scanner.nextInt();
        try {
            Coche coche = cocheDAO.consultarPorId(id);
            if (coche != null){
                System.out.println(coche.toString());
            } else {
                System.out.println("Coche no encontrado");
            }
        } catch (SQLException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    private static void eliminarCochePorId() {
        System.out.println("Introduce id a eliminar");
        try {
            cocheDAO.eliminarPorId(scanner.nextInt());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void agregarCoche() {
        try {
            cocheDAO.addCoche(obtenerCoche());
            System.out.println("Coche agragado correctamente");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Coche obtenerCoche(){

        Coche aux;
        String marca;
        String matricula;

        System.out.println("Vamos a introducir un Nuevo Coche");
        System.out.println("Introduce marca");
        marca = scanner.next();
        System.out.println("Introduce matricula");
        matricula = scanner.next();

        return aux = new Coche(marca, matricula);
    }


}
