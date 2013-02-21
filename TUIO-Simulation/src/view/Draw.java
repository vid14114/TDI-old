package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.ImageCapabilities;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import control.Main;
import model.TUIO;

public class Draw extends JPanel {

	private static final long serialVersionUID = 5072266578204453646L;
	int xPos;
	int yPos;
	int height;
	int width;
	Graphics2D g2D;

	public Draw() {

	}
	//Just for experimentation right now - draws a line that seperates the
	//taskbar and and the desktop
	public void paint(Graphics g) {
		g2D=(Graphics2D) g.create();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2D.drawImage(Toolkit.getDefaultToolkit().getImage("Desk.jpg"),0,0,null);
		//Goes through the array of TUIOs and draws every one of them.
		for(TUIO t : Main.tuios){
			Image i= Toolkit.getDefaultToolkit().getImage("TUIO.jpg");
			g2D.drawImage(i, t.getxPos(), t.getyPos(), null);
		}
		g2D.drawRect(0, getHeight()-70, getWidth(), 1);
	}

}
