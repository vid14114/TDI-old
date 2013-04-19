package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import control.Configuration;
import control.DragDropListener;
import control.ProcessManipulator;
import control.TDIServer;
import model.Icon;

/**
 * The {@link SimView} class is a representation of the user's desktop.
 * All icons are copied accordingly and the background image is also shown to the user.
 * The {@link SimView} class allows the user to interact with the program as if he is interacting with his desktop. If the user moves an icon
 * in simview, the icon is also moved on the real desktop. Icons are coloured to show the user which Icons groups a single TDI manages. Marked TDI's 
 * are marked to give the user visual help
 * @author TDI-Team
 *
 */
public class SimView extends JFrame{
	
	/**
	 * A randomly generated serialVersion
	 */
	private static final long serialVersionUID = 639166567792984188L;	
	int cols;
	int rows;
	int width;
	int height;
	ArrayList<Icon> icons;
	Configuration config;
	JLabel[][] labels;
	JLabel[] taskLabels;
	
	public SimView(int[] resolution, int rows, int cols, Configuration config)
	{
		width = resolution[0];
		height = resolution[1];
		this.rows=rows;//max rows
		this.cols=cols;//max cols
		setTitle("Simulation Linux");
		setLocation(0, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width, height);
		setResizable(true);
		setVisible(true);
		TDIServer ts=new TDIServer(config, icons);
		new Thread(ts);
	}

	/**
	 * Initialises the view of the program
	 * @param icons The icons to be displayed
	 * @param img The background image
	 * @param config A configuration instance
	 */
	public void initDesk(ArrayList<Icon> icons, Image img, Configuration config)
	{
		for(int i=0; i<icons.size(); i++)
		{
			if(icons.get(i).getRow()>=rows)
				rows=icons.get(i).getRow()+1;//Here +1 needs to be added cause the getRow() accesses a given position and arrays start with 0
			if(icons.get(i).getCol()>=cols)
				cols=icons.get(i).getCol()+1;
		}
		
		labels = new JLabel[rows][cols];
		taskLabels = new JLabel[cols];
			
		this.icons=icons;
		JPanel panel = new JPanel(new GridLayout(1, cols));
		DragDropListener ls = new DragDropListener("icon", this, config);
		////Configuring the taskbar Panel
		for(int i = 0; i < cols; i++){
			taskLabels[i] = new JLabel();
			taskLabels[i].setBorder(new LineBorder(Color.WHITE));
			taskLabels[i].setTransferHandler(ls);
			taskLabels[i].addMouseListener(ls);
			panel.add(taskLabels[i]);
		}
		/////Configuring the main JPanel
		CustomPanel jpanel = new CustomPanel(this,img, rows, cols);		
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				labels[i][j] = new JLabel();
				labels[i][j].setBorder(new LineBorder(Color.BLACK));
				//set the Drag&Drop handler		
				labels[i][j].setTransferHandler(ls);
				labels[i][j].addMouseListener(ls);
				labels[i][j].setName("");
				jpanel.add(labels[i][j]);
			}
		}
		for(int i=0; i<icons.size(); i++)
		{
			//populate the grid with icons

			if(icons.get(i).getIcon() != null)
				labels[icons.get(i).getRow()][icons.get(i).getCol()].setIcon(icons.get(i).getIcon());			
			else
				 labels[icons.get(i).getRow()][icons.get(i).getCol()].setText((icons.get(i).getName().substring(1, icons.get(i).getName().length()-1)));		
			labels[icons.get(i).getRow()][icons.get(i).getCol()].setName(icons.get(i).getName());			
		}
		//Here you can change the background of the jpanel
		panel.setBackground(Color.BLACK);
		panel.setPreferredSize(new Dimension(width, height/10));
		//add the JPanel to the frame and make everything visible
		add(jpanel,BorderLayout.CENTER);		
		add(panel,BorderLayout.SOUTH);
		setVisible(true);
	}	
	
	/**
	 * Refreshes the desktop after the user has moved an icon
	 * @param config
	 * @return An ArrayList of the new position of the icons
	 * @throws IOException
	 */
	public ArrayList<Icon> updateDesktop(Configuration config) throws IOException
	{		
		ArrayList<Icon> newIcons=new ArrayList<Icon>();
		for(int i=0; i<labels.length; i++)
		{
			for(int j=0; j<labels[i].length; j++)
			{
				//get the new position of the icons
				Icon icon=new Icon(labels[i][j].getText(),i,j);
				if(labels[i][j].getIcon()!=null)
				{	
					icon.setIcon(labels[i][j].getIcon(), false);
				}	
				if(icon.getName().length()>1 || icon.getIcon()!=null)
					newIcons.add(icon);
			}
		}
		if(newIcons.size()<icons.size())
		{
			for(int i=0; i<icons.size(); i++)
			{
				if(!newIcons.contains(icons.get(i)))
				{
					newIcons.add(icons.get(i));
					labels[icons.get(i).getRow()][icons.get(i).getCol()].setIcon(icons.get(i).getIcon());
				}
			}
		}
		new Thread(new ProcessManipulator(icons, taskLabels, labels)).start(); //Now the view process isn't blocked by the searching of the opened programs
		repaint();
		return newIcons;
	}
}

/**
 * Had to create a custom JPanel class which extends JPanel to be able to override the paintComponent
 * and use an image as the background image
 * @author TDI Team
 *
 */
class CustomPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8731589894224036259L;
	Image img;
	SimView mother;
	public CustomPanel(SimView mother,Image img, int rows, int cols){
		setLayout(new GridLayout(rows, cols));
		setSize(mother.getWidth(), mother.getHeight()/100*95);
		this.img = img;
		this.mother = mother;
	}
	
	@Override
	public void paintComponent(Graphics g){		
		g.drawImage(img, 0, 0, mother.getWidth(), mother.getHeight(), this);
	}
}
