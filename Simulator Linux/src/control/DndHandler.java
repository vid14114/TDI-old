package control;

import java.awt.datatransfer.Transferable;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

import view.SimView;

public class DndHandler extends TransferHandler{
	
	/**
	 * @author Viktor Vidovic
	 * Custom DnD Handler for removing the Icon at the old position
	 */
	private static final long serialVersionUID = 1L;
	SimView t;
	
	public DndHandler(String comp, SimView t)
	{
		super(comp);
		this.t=t;
	}
	
	public void exportDone(JComponent c, Transferable data, int action)
	{
		JLabel source=(JLabel)c;
			source.setText("");
			source.setName("");
			try {
				t.updateDesktop();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}
