package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.io.*;
import java.text.AttributedCharacterIterator;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.border.LineBorder;
import control.Configuration;
import control.DndHandler;
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
	int cols=5; //maximum cols, this values should be found out with a formular which is going to be added later on
	int rows=8; //maximum rows this values should be found out with a formular which is going to be added later on
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

	public void initDesk()
	{
		CustomPanel jpanel = new CustomPanel(img, rows, cols);
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				labels[i][j] = new JLabel();
				labels[i][j].setBorder(new LineBorder(Color.BLACK));
				//set the Drag&Drop handler				
				labels[i][j].addMouseListener(new DragDropListener());
				labels[i][j].setName("");
				add(labels[i][j]);
			}
		}
		for(int i=0; i<vs.size(); i++)
		{
			//populate the grid with icons
			if(vs.get(i).getIcon() != null)
			{
				grid[vs.get(i).getRow()][vs.get(i).getCol()].setTransferHandler(new DndHandler("icon", this));
				grid[vs.get(i).getRow()][vs.get(i).getCol()].setIcon(vs.get(i).getIcon());			
			}
			else
				grid[vs.get(i).getRow()][vs.get(i).getCol()].setText((vs.get(i).getName()));			
			grid[vs.get(i).getRow()][vs.get(i).getCol()].setName(vs.get(i).getName());
			
		}
		//make everything visible
		this.setVisible(true);
	}
	
	
	
	//update the icons file and reload the desktop manager
	public void updateDesktop() throws IOException, InterruptedException
	{
		Vector<Icon> exists=new Vector<Icon>();
		for(int i=0; i<grid.length; i++)
		{
			for(int j=0; j<grid[i].length; j++)
			{
				//get the new position of the icons
				Icon icon=new Icon(grid[i][j].getText(),i,j);
				if(icon.getName().length()>1)
				{
					exists.add(icon);
				}
			}
		}
		//overwrite the old configuration
		vs=exists;
		BufferedWriter bw=new BufferedWriter(new FileWriter(iconsFile));
		for(int i=0; i<vs.size(); i++)
		{
			bw.write(vs.get(i).getName());
			bw.newLine();
			bw.write("row="+vs.get(i).getRow());
			bw.newLine();
			bw.write("col="+vs.get(i).getCol());	
			bw.newLine();
			bw.newLine();
		}
		bw.close();
		//refresh the desktop manager
		Runtime.getRuntime().exec("xfdesktop --reload").waitFor();
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
	
	public CustomPanel(Image img, int rows, int cols){
		setLayout(new GridLayout(rows, cols));
		this.img = img;
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(img,0,0,null);
	}
}
