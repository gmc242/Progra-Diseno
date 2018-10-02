package Logica.cliente.generacion;

public class GeneradorStrings {

    private BuilderString creadorStrings;

    public BuilderString getCreadorStrings() {
        return creadorStrings;
    }

    public void setCreadorStrings(BuilderString creadorStrings) {
        this.creadorStrings = creadorStrings;
    }

    public String getString() {
        return creadorStrings.getCadena();
    }

    public void generarString(int largo, String simbolos) {
        creadorStrings.buildString(largo, simbolos);
    }
}
