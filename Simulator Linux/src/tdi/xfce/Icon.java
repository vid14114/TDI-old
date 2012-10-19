package tdi.xfce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 *
 * @author Viktor Vidovic
 * Every instance of the class save one icon with its name and position on the grid
 *
 */
public class Icon {
	
	private String name;
	private String iconName;
	private int row;
	private int col;
	private ImageIcon icon;
	File dir=new File("/usr/share/icons");
	
	public Icon()
	{
	}
	
	@Override
	/**
	 * custom equals for the contains-method of the vector
	 */
	public boolean equals(Object obj) {
		Icon i=(Icon)obj;
		if(i.getName().equals(name))
			return true;
		return false;
	}

	public Icon(String name, int row, int col)
	{
		setName(name);
		setRow(row);
		setCol(col);
	}
	
	public void setIconName(String iconName) {
		this.iconName=iconName;
	}
	
	public void setIcon(ImageIcon icon) {
		this.icon=icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	public ImageIcon getIcon() {
		return icon;
	}
	
	public String getIconVar(File usrDsk, String what) throws IOException
	{
		String result=" ";
		File[] files=usrDsk.listFiles();
		for(File file : files)
		{
			if(file.getName().contains(name.substring(1, name.length()-1)))
			{
				BufferedReader br;
				try {
					br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
					String line;
					for(line=br.readLine(); line!=null; line=br.readLine())
					{
						if(line.contains(what))
						{
							result=line.split("=")[1];
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}	
		iconName=result;
		return result;
	}
	
	public ArrayList<File> findIcon(File dir)
	{	
		File[] files=dir.listFiles();
		ArrayList<File> result=new ArrayList<File>();
		if(files!=null)		
		{
			for(File file : files)
			{
				if(file.isFile() && file.getName().contains(iconName))
				{
					icon=new ImageIcon(file.getAbsolutePath());
					result.add(file);
				}
				else
					if(file.isDirectory())
					{
						ArrayList<File> tmp = findIcon(file);
						for(File thisFile : tmp)
							if(thisFile.getName().contains(iconName))
							{
								icon=new ImageIcon(file.getAbsolutePath());
								result.add(thisFile);
							}
					}
			}
		}
		if(result.size()>0)
			icon=new ImageIcon(result.get(0).getAbsolutePath());
		return result;	
	}
}
