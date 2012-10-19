package tdi.xfce;

import java.io.File;
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
		findIcon(dir);
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
	
	public ArrayList<File> findIcon(File dir)
	{	
		File[] files=dir.listFiles();
		ArrayList<File> result=new ArrayList<File>();
		if(files!=null)
		{
			for(File file : files)
			{
				if(file.isFile() && file.getName().contains(name.substring(1, name.length()-1)))
					result.add(file);
				else
					if(file.isDirectory())
					{
						ArrayList<File> tmp = findIcon(file);
						for(File thisFile : tmp)
							if(thisFile.getName().contains(name))
								result.add(thisFile);
					}
			}
		}
		return result;	
	}
}
