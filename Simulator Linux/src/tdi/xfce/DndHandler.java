package tdi.xfce;

import java.awt.datatransfer.Transferable;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

public class DndHandler extends TransferHandler{
	
	/**
	 * 
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
		//if(action==TransferHandler.MOVE)
			source.setText("");
			try {
				t.updateDesktop();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
