package controler;

import dao.CocheDAO;
import model.Coche;

import java.util.Scanner;

public class Entrada {

    static CocheDAO cocheDAO = new CocheDAO();
    static Scanner scan = new Scanner(System.in);
    static int opcion;

    public static void main(String[] args) {

/*      Coches a grabar para iniciar la lista
        Coche c1 = new Coche(1, "123GHN", "Mercedez", "A1", "rojo");
        Coche c2 = new Coche(2, "234GHN", "Ford", "A1", "rojo");
        Coche c3 = new Coche(3, "345GHN", "Opel", "A1", "rojo");
        cocheDAO.addCoche(c1);
        cocheDAO.addCoche(c2);
        cocheDAO.addCoche(c3);
*/


        // Recuperar los posibles coches guardados en el fichero
        cocheDAO.recuperarListaCoches();

        do {

            switch (ejecutarMenu()) {
                case 1:
                    addCoche();
                    cocheDAO.listarCoches();
                    break;
                case 2:
                    borrarCochePorId();
                    cocheDAO.listarCoches();
                    break;
                case 3:
                    consultarPorId();
                    break;
                case 4:
                    cocheDAO.listarCoches();
                    break;
                case 5:
                    cocheDAO.guardarCSV();
                    break;
                default:
                    cocheDAO.guardarListaCoches();
                    scan.close();
                    break;
            }

        }while (opcion != 6);

    }

    // FUNCIONALIDAD DEL MENU

    /*
    El metodo pide por teclado un coche y lo guarda en la lista
     */
    public static void addCoche(){

        // Variables a pasar al constructor
        int id;
        String matricula, marca, modelo, color;

        // Lectura datos coche

        System.out.println("Id: ");
        id = scan.nextInt();
            if (cocheDAO.buscarPorId(id) != null){
                System.out.println("Este coche ya existe");
                return;
            }

        System.out.println("Matricula: ");
        matricula = scan.next();
            for (Coche coche : cocheDAO.getListaCoches()){
                if (coche.getMatricula().equalsIgnoreCase(matricula)){
                    System.out.println("Esta matricula ya esta registrada");
                    return;
                }
            }
        System.out.println("Marca: ");
        marca = scan.next();
        System.out.println("Modelo: ");
        modelo= scan.next();
        System.out.println("Color: ");
        color = scan.next();

        // Anadido del coche a la lista
        Coche aux = new Coche(id, matricula, marca, modelo, color);
        cocheDAO.addCoche(aux);

    }

    /*
    El metodo borra un coche buscandolo por una id que solicita por teclado
     */
    private static void borrarCochePorId() {
        System.out.println("Introduce id a borrar");
        int id = scan.nextInt();
        cocheDAO.borrarPorId(id);
    }

    /*
    El metodo busca un coche por una id que solicita por teclado
     */
    private static void consultarPorId() {

        System.out.println("Introduce la id del coche a buscar");
        int id = scan.nextInt();
        System.out.println(cocheDAO.buscarPorId(id).toString());

    }

    // NAVEGACION

    private static void pintarMenu(){

        System.out.println("----- Opciones -----");
        System.out.println("---------------------");
        System.out.println("1. Añadir nuevo coche");
        System.out.println("2. Borrar coche por id");
        System.out.println("3. Consulta coche por id");
        System.out.println("4. Listado de coches");
        System.out.println("5. Exportar coches a archivo CSV");
        System.out.println("6. Terminar el programa");
        System.out.println("---------------------");
        System.out.println("Elige una opcion");

    }

    private static int ejecutarMenu(){

        opcion = 0;

        do {

            pintarMenu();
            opcion = scan.nextInt();

        } while (opcion < 1 | opcion > 6);


        return opcion;

    }


}
