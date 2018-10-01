package Logica.server;

import Logica.DTOAlfabeto;
import Logica.DTOAlgoritmos;

import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ListenerClient implements Runnable {

    private Controlador controlador;
    private Socket cliente;

    public ListenerClient(Controlador controlador, Socket cliente){
        this.controlador = controlador;
        this.cliente = cliente;
    }

    public void run(){
        try{
            // Intenta mantener el socket abierto
            cliente.setKeepAlive(true);

            while(!cliente.isClosed() && cliente.isConnected()){
                if(cliente.getInputStream().available() <= 0) {
                    Thread.sleep(100);
                }
                else{
                    // Recibe el primer objeto
                    ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
                    DTOAlgoritmos dtoAlgoritmos = (DTOAlgoritmos)in.readObject();
                    // Recibe el segundo objeto
                    DTOAlfabeto dtoAlfabeto = (DTOAlfabeto)in.readObject();

                    // Realiza la funcionalidad
                    controlador.procesarPeticion(dtoAlgoritmos, dtoAlfabeto);

                    // Envía los DTO's de vuelta
                    enviarDTOs(dtoAlgoritmos, dtoAlfabeto);
                }
            }

            cliente.close();

        }catch (Exception e){
            System.out.println("Error escribiendo en un socket especifico");
            // ¿Manda mensaje?
        }
    }

    // Función que se encarga de enviar los DTOs a un cliente.
    private void enviarDTOs(DTOAlgoritmos dtoAlgoritmos, DTOAlfabeto dtoAlfabeto) throws Exception{
        // Genera los streams de salida
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bs);

        // Escribe los dos objetos serializados
        out.writeObject(dtoAlgoritmos);
        out.writeObject(dtoAlfabeto);

        // Cierra el stream de objetos
        out.close();

        // Genera el buffer con los objetos
        byte[] bytes = bs.toByteArray();

        // Escribe el buffer
        cliente.getOutputStream().write(bytes);
    }
}
