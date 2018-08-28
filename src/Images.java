
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
	
	BufferedImage tokens[] = new BufferedImage[6];
	BufferedImage cards[] = new BufferedImage[7];
	BufferedImage weapons[] = new BufferedImage[6];
	BufferedImage weaponsCard[] = new BufferedImage[7];
	BufferedImage screens[] = new BufferedImage[17];
	BufferedImage numbers[] = new BufferedImage[6];
	BufferedImage rooms[] = new BufferedImage[9];
	BufferedImage bigTokens[] = new BufferedImage[12];
	BufferedImage secret;
	
	public Images() {
		Initialise();
	}
	
	
	public void Initialise() {
		try {
			tokens[0] = ImageIO.read(getClass().getResource("catLadyToken2.png"));
			tokens[1] = ImageIO.read(getClass().getResource("fatTonyToken2.png"));
			tokens[2] = ImageIO.read(getClass().getResource("hanzToken2.png"));
			tokens[3] = ImageIO.read(getClass().getResource("HomerToken2.png"));
			tokens[4] = ImageIO.read(getClass().getResource("maggieToken2.png"));
			tokens[5] = ImageIO.read(getClass().getResource("moeToken2.png"));
			
			cards[0] = ImageIO.read(getClass().getResource("crazyCatLadyCard.png"));
			cards[1] = ImageIO.read(getClass().getResource("fatTonyCard.png"));
			cards[2] = ImageIO.read(getClass().getResource("hanzMoleManCard.png"));
			cards[3] = ImageIO.read(getClass().getResource("homerCard.png"));
			cards[4] = ImageIO.read(getClass().getResource("maggieCard.png"));
			cards[5] = ImageIO.read(getClass().getResource("moeCard.png"));
			cards[6] = ImageIO.read(getClass().getResource("cluedoBackCard.png"));
			
			bigTokens[0] = ImageIO.read(getClass().getResource("catLadyBigToken.png"));
			bigTokens[1] = ImageIO.read(getClass().getResource("fatTonyBigToken.png"));
			bigTokens[2] = ImageIO.read(getClass().getResource("hanzBigToken.png"));
			bigTokens[3] = ImageIO.read(getClass().getResource("homerBigToken.png"));
			bigTokens[4] = ImageIO.read(getClass().getResource("maggieBigToken.png"));
			bigTokens[5] = ImageIO.read(getClass().getResource("moeBigToken.png"));
			bigTokens[6] = ImageIO.read(getClass().getResource("catLadyBigTokenH.png"));
			bigTokens[7] = ImageIO.read(getClass().getResource("fatTonyBigTokenH.png"));
			bigTokens[8] = ImageIO.read(getClass().getResource("hanzBigTokenH.png"));
			bigTokens[9] = ImageIO.read(getClass().getResource("homerBigTokenH.png"));
			bigTokens[10] = ImageIO.read(getClass().getResource("maggieBigTokenH.png"));
			bigTokens[11] = ImageIO.read(getClass().getResource("moeBigTokenH.png"));
			
			weapons[0] = ImageIO.read(getClass().getResource("axetToken2.png"));
			weapons[1] = ImageIO.read(getClass().getResource("bombToken2.png"));
			weapons[2] = ImageIO.read(getClass().getResource("chainsawToken2.png"));
			weapons[3] = ImageIO.read(getClass().getResource("gunToken2.png"));
			weapons[4] = ImageIO.read(getClass().getResource("knifeToken2.png"));
			weapons[5] = ImageIO.read(getClass().getResource("slingShotToken2.png"));
			
			weaponsCard[0] = ImageIO.read(getClass().getResource("axeCard.png"));
			weaponsCard[1] = ImageIO.read(getClass().getResource("atomicBombCard.png"));
			weaponsCard[2] = ImageIO.read(getClass().getResource("chainsawCard.png"));
			weaponsCard[3] = ImageIO.read(getClass().getResource("gunCard.png"));
			weaponsCard[4] = ImageIO.read(getClass().getResource("knifeCard.png"));
			weaponsCard[5] = ImageIO.read(getClass().getResource("slingShotCard.png"));
			weaponsCard[6] = ImageIO.read(getClass().getResource("atomicBombCardGlow.png"));
			
			screens[0] = ImageIO.read(getClass().getResource("acc.png"));
			screens[1] = ImageIO.read(getClass().getResource("opening.png"));
			screens[2] = ImageIO.read(getClass().getResource("openingButtons.png"));
			screens[3] = ImageIO.read(getClass().getResource("howToPlayButton.png"));
			screens[4] = ImageIO.read(getClass().getResource("murderEnvelope.png"));
			screens[5] = ImageIO.read(getClass().getResource("selectionButton.png"));
			screens[6] = ImageIO.read(getClass().getResource("murderEnvelope2.png"));
			screens[7] = ImageIO.read(getClass().getResource("accusationCheck.png"));
			screens[8] = ImageIO.read(getClass().getResource("box.png"));
			screens[9] = ImageIO.read(getClass().getResource("currentPlayer.png"));
			screens[10] = ImageIO.read(getClass().getResource("finalAcc.png"));
			screens[11] = ImageIO.read(getClass().getResource("currentPlayerRing.png"));
			screens[12] = ImageIO.read(getClass().getResource("questionMark.png"));
			screens[13] = ImageIO.read(getClass().getResource("dice2.png"));
			screens[14] = ImageIO.read(getClass().getResource("BackGroundImage.png"));
			screens[15] = ImageIO.read(getClass().getResource("dice2H.png"));
			screens[16] = ImageIO.read(getClass().getResource("questionMarkH.png"));
			
			numbers[0] = ImageIO.read(getClass().getResource("1.png"));
			numbers[1] = ImageIO.read(getClass().getResource("2.png"));
			numbers[2] = ImageIO.read(getClass().getResource("3.png"));
			numbers[3] = ImageIO.read(getClass().getResource("4.png"));
			numbers[4] = ImageIO.read(getClass().getResource("5.png"));
			numbers[5] = ImageIO.read(getClass().getResource("6.png"));
			
			secret = ImageIO.read(getClass().getResource("secret1.png"));
			
			rooms[0] = ImageIO.read(getClass().getResource("burnsMansionCard.png"));
			rooms[1] = ImageIO.read(getClass().getResource("springfieldElementryCard.png"));
			rooms[2] = ImageIO.read(getClass().getResource("fryingDutchmanCard.png"));
			rooms[3] = ImageIO.read(getClass().getResource("flandersHouseCard.png"));
			rooms[4] = ImageIO.read(getClass().getResource("krustyBurgerCard.png"));
			rooms[5] = ImageIO.read(getClass().getResource("comicBookStoreCard.png"));
			rooms[6] = ImageIO.read(getClass().getResource("kwikEMartCard.png"));
			rooms[7] = ImageIO.read(getClass().getResource("simpsonsHouseCard.png"));
			rooms[8] = ImageIO.read(getClass().getResource("moesTavernCard.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public BufferedImage getImage(int val, String str) {
		if(str.equals("tokens")) {
			if(val == 1) {
				return tokens[0];
			}else if(val == 2) {
				return tokens[1];
			}else if(val == 3) {
				return tokens[2];
			}else if(val == 4) {
				return tokens[3];
			}else if(val == 5) {
				return tokens[4];
			}else if(val == 6) {
				return tokens[5];
			}
		}else if(str.equals("cards")) {
			if(val == 1) {
				return cards[0];
			}else if(val == 2) {
				return cards[1];
			}else if(val == 3) {
				return cards[2];
			}else if(val == 4) {
				return cards[3];
			}else if(val == 5) {
				return cards[4];
			}else if(val == 6) {
				return cards[5];
			}else if(val == 7) {
				return cards[6];
			}
		}else if(str.equals("weapons")) {
			if(val == 1) {
				return weapons[0];
			}else if(val == 2) {
				return weapons[1];
			}else if(val == 3) {
				return weapons[2];
			}else if(val == 4) {
				return weapons[3];
			}else if(val == 5) {
				return weapons[4];
			}else if(val == 6) {
				return weapons[5];
			}
		}else if(str.equals("weaponsCard")) {
			if(val == 21) {
				return weaponsCard[0];
			}else if(val == 22) {
				return weaponsCard[1];
			}else if(val == 23) {
				return weaponsCard[2];
			}else if(val == 24) {
				return weaponsCard[3];
			}else if(val == 25) {
				return weaponsCard[4];
			}else if(val == 26) {
				return weaponsCard[5];
			}else if(val == 52) {
				return weaponsCard[6];
			}
		}else if(str.equals("room")) {
			if(val == 11) {
				return rooms[0];
			}else if(val == 12) {
				return rooms[1];
			}else if(val == 13) {
				return rooms[2];
			}else if(val == 14) {
				return rooms[3];
			}else if(val == 15) {
				return rooms[4];
			}else if(val == 16) {
				return rooms[5];
			}else if(val == 17) {
				return rooms[6];
			}else if(val == 18) {
				return rooms[7];
			}else if(val == 19) {
				return rooms[8];
			}
		}else if(str.equals("screens")) {
			if(val == 1) {
				return screens[0];
			}else if(val == 2) {
				return screens[1];
			}else if(val == 3) {
				return screens[2];
			}else if(val == 4) {
				return screens[3];
			}else if(val == 5) {
				return screens[4];
			}else if(val == 6) {
				return screens[5];
			}else if(val == 7) {
				return screens[6];
			}else if(val == 8) {
				return screens[7];
			}else if(val == 9) {
				return screens[8];
			}else if(val == 10) {
				return screens[9];
			}else if(val == 11) {
				return screens[10];
			}else if(val == 12) {
				return screens[11];
			}else if(val == 13) {
				return screens[12];
			}else if(val == 14) {
				return screens[13];
			}else if(val == 15) {
				return screens[14];
			}else if(val == 16) {
				return screens[15];
			}else if(val == 17) {
				return screens[16];
			}
		}else if(str.equals("numbers")) {
			if(val == 1) {
				return numbers[0];
			}else if(val == 2) {
				return numbers[1];
			}else if(val == 3) {
				return numbers[2];
			}else if(val == 4) {
				return numbers[3];
			}else if(val == 5) {
				return numbers[4];
			}else if(val == 6) {
				return numbers[5];
			}
		}else if(str.equals("bigToken")) {
			if(val == 1) {
				return bigTokens[0];
			}else if(val == 2) {
				return bigTokens[1];
			}else if(val == 3) {
				return bigTokens[2];
			}else if(val == 4) {
				return bigTokens[3];
			}else if(val == 5) {
				return bigTokens[4];
			}else if(val == 6) {
				return bigTokens[5];
			}
			
			else if(val == 31) {
				return bigTokens[6];
			}else if(val == 32) {
				return bigTokens[7];
			}else if(val == 33) {
				return bigTokens[8];
			}else if(val == 34) {
				return bigTokens[9];
			}else if(val == 35) {
				return bigTokens[10];
			}else if(val == 36) {
				return bigTokens[11];
			}
		}else if(str.equals("secret")) {
			if(val == 1) {
				return secret;
			}
		}
		return null;
	}
}