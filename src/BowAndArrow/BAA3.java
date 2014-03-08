package BowAndArrow;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class BAA3 extends Applet implements MouseListener,MouseMotionListener,Runnable

{
	Image pics[]=new Image[12];
	Image current,arrow,burst,back;
	Thread t;
	int num,posX,posY,i=0,j=0,cursor=0;
	int posAX,posAY;
	int boom=0;
	int pos=-100;
	int posBurstY,posBurstX;
	
	public void init()
	{
		String imageString[]={"S20.png","S20 (1).png","S20 (2).png","S20 (3).png","S20 (4).png",
				"S20 (5).png","S30.png","S30 (1).png","S40.png","S40 (1).png","H50.png","B-10.png"};
		String imageString2[]={"ex.jpg","ex1.jpg"};
		for(int i=0;i<imageString.length;i++)
		{
			pics[i] = getImage(getDocumentBase(),imageString[i]);	
		}
		back=getImage(getDocumentBase(),"STAGE I - Morning Glory 2.JPG");
		burst=getImage(getDocumentBase(),"ex.jpg");
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
		while(boom!=1)
		{
			num=(int)((Math.random())*12);					// for random balloon
			loadpic(num);	
		}
	}
	
	//loadpic
	public void loadpic(int i)
	{
		current=pics[i];
		posX=(int)(Math.random()*950);						//	for random position of balloon
		j=0;
		while(j<751&&boom==2)
		{
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
	public void mouseClicked(MouseEvent e) 
	{
		pos=e.getY();
		cursor=1;
		showStatus("mouse clicked");
		repaint();	
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	
	//graphics
	public void paint(Graphics g)
	{	
		g.drawImage(back,0,0,this);
		if (current != null)
		{
			setSize(1024,668);
			
			g.drawString("arrow at "+i+","+posY,0, 620);
			g.drawString(posX+","+(548-j), 0, 600);
			g.drawString("BALLOON NO. "+num, 0, 610);
			
			for(int x=0;x<150;x++)
			{
				if((posAX+100)==posX&&(548-j+x)==pos)	//	For bursting of balloon,
				{										//	if the coordinate matches
					boom=1;								//	in specific region the 
					posBurstY  = 548-j+x;				//	image of explosion would 
					posBurstX=posX;						//	be drawn.
				}
			}
			if(boom==1)
			{
				g.drawImage(burst,posBurstX, posBurstY,this);	//drawing image of explosion			
			}
			else
			{
				//balloon
				g.drawImage(current, posX, 548-j, this);
				//arrow
				g.drawImage(arrow,0, posY, null);		//	arrow always at the left of screen
				g.drawImage(arrow,i+0, pos, null);		//	moving arrow
				posAX=i;posAY=posY;	
				boom=2;
				if(cursor==1&&i<1000)
				{
					i++;
				}
				repaint();
			}
		}
	}
	
	
}
