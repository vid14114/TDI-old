package control;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.Icon;

public class Configuration {
	private File iconsFile;
	private Image background;
	private ArrayList<Icon> icons = new ArrayList<>();
	
	/**
	 * The default constructor for the Configuration class
	 * @throws IOException Throws an IOException when a problem occurs.
	 */
	public Configuration() throws IOException{
		iconsFile = lastFileModified(System.getProperty("user.home")+"/.config/xfce4/desktop"); //Get the last configured file
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(iconsFile)));
		Icon i;
		while (br.ready()){
			String line = br.readLine();
			if(line.contains("[")){
				if(line.contains("~"))
					line.replace("~", ""); //Unix adds the ~ symbol when a file has been currently changed, i remove this symbol
				i = new Icon(line, Integer.parseInt(br.readLine().split("=")[1]), Integer.parseInt(br.readLine().split("=")[1]));
				i.getConfig();
				icons.add(i);
			}
		}
		br.close();
		br = new BufferedReader(new FileReader(System.getProperty("user.home")+"/.config/xfce4/xfconf/xfce-perchannel-xml/xfce4-desktop.xml"));
		while(br.ready()){
			String line = br.readLine();
			if((line.contains("image-path") && line.contains("value=")) || line.contains("last-image")){
				String a = line.substring(line.indexOf("value="),line.length()).split("=")[1];
				a = a.split("\"")[1];
				setBackground(Toolkit.getDefaultToolkit().getImage(a));
				br.close();
				return;
			}
		}
	}

	/**
	 * The method looks for the last modified file in a directory
	 * This method is important because it helps find out the currently used screen resolution
	 * and enables the program to load the correct icons
	 * @param dir
	 * @return
	 */
	public File lastFileModified(String dir) {		
        File[] files = new File(dir).listFiles(new FileFilter() {                  
                @Override
				public boolean accept(File file) {
                        return file.isFile();
                }
        }); //Lists all the files in the given directory
        /*
         * From here we compare the last time the files were modified
         * lastMod is the variable we use to compare the long values with each other
         */
        long lastMod = Long.MIN_VALUE;
        File choice = null;
        for (File file : files) {
                if (file.lastModified() > lastMod) {
                        choice = file;
                        lastMod = file.lastModified();
                }
        }
        return choice;
	}
	
	/**
	 * The method is used to overwrite the configuration files
	 * @param icons The arraylist which contains the updated version of the icons
	 */
	public void updateConfig(ArrayList<Icon> newIcons){
		try{
			BufferedWriter bw=new BufferedWriter(new FileWriter(iconsFile));
			for(int i=0; i<newIcons.size(); i++)
			{
				String name=newIcons.get(i).getName();
				if(newIcons.get(i).getIcon()!=null)
				{
					for(int j=0; j<icons.size(); j++)
					{
						if(icons.get(j).getIcon()!=null && newIcons.get(i).getIcon().equals(icons.get(j).getIcon()))
							name=icons.get(j).getName().substring(1, icons.get(j).getName().length()-1);
					}
				}
				bw.write("["+name+"]");
				bw.newLine();
				bw.write("row="+newIcons.get(i).getRow());
				bw.newLine();
				bw.write("col="+newIcons.get(i).getCol());	
				bw.newLine();
				bw.newLine();
			}
			bw.close();
			//refresh the desktop manager
			Runtime.getRuntime().exec("xfdesktop --reload").waitFor();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "Couldn't save the configuration file");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int[] getResolution()
	{
		int[] resolution = new int[2];
		String[] s=iconsFile.getName().split("-")[1].split("x");
		Integer width=new Integer(s[0]);
		Integer height=new Integer(s[1].split("\\.")[0]);
		resolution[0]=width.intValue();
		resolution[1]=height.intValue();
		return resolution;
	}

	/**
	 * @return the background
	 */
	public Image getBackground() {
		return background;
	}

	/**
	 * @param background the background to set
	 */
	public void setBackground(Image background) {
		this.background = background;
	}
	/**
	 * @return An Array list of the icons found
	 */
	public ArrayList<Icon> getIcons(){
		return icons;
	}
	
	public int calcRows() throws IOException
	{
		int[] resolution=getResolution();
		int height=resolution[1];
		int panelSize=0;
		BufferedReader br1=new BufferedReader(new FileReader(System.getProperty("user.home")+"/.config/xfce4/xfconf/xfce-perchannel-xml/xfce4-panel.xml"));
		while(br1.ready())
		{
			String line=br1.readLine();
			if(line.contains("size"))
				panelSize+=Integer.parseInt(line.split("\"")[5]);
		}
		br1.close();
		int margin=0;
		BufferedReader br2=new BufferedReader(new FileReader(System.getProperty("user.home")+"/.config/xfce4/xfconf/xfce-perchannel-xml/xfwm4.xml"));
		while(br2.ready())
		{
			String line=br2.readLine();
			if(line.contains("placement_ratio"))
				margin=Integer.parseInt(line.split("\"")[5]);
		}
		br2.close();
		int iconSize=0;
		BufferedReader br3=new BufferedReader(new FileReader(System.getProperty("user.home")+"/.config/xfce4/xfconf/xfce-perchannel-xml/xfce4-desktop.xml"));
		while(br3.ready())
		{
			String line=br3.readLine();
			if(line.contains("icon-size"))
				iconSize=Integer.parseInt(line.split("\"")[5]);
		}
		br3.close();
		return ((height-panelSize)-2*margin)/(iconSize+(3*margin));
	}
	
	public int calcCols() throws IOException
	{
		int[] resolution=getResolution();
		int width=resolution[0];

		int margin=0;
		BufferedReader br2=new BufferedReader(new FileReader(System.getProperty("user.home")+"/.config/xfce4/xfconf/xfce-perchannel-xml/xfwm4.xml"));
		while(br2.ready())
		{
			String line=br2.readLine();
			if(line.contains("placement_ratio"))
				margin=Integer.parseInt(line.split("\"")[5]);
		}
		br2.close();
		int iconSize=0;
		BufferedReader br3=new BufferedReader(new FileReader(System.getProperty("user.home")+"/.config/xfce4/xfconf/xfce-perchannel-xml/xfce4-desktop.xml"));
		while(br3.ready())
		{
			String line=br3.readLine();
			if(line.contains("icon-size"))
				iconSize=Integer.parseInt(line.split("\"")[5]);
		}
		br3.close();
		return ((width)-2*margin)/(iconSize+(4*margin));
	}
	
	public void monitorPrograms(JLabel[] taskLabels, JLabel[][] labels) throws IOException{
		for(int i=0; i<taskLabels.length; i++) //opening a program
		{
			for(int j=0; j<icons.size(); j++)
			{
				if(taskLabels[i].getIcon() != null && taskLabels[i].getIcon().equals(icons.get(j).getIcon()) && icons.get(j).getWmctrl()==null)
				{
					// Execute the program and set the and set the save the process in icons
					String exec = icons.get(j).getExec();
					icons.get(j).setProcess(Runtime.getRuntime().exec((exec)));
					//Get the process id of the program
					/*
					 * 1: Get a list of all pid
					 * 2: Find the line with our pid
					 * 3: Remove the whitespaces and save our pid
					 * 4: Do the same thing to find out our wmctrl-id
					 */
					BufferedReader br_pid=new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("ps -a").getInputStream())); //Get a list of all pids
					String pid_line;
					while((pid_line = br_pid.readLine()) !=null && !pid_line.contains(exec)); //Shorter way of searching for the right line
					pid_line=pid_line.trim(); //Removes all the leading or trailing whitespaces
					int i2 = 0;
					for(; i2 < pid_line.length() && Character.isDigit(pid_line.charAt(i2)); i2++);					
					pid_line=pid_line.substring(1, i2);
					
					while(icons.get(j).getWmctrl()==null) //some windows need long to open... need to wait until they are fully opened 
					{
						BufferedReader br_wmctrl=new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("wmctrl -lp").getInputStream()));
						String wmctrl_line;
						while((wmctrl_line = br_wmctrl.readLine()) !=null && !wmctrl_line.contains(pid_line));						
						if(wmctrl_line==null)
							continue;
						for(i2 = 2; i2 < wmctrl_line.length(); i2++) //index begins at 2 because every wmctrl-ID starts with '0x...'
						{
							if(wmctrl_line.charAt(i2)==' ')
							{
								icons.get(j).setWmctrl(wmctrl_line.substring(0, i2));
								break; //no need to furthermore iterate through the loop
							}
						}
					}
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
				if(icons.get(i).getWmctrl()!=null)
				{
					String[] cmd={"wmctrl", "-i", "-c", ""+icons.get(i).getWmctrl()};
					Runtime.getRuntime().exec(cmd);
					icons.get(i).setWmctrl(null);
				}
			}
		}
	}
}
