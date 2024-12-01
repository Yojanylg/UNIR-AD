package controller;

import dao.AlumnoDAO;
import dao.ProfesorDAO;
import model.Alumno;
import model.Profesor;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Scanner;

public class Entrada {

    private static AlumnoDAO alumnoDAO = new AlumnoDAO();
    private static ProfesorDAO profesorDAO = new ProfesorDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion = 0;

        do {

            opcion = menu();

            switch (opcion){
                case 1:
                    insertarProfesor();
                    break;
                case 2:
                    insertarAlumno();
                    break;
                case 3:
                    for (Profesor profesor : profesorDAO.getProfesores()){
                        System.out.println(profesor.toString());
                    }
                    for (Alumno alumno : alumnoDAO.getAlumnos()){
                        System.out.println(alumno.toString());
                    }
                    break;
                case 4:
                    for (Profesor profesor : profesorDAO.getProfesores()){
                        System.out.println(profesor.toString());
                    }
                    break;
                case 5:
                    for (Alumno alumno : alumnoDAO.getAlumnos()){
                        System.out.println(alumno.toString());
                    }
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
            }

        } while (opcion != 10);


    }

    private static int menu(){

        int opcion = -1;

        do {
            System.out.println("---------MENU--------");
            System.out.println("1. Insertar un profesor");
            System.out.println("2. Insertar un alumno");
            System.out.println("3. Mostrar todos los datos");
            System.out.println("4. Mostrar profesores");
            System.out.println("5. Mostrar alumnos");
            System.out.println("6. Buscar alumno");
            System.out.println("7. Buscar profesor");
            System.out.println("8. Actualizar profesor");
            System.out.println("9. Dar de baja alumnos");
            System.out.println("10. Salir");
            System.out.println("Introduce opcion");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                if (opcion < 1 || opcion > 10){
                    System.out.println("Solo valores entre 1 y 10");
                }
            } else {
                System.out.println("Opcion incorrecta, vuelve a intentarlo");
                scanner.next();
            }

        } while (opcion == -1);

        return opcion;
    }

    private static void insertarProfesor(){
        System.out.println("Vamos a insertar un profesor a la BBDD");


        System.out.println("Inserta calificacion");
        int rating = scanner.nextInt();

        System.out.println("Inserta edad");
        int age = scanner.nextInt();

        System.out.println("Inserta nombre");
        String name = scanner.nextLine();

        System.out.println("Inserta genero");
        String gender = scanner.nextLine();

        System.out.println("Inserta correo");
        String email = scanner.nextLine();

        System.out.println("Inserta telefono");
        String phone = scanner.nextLine();

        System.out.println("Inserta asignaturas");
        String subjects = scanner.nextLine();

        System.out.println("Inserta titulos");
        String title = scanner.nextLine();

        profesorDAO.insertarProfesor(new Profesor(rating, age, name, gender, email, phone, subjects, title));
    }

    private static void insertarAlumno(){
        System.out.println("Vamos a insertar un alumno a la BBDD");


        System.out.println("Inserta calificacion");
        int rating = scanner.nextInt();

        System.out.println("Inserta edad");
        int age = scanner.nextInt();

        System.out.println("Inserta nombre");
        String name = scanner.nextLine();

        System.out.println("Inserta genero");
        String gender = scanner.nextLine();

        System.out.println("Inserta correo");
        String email = scanner.nextLine();

        System.out.println("Inserta telefono");
        String phone = scanner.nextLine();

        System.out.println("Inserta calificacion");
        String subjects = scanner.nextLine();

        System.out.println("Inserta curso");
        String higher_grade = scanner.nextLine();

        profesorDAO.insertarProfesor(new Profesor(rating, age, name, gender, email, phone, subjects, higher_grade));
    }
}
