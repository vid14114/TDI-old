package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Draw extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5072266578204453646L;
	int xPos;
	int yPos;
	int height;
	int width;

	enum drawableStuff {
		Rectangle
	}

	drawableStuff drawing;

	public Draw(int xPos, int yPos, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
	}

	public void paint(Graphics g) {
		switch (drawing) {
		case Rectangle:
			g.setColor(Color.black);
			g.fillRect(xPos, yPos, width, height);
			break;
		default:
			break;
		}
	}
	

	@Override
	public void update(Graphics arg0) {
		// TODO Auto-generated method stub
		switch (drawing) {
		case Rectangle:
			arg0.setColor(Color.black);
			arg0.fillRect(xPos, yPos, width, height);
			break;
		default:
			break;
		}
	}

	public drawableStuff getDrawing() {
		return drawing;
	}

	public void setDrawing(drawableStuff drawing) {
		this.drawing = drawing;
	}

}
