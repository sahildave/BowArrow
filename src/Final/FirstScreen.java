package Final;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class FirstScreen extends JApplet implements ActionListener
{
	JLabel label;
	JButton play,quit;
	
	public  void init()
	{
		setLayout(null);
		
		this.setSize(539,329);
	
		ImageIcon lb = new ImageIcon("back.PNG"); 
	    label = new JLabel(lb); 
		
	    label.setBounds(0,0,539,329);
		add(label);
		
		play=new JButton("PLAY",new ImageIcon ("playimage.png"));
		quit= new JButton("QUIT",new ImageIcon ("quitimage.png"));
		
		play.setBounds(343, 237, 171, 63);
		quit.setBounds(50, 237, 171, 63);
		
		label.add(play);
		label.add(quit);
		
		play.addActionListener(this);
		quit.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==quit)
		{
			System.exit(0);
		}
		if(e.getSource()==play)
		{
			GameScreen gs = new GameScreen();
			gs.setVisible(true);
		}
	}
}