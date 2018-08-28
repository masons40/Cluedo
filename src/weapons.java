import java.awt.image.BufferedImage;

public class weapons extends GameObject{
	
	int weaponsID;
	
	public weapons(int weaponsID,BufferedImage icon, int x, int y) {
		super(icon,x,y);
		this.weaponsID = weaponsID;
	}
	
	public int getWeaponsID() {
		return weaponsID;
	}
}
