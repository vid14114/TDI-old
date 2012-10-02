package tdi.xfce;

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

public class Test extends JFrame implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static File iconsFile=new File(System.getProperty("user.home")+"/.config/xfce4/desktop/icons.screen0-1584x853.rc");
	private Vector<Icon> vs=new Vector<Icon>();
	int cols=5;
	int rows=10;
	JLabel[][] grid=new JLabel[rows][cols];

	public void initDesk()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		GridLayout gl=new GridLayout(rows, cols);
		
		this.setLayout(gl);
		for(int i=0; i<rows; i++)
		{
			for(int j=0; j<cols; j++)
			{
				grid[i][j]=new JLabel();
				grid[i][j].setBorder(new LineBorder(Color.BLACK));
				grid[i][j].setTransferHandler(new DndHandler("text", this));
				grid[i][j].addMouseListener(this);
				add(grid[i][j]);
			}
		}
		for(int i=0; i<vs.size(); i++)
		{
			grid[vs.get(i).getRow()][vs.get(i).getCol()].setText(vs.get(i).getName());
		}
		
		this.setVisible(true);
	}
	
	public Test() throws NumberFormatException, IOException
	{
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
				vs.add(i);
				break;
			case 4:
				lineNum=0;
				break;
			}
			lineNum++;		
		}
		br.close();
		initDesk();
	}
	
	public void updateDesktop() throws IOException, InterruptedException
	{
		Vector<Icon> exists=new Vector<Icon>();
		for(int i=0; i<grid.length; i++)
		{
			for(int j=0; j<grid[i].length; j++)
			{
				Icon icon=new Icon(grid[i][j].getText(),i,j);
				if(icon.getName().length()>1)
				{
					exists.add(icon);
				}
			}
		}
		
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
		Runtime.getRuntime().exec("xfdesktop --reload").waitFor();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Test();

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
		JLabel comp=(JLabel)arg0.getSource();
		TransferHandler th=comp.getTransferHandler();
		th.exportAsDrag(comp, arg0, TransferHandler.COPY);	

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
