package control;

import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JOptionPane;

/**
 * @author TDI Team
 *
 */
public class TDIServer implements Runnable{
	ServerSocket server;

	public TDIServer(){			
		try {
			//Created a new serversocket instance, which is bound to the port 1234
			server = new ServerSocket(1234);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error! Couldn't initialize the server", "Error at Server initialization", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void run() {
		while(true){
			try {
				//Waits for a client connection, a new TUIO instance, which is started in a new Thread started here
				//This method also blocks this thread until a client connects to the server
				server.accept(); 				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}

	@Override
	protected void finalize() throws Throwable {
		server.close();
	}
	
	
}
