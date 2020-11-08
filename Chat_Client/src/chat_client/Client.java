/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_client;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
/**
 *
 * @author Sony
 */
public class Client {

	private String host;
	private int port;
	private String nickname;

	public static void main(String[] args) throws UnknownHostException, IOException {
		new Client("127.0.0.1", 12345).run();
	}

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() throws UnknownHostException, IOException {
		// collegare il client al server
		Socket client = new Socket(host, port);
		System.out.println("Il client si è connesso con successo al server!");

		// creare un nuovo thread per la gestione dei messaggi del server
		new Thread(new ReceivedMessagesHandler(client.getInputStream())).start();

		// chiede un nickname
		Scanner sc = new Scanner(System.in);
		System.out.print("Inserisci un nickname: ");
		nickname = sc.nextLine();

		// leggere i messaggi da tastiera e inviare al server
		System.out.println("Send messages: ");
		PrintStream output = new PrintStream(client.getOutputStream());
		while (sc.hasNextLine()) {
			output.println(nickname + ": " + sc.nextLine());
		}
		
		output.close();
		sc.close();
		client.close();
	}   
}
