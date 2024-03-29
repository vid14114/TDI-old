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
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;
import model.Icon;

public class Configuration{
	private File iconsFile;
	private Image background;
	protected ArrayList<Icon> icons = new ArrayList<>();
	
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
		Collections.sort(icons,new Comparator<Icon>() {
			@Override
			public int compare(Icon o1, Icon o2) {
				if(o1.getRow() == o2.getRow())
					return o1.getCol() - o2.getCol();
				return o1.getRow() - o2.getRow();
			}
			
		});
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
	
	/**
	 * This method calculates the resolution of the user's pc 
	 * @return An array with the resolution of the user's pc in the format [width] [height]
	 */
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
	
	/**
	 * Calculate the maximum amount of rows the desktop supports. 
	 * @return the estimated maximum of rows allowed
	 * @throws IOException When a file is corrupt
	 */
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
	
	/**
	 * Calculates the maximum amount of cols the desktop supports	 
	 * @return an estimated amount of cols allowed 
	 * @throws IOException When the file is corrupt
	 */
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
}
