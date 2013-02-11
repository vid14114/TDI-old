package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import view.Desk;

public class UniversalActionListener implements ActionListener{

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
	}
	public int sendTilting(int id)
	{
		System.out.println(id);
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
//			d.setShowTitlt("1_U");
		}
		if(e.getActionCommand()=="tiltDown")
		{
			//set value used for sockets later (default value right now)
			sendTilting(2);
			//shows the sent value in the TDI simulation 
//			d.setShowTitlt("1_D");
		}
		if(e.getActionCommand()=="tiltRight")
		{
			//set value used for sockets later (default value right now)
			sendTilting(3);
			//shows the sent value in the TDI simulation 
//			d.setShowTitlt("1_R");
		}
		if(e.getActionCommand()=="tiltLeft")
		{
			//set value used for sockets later (default value right now)
			sendTilting(4);
			//shows the sent value in the TDI simulation 
//			d.setShowTitlt("1_L");
		}
		
	}

}
