package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import view.Desk;

public class MenuMouseListener implements MouseListener{

	Desk d;
	public MenuMouseListener(Desk d){
		this.d=d;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		JFrame frame = new JFrame();					           
        frame.setTitle("About");
        frame.setSize(500,500);
        frame.setLocation(250, 100);
        JPanel panel = new JPanel();
        frame.add(panel);
        JTextArea text = new JTextArea("", 5, 50);
        text.setLineWrap(true);
        JScrollPane sbrText = new JScrollPane(text);
        sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.requestFocus();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
