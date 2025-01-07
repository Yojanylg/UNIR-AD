package database;

public interface DBSchema {

    String HOST = "127.0.0.1";
    String PORT = "3306";
    String DATABASE = "concesionario";
    String TAB_COCHES = "coches";
    String TAB_PASAJEROS = "pasajeros";
    String TAB_PASAJEROS_COCHE = "pasajeros_en_coche";

    String ID = "ID";
    String ID_COCHE = "id_coche";
    String ID_PASAJERO = "id_pasajero";

    String MARCA = "marca";
    String MATRICULA = "matricula";

    String NOMBRE = "nombre";
    String EDAD = "edad";
    String PESO = "peso";

}
