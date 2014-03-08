import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class ArrowMouse3 extends Applet implements MouseListener,MouseMotionListener,Runnable
{
	Image arrow;
	int i=0,j;
	Thread t;
	int posY,cursor=0;
	int pos=-100;
	
	public void init()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
		arrow=getImage(getDocumentBase(),"1.PNG");
	}
	
	
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
	
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	
	
	
	public void paint(Graphics g)
	{
		
		
		setSize(1024,668);
		
		g.drawImage(arrow,0, posY, null);//permanent arrow
		g.drawImage(arrow,i+0, pos, null);//moving arrow
		//strings
		g.drawString(i+","+posY,0, 600);
		g.drawString("arrow at "+i+","+posY,0, 610);
		
		if(cursor==1&&i<1000)
		{
			i++;
		}
		repaint();
		if(i==1000)
		{
			cursor=0;
		
		}
	}
	public void start()
	{
		
		{
			try		
			{
				t= new Thread(this);
				t.start();
			}
			catch(Exception e){}
		}
	}
	
	public void run()
	{
		try
		{
			
			Thread.sleep(20);

		}
		catch(Exception e){}
	}
}
