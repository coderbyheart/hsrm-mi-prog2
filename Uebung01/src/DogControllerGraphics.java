import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class DogControllerGraphics extends DogController
{
	public void paint(Graphics g)
	{
		g.setColor(Color.BLACK);
		for(int i = 0; i < this.dogs.size(); i++) {
			Dog currentDog = this.dogs.get(i);
			g.drawOval(currentDog.getX(), currentDog.getY(), currentDog.getWidth(), currentDog.getHeight());
			g.drawString(currentDog.getState(), currentDog.getX() + 10, currentDog.getY() + 30);	
		}		
	}
}
