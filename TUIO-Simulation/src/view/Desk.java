package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel; 
import javax.swing.JTextField;

public class Desk extends JFrame{

	private static final long serialVersionUID = -5944444691938882393L;

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
	public JPanel workDesk() {
		Draw t = new Draw();
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
		JTextField j2 = new JTextField();
		JLabel j3 = new JLabel("X-Axis:");
		JTextField j4 = new JTextField();
		JLabel j5 = new JLabel("Y-Axis:");
		JTextField j6 = new JTextField();
		JLabel j7 = new JLabel("Rotation:");
		JTextField j8 = new JTextField();
		p.add(j1);
		p.add(j2);
		p.add(j3);
		p.add(j4);
		p.add(j5);
		p.add(j6);
		p.add(j7);
		p.add(j8);
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


		//master Panel
		JPanel master = new JPanel();
		//GridLayout(rows,cols)
		GridLayout gr=new GridLayout(3,1);
		master.setLayout(gr);
		

		//Panel for the top -> button up
		JPanel topPanel=new JPanel();
		//							(align, hgap, vgap)
		topPanel.setLayout(new FlowLayout(1,1,150));
		topPanel.add(tiltUp);


		//Panel for the middle -> button left
		JPanel middlePanel1=new JPanel();
		middlePanel1.setLayout(new FlowLayout());
		middlePanel1.add(tiltLeft);

		//Panel for the middle -> TDI
		JPanel middlePanel2=new JPanel();
		middlePanel2.setLayout(new FlowLayout());
		middlePanel2.add(TDI);

		//Panel for the middle -> button right
		JPanel middlePanel3=new JPanel();
		middlePanel3.setLayout(new FlowLayout());
		middlePanel3.add(tiltRight);

		//Panel for the bottom -> button down
		JPanel bottomPanel=new JPanel();
		//								(align, hgap(horizontal gap), vgap(vertical gap))
		bottomPanel.setLayout(new FlowLayout(1,1,10));
		bottomPanel.add(tiltDown);
		
		//Panel for the middle so that all Three Items are in one row
		JPanel mid=new JPanel();
		mid.setLayout(new FlowLayout(1,10,10));
		mid.add(middlePanel1);
		mid.add(middlePanel2);
		mid.add(middlePanel3);
		

		master.add(topPanel);
		master.add(mid);
		master.add(bottomPanel);
		
		return master;
	}

}