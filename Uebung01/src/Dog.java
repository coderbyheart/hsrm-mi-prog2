import java.awt.Rectangle;

public class Dog 
{
	private String name;
	private int motionState;
	private int x = 0;
	private int y = 0;
	private int width = 150;
	private int height = 50;
	private boolean active = false;
	
	public static final int MOTION_STATE_STANDING = 1;
	public static final int MOTION_STATE_SITTING  = 2;
	public static final int MOTION_STATE_LAYING   = 3;
	public static final int MOTION_STATE_WALKING  = 4;
	public static final int MOTION_STATE_RUNNING  = 5;
	public static final int MOTION_STATE_BARKING  = 6;
	
	public Dog(String name)
	{
		this.name = name;
		this.motionState = Dog.MOTION_STATE_STANDING;
	}
	
	public String getState()
	{
		return this.getName() + " is " + this.getMotionStateAsText() + " at " + this.x + "/" + this.y; 
	}
	
	public int getMotionState()
	{
		return this.motionState;
	}
	
	public String getMotionStateAsText()
	{
		String textState;
		switch(this.getMotionState()) {
		case Dog.MOTION_STATE_SITTING:
			textState = "sitting";
			break;
		case Dog.MOTION_STATE_LAYING:
			textState = "laying";
			break;
		case Dog.MOTION_STATE_WALKING:
			textState = "walking";
			break;
		case Dog.MOTION_STATE_RUNNING:
			textState = "running";
			break;
		case Dog.MOTION_STATE_BARKING:
			textState = "barking";
			break;
		case Dog.MOTION_STATE_STANDING:
		default:
			textState = "standing";
		}
		return textState;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Dog lay()
	{
		this.motionState = Dog.MOTION_STATE_LAYING;
		return this;
	}
	
	public Dog stand()
	{
		this.motionState = Dog.MOTION_STATE_STANDING;
		return this;
	}
	
	public Dog bark()
	{
		this.motionState = Dog.MOTION_STATE_BARKING;
		return this;
	}
	
	public Dog go(int x, int y)
	{
		this.motionState = Dog.MOTION_STATE_WALKING;
		this.x = x;
		this.y = y;
		return this;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public Rectangle getBounds()
	{
		Rectangle bounds = new Rectangle();
		bounds.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		return bounds;
	}
	
	public Dog isActive(boolean active)
	{
		this.active = active;
		return this;
	}
}
