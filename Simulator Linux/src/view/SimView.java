package view;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.TransferHandler;
import javax.swing.border.LineBorder;
import control.Configuration;
import control.DndHandler;
import model.Icon;

public class SimView extends JFrame{
	/**
	 * @author TDI Team
	 */
	
	/**
	 * A randomly generated serialVersion
	 */
	private static final long serialVersionUID = 639166567792984188L;
	int cols=5; //maximum cols
	int rows=8; //maximum rows
	
	public SimView()
	{
		setTitle("Simulation Linux");
		setLocation(0, 0);
		setSize(MAXIMIZED_HORIZ, MAXIMIZED_VERT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);			
	}

	public GridLayout initDesk()
	{		
		setLayout(new GridLayout(rows, cols));
		set
		for(int i=0; i<rows; i++)
		{
			for(int j=0; j<cols; j++)
			{
				grid[i][j]=new JLabel();
				grid[i][j].setBorder(new LineBorder(Color.BLACK));
				//set the Drag&Drop handler
				grid[i][j].setTransferHandler(new DndHandler("text", this));				
				grid[i][j].addMouseListener(this);
				grid[i][j].setName("");
				add(grid[i][j]);
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
