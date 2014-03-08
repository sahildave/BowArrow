package BowAndArrow;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class BAA1 extends Applet implements MouseListener,MouseMotionListener,Runnable

{
	
	Image pics[]=new Image[12];
	Image current,arrow;
	Thread t;
	int num,posX,posY,i=0,j=0,cursor=0,k=0;
	
	public void init()
	{
		String imageString[]={"S20.png","S20 (1).png","S20 (2).png","S20 (3).png","S20 (4).png",
				"S20 (5).png","S30.png","S30 (1).png","S40.png","S40 (1).png","H50.png","B-10.png"};
		for(int i=0;i<imageString.length;i++)
		{
			pics[i] = getImage(getDocumentBase(),imageString[i]);	
		}
		addMouseListener(this);
		addMouseMotionListener(this);
		arrow=getImage(getDocumentBase(),"1.PNG");
	}
	//start
	public void start()
	{
		t= new Thread(this);
		t.start();
	}
	//run
	public void run()
	{
		while(true)
		{
			num=(int)((Math.random())*12);
			loadpic(num);	
		}
	}
	//loadpic
	public void loadpic(int i)
	{
		current=pics[i];
		posX=(int)(Math.random()*950);
		j=0;
		
		while(j<751)
		{
			k=k+10;
			
			repaint();
			//System.out.println("in while j = "+j);
			//System.out.println("in while k = "+k);
			pause(100);
			j=j+30;
		}
		
	}
	//pause
	public void pause(int t)
	{
		try 
		{
			Thread.sleep(t);
		}
		catch (InterruptedException e) {}
	}
	
	//mouseEvents
	public void mouseMoved(MouseEvent e)
	{
		posY=e.getY();
		showStatus("mouse moved");
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	
	//grpahics
	public void paint(Graphics g)
	{
		if (current != null)
		{
			setSize(1024,668);
			
			g.drawImage(current, posX, 548-j, this);
			
			g.drawImage(arrow,0, posY, null);
			g.drawImage(arrow,k+0, posY, null);
			
			g.drawString(0+","+posY,i+0, posY);
			g.drawString("arrow at "+k+","+posY,0, 620);
			
			g.drawString(posX+","+(548-j), 0, 600);
			g.drawString("BALLOON NO. "+num, 0, 610);
		}
	}
	
	
}
