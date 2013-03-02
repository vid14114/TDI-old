package control;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * @author TDI Team
 *
 */
public class TDIServer implements Runnable{
	ServerSocket server;
	BufferedWriter send;

	public TDIServer(){			
		try {
			//Created a new serversocket instance, which is bound to the port 1234
			server = new ServerSocket(2345);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error! Couldn't initialize the server", "Error at Server initialization", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void run() {
		//Waits for a client connection, a new TUIO instance, which is started in a new Thread started here
		//This method also blocks this thread until a client connects to the server
		while(true){
			try{
				Socket client = null;
				while(client == null || client.isClosed()){
					client = server.accept();
					if(JOptionPane.showConfirmDialog(null, "Client: "+client.getInetAddress().getHostName()+" would like to connect to this PC, Allow?","Confirm",JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION ){
						client.close();
					}
				}
				client.setKeepAlive(true);
				ObjectInputStream read = new ObjectInputStream(client.getInputStream());
				send = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));				
				while(client.isConnected()){
					String[] message = ((String)read.readObject()).split(";"); //For some reasons, only when the objectoutputstream sends messages, they can be read 
					System.out.println("Received message "+message[0]);
					switch(message[0].toLowerCase()){
					case "start": break;
					case "delete": break;
					case "rotate": break;
					case "tilt": break;
					case "move": break;
					default: send.write("Unknown command");
					}				
				}
			}
			catch(IOException e){
				e.printStackTrace();
				System.out.println(e.getCause().getMessage());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println(e.getCause().getMessage());
			}
		}
	}
	
	/**
	 * Used by the server to send commands to the TDIs 
	 */
	public void sendMessage(){
		//TODO Viktor, use this method to send messages to the TUIOs
	}

	@Override
	protected void finalize() throws Throwable {
		send.close();
		server.close();
	}
	
	
}
