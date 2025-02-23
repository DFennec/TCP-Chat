package chatInterfaz;

import chatSockets.*;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ChatInterfaz {

	String nombreUsuario=null;
	JTextArea cajaChat = new JTextArea();
	
	
	public void identificarUsuario() {

		while (nombreUsuario==null || nombreUsuario.isBlank()) {
			nombreUsuario =	JOptionPane.showInputDialog
					(null, "¿Con qué nombre quieres entrar al chat?", "Identifícate" ,JOptionPane.QUESTION_MESSAGE);
		}
	}
	
	public void escribirEnElChat(String mensaje) {
		cajaChat.append(mensaje);
	}
	
	public void desconectarCliente(ClienteChat cliente) {
		System.out.println("TO DO");
		cliente.desconectar();
	}
	
	public void enviarMensaje() {
		System.out.println("TO DO");
	}
	
	private JFrame frame;
	private JTextField mensajeTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatInterfaz window = new ChatInterfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChatInterfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		identificarUsuario();
		
		ClienteChat cc = new ClienteChat();
		
		frame = new JFrame("Chat sin GPT");
		frame.setResizable(false);
		frame.setBounds(525, 250, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			
			 public void windowClosing(WindowEvent e) {
				 String[] opciones={"Sí","No"};
				 if(JOptionPane.showOptionDialog(frame, "¿Seguro que quieres salir?\n Te desconectarás del chat.", "Desconexión al salir" ,JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,opciones,opciones[1])==JOptionPane.YES_OPTION)
				 {
						desconectarCliente(cc);
						frame.dispose();
				 }
			 }
		});
		frame.getContentPane().setLayout(null);
		
		mensajeTextField = new JTextField();
		mensajeTextField.setBounds(20, 232, 303, 21);
		frame.getContentPane().add(mensajeTextField);
		mensajeTextField.setColumns(10);
		
		JButton btnDesconectarse = new JButton("Desconectarse");
		
		btnDesconectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				desconectarCliente(cc);
				System.exit(0);
			}
		});
		
		btnDesconectarse.setBounds(333, 10, 93, 21);
		frame.getContentPane().add(btnDesconectarse);
		
		JPanel panelChat = new JPanel();
		panelChat.setBackground(Color.WHITE);
		panelChat.setLayout(new BoxLayout(panelChat, BoxLayout.Y_AXIS));
		

		cajaChat.setEditable(false);
		panelChat.add(cajaChat);

		JScrollPane scrollChat = new JScrollPane(panelChat);
		scrollChat.setBounds(20, 10, 303, 210);
		frame.getContentPane().add(scrollChat);
		
		
		JButton botonEnviar = new JButton("Enviar");
		
		botonEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*Añadir acciones para enviar mensaje al servidor e imprimirlo en la respuesta para todos*/
				escribirEnElChat(nombreUsuario+": "+ mensajeTextField.getText()+"\n");
				enviarMensaje();
				mensajeTextField.setText("");
			}
		});
		
		botonEnviar.setBounds(333, 232, 93, 21);
		frame.getContentPane().add(botonEnviar);
	}
}
