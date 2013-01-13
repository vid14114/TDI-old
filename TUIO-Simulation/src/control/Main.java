package control;

import model.TUIO;
import view.Desk;

public class Main {

	public static TUIO[] tuios=new TUIO[2];
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Desk d = new Desk();
		// adding new TUIOs this will be moved away from this later
		// on - just test values for now
		tuios[0]=new TUIO(1, 10, 10, 0);
		tuios[1]=new TUIO(2, 80, 80, 0);
	}

}
