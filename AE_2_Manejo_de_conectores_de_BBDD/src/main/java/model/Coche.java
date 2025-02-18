package model;

public class Coche {

    private int id;
    private String matricula;
    private String marca;

    public Coche() {
    }

    public Coche(String matricula, String marca){
        this.matricula = matricula;
        this.marca = marca;
    }

    public int getId() {
        return id;
    }


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
