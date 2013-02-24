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
	public void setY(String i)
	{
		yAxis=i;
	}
	public void setX(String i)
	{
		xAxis=i;
	}
	public void setRotation(String i)
	{
		rotation=i;
	}
	
	public int getY()
	{
		try
		{
			return Integer.parseInt(xAxis);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please insert only numbers");
		}
		return 0;
	}
	public int getX()
	{
		try
		{
			return Integer.parseInt(xAxis);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please insert only numbers");
		}
		return 0;
	}
	public double getRotation()
	{
		try
		{
			return Double.parseDouble(rotation);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please insert only numbers");
		}
		return 0;
	}
	
	@Override
	public void insertUpdate(DocumentEvent y) {
		// TODO Auto-generated method stub
		// Gives notification that there was an insert into the document  (= the textField).
		
		try {
			if(y.getDocument().getProperty("TextField").equals("xAxis")) // checks witch TextField has been changed 
			{
				setX(y.getDocument().getText(0,( y.getDocument().getLength()))); // sets the local variable
				for(TUIO t:Main.tuios.values())
				{
					if(t.getId()==d.getIdJLabel()) // if same id 
					{
						t.setxPos(getX());
					}
				}
			}
			if(y.getDocument().getProperty("TextField").equals("yAxis")) // checks witch TextField has been changed 
			{
				setY(y.getDocument().getText(0,( y.getDocument().getLength()))); // sets the local variable
				for(TUIO t:Main.tuios.values())
				{
					if(t.getId()==d.getIdJLabel()) // if same id 
					{
						t.setyPos(getY());
					}
				}
			}
			if(y.getDocument().getProperty("TextField").equals("rotation")) // checks witch TextField has been changed 
			{
				setRotation(y.getDocument().getText(0,( y.getDocument().getLength()))); // sets the local variable
				for(TUIO t:Main.tuios.values())
				{
					if(t.getId()==d.getIdJLabel()) // if same id 
					{
						t.setRotation(getRotation());
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
	public void removeUpdate(DocumentEvent z) {
		// TODO Auto-generated method stub
		// Gives notification that a portion of the document (= the textField) has been removed.
		try {
			if(z.getDocument().getProperty("TextField").equals("xAxis")) // checks witch TextField has been changed 
			{
				setX(z.getDocument().getText(0,( z.getDocument().getLength()))); // sets the local variable
			}
			if(z.getDocument().getProperty("TextField").equals("yAxis"))  // checks witch TextField has been changed 
			{
				setY(z.getDocument().getText(0,( z.getDocument().getLength()))); // sets the local variable
			}
			if(z.getDocument().getProperty("TextField").equals("rotation"))  // checks witch TextField has been changed 
			{
				setRotation(z.getDocument().getText(0,( z.getDocument().getLength()))); // sets the local variable
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
