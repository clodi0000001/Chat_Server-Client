/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_client;

import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author Sony
 */
public class ReceivedMessagesHandler implements Runnable  {

	private final InputStream server;

	public ReceivedMessagesHandler(InputStream server) {
		this.server = server;
	}

	@Override
	public void run() {
            try ( // ricevere i messaggi del server e stampare a schermo
                    Scanner s = new Scanner(server)) {
                while (s.hasNextLine()) {
                    System.out.println(s.nextLine());
                }
            }
	}       
}
