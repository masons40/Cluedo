import java.awt.image.BufferedImage;

public class GameObject {
	
	//every sprite in the game is a GameObject
	//variables to be used
	int x,y;  //co-ordinates (x, y)
	boolean accusation = false;
	String name;
	BufferedImage icon;
	
	//constructor
	public GameObject(BufferedImage icon,int x, int y)
	{
		this.icon = icon;
		this.x = x;
		this.y = y;
	}
	
	public int getx()
	{
		return x;
	}
	
	public int gety()
	{
		return y;
	}
	
	public BufferedImage getImage()
	{
		return icon;
	}
	
	
	//mutators for the x and y co-ordinates
	public void setx(int xValue)
	{
		x = xValue;
	}
	
	public void sety(int yValue)
	{
		y = yValue;
	}
	
	public void setAccusation(boolean bool)
	{
		accusation = bool;
	}
	
	public void setImage(BufferedImage image)
	{
		icon = image;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

}
