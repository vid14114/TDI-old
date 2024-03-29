package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import model.TUIO;
import view.Desk;

public class TUIOMouseListener implements MouseListener, MouseMotionListener{
	Desk d;
	static int idOfTUIOToBeDragged; 
	static int mouseTUIOEdgeDifferenceX; //X Difference between mouse and TUIO
	static int mouseTUIOEdgeDifferenceY; //Y Difference between mouse and TUIO
	public TUIOMouseListener(Desk d){
		this.d=d;
		idOfTUIOToBeDragged=-1; //(default)
		d.repaint();
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
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
		// Looks at which TUIO is to be dragged and sets the variable.
		for(TUIO t:Main.tuios.values()){
			//If the position of the mouse is within a 
			if(arg0.getX()>t.getxPos()&&(arg0.getX()<t.getxPos()+70))
			{
				if(arg0.getY()>t.getyPos()&&(arg0.getY()<t.getyPos()+50))
				{
					idOfTUIOToBeDragged=t.getId();
					mouseTUIOEdgeDifferenceY=arg0.getY()-t.getyPos();
					mouseTUIOEdgeDifferenceX=arg0.getX()-t.getxPos();
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Sets the values in TDInfo to the dragged TUIO
		if(idOfTUIOToBeDragged!=-1){ // If you do not drag NOTHING
		d.idJLabel.setText(""+idOfTUIOToBeDragged);
		d.xAxisJTextField.setText(""+Main.tuios.get(idOfTUIOToBeDragged).getxPos());
		d.yAxisJTextField.setText(""+Main.tuios.get(idOfTUIOToBeDragged).getyPos());
		d.rotationJTextField.setText(""+Main.tuios.get(idOfTUIOToBeDragged).getRotation());
		}
		// Releases the id of the TUIO to be dragged.
		idOfTUIOToBeDragged=-1;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		//Moves the TUIO when it is being dragged.
		if(idOfTUIOToBeDragged!=-1){ // If you do not drag NOTHING
		Main.tuios.get(idOfTUIOToBeDragged).setxPos(e.getX()-mouseTUIOEdgeDifferenceX); // X postion of moue - mouse to TUIO edge difference, so it doesn't jump around.
		Main.tuios.get(idOfTUIOToBeDragged).setyPos(e.getY()-mouseTUIOEdgeDifferenceY); // Y postion of moue - mouse to TUIO edge difference, so it doesn't jump around.
		d.repaint();
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
	    // TODO Auto-generated method stub
	}

}
