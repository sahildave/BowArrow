package Balloon;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class Balloon2 extends Applet implements Runnable
{
	Image pics;
	Thread t;
	int speed,i=0;
	public void init()
	{
		pics = getImage(getDocumentBase(),"H50.png");
	}

	public void start()
	{
		t= new Thread(this);
		t.start();
	}
	
	public void run()
	{
		speed = (int)(Math.random()*50);
	//	changepos();
		while(i<3000)
		{
			repaint();
			try
			{
				Thread.sleep(50);
			}
			catch(Exception e){}
			i=i+speed;
		}
	}
	//public void changepos()
//	{
	//	speed = (int)(Math.random()*50);
		
//	}
	
	public void paint(Graphics g)
	{
		if (pics != null)
		{
			setSize(1024,668);
			g.drawImage(pics, 0, 558-i, this);
		}
	}
	
}