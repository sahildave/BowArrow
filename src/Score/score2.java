package Score;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class score2 extends Applet implements Runnable

{
	
	Image pics[]=new Image[12];
	Image current,currentEx;
	Image burst[]=new Image[2];
	Thread t;
	int num,num2,posX,posY,j=0;
	int score=0;
	
	public void init()
	{
		String imageString[]={"S20.png","S20 (1).png","S20 (2).png","S20 (3).png","S20 (4).png",
				"S20 (5).png","S30.png","S30 (1).png","S40.png","S40 (1).png","H50.png","B-10.png"};
		String imageString2[]={"ex.jpg","ex1.jpg"};
		for(int i=0;i<imageString.length;i++)
		{
			pics[i] = getImage(getDocumentBase(),imageString[i]);
		}
		for(int i=0;i<imageString2.length;i++)
		{
			burst[i]= getImage(getDocumentBase(),imageString2[i]);
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
			num2=(int)((Math.random())*2);
			loadpic(num,num2);	
		}
	}

	public void loadpic(int i,int k)
	{
		current=pics[i];
		currentEx=burst[k];
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
			g.drawString("BALLOON NO. "+num, 0, 610);
			g.drawImage(current, posX, 548-j, this);
			if(548-j<20)
			{
				g.drawImage(currentEx,posX-10,0,this);
				if(num==1||num==2||num==3||num==4||num==5||num==0)
				{
					score=score+20;
					g.drawString("SCORE +20", 0, 630);
				}
				if(num==7||num==6)
				{
					score=score+30;
					g.drawString("SCORE +30", 0, 630);
				}
				if(num==9||num==8)
				{
					score=score+40;
					g.drawString("SCORE +40", 0, 630);
				}
				if(num==10)
				{
					score=score+50;
					g.drawString("SCORE +50", 0, 630);
				}
				if(num==11)
				{
					score=score-10;
					g.drawString("SCORE -10", 0, 630);
				}
			}
			g.drawString("TOTAL SCORE "+score, 0, 650);
		}
	}
	
	
}
