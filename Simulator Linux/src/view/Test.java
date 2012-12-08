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

import control.DndHandler;

import model.Icon;

public class Test extends JFrame implements MouseListener {
	
	/**
	 * @author Viktor Vidovic
	 */
	//auto-generated
	private static final long serialVersionUID = 1L;
	//File where the icon-position is stored on the hard disk
	private static File iconsFile;
	//Vector contains all icons
	private Vector<Icon> vs=new Vector<Icon>();
	//maximum number of columns and rows
	int cols=5;
	int rows=8;
	//grid where the icons are placed
	JLabel[][] grid=new JLabel[rows][cols];
	
	public static void main(String[] args) throws NumberFormatException, IOException, InterruptedException {
		new Test();
	}
	
	public Test() throws NumberFormatException, IOException
	{
		//get the actual IconConfiguration and generate Icon instances
		iconsFile=lastFileModified(System.getProperty("user.home")+"/.config/xfce4/desktop");
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(iconsFile)));
		String line;
		int lineNum=1;
		Icon i=new Icon();
		for(line=br.readLine(); line!=null; line=br.readLine())
		{
			switch(lineNum)
			{
			case 1:
				i=new Icon();
				i.setName(line);
				break;
			case 2:
				i.setRow(Integer.parseInt(line.split("=")[1]));
				break;
			case 3:
				i.setCol(Integer.parseInt(line.split("=")[1]));
				i.getConfig();
				vs.add(i);
				break;
				//after every Icon there's a blank row
			case 4:
				lineNum=0;
				break;
			}
			lineNum++;		
		}
		br.close();
		initDesk();
	}

	public void initDesk()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		//set the Grid Layout
		GridLayout gl=new GridLayout(rows, cols);	
		this.setLayout(gl);
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
		bw.flush();
		bw.close();
		//refresh the desktop manager
		Runtime.getRuntime().exec("xfdesktop --reload").waitFor();
	}
	
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		//initiate the Drag&Drop handler when an Icon is pressed on
		JLabel comp=(JLabel)arg0.getSource();
		TransferHandler th=comp.getTransferHandler();
		th.exportAsDrag(comp, arg0, TransferHandler.COPY);	

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	//look for the newest file in the icons configuration folder because that will be the one for the actual desktop resolution
	public static File lastFileModified(String dir) {
        File fl = new File(dir);
        File[] files = fl.listFiles(new FileFilter() {                  
                public boolean accept(File file) {
                        return file.isFile();
                }
        });
        long lastMod = Long.MIN_VALUE;
        File choise = null;
        for (File file : files) {
                if (file.lastModified() > lastMod) {
                        choise = file;
                        lastMod = file.lastModified();
                }
        }
        return choise;
}
}
