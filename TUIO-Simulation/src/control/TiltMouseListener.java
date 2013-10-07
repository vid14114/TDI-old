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
	public int sendTilting(int degree)
	{
		return degree;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// If Button is clicked
		
		if(e.getActionCommand()=="tiltUp")
		{
			
			sendTilting(1);//set value used for sockets later (default value right now)
			d.setShowTitlt("up");//shows the sent value in the TDI simulation 
			d.socket.sendTilt(d.getIdJLabel(),"up"); // sends the changes value to the server
		}
		if(e.getActionCommand()=="tiltDown")
		{
			
			sendTilting(2);//set value used for sockets later (default value right now)
			d.setShowTitlt("down");//shows the sent value in the TDI simulation
			d.socket.sendTilt(d.getIdJLabel(),"down"); // sends the changes value to the server
		}
		if(e.getActionCommand()=="tiltRight")
		{
			
			sendTilting(3);//set value used for sockets later (default value right now)
			d.setShowTitlt("right");//shows the sent value in the TDI simulation 
			d.socket.sendTilt(d.getIdJLabel(),"right"); // sends the changes value to the server
		}
		if(e.getActionCommand()=="tiltLeft")
		{
			
			sendTilting(4);//set value used for sockets later (default value right now)
			d.setShowTitlt("left");//shows the sent value in the TDI simulation
			d.socket.sendTilt(d.getIdJLabel(),"left"); // sends the changes value to the server
		}
		
	}

}
