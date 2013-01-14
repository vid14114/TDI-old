package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.Configuration;
import control.DragDropListener;
import model.Icon;

public class SimView extends JFrame{
	/**
	 * @author TDI Team
	 */
	
	/**
	 * A randomly generated serialVersion
	 */
	private static final long serialVersionUID = 639166567792984188L;
	
	int cols;
	int rows;
	int width;
	int height;
	ArrayList<Icon> icons;
	JLabel[][] labels;
	JLabel[] taskLabels;
	
	public SimView(int[] resolution, int rows, int cols)
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
	
	public ArrayList<Icon> updateDesktop() throws IOException 
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
		
		for(int i=0; i<taskLabels.length; i++) //opening a program
		{
			for(int j=0; j<icons.size(); j++)
			{
				if(taskLabels[i].getIcon() != null && taskLabels[i].getIcon().equals(icons.get(j).getIcon()) && icons.get(j).getWmctrl()==null)
				{
					String exec=icons.get(j).getExec();
					Process p=Runtime.getRuntime().exec(exec); //excecute the program
					icons.get(j).setProcess(p);
					Process pid=Runtime.getRuntime().exec("ps a"); //get a list of all processes with name+PID
					
					
					BufferedReader br_pid=new BufferedReader(new InputStreamReader(pid.getInputStream()));
					
					
					String pid_line;
					while((pid_line = br_pid.readLine()) !=null)
					{
						if(pid_line.contains(exec))
							break; //find line from 'ps a' corresponding to our program
					}
					int index=1; //index begins at 1 because every line from 'ps a' starts with a whitespace
					for(; index<pid_line.length(); index++)
					{
						if(!Character.isDigit(pid_line.charAt(index)))
						{
							pid_line=pid_line.substring(1, index);
							break; //find the end-point of the Process number (ID)
						}
					}
					while(icons.get(j).getWmctrl()==null) //some windows need long to open... need to wait until they are fully opened 
					{
						Process wmctrl=Runtime.getRuntime().exec("wmctrl -lp"); // get a list with all windows with the wmctrlID and the PID
						BufferedReader br_wmctrl=new BufferedReader(new InputStreamReader(wmctrl.getInputStream()));
						String wmctrl_line;
						while((wmctrl_line = br_wmctrl.readLine()) !=null)
						{
							if(wmctrl_line.contains(pid_line)) //find line from 'wmctrl -lp' corresponding to our program
								break; //no need to furthermore iterate through the loop
						}
						index=2;
						for(; index<wmctrl_line.length(); index++) //index begins at 2 because every wmctrl-ID starts with '0x...'
						{
							if(!Character.isDigit(pid_line.charAt(index)))
							{
								icons.get(j).setWmctrl(wmctrl_line.substring(0, index));
								break; //no need to furthermore iterate through the loop
							}
						}
					}
					
					
					System.out.println(icons.get(j).getWmctrl());
					break;
				}
			}
		}
		
		
		for(int i=0; i<icons.size(); i++) //closing (okay... killing :/  ) a program
		{
			int count=0;
			for(int j=0; j<taskLabels.length; j++)
			{
				if(taskLabels[j].getIcon() != null && taskLabels[j].getIcon().equals(icons.get(i).getIcon()))
				{
					count++;
					break;
				}
			}
			if(count<1)
			{
				if(icons.get(i).getProcess()!=null)
				{
					icons.get(i).setWmctrl(null);
					icons.get(i).getProcess().destroy();
				}
			}
		}
		
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
