package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.Desk;

import model.TUIO;

public class UniversalActionListener implements ActionListener{

	/**
	 * @param args
	 */
	Desk d;
	public static int differentYAxis=0;
	public static int differentXAxis=0;
	
	
	public UniversalActionListener(Desk d)
	{
		this.d =d;
	}
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
			view.Desk.setShowTitlt("1_U");
		}
		if(e.getActionCommand()=="tiltDown")
		{
			//set value used for sockets later (default value right now)
			sendTilting(2);
			//shows the sent value in the TDI simulation 
			view.Desk.setShowTitlt("2_D");
		}
		if(e.getActionCommand()=="tiltRight")
		{
			//set value used for sockets later (default value right now)
			sendTilting(3);
			//shows the sent value in the TDI simulation 
			view.Desk.setShowTitlt("3_R");
		}
		if(e.getActionCommand()=="tiltLeft")
		{
			//set value used for sockets later (default value right now)
			sendTilting(4);
			//shows the sent value in the TDI simulation 
			view.Desk.setShowTitlt("4_L");
		}
		if(e.getActionCommand()=="deleteMenuItem")
		{
			// deletes selected TUIO
			if(Main.tuios.size()!=0)
				for(TUIO t:Main.tuios.values())
				{
					 if(d.getIdJLabel()!=0) // 0 is the default value that the getIdJLabel() method returns if the idTextField is empty
					 {
						if(t.getId()==d.getIdJLabel()) // if same id (in the TextField and for the actual Tuio in the array)
						{
							Main.tuios.remove(t.getId());
							//delete this TUIO
							d.socket.sendDelete(t.getId());
							d.repaint();
							return;
						}
					 }
				}
		}
		if(e.getActionCommand()=="AddMenuItem")
		{
			//adds a TUIO
			//500 the begin of the taskbar (maxyAxis)
			//885 max xAxis
				if(differentYAxis<=500)
				{
					Main.tuios.put(Main.automaticGeneratedId, new TUIO(Main.automaticGeneratedId,5+differentXAxis,5+differentYAxis,0));
					d.socket.sendStart(Main.automaticGeneratedId);
					
					Main.automaticGeneratedId ++;
					differentYAxis += 60; // used to set the position of the new added tuio under the last one so that overlapping is prevented
				}
				else
				{
					if(differentXAxis<=885)
					{
						differentYAxis = 0;
						differentXAxis += 80;
						
						Main.tuios.put(Main.automaticGeneratedId, new TUIO(Main.automaticGeneratedId,5+differentXAxis,5+differentYAxis,0));
						d.socket.sendStart(Main.automaticGeneratedId);
						
						Main.automaticGeneratedId ++;
						differentYAxis += 60;
					}
					else
						JOptionPane.showMessageDialog(null, "Not enough space left on the table");
				}
				d.repaint();
		}
		
	}

}
