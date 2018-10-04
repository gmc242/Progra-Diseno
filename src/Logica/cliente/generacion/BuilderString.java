package Logica.cliente.generacion;

// Rol de AbstractBuilder
public abstract class BuilderString {

    protected String cadena;

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public abstract void buildString(int largo, String alfabeto);

}
