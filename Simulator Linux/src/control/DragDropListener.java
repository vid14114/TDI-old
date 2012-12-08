/**
 * This listener will be responsible for handling all drag and drop events
 * Whether the individual icons are being moved to another position or being moved to the taskbar
 */
package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.TransferHandler;

/**
 * @author TDI Team
 *
 */
public class DragDropListener implements MouseListener, MouseMotionListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
		//initiate the Drag&Drop handler when an Icon is pressed on
		JLabel comp=(JLabel)arg0.getSource();
		TransferHandler th=comp.getTransferHandler();
		th.exportAsDrag(comp, arg0, TransferHandler.COPY);	

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
