/**
 * 
 */
package control;

import java.util.ArrayList;
import model.Icon;
import model.TUIO;

/**
 * @author abideen
 *
 */
public class RotateHandler implements Runnable {
	ArrayList<Icon> icons;
	TUIO t;
	
	public RotateHandler(ArrayList<Icon> icons, TUIO t){
		this.icons = icons;
		this.t = t;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		ArrayList<Icon> opened = new ArrayList<Icon>();
		for(Icon i : icons)
			if(i.getProcess() != null)
				opened.add(i);
		ProcessManipulator.minimizeProgram(opened.get(t.getRotationPosition()/t.getMinimalRotation()).getWmctrl());
		
	}

}
