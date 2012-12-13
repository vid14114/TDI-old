/**
 * 
 */
package control;
import java.io.IOException;
import javax.swing.JOptionPane;

import view.SimView;

/**
 * @author TDI Team
 *
 */
public class Main {
	
	public void run(){
		try {
			Configuration config = new Configuration();
			int rows=config.calcRows();
			int cols=config.calcCols();
			SimView view = new SimView(config.getResolution(), rows, cols);
			view.initDesk(config.getIcons(), config.getBackground(), config);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Couldn't load the configuration file for initialization.\n"
					+" The program can not start without having read the configuaration file. Exiting.");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}

}
