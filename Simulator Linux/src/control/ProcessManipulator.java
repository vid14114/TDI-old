/**
 * 
 */
package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JLabel;
import model.Icon;

/**
 * @author TDI Team
 * This class should be the main entry point of manipulating our programs
 * All methods should be statically accessible
 */
public class ProcessManipulator {

	public static void minimizeProgram(String wmctrlID){
		String []cmd = {"wmctrl", "-i", "-r", wmctrlID, "-b", "toggle,below"};
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			System.err.println("An error happened while trying to minimize a program");
		}
	}
	
	public static void moveProgram(String wmctrlID, int x, int y, int widht, int heigth){
		String []cmd = {"wmctrl", "-i", "-r", wmctrlID, "-e", "0,"+x+","+y+","+widht+","+heigth};
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			System.err.println("An error happened while trying to move or resize a program");
		}
	}
	
	
	public static void maximizeProgram(String wmctrlID) throws IOException
	{
		String[] cmd={"wmctrl", "-i", "-r", wmctrlID, "-b", "toggle,maximized_vert,maximized_horz"};
		Runtime.getRuntime().exec(cmd);
	}
	
	public static void monitorPrograms(ArrayList<Icon> icons, JLabel[] taskLabels, JLabel[][] labels) throws IOException{
		for(int i=0; i<taskLabels.length; i++) //opening a program
		{
			for(int j=0; j<icons.size(); j++)
			{
				if(taskLabels[i].getIcon() != null && taskLabels[i].getIcon().equals(icons.get(j).getIcon()) && icons.get(j).getWmctrl()==null)
				{
					// Execute the program and save the process in icons
					String exec = icons.get(j).getExec();
					try {
						icons.get(j).setProcess(Runtime.getRuntime().exec((exec)));
					} catch (IOException e) {
						System.err.println("Error while trying to execute program with exec path:"+exec);
					}
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
					try {
						Runtime.getRuntime().exec(cmd);
					} catch (IOException e) {
						System.err.println("An error occured while trying to close a program");
					}
					icons.get(i).setWmctrl(null);
				}
			}
		}
	}
}
