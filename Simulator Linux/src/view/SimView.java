package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
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
	int cols=15; //maximum cols, this values should be found out with a formula which is going to be added later on
	int rows=7; //maximum rows this values should be found out with a formula which is going to be added later on
	JLabel[][] labels = new JLabel[rows][cols];
	
	public SimView()
	{
		setTitle("Simulation Linux");
		setLocation(0, 0);
		setSize(600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);			
	}

	public void initDesk(ArrayList<Icon> icons, Image img, Configuration config)
	{
		CustomPanel jpanel = new CustomPanel(this,img, rows, cols);
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				labels[i][j] = new JLabel();
				labels[i][j].setBorder(new LineBorder(Color.BLACK));
				//set the Drag&Drop handler		
				DragDropListener ls = new DragDropListener("text", this, config);
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
			{
				labels[icons.get(i).getRow()][icons.get(i).getCol()].setIcon(icons.get(i).getIcon());			
			}
			else
				labels[icons.get(i).getRow()][icons.get(i).getCol()].setText((icons.get(i).getName()));			
			labels[icons.get(i).getRow()][icons.get(i).getCol()].setName(icons.get(i).getName());
			
		}
		//add the JPanel to the frame and make everything visible
		add(jpanel);
		setVisible(true);
	}

	public ArrayList<Icon> updateDesktop(){		
		ArrayList<Icon> icons=new ArrayList<Icon>();
		for(int i=0; i<labels.length; i++)
		{
			for(int j=0; j<labels[i].length; j++)
			{
				//get the new position of the icons
				Icon icon=new Icon(labels[i][j].getText(),i,j);
				if(icon.getName().length()>1)
				{
					icons.add(icon);
				}
			}
		}
		repaint();
		return icons;
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
		this.img = img;
		this.mother = mother;
	}
	
	@Override
	public void paintComponent(Graphics g){		
		g.drawImage(img, 0, 0, mother.getWidth(), mother.getHeight(), this);
	}
}
