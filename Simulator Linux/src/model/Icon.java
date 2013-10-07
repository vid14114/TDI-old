package model;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import control.Xpm;

/**
 * @author TDI Team
 * Every icons name, config, row and col position and other important info is saved in the Icon's class
 */
public class Icon {
	
	//Name as-seen on the desktop
	private String name;
	private File config;
	private String iconName;
	private int row;
	private int col;
	private ImageIcon icon;
	private int searchDepth = 0;
	private String exec=""; //The execution path of the icon, when it is a program
	private Process process; //When the user is started, saves the process in the Icon
	File dir=new File("/usr/share/icons");
	/**
	 * The id used to minimize, maximize and close programs
	 */
	private String wmctrl;
	boolean minimized; // true -> program is minimized
	
	@Override
	/**
	 * custom equals for the contains-method of the vector
	 */
	public boolean equals(Object obj) {
		Icon i=(Icon)obj;
		if(i.getIcon().equals(icon))
			return true;
		return false;
	}

	public Icon(String name, int row, int col)
	{
		setName(name);
		setRow(row);
		setCol(col);
		
	}
	
	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public void getConfig() throws IOException
	{
		findConfig();
		if(config!=null)
		{
			iconName=getIconVar("Icon");
			exec=getIconVar("Exec");
			if(exec.contains("%U") || exec.contains("%F"))
				exec=exec.substring(0, exec.length()-3);
			if(iconName!=null)
				findIcon(dir);
		}
	}
	
	public void setConfName(File confName) {
		this.config=confName;
	}
	
	public void setIcon(javax.swing.Icon icon, boolean first) {
		ImageIcon imgic=(ImageIcon) icon;
		if(first)
		{
			BufferedImage icon2=new BufferedImage(imgic.getIconWidth()+40, imgic.getIconHeight()+10, BufferedImage.TYPE_INT_ARGB);
			icon2.getGraphics().drawImage(imgic.getImage(),0,0, imgic.getImageObserver());		
			icon2.getGraphics().setFont(new Font("Serif", Font.BOLD, 30));
			icon2.getGraphics().drawString(name.substring(1, name.length()-1), 0, 55);
			icon2.setRGB(row, col, 0);
			imgic=new ImageIcon(icon2);
		}
		this.icon=imgic;
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
	
	public String getExec()
	{
		return exec;
	}
	
	/**
	 * Searches for the directory of the icon file
	 * Currently only works in english and german
	 * @throws IOException
	 */
	public void findConfig() throws IOException
	{
		setIcon(new ImageIcon("/usr/share/icons/gnome/48x48/status/dialog-question.png"), true);
		File[] files;
		if(new File(System.getProperty("user.home")+"/Arbeitsfl��che").exists())
			files=new File(System.getProperty("user.home")+"/Arbeitsfl��che").listFiles();
		else
			files=new File(System.getProperty("user.home")+"/Desktop").listFiles();
		for(File file : files)
		{
			if(file.getName().equals(name.substring(1, name.length()-1)) && file.canExecute() && !file.isDirectory())
			{
				setIcon(new ImageIcon("/usr/share/icons/gnome/48x48/apps/utilities-terminal.png"), true);
				BufferedReader br=new BufferedReader(new FileReader(file));
				while(br.ready())
					exec+=""+br.readLine()+"\n";
				br.close();
				break;
			}
			if(file.isDirectory()) 
			{
				if(file.getName().equals(name.substring(1, name.length()-1)))
				{
					setIcon (new ImageIcon("/usr/share/icons/gnome/48x48/places/folder.png"), true);
					exec = "thunar "+file.getAbsolutePath();
					break;
				}
				continue; //directly jump to the next element of the for loop
			}
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for(String line=br.readLine(); line!=null; line=br.readLine())
			{
				String[] splitLine=line.split("=");
				if(splitLine.length>0 && splitLine[0].equals("Name"))
				{
					if(splitLine[1].equals(name.substring(1, name.length()-1)))
						config=file;
					break;
				}
			}
			br.close();
		}
	}
	/**
	 * The icons var is searched in the config file
	 * @param iconName The name of the icon
	 * @return returns a specific variable out the confName
	 * @throws IOException
	 */
	public String getIconVar(String iconName) throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(config)));
		for(String line=br.readLine(); line!=null; line=br.readLine())
		{
			String[] splitLine=line.split("=");
			if(splitLine[0].equals(iconName)){
				br.close();
				if(iconName.equals("Icon") && splitLine.length<2)
					return null;
				return splitLine[1];
			}
		}
		br.close();
		return null;
	}
	
	
	
	/** @Vidovic--> more info please
	 * Goes through all subdirectories of a given folder to find a graphic that matches the icon
	 * @param dir From here the program searches an Icon to display
	 * @return An arraylist of the files it needs
	 * @throws IOException
	 */
	public ArrayList<File> findIcon(File dir) throws IOException
	{	
		if(new File(iconName).exists() && iconName.contains(".xpm"))
		{
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(iconName)));
			String s="";
			for(String line=br.readLine(); line!=null; line=br.readLine())
				s+=line+"\n";
			Image i=Xpm.XpmToImage(s);
			setIcon(new ImageIcon(i), true);
			br.close();
		}
		File[] files=dir.listFiles();
		ArrayList<File> result=new ArrayList<File>();
		if(files!=null)		
		{
			for(File file : files)
			{
				if(file.isFile() && file.getName().contains(iconName))
				{
					setIcon(new ImageIcon(file.getAbsolutePath()),true);
					result.add(file);
				}
				else
				{
					if(file.isDirectory())
					{
						ArrayList<File> tmp = findIcon(file);
						for(File thisFile : tmp)
							if(thisFile.getName().contains(iconName))
							{
								setIcon(new ImageIcon(file.getAbsolutePath()),true);
								result.add(thisFile);
							}
					}
				}
			}
		}
		if(searchDepth<1)
		{
			searchDepth++;
			result.addAll(findIcon(new File("/usr/share/pixmaps")));
		}
		if(result.size()>0)
		{
			for(int i=0; i< result.size(); i++)
			{
				if(result.get(i).getAbsolutePath().contains("48"))
				{
					setIcon(new ImageIcon(result.get(i).getAbsolutePath()),true);
					return result;
				}
			}
			setIcon(new ImageIcon(result.get(0).getAbsolutePath()),true);
			icon.setImage(icon.getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));
		}
			
			
		return result;	
	}

	/**
	 * @return the wmctrl id used to manipulate programs
	 */
	public String getWmctrl() {
		return wmctrl;
	}

	/**
	 * Sets the wmctrl id
	 * @param wmctrl
	 */
	public void setWmctrl(String wmctrl) {
		this.wmctrl = wmctrl;
	}
}
