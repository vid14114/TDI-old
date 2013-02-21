package control;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class MyDocumentListener implements DocumentListener{

	public int xAxis;
	public int yAxis;
	public double rotation;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void setY(String i)
	{
		yAxis=Integer.valueOf(i);
	}
	public void setX(String i)
	{
		xAxis=Integer.valueOf(i);
	}
	public void setRotation(String i)
	{
		rotation=Double.valueOf(i);
	}
	
	public int getY()
	{
		return yAxis;
	}
	public int getX()
	{
		return xAxis;
	}
	public double getRotation()
	{
		return rotation;
	}
	
	@Override
	public void insertUpdate(DocumentEvent y) {
		// TODO Auto-generated method stub
		// Gives notification that there was an insert into the document.
		
		try {
			if(y.getDocument().getProperty("TextField").equals("xAxis"))
			{
				System.out.println(y.getDocument().getText(0,( y.getDocument().getLength())));
				setX(y.getDocument().getText(0,( y.getDocument().getLength())));
			}
			if(y.getDocument().getProperty("TextField").equals("yAxis"))
			{
				setY(y.getDocument().getText(0,( y.getDocument().getLength())));
			}
			if(y.getDocument().getProperty("TextField").equals("rotation"))
			{
				setRotation(y.getDocument().getText(0,( y.getDocument().getLength())));
			}
			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void removeUpdate(DocumentEvent z) {
		// TODO Auto-generated method stub
		// Gives notification that a portion of the document has been removed.
		try {
			if(z.getDocument().getProperty("TextField").equals("xAxis"))
			{
				System.out.println(z.getDocument().getText(0,( z.getDocument().getLength())));
				setX(z.getDocument().getText(0,( z.getDocument().getLength())));
			}
			if(z.getDocument().getProperty("TextField").equals("yAxis"))
			{
				setY(z.getDocument().getText(0,( z.getDocument().getLength())));
			}
			if(z.getDocument().getProperty("TextField").equals("rotation"))
			{
				setRotation(z.getDocument().getText(0,( z.getDocument().getLength())));
			}
			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		// not used for plain text objects
	}

}
