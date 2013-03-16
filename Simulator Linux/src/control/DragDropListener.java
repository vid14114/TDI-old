/**
 * This listener will be responsible for handling all drag and drop events
 * Whether the individual icons are being moved to another position or being moved to the taskbar
 */
package control;

import java.awt.datatransfer.Transferable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

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

	public DragDropListener(String comp, SimView view, Configuration config) {
		super(comp);
		this.view = view;
		this.config = config;
	}

	/**
	 * When the user has finished dragging the label, this method is called
	 * The method sets the source text, name and icon to null and refreshed the screen
	 */
	public void exportDone(JComponent c, Transferable data, int action) {
		JLabel source = (JLabel) c;
		source.setText("");
		source.setName("");
		source.setIcon(null);
		try {
			config.updateConfig(view.updateDesktop(config));// The update config file is called to update the config file and refresh the screen
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
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
