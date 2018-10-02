package Logica.cliente.Builder;

public abstract class BuilderString {

    protected String cadena;
    protected int largoString;

    public int getLargoString() {
        return largoString;
    }

    public void setLargoString(int largoString) {
        this.largoString = largoString;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public abstract void buildString(int largo, String alfabeto);

}
