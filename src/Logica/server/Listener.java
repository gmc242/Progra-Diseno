package Logica.server;

import Logica.DTOAlfabeto;
import Logica.DTOAlgoritmos;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Listener implements Runnable {

    private boolean activo;
    private Controlador controlador;

    public Listener(Controlador controlador){
        this.controlador = controlador;
        activo = true;
    }

    public void run(){
        try
        {
            // Inicializa valores "globales" para el comportamiento
            ServerSocket socket = new ServerSocket(4444);

            while(activo){
               try{
                   // Agrega un timeout para que socket.accept() no bloquee sin interrupciones
                   socket.setSoTimeout(100);

                   // Acepta la petición
                   Socket cliente = socket.accept();

                   // Envia los DTO's
                   enviarDTOs(cliente, controlador.getDTOAlgoritmos(), controlador.getDTOAlfabeto());

                   // Maneja la conexión
                   ListenerClient listenerClient = new ListenerClient(controlador, cliente);
                   Thread hilo = new Thread(listenerClient);
                   hilo.start();
               }catch (SocketTimeoutException e){
                   continue;
               }
            }

            System.out.println("Estoy cerrando el proceso");
        }
        catch (Exception e){
            // Deberia generar un mensaje de error usando el thread original
            System.out.println("Error en el listener");
        }
    }

    public void setActivo(boolean activo){ this.activo = activo; }

    // Función que se encarga de enviar los DTOs a un cliente.
    private void enviarDTOs(Socket cliente, DTOAlgoritmos dtoAlgoritmos, DTOAlfabeto dtoAlfabeto) throws Exception{
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
