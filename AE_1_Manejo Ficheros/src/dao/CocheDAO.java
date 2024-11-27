package dao;

import model.Coche;

import java.io.*;
import java.util.ArrayList;

public class CocheDAO {

    private static ArrayList<Coche> listaCoches = new ArrayList<Coche>();
    private static String path = "src/resources/coches.obj";
    private static String pathCSV = "src/resources/coches.csv";

    public void addCoche(Coche coche){
        listaCoches.add(coche);
    }



    public Coche buscarPorId(int id){
        Coche aux = null;
        for (Coche coche : listaCoches){
            if (coche.getId() == id){
                aux = coche;
            }
        }
        return aux;
    }

    public void borrarPorId(int id){
        Coche aux = null;
       for (Coche coche : listaCoches){
           if (coche.getId() == id){
               aux = coche;
           }
       }
       if (aux != null){
           listaCoches.remove(aux);
       }
    }

    public ArrayList<Coche> getListaCoches() {
        return listaCoches;
    }

    public void listarCoches(){
        for (Coche coche : listaCoches){
            System.out.println(coche.toString());
        }
    }

    public void guardarListaCoches(){

        ObjectOutputStream objectOutputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(path),true));
            for (Coche coche : listaCoches){
                objectOutputStream.writeObject(coche);
            }
        } catch (IOException e) {
            System.out.println("Error escritura");
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error al cerrar flujo de escritura");
            }
        }


    }

    public void recuperarListaCoches(){
        File file = new File(path);

        if (file.exists()){

            ObjectInputStream objectInputStream= null;

            try {

                objectInputStream = new ObjectInputStream(new FileInputStream(file));

                Coche coche = null;
                while ((coche = (Coche) objectInputStream.readObject()) != null){
                    listaCoches.add(coche);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error lectura de archivo");
            } finally {
                try {
                    objectInputStream.close();
                } catch (IOException | NullPointerException e) {
                    System.out.println("Error al cerrar la lectura");
                } catch (ClassCastException e){
                    System.out.println("Error en el casteo");

                }
            }


        }
    }

    public void guardarCSV(){

        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(new File(pathCSV)));
            for (Coche coche : listaCoches){
                bufferedWriter.write(coche.csv());
            }

        } catch (IOException e) {
            System.out.println("Error al guardar csv");
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error al cerrar escritura");
            }
        }


    }

}
