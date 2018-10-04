package Logica.cliente.generacion;

// Rol de director en el patrón Builder
public class GeneradorStrings {

    private BuilderString creadorStrings;

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
