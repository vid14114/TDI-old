package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import model.TUIO;
import view.Desk;

public class TUIOMouseListener implements MouseListener, MouseMotionListener{
	Desk d;
	static int x;
	boolean pressed;
	public TUIOMouseListener(Desk d){
		this.d=d;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		for(TUIO t:Main.tuios.values()){
			//If the position of the mouse is within a 
			//TUIO it sets the values of the TDInfo to the corresponding ones.
			if(arg0.getX()>t.getxPos()&&(arg0.getX()<t.getxPos()+70))
			{
				if(arg0.getY()>t.getyPos()&&(arg0.getY()<t.getyPos()+50))
				{
					d.idJLabel.setText(""+t.getId());
					d.xAxisJTextField.setText(""+t.getxPos());
					d.yAxisJTextField.setText(""+t.getyPos());
					d.rotationJTextField.setText(""+t.getRotation());
				}
			}
		}
		d.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		for(TUIO t:Main.tuios.values()){
			//If the position of the mouse is within a 
			if(arg0.getX()>t.getxPos()&&(arg0.getX()<t.getxPos()+70))
			{
				if(arg0.getY()>t.getyPos()&&(arg0.getY()<t.getyPos()+50))
				{
					x=t.getId();
					System.out.println(""+x);
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		Main.tuios.get(x).setxPos(e.getX());
		Main.tuios.get(x).setyPos(e.getY());
		d.repaint();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
	    // TODO Auto-generated method stub
	}

}
