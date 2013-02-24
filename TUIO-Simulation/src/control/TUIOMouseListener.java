package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.TUIO;
import view.Desk;

public class TUIOMouseListener implements MouseListener{
	Desk d;
	public TUIOMouseListener(Desk d){
		this.d=d;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		for(TUIO t:Main.tuios){
			if(arg0.getX()>t.getxPos()&&(arg0.getX()<t.getxPos()+70))
			{
				if(arg0.getY()>t.getyPos()&&(arg0.getY()<t.getyPos()+50))
				{
					d.getIdJTextField().setText(""+t.getId());
					d.xAxisJTextField.setText(""+t.getxPos());
					d.yAxisJTextField.setText(""+t.getyPos());
					d.rotationJTextField.setText(""+t.getRotation());
				}
			}
		}
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
