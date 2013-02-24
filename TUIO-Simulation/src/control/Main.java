package control;

import java.util.HashMap;

import model.TUIO;
import view.Desk;

public class Main {

	public static HashMap<Integer, TUIO> tuios;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Desk d = new Desk();
		// adding new TUIOs this will be moved away from this later
		// on - just test values for now
		// id , x, y, rotation
		tuios=new HashMap<Integer, TUIO>();
		tuios.put(1, new TUIO(1,10,10,0));
		tuios.put(2, new TUIO(2,80,80,0));
		tuios.put(3, new TUIO(3,100,160,0));
	}
	
	

}
