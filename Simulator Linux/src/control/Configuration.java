package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Icon;

public class Configuration {
	private File iconsFile;
	
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
				// Here i remove the [] which are used to mark the beginning of an icon
				line.replace("[", "");
				line.replace("]", "");
				if(line.contains("~"))
					line.replace("~", ""); //Unix adds the ~ symbol when a file has been currently changed, i remove this symbol
				i = new Icon(line, Integer.parseInt(br.readLine().split("=")[1]), Integer.parseInt(br.readLine().split("=")[1]));
				i.getConfig();
			}
		}
		br.close();
		//Old way of getting the icons
//		for(line=br.readLine(); line!=null; line=br.readLine())
//		{
//			switch(lineNum)
//			{
//			case 1:
//				i=new Icon();
//				i.setName(line);
//				break;
//			case 2:
//				i.setRow(Integer.parseInt(line.split("=")[1]));
//				break;
//			case 3:
//				i.setCol(Integer.parseInt(line.split("=")[1]));
//				i.getConfig();
//				vs.add(i);
//				break;
//				//after every Icon there's a blank row
//			case 4:
//				lineNum=0;
//				break;
//			}
//			lineNum++;		
//		}
//		br.close();
	}

	
	//look for the newest file in the icons configuration folder because that will be the one for the actual desktop resolution
	/**
	 * The method looks for the last modified file in a directory
	 * This method is important because it helps find out the currently used screen resolution
	 * and enables the program to load the correct icons
	 * @param dir
	 * @return
	 */
	public static File lastFileModified(String dir) {		
        File[] files = new File(dir).listFiles(new FileFilter() {                  
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
}
