package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel; 
import javax.swing.JTextField;

import control.TUIOMouseListener;

public class Desk extends JFrame{

	private static final long serialVersionUID = -5944444691938882393L;
	public JTextField idJTextField;
	public JTextField xAxisJTextField;
	public JTextField yAxisJTextField;
	public JTextField rotationJTextField;
	public Desk() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("TUIO Desk Simulation");
		setSize(1250, 700);
		setLayout(new BorderLayout());
		add(workDesk(), BorderLayout.CENTER);
		add(menuDesk(), BorderLayout.EAST);
		setJMenuBar(menuBar());
		setVisible(true);
	}

	public JMenuBar menuBar(){
		//Where the GUI is created:
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;
		JFrame frame;

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("A Menu");
		menu.setMnemonic(KeyEvent.VK_A);
		//should show you the description!!!!
		menu.setToolTipText("This allows you to modify/delete/add TDIs");
		menuBar.add(menu);
		//a group of JMenuItems
		menuItem = new JMenuItem("Add/Modify");
		//shows the Mnemonic to use this item
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(
		//      KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		menu.add(menuItem);

		menuItem = new JMenuItem("Delete");
		menu.add(menuItem);

		//Build second menu in the menu bar.
		menu = new JMenu("About");
		menu.getAccessibleContext().setAccessibleDescription(
				"This menu does nothing too :)");
		menuBar.add(menu);

		menu = new JMenu("Help");
		menu.getAccessibleContext().setAccessibleDescription(
				"This menu does nothing too :)");
		menuBar.add(menu);
		return menuBar;
	}

	//This simulates the desk the user works on
	public Draw workDesk() {
		Draw t = new Draw();
		t.addMouseListener(new TUIOMouseListener(this));
		return t;
	}

	//This is the menuDesk on the right -- all of it.
	public JPanel menuDesk() {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(topMenuDesk(),BorderLayout.NORTH);
		p.add(bottomMenuDesk(),BorderLayout.CENTER);
		return p;
	}
	//This is the top part of the menuDesk with the information about the TUIO
	public JPanel topMenuDesk() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,2));
		JLabel j1 = new JLabel("ID:");
		idJTextField = new JTextField();
		JLabel xAxisLabel = new JLabel("X-Axis:");
		xAxisJTextField = new JTextField();
		JLabel yAxisLabel = new JLabel("Y-Axis:");
		yAxisJTextField= new JTextField();
		JLabel j7 = new JLabel("Rotation:");
		rotationJTextField = new JTextField();
		p.add(j1);
		p.add(idJTextField);
		p.add(xAxisLabel);
		p.add(xAxisJTextField);
		p.add(yAxisLabel);
		p.add(yAxisJTextField);
		p.add(j7);
		p.add(rotationJTextField);
		return p;
	}
	//This is the center of the menuDesk with the tilt options
	public JPanel bottomMenuDesk() {
		//buttons
		
		JButton tiltLeft=new JButton("Left");
		JButton tiltRight=new JButton("Right");
		JButton tiltDown=new JButton("Down");
		JButton tiltUp=new JButton("Up");
		JLabel TDI=new JLabel("TDI");

		//master panel
		JPanel master=new JPanel();
		master.setLayout(new FlowLayout());
		
		//inner Panel
		JPanel inner = new JPanel();
		//GridLayout(rows,cols)
		inner.setLayout(new GridLayout(3,3));
		
		inner.add(new JLabel());
		inner.add(tiltUp);
		inner.add(new JLabel());
		inner.add(tiltLeft);
		inner.add(TDI);
		inner.add(tiltRight);
		inner.add(new JLabel());
		inner.add(tiltDown);

		master.add(inner);
		return master;
	}

}