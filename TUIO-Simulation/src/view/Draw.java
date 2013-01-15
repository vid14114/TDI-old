package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
		//Goes through the array of TUIOs and draws every one of them.
		for(TUIO t : Main.tuios){
			g2D.drawRect(t.getxPos(), t.getyPos(), 70, 50);
		}
		g2D.drawRect(0, getHeight()-70, getWidth(), 1);
	}

}
