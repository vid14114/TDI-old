package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import model.TUIO;
import view.Desk;

public class Main {

	public static HashMap<Integer, TUIO> tuios;
	public static int automaticGeneratedId = 1;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Desk d = new Desk();
		Thread t = new Thread(new SocketListener());
		t.start();
		// adding new TUIOs this will be moved away from this later
		// on - just test values for now
		// id , x, y, rotation
		tuios=new HashMap<Integer, TUIO>();
	}
	



}
