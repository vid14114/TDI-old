package control;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyDocumentListener implements DocumentListener{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	 
    
	@Override
	public void changedUpdate(DocumentEvent x) {
		// TODO Auto-generated method stub
		// Gives notification that an attribute or set of attributes changed.
		System.out.println(x.getDocument());
		
	}
	
	@Override
	public void insertUpdate(DocumentEvent y) {
		// TODO Auto-generated method stub
		// Gives notification that there was an insert into the document.
		
	}
	
	@Override
	public void removeUpdate(DocumentEvent z) {
		// TODO Auto-generated method stub
		// Gives notification that a portion of the document has been removed.
		
	}

}
