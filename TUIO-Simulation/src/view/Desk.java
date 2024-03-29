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
import control.MyDocumentListener;
import control.SocketListener;
import control.TUIOMouseListener;
import control.UniversalActionListener;
import control.MenuMouseListener;

public class Desk extends JFrame{

	private static final long serialVersionUID = -5944444691938882393L;
	public JLabel idJLabel;
	public Thread t;
	public SocketListener socket;
	public JTextField xAxisJTextField;
	public JTextField yAxisJTextField;
	public JTextField rotationJTextField;
	public static JLabel ShowTitlt=new JLabel("");
	UniversalActionListener actionListener=new UniversalActionListener(this);
	MenuMouseListener menuMouseListener = new MenuMouseListener(this);
	MyDocumentListener docLis = new MyDocumentListener(this);

	public static void setShowTitlt(String text) {
		ShowTitlt.setText(text);
	}
	
	public Desk() {
		socket=new SocketListener();
		t = new Thread(socket);
		t.start();
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
		JMenuBar menuBar;

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		JMenu menu1 = new JMenu("Action");
		//gives a shortcut to menu1
		menu1.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu1);
		
		//a group of JMenuItems
		JMenuItem menuItem1 = new JMenuItem("Add");
		menuItem1.addActionListener(actionListener);
		menuItem1.setActionCommand("AddMenuItem");
		menuItem1.setMnemonic(KeyEvent.VK_ENTER);
		menu1.add(menuItem1);

		JMenuItem menuItem2 = new JMenuItem("Delete");
		menuItem2.addActionListener(actionListener);
		menuItem2.setActionCommand("deleteMenuItem");
		menuItem2.setMnemonic(KeyEvent.VK_DELETE);
		menu1.add(menuItem2);

		JMenu menu2 = new JMenu("About");
		menu2.setMnemonic(KeyEvent.VK_B);
		menu2.addMouseListener(menuMouseListener);
		menuBar.add(menu2);
		
		//Build the Help menu for the users
		JMenu menu3 = new JMenu("Help");
		menu3.setMnemonic(KeyEvent.VK_H);
		menu3.addMouseListener(menuMouseListener);
		menuBar.add(menu3);
		return menuBar;
	}

	//This simulates the desk the user works on
	public Draw workDesk() {
		Draw t = new Draw();
		t.addMouseListener(new TUIOMouseListener(this));
		t.addMouseMotionListener(new TUIOMouseListener(this));
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
		setIdJLabel(new JLabel());
				
		JLabel xAxisLabel = new JLabel("X-Axis:");
		xAxisJTextField = new JTextField();
		xAxisJTextField.getDocument().addDocumentListener(docLis);
		xAxisJTextField.getDocument().putProperty("TextField", "xAxis");

		
		JLabel yAxisLabel = new JLabel("Y-Axis:");
		yAxisJTextField= new JTextField();
		yAxisJTextField.getDocument().addDocumentListener(docLis);
		yAxisJTextField.getDocument().putProperty("TextField", "yAxis");
		
		JLabel j7 = new JLabel("Rotation:");
		rotationJTextField = new JTextField();
		rotationJTextField.getDocument().addDocumentListener(docLis);
		rotationJTextField.getDocument().putProperty("TextField", "rotation");
		
		
		p.add(j1);
		p.add(idJLabel);
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
		tiltLeft.setActionCommand("tiltLeft");
		tiltLeft.addActionListener(actionListener);
		
		JButton tiltRight=new JButton("Right");
		tiltRight.setActionCommand("tiltRight");
		tiltRight.addActionListener(actionListener);
		
		JButton tiltDown=new JButton("Down");
		tiltDown.setActionCommand("tiltDown");
		tiltDown.addActionListener(actionListener);
		
		JButton tiltUp=new JButton("Up");
		tiltUp.setActionCommand("tiltUp");
		tiltUp.addActionListener(actionListener);

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
		inner.add(ShowTitlt);
		inner.add(tiltRight);
		inner.add(new JLabel());
		inner.add(tiltDown);

		master.add(inner);
		return master;
	}



	public int getIdJLabel() {
		if(!idJLabel.getText().equals(""))
		return Integer.parseInt(idJLabel.getText());
		else
			return -1; // return -1 as default value because no TUIO is allowed to have the id 0
	}



	public void setIdJLabel(JLabel idJTextField) {
		
		this.idJLabel = idJTextField;
	}

}