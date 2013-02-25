package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;
import control.Main;
import model.TUIO;

public class Draw extends JPanel {

	private static final long serialVersionUID = 5072266578204453646L;
	int xPos;
	int yPos;
	int height;
	int width;
	AffineTransform identity = new AffineTransform();
	static int back;
	Graphics2D g2D;

	public Draw() {
	}
	//taskbar and and the desktop
	public void paint(Graphics g) {
		g2D=(Graphics2D) g.create();
		Image backdraw= Toolkit.getDefaultToolkit().getImage("Desk.jpg");
		g2D.drawImage(backdraw,0, 0, null);
		if(back==0){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		back=1;
		}
		
		//Goes through the array of TUIOs and draws every one of them.
		for(TUIO t : Main.tuios.values()){
			Image i= Toolkit.getDefaultToolkit().getImage("TUIO.jpg");
			// Sets the affinity so that it is rotated.
			AffineTransform trans = new AffineTransform();
			trans.setTransform(identity);
			trans.setToTranslation(t.getxPos(), t.getyPos());
			trans.rotate( Math.toRadians(t.getRotation()) );
			g2D.drawImage(i, trans, this);
			//g2D.drawImage(i, t.getxPos(), t.getyPos(), null);
		}
		// draws the taskbar
		g2D.drawRect(0, getHeight()-70, getWidth(), 1);
	}
}
