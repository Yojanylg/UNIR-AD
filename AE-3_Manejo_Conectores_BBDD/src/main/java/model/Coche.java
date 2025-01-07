package model;

public class Coche {

    int id;
    String marca;
    String matricula;

    public Coche() {
    }

    public Coche(int id, String marca, String matricula) {
        this.id = id;
        this.marca = marca;
        this.matricula = matricula;
    }

    public Coche(String marca, String matricula) {
        this.marca = marca;
        this.matricula = matricula;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
