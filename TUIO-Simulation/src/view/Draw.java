package view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Draw extends JPanel {

	private static final long serialVersionUID = 5072266578204453646L;
	int xPos;
	int yPos;
	int height;
	int width;
	Graphics2D g2D;
	enum drawableStuff {
		Rectangle
	}

	drawableStuff drawing;

	public Draw() {

	}
	//Just for experimentation right now - draws a line that seperates the
	//taskbar and and the desktop
	public void paint(Graphics g) {
		g2D=(Graphics2D) g.create();
		g2D.drawRect(10, 10, getWidth()/10, getHeight()/10);
		g2D.drawRect(0, getHeight()/5*4, getWidth(), 1);
	}


	public drawableStuff getDrawing() {
		return drawing;
	}

	public void setDrawing(drawableStuff drawing) {
		this.drawing = drawing;
	}

}
