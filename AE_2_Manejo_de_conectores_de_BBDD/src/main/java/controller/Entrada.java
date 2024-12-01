package controller;

import java.util.Scanner;

public class Entrada {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        boolean menu = true;

        do {
            switch (menu()) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    System.out.println("Hasta pronto");
                    scan.close();
                    menu = false;
                    break;
            }
            ;
        } while (menu);

    }


    private static int menu() {

        int opcion = 0;

        do {

            System.out.println("---------------------------");
            System.out.println("---------- MENU -----------");
            System.out.println("---------------------------");
            System.out.println("1. Añadir nuevo coche");
            System.out.println("2. Borrar coche por ID");
            System.out.println("3. Consulta coche por ID");
            System.out.println("4. Modificar coche por ID");
            System.out.println("5. Listado de coches");
            System.out.println("6. Terminar el programa");
            System.out.println("---------------------------");
            System.out.println("------  ELIGE OPCION ------");
            System.out.println("---------------------------");

            if (scan.hasNextInt()) {
                opcion = scan.nextInt();
            } else {
                scan.next();
                System.out.println("Opcion ivalida");
                System.out.println("Intenta otra vez");
            }
        } while (opcion < 1 || opcion > 6);

        return opcion;
    }
}
