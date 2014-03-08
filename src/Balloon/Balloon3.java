package Balloon;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class Balloon3 extends Applet implements Runnable

{
	
	Image pics[]=new Image[12];
	Image current;
	Thread t;
	int num,posX,posY,j=0;
	
	public void init()
	{
		String imageString[]={"S20.png","S20 (1).png","S20 (2).png","S20 (3).png","S20 (4).png",
				"S20 (5).png","S30.png","S30 (1).png","B-10.png","S40.png","S40 (1).png","H50.png"};
		for(int i=0;i<imageString.length;i++)
		{
			pics[i] = getImage(getDocumentBase(),imageString[i]);
			
		}
	}
	
	public void start()
	{
		t= new Thread(this);
		t.start();
	}
	
	public void run()
	{
		while(true)
		{
			num=(int)((Math.random())*12);
			loadpic(num);	
			
		}
	}

	public void loadpic(int i)
	{
		current=pics[i];
		posX=(int)(Math.random()*950);
		repaint();
		j=0;
		while(j<751)
		{
			repaint();
		//	System.out.println("in while "+j);
			pause(100);
			j=j+30;
		}
		pause(1000);
	}
	
	public void pause(int t)
	{
		try 
		{
			Thread.sleep(t);
		}
		catch (InterruptedException e) {}
	}

	public void paint(Graphics g)
	{
		
		if (current != null)
		{
			setSize(1024,668);
			g.drawString(posX+","+(548-j), 0, 600);
			g.drawString("BALLOON NO. "+(num+1), 0, 610);
			g.drawImage(current, posX, 548-j, this);
			
		}
	}
	
	
}
