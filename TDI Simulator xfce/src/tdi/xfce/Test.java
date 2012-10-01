package tdi.xfce;

import java.io.*;
import java.util.*;

import javax.swing.JFrame;

public class Test extends JFrame{
	
	private static File iconsFile=new File("/home/viktor/.config/xfce4/desktop/icons.screen0-1584x853.rc");
	private Vector<Icon> vs=new Vector<Icon>();

	public void initDesk()
	{
		this.setSize(800, 600);
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
		initDesk();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Test();

	}

}
