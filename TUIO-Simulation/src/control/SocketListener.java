package control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketListener implements Runnable{
	public Socket socket;
	public ObjectOutputStream outgoing;
	public ObjectInputStream incoming;
	public SocketListener(){
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			//Create socket connection
			   try{
			     socket = new Socket("10.0.0.1", 2345);
			     //Not used yet.
			     outgoing= new ObjectOutputStream(socket.getOutputStream());
			     incoming= new ObjectInputStream(socket.getInputStream());
			     while(socket.isConnected()){
			    	 String[] message = ((String)incoming.readObject()).split(";");
			    	 //Here we get the commands form the server.... 
			    	 switch(message[0].toLowerCase()){
			    	 }
			     }
			   } catch (UnknownHostException e) {
			     System.out.println("Unknown host: 10.0.0.1");
			     System.exit(1);
			   } catch  (IOException e) {
			     System.out.println("No I/O");
			     System.exit(1);
			   } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
