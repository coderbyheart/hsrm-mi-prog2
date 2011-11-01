import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;


public class Applet extends java.applet.Applet implements Runnable, MouseListener
{
	private DogController myDogController;
	private Thread animation;
	private int activeDog = 0;
	
	public void start()
	{
		this.myDogController = new DogControllerGraphics();
		myDogController.addDog(new Dog("Hasso"));
		myDogController.addDog(new Dog("Bolt"));
	}
	
	public void init()
	{
		super.init();
		this.addMouseListener(this);
		this.resize(800, 600);
		this.animation = new Thread(this);
		this.animation.start();
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, 800, 600);
		((DogControllerGraphics) this.myDogController).paint(g);
	}
	
	/**
	 * Run-Methode des Threads
	 */
	public void run()
	{
		 Thread myThread = this.animation.currentThread();
		 while(myThread == this.animation) {
			 this.repaint();
			 try {
				 this.animation.sleep(100);
			 } catch(InterruptedException e) {
				 // Kommt vor
			 }
		 }
	}
	
	public void mouseClicked(MouseEvent e)
	{
		LinkedList<Dog> dogs = this.myDogController.getDogs();
		boolean hasHit = false;
		for (int i = 0; i < dogs.size(); i++) {
			if (dogs.get(i).getBounds().contains(e.getX(), e.getY())) {
				this.activeDog = i;
				hasHit = true;
				dogs.get(i).isActive(true);
			} else {
				dogs.get(i).isActive(false);
			}
		}
		if (!hasHit) {
			dogs.get(this.activeDog).go(e.getX(), e.getY());
		}		
	}
	
	public void mouseEntered(MouseEvent e){

	}
	
	public void mouseExited(MouseEvent e){

	}
	
	public void mousePressed(MouseEvent e)
	{
	}

	public void mouseReleased(MouseEvent e)
	{
	}
}
