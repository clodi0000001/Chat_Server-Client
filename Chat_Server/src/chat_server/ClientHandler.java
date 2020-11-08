/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_server;

import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author Sony
 */
public class ClientHandler implements Runnable {

	private final Server server;
	private final InputStream client;

	public ClientHandler(Server server, InputStream client) {
		this.server = server;
		this.client = client;
	}


        @Override
	public void run() {
		String msg;
		
            try ( //quando c'è un nuovo messaggio, trasmesso a tutti in questo caso solo 2 client
                    Scanner sc = new Scanner(this.client)) {
                while (sc.hasNextLine()) {
                    msg = sc.nextLine();
                    server.Messagio(msg);
                }
            }

       }    
}
