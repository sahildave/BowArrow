import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class Arrow extends Applet implements Runnable
{
	Image arrow;
	int i=0;
	Thread t;
	
	public void init()
	{
		arrow=getImage(getDocumentBase(),"1.PNG");
	}
	
	public void paint(Graphics g)
	{
		setSize(1024,668);
		g.drawImage(arrow,i+0, 300, null);
	}
	
	public void start()
	{
		try
		{
			t=new Thread(this);
			t.start();
		}
		catch(Exception e){}
	}

	public void run()
	{
		for(i=0;i<3000;i+=10)
		{
			repaint();
			try
			{
				Thread.sleep(20);
			}
			catch(Exception e){}
		}
	}
}
