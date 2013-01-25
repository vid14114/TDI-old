package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.Desk;

public class TUIOMouseListener implements MouseListener{
	Desk d;
	public TUIOMouseListener(Desk d){
		this.d=d;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		d.xAxisJTextField.setText(""+arg0.getX());
		d.yAxisJTextField.setText(""+arg0.getY());
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
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
