package control;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import view.Desk;

import model.TUIO;

public class MyDocumentListener implements DocumentListener{
// reads the values out of the textboxes that show the rotation and the x/y axis
// it returns Double values
	public String xAxis;
	public String yAxis;
	public String rotation;
	private Desk d;
	/**
	 * @param args
	 */
	public MyDocumentListener(Desk d)
	{
		this.d = d;
	}
	public void main(String[] args) {
		// TODO Auto-generated method stub
			}
// getters and setters
	public void setY(String str)
	{
		yAxis=str;
	}
	public void setX(String str)
	{
		xAxis=str;
	}
	public void setRotation(String str)
	{
		rotation=str;
	}
	
	public int getY()
	{
		if(!yAxis.equals("")){ // If the value is not empty. (Can happen when being focused, because the TUIOMouseListener changes values when clicking etc.)
		try
		{
			return Integer.parseInt(yAxis);
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "Please insert only numbers");
		}
		}
		return 0;
	}
	public int getX()
	{
		if(!xAxis.equals("")){ //If the value is not empty. (Can happen when being focused, because the TUIOMouseListener changes values when clicking etc.)
		try
		{
			return Integer.parseInt(xAxis);
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "Please insert only numbers");
		}
		}
		return 0;
	}
	public double getRotation()
	{
		if(!rotation.equals("")){ //If the value is not empty. (Can happen when being focused, because the TUIOMouseListener changes values when clicking etc.)
		try
		{	
			return Double.parseDouble(rotation);
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "Please insert only numbers");
		}
		}
		return 0;
	}
	
	@Override
	public void insertUpdate(DocumentEvent docEvent) {
		// TODO Auto-generated method stub
		// Gives notification that there was an insert into the document  (= the textField).
		
		try {
			if(docEvent.getDocument().getProperty("TextField").equals("xAxis")) // checks if the TextField xAxis has been changed 
			{
				setX(docEvent.getDocument().getText(0,( docEvent.getDocument().getLength()))); // sets the local variable xAxis
				// setX(docEvent.getDocument().getText(min,max))
				for(TUIO t:Main.tuios.values())
				{
					if(t.getId()==d.getIdJLabel()) // if same id (in the TextField and for the actual Tuio in the array)
					{
						t.setxPos(getX()); // changes the position of the TUIO
					}
				}
			}
			if(docEvent.getDocument().getProperty("TextField").equals("yAxis")) // checks if the TextField  yAxis has been changed 
			{
				setY(docEvent.getDocument().getText(0,( docEvent.getDocument().getLength()))); // sets the local variable yAxis
				for(TUIO t:Main.tuios.values())
				{
					if(t.getId()==d.getIdJLabel()) // if same id (in the TextField and for the actual Tuio in the array)
					{
						t.setyPos(getY());// changes the position of the TUIO
					}
				}
			}
			if(docEvent.getDocument().getProperty("TextField").equals("rotation")) // checks is the TextField rotation has been changed 
			{
				setRotation(docEvent.getDocument().getText(0,( docEvent.getDocument().getLength()))); // sets the local variable rotation
				for(TUIO t:Main.tuios.values())
				{
					if(t.getId()==d.getIdJLabel()) // if same id (in the TextField and for the actual Tuio in the array)
					{
						t.setRotation(getRotation());// changes the position of the TUIO
					}
				}
			}
			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		d.repaint();
	}
	
	@Override
	public void removeUpdate(DocumentEvent docEvent) {
		// TODO Auto-generated method stub
		// Gives notification that a portion of the document (= the textField) has been removed.
		try {
			if(docEvent.getDocument().getProperty("TextField").equals("xAxis")) // checks if the  TextField xAxis has been changed (deletion)
			{
				setX(docEvent.getDocument().getText(0,( docEvent.getDocument().getLength()))); // sets the local variable xAxis
				for(TUIO t:Main.tuios.values())
				{
					if(t.getId()==d.getIdJLabel()) // if same id (in the TextField and for the actual Tuio in the array)
					{
						t.setxPos(getX());// changes the position of the TUIO
					}
				}
			}
			if(docEvent.getDocument().getProperty("TextField").equals("yAxis"))  // checks if the TextField yAxis has been changed (deletion)
			{
				setY(docEvent.getDocument().getText(0,( docEvent.getDocument().getLength()))); // sets the local variable yAxis
				for(TUIO t:Main.tuios.values())
				{
					if(t.getId()==d.getIdJLabel()) // if same id (in the TextField and for the actual Tuio in the array)
					{
						t.setyPos(getY());// changes the position of the TUIO
					}
				}
			}
			if(docEvent.getDocument().getProperty("TextField").equals("rotation"))  // checks if the TextField rotation has been changed (deletion)
			{
				setRotation(docEvent.getDocument().getText(0,( docEvent.getDocument().getLength()))); // sets the local variable rotation
				for(TUIO t:Main.tuios.values())
				{
					if(t.getId()==d.getIdJLabel()) // if same id (in the TextField and for the actual Tuio in the array)
					{
						t.setRotation(getRotation());// changes the position of the TUIO
					}
				}
			}
			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		d.repaint();
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		// not used for plain text objects
	}

}
