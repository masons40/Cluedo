import java.awt.image.BufferedImage;

public class Card extends GameObject{
	
	int cardId;
	int choosenId;
	int cardLocId;
	
	public Card(int cardId,BufferedImage icon, int x, int y,int cardLocId) {
		super(icon,x,y);
		this.cardId = cardId;
		this.cardLocId = cardLocId;
	}
	
	public int getCardId() {
		return cardId;
	}
	
	public void setChoosenId(int choosenId) {
		this.choosenId = choosenId;
	}
	
	public int getChoosenId() {
		return choosenId;
	}
	
	public int getCardLocId() {
		return cardLocId;
	}

}
