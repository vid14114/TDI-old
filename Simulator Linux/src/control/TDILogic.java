package control;

import java.util.ArrayList;

import model.Icon;
import model.TUIO;

public class TDILogic implements Runnable{
	private ArrayList<Icon> icons;
	private ArrayList<TUIO> tuios;
	private static TDIServer server;
	static int tableWidth=800;	//in reality we need to get these values from the tracking system
	static int tableHeight=600;
	//multidimensional ArrayList, because not every TUIO has the same number of icons
	private ArrayList<ArrayList<Icon>> assignments=new ArrayList<ArrayList<Icon>>();
	
	public TDILogic(ArrayList<Icon> icons, ArrayList<TUIO> tuios)
	{
		this.icons=icons;
		this.tuios=tuios;
	}
	//assign icons to the TUIOs and calculate their position on the table
	public void run() {
		//calculate how many icons per tuio
		boolean fits=true;
		int count=icons.size()/tuios.size();
		//if it doesn't fit --> add one icon more per tuio
		if(icons.size()%tuios.size()>0)
		{
			fits=false;
			count++;
		}
		int iconNum=0;
		for(int i=0; i<tuios.size(); i++)
		{
			assignments.add(new ArrayList<Icon>());
			for(int j=0; iconNum<icons.size() || j<count; iconNum++, j++)
			{
				assignments.get(i).add(icons.get(iconNum));
			}
			//if the remaining icons fit exactly to the remaining tuios
			if(!fits && (icons.size()-iconNum)%tuios.size()==0)
			{
				count--;
				fits=true;
			}
		}
		//calculate and set the position of the TDI on the table
		for(int i=0; i<assignments.size(); i++)
		{
			int rows=0;
			int cols=0;
			for(Icon icon: assignments.get(i))
			{
				rows+=icon.getRow();
				cols+=icon.getCol();
			}
			tuios.get(i).setPos(tableWidth/rows, tableHeight/cols);
		}
		for(TUIO tuio: tuios)
		{
			server.sendMessage(tuio.getId(), tuio.getPos()[0], tuio.getPos()[1], tuio.getMinimalRotation());
		}
	}
}
