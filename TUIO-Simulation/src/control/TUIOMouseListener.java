package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import model.TUIO;
import view.Desk;

public class TUIOMouseListener implements MouseListener, MouseMotionListener{
	Desk d;
	static int idOfTUIOToBeDragged;
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
		// Looks at which TUIO is to be dragged and sets the variable.
		for(TUIO t:Main.tuios.values()){
			//If the position of the mouse is within a 
			if(arg0.getX()>t.getxPos()&&(arg0.getX()<t.getxPos()+70))
			{
				if(arg0.getY()>t.getyPos()&&(arg0.getY()<t.getyPos()+50))
				{
					idOfTUIOToBeDragged=t.getId();
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// Sets the values in TDInfo to the dragged TUIO
		if(idOfTUIOToBeDragged!=0){ // If you do not drag NOTHING
		d.idJLabel.setText(""+idOfTUIOToBeDragged);
		d.xAxisJTextField.setText(""+Main.tuios.get(idOfTUIOToBeDragged).getxPos());
		d.yAxisJTextField.setText(""+Main.tuios.get(idOfTUIOToBeDragged).getyPos());
		d.rotationJTextField.setText(""+Main.tuios.get(idOfTUIOToBeDragged).getRotation());
		}
		// Releases the id of the TUIO to be dragged.
		idOfTUIOToBeDragged=0;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		//Moves the TUIO when it is being dragged.
		if(idOfTUIOToBeDragged!=0){ // If you do not drag NOTHING
		Main.tuios.get(idOfTUIOToBeDragged).setxPos(e.getX());
		Main.tuios.get(idOfTUIOToBeDragged).setyPos(e.getY());
		d.repaint();
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
	    // TODO Auto-generated method stub
	}

}
