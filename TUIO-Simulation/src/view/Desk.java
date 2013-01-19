package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		setVisible(true);
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
		p.add(centralMenuDesk(),BorderLayout.CENTER);
		p.add(bottomMenuDesk(),BorderLayout.SOUTH);
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
	public JPanel centralMenuDesk() {
		//buttons
		JButton tiltLeft=new JButton("Left");
		JButton tiltRight=new JButton("Right");
		JButton tiltDown=new JButton("Down");
		JButton tiltUp=new JButton("Up");
		JLabel TDI=new JLabel("TDI");
		
		
		//master Panel
		JPanel master = new JPanel();
		master.setLayout(new GridLayout(3,3));
		
		//Panel for the top
		JPanel topPanel=new JPanel();
		topPanel.setLayout(new FlowLayout());
		topPanel.add(tiltUp, topPanel.CENTER_ALIGNMENT);
		
		
		//Panel for the middle -> button left
		JPanel middlePanel1=new JPanel();
		middlePanel1.setLayout(new FlowLayout());
		middlePanel1.add(tiltLeft, topPanel.CENTER_ALIGNMENT);
		
		//Panel for the middle -> TDI
		JPanel middlePanel2=new JPanel();
		middlePanel2.setLayout(new FlowLayout());
		middlePanel2.add(TDI, topPanel.CENTER_ALIGNMENT);
		
		//Panel for the middle -> button right
		JPanel middlePanel3=new JPanel();
		middlePanel3.setLayout(new FlowLayout());
		middlePanel3.add(tiltRight, topPanel.CENTER_ALIGNMENT);
		
		//Panel for the bottom
		JPanel bottomPanel=new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(tiltDown, topPanel.CENTER_ALIGNMENT);
		
		master.add(topPanel);
		master.add(middlePanel1);
		master.add(middlePanel2);
		master.add(middlePanel3);
		master.add(bottomPanel);
		return master;
	}
	//This is the south of the menuDesk with the add,delete,update options - maybe implemented as a menu
	public JPanel bottomMenuDesk() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,3));
		JButton delButton = new JButton("DELETE");
		JButton addModButton = new JButton("ADD / MODIFY");
		p.add(delButton);
		p.add(addModButton);
		
		return p;
	}
	
}