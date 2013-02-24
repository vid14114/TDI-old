package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Desk;

public class TiltMouseListener implements ActionListener{

	/**
	 * @param args
	 */
	
	Desk d;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Desk d=new Desk();
	}
	public int sendTilting(int id)
	{
		return id;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// If Button is clicked
		
		if(e.getActionCommand()=="tiltUp")
		{
			//set value used for sockets later (default value right now)
			sendTilting(1);
			//shows the sent value in the TDI simulation 
			d.setShowTitlt("1_U");
		}
		if(e.getActionCommand()=="tiltDown")
		{
			//set value used for sockets later (default value right now)
			sendTilting(2);
			//shows the sent value in the TDI simulation 
			d.setShowTitlt("1_D");
		}
		if(e.getActionCommand()=="tiltRight")
		{
			//set value used for sockets later (default value right now)
			sendTilting(3);
			//shows the sent value in the TDI simulation 
			d.setShowTitlt("1_R");
		}
		if(e.getActionCommand()=="tiltLeft")
		{
			//set value used for sockets later (default value right now)
			sendTilting(4);
			//shows the sent value in the TDI simulation 
			d.setShowTitlt("1_L");
		}
		
	}

}
