/**
 * This listener will be responsible for handling all drag and drop events
 * Whether the individual icons are being moved to another position or being moved to the taskbar
 */
package control;

import java.awt.datatransfer.Transferable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;
import view.SimView;

/**
 * @author TDI Team
 * 
 */
public class DragDropListener extends TransferHandler implements MouseListener {
	/**
	 * The generated serial version number
	 */
	private static final long serialVersionUID = 7619130164209787294L;
	private SimView view;
	private Configuration config;
	/**
	 * A boolean set to true when the user has begun to drag the icons
	 */
	boolean dragBegun;

	public DragDropListener(String comp, SimView view, Configuration config) {
		super(comp);
		this.view = view;
		this.config = config;
	}

	public void exportDone(JComponent c, Transferable data, int action) {
		JLabel source = (JLabel) c;
		source.setText("");
		source.setName("");
		config.updateConfig(view.updateDesktop());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JLabel comp=(JLabel)e.getSource();
		TransferHandler th=comp.getTransferHandler();
		th.exportAsDrag(comp, e, TransferHandler.COPY);	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
