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
	public String ipOfServer;
	public SocketListener(){
		ipOfServer="10.0.0.1"; //dummy ip right now
	}
	@Override
	public void run() {
		while(true){
			   try{
			     socket = new Socket(ipOfServer, 2345);		//Create socket connection
			     outgoing= new ObjectOutputStream(socket.getOutputStream()); //Not used yet.
			     incoming= new ObjectInputStream(socket.getInputStream());
			     while(socket.isConnected()){
			    	 String[] message = ((String)incoming.readObject()).split(";");
			    	 switch(message[0].toLowerCase()){ 	 //Here we get the commands form the server.... 
			    	 //Logic will be implented here.
			    	 }
			     }
			   } catch (UnknownHostException e) {
			     System.out.println("Unknown host: "+ipOfServer);
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
