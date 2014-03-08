package Balloon;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class Balloon4 extends Applet implements Runnable

{
	
	Image pics[]=new Image[12];
	Image pics1[]=new Image[12];
	Image pics2[]=new Image[12];
	Image pics3[]=new Image[12];
	Image current,current1,current2,current3;

	Thread t;
	int num,j=0;
	int posX,posX1,posX2,posX3;
	
	public void init()
	{
		String imageString[]={"S20.png","S20 (1).png","S20 (2).png","S20 (3).png","S20 (4).png",
				"S20 (5).png","S30.png","S30 (1).png","S40.png","S40 (1).png","H50.png","B-10.png"};
		for(int i=0;i<imageString.length;i++)
		{
			pics[i] = getImage(getDocumentBase(),imageString[i]);
			pics1[i] = getImage(getDocumentBase(),imageString[i]);
			pics2[i] = getImage(getDocumentBase(),imageString[i]);
			pics3[i] = getImage(getDocumentBase(),imageString[i]);
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
			loadpic();	
			
			
		}
	}

	
	public void loadpic()
	{
		current=pics[(int)((Math.random())*11)];
		current1=pics[(int)((Math.random())*11)];
		current2=pics[(int)((Math.random())*11)];
		current3=pics[(int)((Math.random())*11)];
		posX=(int)(Math.random()*950);
		posX1=(int)(Math.random()*950);
		posX2=(int)(Math.random()*950);
		posX3=(int)(Math.random()*950);
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
			g.drawString("BALLOON NO. "+num, 0, 610);
			g.drawImage(current, posX, 548-j, this);
			g.drawImage(current1, posX1, 548-j, this);
			g.drawImage(current2, posX2, 548-j, this);
			g.drawImage(current3, posX3, 548-j, this);
		}
	}
	
	
}
