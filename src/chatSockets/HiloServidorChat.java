package chatSockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidorChat extends Thread {

	BufferedReader fentrada;
	PrintWriter fsalida;
	Socket socket = null;

	public HiloServidorChat(Socket s) {
		// Socket recibido del servidor
		socket = s;
		// streams de salida y entrada
		try {
			fsalida = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("HILO SERVIDOR: Problema con el envío de datos.");// Gestión de errores
			e.printStackTrace();
		}

		try {
			fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.out.println("HILO SERVIDOR: Problema con la recepción de datos.");// Gestión de errores
			e.printStackTrace();
		}
	}

	public void run() {
		String cadena = "";
		System.out.println("Comunicación con el cliente: " + socket.toString());

		while (!cadena.trim().equals("*")) {
			try {
				cadena = fentrada.readLine();
				System.out.println(cadena);
			} catch (IOException e) {
				System.out.println("HILO SERVIDOR: Error en al recibir datos."+e.getMessage());
			}
			fsalida.println(cadena.trim().toUpperCase());
		}

		System.out.println("Conexión Finalizada con el cliente: " + socket.toString());

	}

}
