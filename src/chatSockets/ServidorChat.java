package chatSockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorChat {

    public static void main(String[] args) {
        ServerSocket servidor = null;

        try {
            servidor = new ServerSocket(666);
            System.out.println("Servidor Iniciado...");

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Nuevo cliente conectado: " + cliente.getInetAddress());

                HiloServidorChat hiloCliente = new HiloServidorChat(cliente);
                hiloCliente.start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        } finally {
            if (servidor != null) {
                try {
                    servidor.close();
                    System.out.println("Servidor detenido.");
                } catch (IOException e) {
                    System.out.println("Error al cerrar el servidor: " + e.getMessage());
                }
            }
        }
    }
}
