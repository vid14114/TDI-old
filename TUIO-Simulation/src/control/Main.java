package control;

import java.util.ArrayList;
import model.TUIO;
import view.Desk;

public class Main {

	public static ArrayList<TUIO> tuios=new ArrayList<>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Desk d = new Desk();
		// adding new TUIOs this will be moved away from this later
		// on - just test values for now
		tuios.add(new TUIO(1, 10, 10, 0));
		tuios.add(new TUIO(2, 80, 80, 0));
		tuios.add(new TUIO(3, 100, 160, 0));
	}
	
	

}
