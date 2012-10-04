package tdi.xfce;

import java.awt.datatransfer.Transferable;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

public class DndHandler extends TransferHandler{
	
	/**
	 * @author Viktor Vidovic
	 * Custom DnD Handler for removing the Icon at the old position
	 */
	private static final long serialVersionUID = 1L;
	Test t;
	
	public DndHandler(String comp, Test t)
	{
		super(comp);
		this.t=t;
	}
	
	public void exportDone(JComponent c, Transferable data, int action)
	{
		JLabel source=(JLabel)c;
			source.setText("");
			try {
				t.updateDesktop();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}
