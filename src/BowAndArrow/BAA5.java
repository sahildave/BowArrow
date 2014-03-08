package BowAndArrow;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class BAA5 extends Applet implements MouseListener,MouseMotionListener,Runnable

{
	Image pics[]=new Image[12];
	Image burst[]=new Image[2];
	Image current,currentEx,arrow,back;
	Thread t;
	int num,num2,posX,posY,i=0,j=0,cursor=0;
	int posAX;
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
		for(int i=0;i<imageString2.length;i++)
		{
			burst[i] = getImage(getDocumentBase(),imageString2[i]);
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
		while(boom!=1)
		{
			num=(int)((Math.random())*12);					// for random balloon
			num2=(int)((Math.random())*2);
			loadpic(num,num2);	
		}
	}
	
	//loadpic
	public void loadpic(int i,int k)
	{
		current=pics[i];
		currentEx=burst[k];
		posX=(int)(Math.random()*750)+200;						//	for random position of balloon
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
		//g.drawImage(back,0,0,this);
		if (current != null)
		{
			setSize(1024,668);
			
			g.drawString("arrow at "+i+","+posY,0, 620);
			g.drawString("Balloon at "+posX+","+(548-j), 0, 600);
			g.drawString("BALLOON NO. "+num, 0, 610);
			int tip =posAX+arrow.getWidth(null);
			for(int x=0;x<current.getHeight(null);x++)
			{															//	For bursting of balloon,
				for(int y=0;y<current.getWidth(null);y++)
				{
					if(tip==(posX+y)&&(548-j+x)==pos)
				{										//	if the coordinate matches
				//	System.out.println((548-j)+"and"+x);
					boom=1;								//	in specific region the 
					posBurstY  = 548-j+x;				//	image of explosion would 
					posBurstX=posX;						//	be drawn.
				}
				}
			}
			if(boom==1)
			{
				g.drawImage(arrow,0, posY, null);
				g.drawImage(currentEx,posBurstX, posBurstY,this);	//drawing image of explosion			
				boom=3;
				pause(500);
			}
			else
			{
				//balloon
				g.drawImage(current, posX, 548-j, this);
				//arrow
				g.drawImage(arrow,0, posY, null);		//	arrow always at the left of screen
				posAX=i;	
				boom=2;
				if(cursor==1&&i<1000)
				{
					i+=8;
					g.drawImage(arrow,i+0, pos, null);  //	moving arrow
				}
				if(i>=990)
				{
					i=0;
					cursor=2;
				}
				repaint();
			}
		}
	}
	
	
}
