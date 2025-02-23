package chatSockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteChat {

    static final String host = "localhost";
    static final int puerto = 666;
	private static boolean conectado;
    
    public ClienteChat() {
    	this.conectado=true;
    }
    
    public void desconectar() {
    	this.conectado=false;
    }
    
    public static void main(String[] args) {
        try (Socket cliente = new Socket(host, puerto);
             PrintWriter fsalida = new PrintWriter(cliente.getOutputStream(), true);
             BufferedReader fentrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
             BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado al servidor.");

  /*          String cadena, cadenaSvr;
            do {
                System.out.print("Teclee una cadena de caracteres: ");
                cadena = in.readLine();

                // Enviar la cadena al servidor
                fsalida.println(cadena);

                // Leer la respuesta del servidor
                cadenaSvr = fentrada.readLine();
                if (cadenaSvr != null) {
                    System.out.println("Cadena recibida por el servidor: " + cadenaSvr);
                } else {
                    System.out.println("El servidor ha cerrado la conexión.");
                    break;
                }

            } while (conectado);
             
            System.out.println("Finalizado el envío de tareas al servidor.");
*/
        } catch (IOException e) {
            System.out.println("CLIENTE: Error en la conexión o comunicación con el servidor."+ e.getMessage());
            e.printStackTrace();
        }
    }
}
