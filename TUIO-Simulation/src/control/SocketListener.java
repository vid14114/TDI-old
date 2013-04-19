package control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import model.TUIO;

public class SocketListener implements Runnable{
	public Socket socket;
	public ObjectOutputStream outgoing;
	public ObjectInputStream incoming;
	public String ipOfServer;
	public SocketListener(){
		ipOfServer="192.168.0.1"; //dummy ip right now
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
			    	 case "move":
			    		 if( getTuioThroughID(message[1])!=null)
			    		 {
				    		 getTuioThroughID(message[1]).setxPos(Integer.parseInt(message[2]));  
				    		 getTuioThroughID(message[1]).setyPos(Integer.parseInt(message[3]));	
			    		 }
			    		 break;
			    	 }
			     }
			   } catch (UnknownHostException e) {
			     System.out.println("Unknown host: "+ipOfServer);
			     //System.exit(1);
			   } catch  (IOException e) {
			     System.out.println("No I/O");
			     //System.exit(1);
			   } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public TUIO getTuioThroughID(String id)
	{
		for(TUIO t:Main.tuios.values())
		{
			if(t.getId()==Integer.parseInt(id))
			{
				return t;
			}
		}
		return null;
		
	}
	// all send methods 
	public void sendMove(int id, int x, int y){
		try {
			outgoing.writeObject("move;"+id+";"+x+";"+y);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendTilt(int id, String direction){
		try {
			outgoing.writeObject("tilt;"+id+";"+direction);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendRotate(int id, double degrees){
		try {
			outgoing.writeObject("rotate;"+id+";"+degrees);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendStart(int id){
		try {
			outgoing.writeObject("start;"+id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendDelete(int id){ // same function as add
		try {
			outgoing.writeObject("delete;"+id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
