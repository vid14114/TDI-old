package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.Draw.drawableStuff;

public class Desk extends JFrame{
	private static final long serialVersionUID = -5944444691938882393L;

	public Desk() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 600);
		setLayout(new BorderLayout());
		add(workDesk(), BorderLayout.CENTER);
		add(menuDesk(), BorderLayout.EAST);
		setVisible(true);
	}

	public JPanel workDesk() {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		Draw t = new Draw(10, this.getSize().height/5*4, 100000, 1);
		t.setDrawing(drawableStuff.Rectangle);
		p.add(t, BorderLayout.CENTER);
		return p;
	}

	public JPanel menuDesk() {
		JPanel p = new JPanel();
		JButton b1 = new JButton("Test");
		JButton b2 = new JButton("Test2");
		p.add(b1);
		p.add(b2);
		return p;
	}
}
