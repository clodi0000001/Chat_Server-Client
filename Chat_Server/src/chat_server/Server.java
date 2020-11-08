/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_server;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Sony
 */
public class Server {

	private int port;
	private List<PrintStream> clients;
	private ServerSocket server;

	public static void main(String[] args) throws IOException {
		new Server(12345).run();
	}

	public Server(int port) {
		this.port = port;
		this.clients = new ArrayList<PrintStream>();
	}

	public void run() throws IOException {
		server = new ServerSocket(port) {
                        @Override
			protected void finalize() throws IOException, Throwable {
                            try {
                                this.close();
                            } finally {
                                super.finalize();
                            }
			}
		};
		System.out.println("La porta 12345 è ora aperta.");

		while (true) {
			// accetta un nuovo cliente
			Socket client = server.accept();
			System.out.println("Collegamento stabilito con il client: " + client.getInetAddress().getHostAddress());
			
			// aggiungere un messaggio del cliente all'elenco
			this.clients.add(new PrintStream(client.getOutputStream()));
			
			// creare un nuovo thread per la gestione dei clienti
			new Thread(new ClientHandler(this, client.getInputStream())).start();
		}
	}



    void Messagio(String msg) {
    		for (PrintStream client : this.clients) {
			client.println(msg);
		}   
    }

   
}

