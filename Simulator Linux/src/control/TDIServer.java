package control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.Icon;
import model.TUIO;

import javax.swing.JOptionPane;

/**
 * @author TDI Team
 *
 */
public class TDIServer implements Runnable{
	private ServerSocket server;
	private ObjectOutputStream send;
	private ArrayList<TUIO> tuios = new ArrayList<TUIO>();
	ArrayList<Icon> icons;
	private Configuration config;
	private TDILogic logic;

	public TDIServer(Configuration config, ArrayList<Icon> icons){			
		try {
			//Created a new serversocket instance, which is bound to the port 1234
			server = new ServerSocket(2345);
			this.config = config;
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
				send = new ObjectOutputStream(client.getOutputStream());				
				while(client.isConnected()){ //Here the whole message handling is done
					String[] message = ((String)read.readObject()).split(";"); //For some reasons, only when the objectoutputstream sends messages, can they be read by the server
					switch(message[0].toLowerCase()){
					case "start": 
						tuios.add(new TUIO(message[1]));
						logic=new TDILogic(icons, tuios);
						new Thread(logic).start();
						break;
					case "delete": break;
					case "rotate": 	
						for(int i = 0; i<tuios.size(); i++)
							if(tuios.get(i).getId().equals(message[1])){
								if(message[2].equals("left"))
									tuios.get(i).rotateLeft();
								if(message[2].equals("right"))
									tuios.get(i).rotateRight();
							////This rotate algorithm is for taskbar mode, 
								//since we haven't programmed that yet, I have commented it out;								
								//	new Thread(new RotateHandler(config.icons, tuios.get(0))).start();
							}								
						break;
					case "tilt": break;						
					case "move":
						for(int i = 0; i<tuios.size(); i++)
							if(tuios.get(i).getId().equals(message[1]))
								tuios.get(i).setPos(message[2], message[3]);
						break;
					default: send.writeObject("Unknown command"); break;
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
	 * uses the method writeObject of the attribute send to send messages
	 * {@code ObjectOutputStream.writeObject(Object)} 
	 */
	public void sendMessage(String id, int x, int y, int minimalRotation){
		try {
			send.writeObject("MOVE;"+id+";"+x+";"+y+";"+minimalRotation);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		send.close();
		server.close();
	}
	
	
}
