import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GameMechanics {
	
	Random rand = new Random();
	
	ArrayList<Integer> startList = new ArrayList<Integer>();
	ArrayList<Integer> murderEnvelope = new ArrayList<Integer>();
	ArrayList<Integer> loser = new ArrayList<Integer>();
	ArrayList<Integer> CList = new ArrayList<Integer>();
	
	ArrayList<String> start = new ArrayList<String>();
	ArrayList<Integer> choosen = new ArrayList<Integer>();
	
	ArrayList<String> names = new ArrayList<String>();
	
	Panel panel;
	
	int gameStateCurrent = 1;
	int numOfCards = 21;
	
	int accuseCurrent;
	int current = 0;
	int exitNum = 0;
	int secretExitNum = 0;
	
	boolean setShow = false;
	
	int password = 0;
	boolean showHidden = false;
	
	int x=0,xOne = 852/2;
	int numOfPlayers;
	int diff;
	
	int rollNum = 0;
	int done = 0;
	
	int[] gameState = {1,2,3,4,5,6,7,8};
	int width, height;
	
	Images images = new Images();
	
	Players[] Players = new Players[6];
	Players[] PlayersTwo = new Players[6];
	Card[] Rooms = new Card[9];
	weapons[] Weapons = new weapons[6];
	
	int wait = 0;

	Accusation accuse = new Accusation(Players,this);
	InputPanel inputPanel = new InputPanel(width,height,this,accuse);
	BufferedImage background;
	BufferedImage murder[] = new BufferedImage[3];
	
	Dimensions dimensions = new Dimensions();
	Moving moving = new Moving();
	CollisonTesting cTest = new CollisonTesting(this);
	
	//constructor to get everything ready
	public GameMechanics(int width,int height) {
		this.width = width;
		this.height = height;
		
		try {
			background = ImageIO.read(getClass().getResource("map2.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		//Initialise();
	}
	//loop to continue the process of drawing the sprites and maps when updates happen 
	/*
	public void beginLoop() {
		while(true) {
			
		}
	}*/
	public void start(Graphics g) {
		
		if(gameState[0] == gameStateCurrent) {
			
			g.drawImage(images.getImage(2, "screens"), 0, 0, null);
			g.drawImage(images.getImage(3, "screens"), 480, 80, null);
		}
		//main
		else if(gameState[1] == gameStateCurrent) {
	
			g.drawImage(background, 180, 0, null);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, width, height);
			g.drawImage(background,180, 0, null);
			if(Players[current].getDoor() != 0) {
				exit();
			}
			checkDone();
			Draw(g);
			if(Players[current].getDoor() > 0) {
				g.drawImage(images.getImage(17, "screens"),810, 10, null);
			}else {
				g.drawImage(images.getImage(13, "screens"),810, 10, null);
			}
			if(CList.size() != 0) {
				int x = 160;
				int y = 632;
				if(showHidden) {
					y = 550;
				}else {
					y = 632;
				}
				for(int i=0;i<CList.size();i++) {
					if(CList.get(i) <= 6) {
						g.drawImage(images.getImage(CList.get(i), "cards"), x, y, null);
						x+=100;
					}else if(CList.get(i) >= 11 && CList.get(i) < 20) {
						g.drawImage(images.getImage(CList.get(i), "room"), x, y, null);
						x+=100;
					}else if(CList.get(i) >= 21 && CList.get(i) < 27) {
						g.drawImage(images.getImage(CList.get(i), "weaponsCard"), x, y, null);
						x+=100;
					}
				}
			}
			//murder (cheat)
		}else if(gameState[2] == gameStateCurrent) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, width, height);
			g.drawImage(images.getImage(15, "screens"), 0, 0, null);
			
			g.drawImage(images.getImage(5, "screens"), 0, 0, null);
			g.drawImage(images.getImage(murderEnvelope.get(0), "cards"), 231, 255, null);
			g.drawImage(images.getImage(murderEnvelope.get(1), "room"), 368, 255, null);
			g.drawImage(images.getImage(murderEnvelope.get(2), "weaponsCard"), 505, 255, null);
			g.drawImage(images.getImage(7, "screens"), 0, 0, null);
			
			//murder (cheat)
		}else if(gameState[3] == gameStateCurrent) {
			g.setColor(new Color(20,20,20,240));
			g.fillRect(0, 0, width, height);
			g.drawImage(images.getImage(15, "screens"), 0, 0, null);
			
			g.drawImage(images.getImage(5, "screens"), 0, 0, null);
			g.drawImage(images.getImage(murderEnvelope.get(0), "cards"), 231, 255, null);
			g.drawImage(images.getImage(murderEnvelope.get(1), "room"), 368, 255, null);
			g.drawImage(images.getImage(murderEnvelope.get(2), "weaponsCard"), 505, 255, null);
			g.drawImage(images.getImage(7, "screens"), 0, 0, null);
			//Accusation board
		}else if(gameState[4] == gameStateCurrent) {
			g.drawImage(images.getImage(15, "screens"), 0, 0, null);
			g.setColor(new Color(20,20,20,240));
			g.fillRect(0, 0, width, height);
			g.setFont(new Font("Courier", Font.BOLD,50));
			g.setColor(Color.white);
			g.drawString("Make accusation", 170, 100);
			g.setFont(new Font("Courier", Font.BOLD,35));
			g.setColor(Color.white);
			g.drawString("Type the number of the card", 150, 130);
			int x = 10; int y = 240;
			for(int i=1;i<=6;i++) {
				if(accuse.getAccuseList().contains(i)) {
					g.drawImage(images.getImage(7, "cards"), x, y, null);
					x+=140;
				}else {
					g.drawImage(images.getImage(i, "cards"), x, y, null);
					x+=140;
				}
			}
			x = 10;
			y += 170;
			for(int i=1;i<=6;i++) {
				if(accuse.getAccuseList().contains(i+20)) {
					g.drawImage(images.getImage(7, "cards"), x, y, null);
					x+=140;
				}else {
					g.drawImage(images.getImage(i+20, "weaponsCard"), x, y, null);
					x+=140;
				}
			}
			
			//end game view
		}else if(gameState[6] == gameStateCurrent) {
			g.drawImage(images.getImage(15, "screens"), 0, 0, null);
			g.setColor(new Color(20,20,20,200));
			g.fillRect(0, 0, width, height);
			int x = 430; int y = 5;
			for(int i=1;i<=6;i++) {
				if(!(getAccuse().getAccuseList().contains(i))) {
					g.drawImage(images.getImage(i, "cards"), x, y, null);
					x+=140;
				}else {
					g.drawImage(images.getImage(7, "cards"), x, y, null);
					x+=140;
				}
				if(x > 800) {
					x = 10;
					y += 167;
				}
			}
			for(int i=11;i<=19;i++) {
				if(!(getAccuse().getAccuseList().contains(i))) {
					g.drawImage(images.getImage(i, "room"), x, y, null);
					x+=140;
				}else {
					g.drawImage(images.getImage(7, "cards"), x, y, null);
					x+=140;
				}
				if(x > 800) {
					x = 10;
					y += 167;
				}
			}
			for(int i=21;i<=26;i++) {
				if(!(getAccuse().getAccuseList().contains(i))) {
					g.drawImage(images.getImage(i, "weaponsCard"), x, y, null);
					x+=140;
				}else {
					g.drawImage(images.getImage(7, "cards"), x, y, null);
					x+=140;
				}
				if(x > 800) {
					x = 10;
					y += 167;
				}
			}
		}else if(gameState[5] == gameStateCurrent) {
			draw(g);
			//choose card
		}else if(gameState[7] == gameStateCurrent) {
			g.drawImage(images.getImage(15, "screens"), 0, 0, null);
			g.setColor(new Color(20,20,20,200));
			g.fillRect(0, 0, width, height);
			
			int x = 10; int y = 240;
			for(int i=1;i<=6;i++) {
					g.setFont(new Font("Courier", Font.BOLD,50));
					g.setColor(Color.white);
					g.drawString("Choose Player based", 170, 100);
					g.drawString("on the input of the", 170, 140);
					g.drawString("names", 170, 180);
					g.setFont(new Font("Courier", Font.BOLD,35));
					g.drawString("Click on cards", 170, 220);
			
				if(choosen.contains(i)) {
					g.drawImage(images.getImage(7, "cards"), x, y, null);
					x+=140;
					wait+=1;
				}else {
					g.drawImage(images.getImage(i, "cards"), x, y, null);
					x+=140;
				}
			}
		}
		g.dispose();
	}
	
	//handles all drawing of sprites
	public void Draw(Graphics g) {
		
		for(int i=0;i<numOfPlayers;i++) {
			g.drawImage(Players[i].getImage(), (Players[i].getx()*24) +180, Players[i].gety()*24, null);
		}
		
		for(int i=0;i<PlayersTwo.length-numOfPlayers;i++) {
			g.drawImage(images.getImage(PlayersTwo[i].getPlayerId(), "tokens"),(PlayersTwo[i].getx()*24) +180, PlayersTwo[i].gety()*24, null);
		}
		
		for(int i=0;i<6;i++){
			g.drawImage(Weapons[i].getImage(), (Weapons[i].getx()*24) +170, (Weapons[i].gety()*24), null);
		}
		
		int y = 190;
		for(int i=0;i<startList.size();i++){
			g.drawImage(images.getImage(startList.get(i), "bigToken"), 157, y, null);
			if(i == current) {
				g.drawImage(images.getImage(12, "screens"), 148, y-8, null);
			}
			y+=70;
		}
		
		if(exit() || secretExit()) {
			if(secretExit() && rollNum > 0) {
				if(Players[current].getDoor() == 10 || Players[current].getDoor() == 5) {
					g.drawImage(images.getImage(1, "secret"),(25*24) + 180, 22*24, null);
					g.drawImage(images.getImage(1, "secret"),(7*24) + 180, 2*24, null);
				}else if(Players[current].getDoor() == 3 || Players[current].getDoor() == 7){
					g.drawImage(images.getImage(1, "secret"),(2*24) + 180, 20*24, null);
					g.drawImage(images.getImage(1, "secret"),(21*24) + 180, 6*24, null);
				}
		
				if(getExitNum(5) == 5) {
					
					if(Players[current].getDoor() == 10) {
						dimensions.setVal(Players[current].getx(), Players[current].gety(), Players[current].getDoor());
						
						if(dimensions.checkPosAvailable(4,5,5)) {
							Players[current].setx(dimensions.getX());
							Players[current].sety(dimensions.getY());
						}
						Players[current].setDoor((dimensions.getVal(Players[current].getx(),Players[current].gety())));	
						dimensions.setVal(Players[current].getx(), Players[current].gety(),47);
						
						setExitNum(0);
						rollNum = 1;
						
					}else if(Players[current].getDoor() == 5) {
						dimensions.setVal(Players[current].getx(), Players[current].gety(), Players[current].getDoor());
						
						if(dimensions.checkPosAvailable(24,22,10)) {
							Players[current].setx(dimensions.getX());
							Players[current].sety(dimensions.getY());
						}
						Players[current].setDoor((dimensions.getVal(Players[current].getx(),Players[current].gety())));	
						dimensions.setVal(Players[current].getx(), Players[current].gety(),47);
						
						setExitNum(0);
						rollNum = 1;
						
					}else if(Players[current].getDoor() == 3) {
						dimensions.setVal(Players[current].getx(), Players[current].gety(), Players[current].getDoor());
						
						if(dimensions.checkPosAvailable(4,22,7)) {
							Players[current].setx(dimensions.getX());
							Players[current].sety(dimensions.getY());
						}
						Players[current].setDoor((dimensions.getVal(Players[current].getx(),Players[current].gety())));	
						dimensions.setVal(Players[current].getx(), Players[current].gety(),47);
						
						setExitNum(0);
						rollNum = 1;
						
					}else if(Players[current].getDoor() == 7) {
						dimensions.setVal(Players[current].getx(), Players[current].gety(), Players[current].getDoor());
						;
						if(dimensions.checkPosAvailable(23,5,3)) {
							Players[current].setx(dimensions.getX());
							Players[current].sety(dimensions.getY());
						}
						Players[current].setDoor((dimensions.getVal(Players[current].getx(),Players[current].gety())));	
						dimensions.setVal(Players[current].getx(), Players[current].gety(),47);
						setExitNum(0);
						rollNum = 1;
					}
				}
				panel.reDraw();
			}
			if(exit() && rollNum > 0) {
			
				int count =1;
				for(int i=0;i<28;i++) {
					for(int j=0;j<28;j++) {
						if(dimensions.getVal(j,i) == (Players[current].getDoor())*10) {
							g.drawImage(images.getImage(count, "numbers"),(j*24) + 180, i*24, null);
							if(count == getExitNum(2)) {
								if(dimensions.checkPosAvailable(i,j,0)) {
									dimensions.setVal(i, j, Players[current].getDoor()/10);
									Players[current].setx(dimensions.getX());
									Players[current].sety(dimensions.getY());
									dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
									Players[current].setDoor(0);
								}
							}
							count += 1;
						}
					}
				}
				setExitNum(0);
			}
			panel.reDraw();
		}
		
		int yValue = 15;
		if(password == 1) {
			for(int i=0;i<diff;i++) {
				if(Players[current].cards.get(i) <= 6) {
					g.drawImage(images.getImage(Players[current].cards.get(i), "cards"), 15, yValue, null);
					yValue += 30*numOfPlayers;
				}else if(Players[current].cards.get(i) >= 11 && Players[current].cards.get(i) < 20) {
					g.drawImage(images.getImage(Players[current].cards.get(i), "room"), 15, yValue, null);
					yValue += 30*numOfPlayers;
				}else if(Players[current].cards.get(i) >= 21 && Players[current].cards.get(i) < 27) {
					g.drawImage(images.getImage(Players[current].cards.get(i), "weaponsCard"), 15, yValue, null);
					yValue += 30*numOfPlayers;
				}
			}
		}else {
			for(int i=0;i<diff;i++) {
				g.drawImage(images.getImage(7, "cards"), 15, yValue, null);
				yValue += 30*numOfPlayers;
			}
		}
		
	}
	
	public void movement(int num) {
		if(rollNum != 0 && done == 0) {
			if(num == 1) {
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(moving.moveUp(Players[current].gety()));
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum--;
				panel.reDraw();
			}else if(num == 2) {
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(moving.moveDown(Players[current].gety()));
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum--;
				panel.reDraw();
			}else if(num == 3) {
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].setx(moving.moveRight(Players[current].getx()));
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum--;
				panel.reDraw();
			}else if(num == 4){
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].setx(moving.moveLeft(Players[current].getx()));
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum--;
				panel.reDraw();
			}
		}else if(rollNum == 0) {
			setDone(0);
		}
	}
	
	//receives all images and sets all object to positions
	public void Initialise() {

		diff = 18 / numOfPlayers;
		
		CList.add(1);
		CList.add(2);
		CList.add(3);
		CList.add(4);
		CList.add(5);
		CList.add(6);
		
		CList.add(11);
		CList.add(12);
		CList.add(13);
		CList.add(14);
		CList.add(15);
		CList.add(16);
		CList.add(17);
		CList.add(18);
		CList.add(19);
		
		CList.add(21);
		CList.add(22);
		CList.add(23);
		CList.add(24);
		CList.add(25);
		CList.add(26);
		
		for(int j=0;j<numOfPlayers;j++) {
			if(start.get(j) == "Crazy Cat Lady") {
				Players[j] = new Players(1,images.getImage(1, "tokens"), 11, 1);
				Players[j].setName(names.get(j));
				Players[j].setPlayerName("Crazy Cat Lady");
				startList.add(1);
			}else if(start.get(j) == "Hanz Moleman") {
				Players[j] = new Players(3,images.getImage(3, "tokens"), 2, 18);
				Players[j].setName(names.get(j));
				Players[j].setPlayerName("Hanz Moleman");
				startList.add(3);
			}else if(start.get(j) == "Fat Tony") {
				Players[j] = new Players(2,images.getImage(2, "tokens"), 25, 20);
				Players[j].setName(names.get(j));
				Players[j].setPlayerName("Fat Tony");
				startList.add(2);
			}else if(start.get(j) == "Moe Syzlack") {
				Players[j] = new Players(6,images.getImage(6, "tokens"), 16, 1);
				Players[j].setName(names.get(j));
				Players[j].setPlayerName("Moe Syzlack");
				startList.add(6);
			}else if(start.get(j) == "Maggie Simpson") {
				Players[j] = new Players(5,images.getImage(5, "tokens"), 9, 25);
				Players[j].setName(names.get(j));
				Players[j].setPlayerName("Maggie Simpson");
				startList.add(5);
			}else if(start.get(j) == "Homer Simpson") {
				Players[j] = new Players(4,images.getImage(4, "tokens"), 25, 7);
				Players[j].setName(names.get(j));
				Players[j].setPlayerName("Homer Simpson");
				startList.add(4);
			}
		}
		ArrayList<String> extraNames = new ArrayList<String>();
		extraNames.add("Moe Syzlack");
		extraNames.add("Maggie Simpson");
		
		extraNames.add("Homer Simpson");
		extraNames.add("Fat Tony");
		extraNames.add("Hanz Moleman");
		extraNames.add("Crazy Cat Lady");
		int pos = 0;
		for(int i=0;i<6;i++) {
			if(!(start.contains(extraNames.get(i)))) {
				if(extraNames.get(i) == "Crazy Cat Lady") {
					PlayersTwo[pos] = new Players(1,images.getImage(1, "tokens"), 11, 1);
					PlayersTwo[pos].setName("noName");
					PlayersTwo[pos].setPlayerName("Unkown");
					
				}else if(extraNames.get(i) == "Hanz Moleman") {
					PlayersTwo[pos] = new Players(3,images.getImage(3, "tokens"), 2, 18);
					PlayersTwo[pos].setName("noName");
					PlayersTwo[pos].setPlayerName("Unkown");
					
				}else if(extraNames.get(i) == "Fat Tony") {
					PlayersTwo[pos] = new Players(2,images.getImage(2, "tokens"), 25, 20);
					PlayersTwo[pos].setName("noName");
					PlayersTwo[pos].setPlayerName("Unkown");
					
				}else if(extraNames.get(i) == "Moe Syzlack") {
					PlayersTwo[pos] = new Players(6,images.getImage(6, "tokens"), 16, 1);
					PlayersTwo[pos].setName("noName");
					PlayersTwo[pos].setPlayerName("Unkown");
					
				}else if(extraNames.get(i) == "Maggie Simpson") {
					PlayersTwo[pos] = new Players(5,images.getImage(5, "tokens"), 9, 25);
					PlayersTwo[pos].setName("noName");
					PlayersTwo[pos].setPlayerName("Unkown");
					
				}else if(extraNames.get(i) == "Homer Simpson") {
					PlayersTwo[pos] = new Players(4,images.getImage(4, "tokens"), 25, 7);
					PlayersTwo[pos].setName("noName");
					PlayersTwo[pos].setPlayerName("Unkown");
				}
				pos+=1;
			}
		}
			
		//beginning dice roll here
		//for loop to roll the dice
		//check who gets the highest
		//put the player id value into an array of the player who rolled the highest number
		//if equal we roll again
		
		DiceRoll roll = new DiceRoll();
		
		//the starting roll for all players	
		for(int i=0; i<numOfPlayers; i++)
		{
			roll.Diceroll();
			Players[i].setstartingRoll(roll.getTotal());
		}
		
		//checking for duplicate starting rolls
		for(int i=0; i<numOfPlayers; i++)
		{
			for(int j=i+1; j<numOfPlayers; j++)
			{
				if(Players[i].getstartingRoll() == Players[j].getstartingRoll())
				{
					//players keep rolling until they roll different totals
					while(Players[i].getduplicateRoll() == Players[j].getduplicateRoll())
					{
						roll.Diceroll();
						int num1 = roll.getTotal();
						Players[i].setduplicateRoll(num1);
						
						
						roll.Diceroll();
						int num2 = roll.getTotal();
						Players[j].setduplicateRoll(num2);
					}
				}
			}
		}
		
		for(int i=0; i<numOfPlayers; i++)
		{
			for(int j=1; j<(numOfPlayers-i); j++) 
			{
	            if(Players[j - 1].getstartingRoll() < Players[j].getstartingRoll()) 
	            {
	            	Players[j].setbiggestRoll(true);
	            	Players[j - 1].setbiggestRoll(false);
	            }
	            //checking the case in which players rolled the same number to start
	            else if(Players[j - 1].getstartingRoll() == Players[j].getstartingRoll()) 
	            {
	            	//checks the duplicate roll total to see who'd go first between the players
	            	if(Players[j - 1].getduplicateRoll() < Players[j].getduplicateRoll()) 
	            	{
	            		Players[j].setbiggestRoll(true);
		            	Players[j - 1].setbiggestRoll(false);
	            	}
	            }
	        }
		}
		
		for(int i=0; i<numOfPlayers; i++)
		{
			if(Players[i].getbiggestRoll() == true)
			{
				current = i;
			}
		}
		
		int suspect = rand.nextInt(6)+1;
		int room = rand.nextInt(9)+11;
		int weapon = rand.nextInt(6)+21;
		
		murderEnvelope.add(suspect);
		murderEnvelope.add(room);
		murderEnvelope.add(weapon);

		CList.remove(CList.indexOf(suspect));
		CList.remove(CList.indexOf(room));
		CList.remove(CList.indexOf(weapon));

		setPlayerCards();

		Weapons[0] = new weapons(21,images.getImage(1, "weapons"),0,1);
		Weapons[1] = new weapons(22,images.getImage(2, "weapons"),0,2);
		Weapons[2] = new weapons(23,images.getImage(3, "weapons"),0,3);
		Weapons[3] = new weapons(24,images.getImage(4, "weapons"),0,4);
		Weapons[4] = new weapons(25,images.getImage(5, "weapons"),0,5);
		Weapons[5] = new weapons(26,images.getImage(6, "weapons"),0,6);
		
		Rooms[0] = new Card(60,images.getImage(11, "room"),0,0,11);
		Rooms[0] = new Card(50,images.getImage(16, "room"),0,0,16);
		Rooms[0] = new Card(40,images.getImage(12, "room"),0,0,12);
		Rooms[0] = new Card(30,images.getImage(19, "room"),0,0,19);
		Rooms[0] = new Card(20,images.getImage(15, "room"),0,0,15);
		Rooms[0] = new Card(70,images.getImage(17, "room"),0,0,17);
		Rooms[0] = new Card(80,images.getImage(14, "room"),0,0,14);
		Rooms[0] = new Card(90,images.getImage(18, "room"),0,0,18);
		Rooms[0] = new Card(100,images.getImage(13, "room"),0,0,13);
		
		setCurrentGameState(2);
		panel.reDraw();

	}
	
	//used to control players and weapons, will be updated in the future to work for different input styles
	public void setInput(String string) {
		if(string.equals("u")) {
			this.movement(1);
		}else if(string.equals("d")){
			this.movement(2);
		}else if(string.equals("l")){
			this.movement(3);
		}else if(string.equals("r")){
			this.movement(4);
		}
	}
	
	public void setDoor(int val,boolean moving) {	
		if(val == 20) {
			if(dimensions.checkPosAvailable(23,15,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				if(moving) {
					inputPanel.message("Type Question to ask a Question");
					inputPanel.message("or click on the question mark.");
					accuse.accuseAdditionRoom(Players[current].getDoor());
				}
				panel.reDraw();
			}
			
		}else if(val == 30) {
			if(dimensions.checkPosAvailable(23,5,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				if(moving) {
					inputPanel.message("Type Question to ask a Question");
					inputPanel.message("or click on the question mark.");
					accuse.accuseAdditionRoom(Players[current].getDoor());
				}
				panel.reDraw();
			}
		}else if(val == 40) {
			if(dimensions.checkPosAvailable(13,5,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				if(moving) {
					inputPanel.message("Type Question to ask a Question");
					inputPanel.message("or click on the question mark.");
					accuse.accuseAdditionRoom(Players[current].getDoor());
				}
				panel.reDraw();
			}
		}else if(val == 50){
			if(dimensions.checkPosAvailable(4,5,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				if(moving) {
					inputPanel.message("Type Question to ask a Question");
					inputPanel.message("or click on the question mark.");
					accuse.accuseAdditionRoom(Players[current].getDoor());
				}
				panel.reDraw();
			}
		}else if(val == 60){
			if(dimensions.checkPosAvailable(5,14,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				if(moving) {
					inputPanel.message("Type Question to ask a Question");
					inputPanel.message("or click on the question mark.");
					accuse.accuseAdditionRoom(Players[current].getDoor());
				}
				panel.reDraw();
			}
		}else if(val == 70){
			if(dimensions.checkPosAvailable(4,22,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				if(moving) {
					inputPanel.message("Type Question to ask a Question");
					inputPanel.message("or click on the question mark.");
					accuse.accuseAdditionRoom(Players[current].getDoor());
				}
				panel.reDraw();
			}
		}else if(val == 80){
			if(dimensions.checkPosAvailable(11,24,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				if(moving) {
					inputPanel.message("Type Question to ask a Question");
					inputPanel.message("or click on the question mark.");
					accuse.accuseAdditionRoom(Players[current].getDoor());
				}
				panel.reDraw();
			}
		}else if(val == 90){
			if(dimensions.checkPosAvailable(17,24,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				if(moving) {
					inputPanel.message("Type Question to ask a Question");
					inputPanel.message("or click on the question mark.");
					accuse.accuseAdditionRoom(Players[current].getDoor());
				}
				panel.reDraw();
			}
		}else if(val == 100){
			if(dimensions.checkPosAvailable(24,22,(val/10))) {
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(dimensions.getY());
				Players[current].setx(dimensions.getX());
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				if(moving) {
					inputPanel.message("Type Question to ask a Question");
					inputPanel.message("or click on the question mark.");
					accuse.accuseAdditionRoom(Players[current].getDoor());
				}
				panel.reDraw();
			}
		}else if(val == 110){
				Players[current].setDoor(val/10);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 0);
				Players[current].sety(14);
				Players[current].setx(14);
				dimensions.setVal(Players[current].getx(), Players[current].gety(), 47);
				rollNum = 0;
				
				gameStateCurrent = 7;
				panel.reDraw();
		}
	}
	
	public InputPanel getInputPanel() {
		return inputPanel;
	}
	
	public Dimensions getDimensions() {
		return dimensions;
	}
	
	public Players getOb() {
		return Players[current];
	}
	
	public int getGameState() {
		return gameStateCurrent;
	}
	
	public void setCurrentGameState(int num) {
		gameStateCurrent = num;
	}
	
	public void setExitNum(int val) {
		if(val == 0) {
			exitNum = 0;
			secretExitNum = 0;
		}else if (val > 0 && val < 5){
			exitNum = val;
		}else {
			secretExitNum = 5;
		}
	}
	public int getExitNum(int val) {
		if(val < 5) {
			return exitNum;
		}else {
			return secretExitNum;
		}
	}

	public boolean secretExit() {
		if(Players[current].getDoor() == 10 || Players[current].getDoor() == 7 || Players[current].getDoor() == 5 || Players[current].getDoor() == 3) {
			return true;
		}
		return false;
	}
	public boolean exit() {
		if(Players[current].getDoor() > 0) {
			return true;
		}
		return false;
	}
	
	public void setMax(int max) {
		numOfPlayers= max;
	}
	
	public void setRoll(int val) {
		rollNum = val;
	}
	
	public void setDone(int val) {
		done = val;
	}
	
	public void checkCurrent() {
		if(current < (numOfPlayers-1)) {
			current++;
			if(loser.contains(current)) {
				checkCurrent();
			}
		}else{
			
			current = 0;
			if(loser.contains(current)) {
				checkCurrent();
			}
		}
	}
	
	public Players getcurrent(){
		return Players[current];
	}
	public void newMClass() {
		murderEnvelope.add(rand.nextInt(6)+1);
		murderEnvelope.add(rand.nextInt(9)+11);
		murderEnvelope.add(rand.nextInt(6)+21);
	}
	
	public void checkDone() {
		if(done == 1) {
			checkCurrent();
			setDone(0);
		}
	}
	
	public void setPlayerCards() {
		for(int i=0;i<numOfPlayers;i++){
			for(int j=0;j<diff;j++) {
				int ran = rand.nextInt(CList.size());
				Players[i].cards.add(CList.get(ran));
				CList.remove(ran);
			}
		}
	}
	
	public String getCards(int num){
		for(int i = 0; i<Players[current].cards.size(); i++){
			if(Players[current].cards.get(i) == num){
				return "X";
			}
			else if(CList.size() > 0){
				for(int j = 0; j<CList.size(); j++){
					if(CList.get(j) == num){
						return "A";
					}
				}
			}else if(getcurrent().getSeen().size()>0) {
				for(int j = 0; j<getcurrent().getSeen().size(); j++){
					if(Players[current].getSeen().get(j) == num){
						return "V";
					}
				}
			}
		}
		return "";
	}
	
	public void table() {
	    JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    Object rows[] = {"carzyCatLady", "fatTony", "hanzMoleMan", "homer", "maggie", "moe", "axe",
	    		"PlutoniumRod","chainsaw", "gun", "knife", "slingShot", "Burn's Mansion", "Springfield elementary",
	    		"Frying Dutchman", "Flander's house", "krusty burger", "comic book store", "kwik-e-mart", "simpson's house",
	    		"moe's tavern"};
	    Object columnNames[] = { "All Cards", "Your Cards"};
	   
	    Object rowData[][] = { { rows[0], getCards(1) },
	    		{ rows[1], getCards(2) },
	    		{ rows[2], getCards(3) },
	    		{ rows[3], getCards(4) },
	    		{ rows[4], getCards(5) },
	    		{ rows[5], getCards(6) },
	    		{ rows[6], getCards(21) },
	    		{ rows[7], getCards(22) },
	    		{ rows[8], getCards(23) },
	    		{ rows[9], getCards(24) },
	    		{ rows[10], getCards(25) },
	    		{ rows[11], getCards(26) },
	    		{ rows[12], getCards(11) },
	    		{ rows[13], getCards(12) },
	    		{ rows[14], getCards(13) },
	    		{ rows[15], getCards(14) },
	    		{ rows[16], getCards(15) },
	    		{ rows[17], getCards(16) },
	    		{ rows[18], getCards(17) },
	    		{ rows[19], getCards(18) },
	    		{ rows[20], getCards(19) },
	    		};
	    
	      
	    JTable table = new JTable(rowData, columnNames);
	    table.setEnabled(false);

	    JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    frame.setSize(700, 398);
	    frame.setVisible(true);

	  }
	
	public Accusation getAccuse() {
		return accuse;
	}
	public void setPassword(int t) {
		password = t;
	}
	public int getAccuseCurrent() {
		return accuseCurrent;
	}
	public void setAccuseCurrent(int number) {
		accuseCurrent = number;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawImage(images.getImage(8, "screens"), 0, 0, null);
		if(accuseCurrent == numOfPlayers) {
			accuseCurrent = 0;
		}
		for(int i=0;i<6;i++) {
			if(i == accuseCurrent) {
				g.drawImage(images.getImage(12, "screens"), 660, 50, null);
				g.drawImage(images.getImage(Players[accuseCurrent].getPlayerId(), "bigToken"), 668,58, null);
			}
		}	
		for(int i=0;i<accuse.getAccuseList().size();i++) {
			if(accuse.getAccuseList().get(i)<=6) {
				g.drawImage(images.getImage(accuse.getAccuseList().get(i), "cards"), 231, 255, null);
			}else if(accuse.getAccuseList().get(i) >=11 && accuse.getAccuseList().get(i)<=19) {
					g.drawImage(images.getImage(accuse.getAccuseList().get(i), "room"), 368, 255, null);
			}else if(accuse.getAccuseList().get(i) >=21 && accuse.getAccuseList().get(i)<=26) {
				g.drawImage(images.getImage(accuse.getAccuseList().get(i), "weaponsCard"), 505, 255, null);
			}
		}
			
		int xValue = 20;
		for(int i=0;i<diff;i++) {
			if(Players[accuseCurrent].cards.get(i) <= 6) {
				if(setShow) {
					g.drawImage(images.getImage(Players[accuseCurrent].cards.get(i), "cards"), xValue, 450, null);
					xValue += 43*numOfPlayers;
				}else {
					g.drawImage(images.getImage(7, "cards"), xValue, 450, null);
					xValue += 43*numOfPlayers;
				}
			}else if(Players[accuseCurrent].cards.get(i) >= 11 && Players[accuseCurrent].cards.get(i) < 20) {
				if(setShow) {
					g.drawImage(images.getImage(Players[accuseCurrent].cards.get(i), "room"), xValue, 450, null);
					xValue += 43*numOfPlayers;
				}else {
					g.drawImage(images.getImage(7, "cards"), xValue, 450, null);
					xValue += 43*numOfPlayers;
				}
			}else if(Players[accuseCurrent].cards.get(i) >= 21 && Players[accuseCurrent].cards.get(i) < 27) {
				if(setShow) {
					g.drawImage(images.getImage(Players[accuseCurrent].cards.get(i), "weaponsCard"), xValue, 450, null);
					xValue += 43*numOfPlayers;
				}else {
					g.drawImage(images.getImage(7, "cards"), xValue, 450, null);
					xValue += 43*numOfPlayers;
				}
			}
		}
	}
	
	
	public void addToStart(String e) {
		start.add(e);
	}
	
	public void addToChoosen(int e) {
		choosen.add(e);
	}
	
	public CollisonTesting getCollision() {
		return cTest;
	}
	
	public weapons[] getWeapons() {
		return Weapons;
	}
	
	public Players[] getPlayers() {
		return Players;
	}
	
	public Card[] getRooms() {
		return Rooms;
	}
	public int getCurrentPlayerNum() {
		return current;
	}
	public int getNumOfPlayers() {
		return numOfPlayers;
	}
	
	public void setPanel(Panel panel) {
		this.panel = panel;
	}
	
	public void setHidden(boolean t) {
		showHidden = t;
	}
	
	public Panel getPanel() {
		return panel;
	}
	
	public void weaponMove() {
		int weaponNum = 0;
		int roomNum = 0;
		for(int i=0;i<3;i++) {
			if(getAccuse().getAccuseList().get(i) >= 21 && getAccuse().getAccuseList().get(i) <= 26) {
				weaponNum = getAccuse().getAccuseList().get(i);
			}
			if(getAccuse().getAccuseList().get(i) >= 11 && getAccuse().getAccuseList().get(i) <= 19) {
				roomNum = getAccuse().getAccuseList().get(i);
			}
		}
		
		if(roomNum == 15) {
			if(dimensions.checkPosAvailable(23,15,(20/10))) {
				for(int i=0;i<6;i++) {
					if(Weapons[i].getWeaponsID() == weaponNum) {
						
						if(dimensions.getVal(Weapons[i].getx(), Weapons[i].gety()) == 0) {
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}else {
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 0);
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}
					}
				}
				panel.reDraw();
			}
			
		}else if(roomNum == 19) {
			if(dimensions.checkPosAvailable(23,5,(30/10))) {
				for(int i=0;i<6;i++) {
					if(Weapons[i].getWeaponsID() == weaponNum) {
						
						if(dimensions.getVal(Weapons[i].getx(), Weapons[i].gety()) == 0) {
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}else {
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 0);
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}
					}
				}
				panel.reDraw();
			}
		}else if(roomNum == 12) {
			if(dimensions.checkPosAvailable(13,5,(40/10))) {
				for(int i=0;i<6;i++) {
					if(Weapons[i].getWeaponsID() == weaponNum) {
						
						if(dimensions.getVal(Weapons[i].getx(), Weapons[i].gety()) == 0) {
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}else {
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 0);
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}
					}
				}
				panel.reDraw();
			}
		}else if(roomNum == 16){
			if(dimensions.checkPosAvailable(4,5,(50/10))) {
				for(int i=0;i<6;i++) {
					if(Weapons[i].getWeaponsID() == weaponNum) {
						
						if(dimensions.getVal(Weapons[i].getx(), Weapons[i].gety()) == 0) {
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}else {
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 0);
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}
					}
				}
				panel.reDraw();
			}
		}else if(roomNum == 11){
			if(dimensions.checkPosAvailable(5,14,(60/10))) {
				for(int i=0;i<6;i++) {
					if(Weapons[i].getWeaponsID() == weaponNum) {
						
						if(dimensions.getVal(Weapons[i].getx(), Weapons[i].gety()) == 0) {
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}else {
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 0);
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}
					}
				}
				panel.reDraw();
			}
		}else if(roomNum == 17){
			if(dimensions.checkPosAvailable(4,22,(70/10))) {
				for(int i=0;i<6;i++) {
					if(Weapons[i].getWeaponsID() == weaponNum) {
						
						if(dimensions.getVal(Weapons[i].getx(), Weapons[i].gety()) == 0) {
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}else {
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 0);
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}
					}
				}
				panel.reDraw();
			}
		}else if(roomNum == 14){
			if(dimensions.checkPosAvailable(11,24,(80/10))) {
				for(int i=0;i<6;i++) {
					if(Weapons[i].getWeaponsID() == weaponNum) {
						
						if(dimensions.getVal(Weapons[i].getx(), Weapons[i].gety()) == 0) {
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}else {
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 0);
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}
					}
				}
				panel.reDraw();
				panel.reDraw();
			}
		}else if(roomNum == 18){
			if(dimensions.checkPosAvailable(17,24,(90/10))) {
				for(int i=0;i<6;i++) {
					if(Weapons[i].getWeaponsID() == weaponNum) {
						
						if(dimensions.getVal(Weapons[i].getx(), Weapons[i].gety()) == 0) {
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}else {
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 0);
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}
					}
				}
				panel.reDraw();
				panel.reDraw();
			}
		}else if(roomNum == 13){
			if(dimensions.checkPosAvailable(24,22,(100/10))) {
				for(int i=0;i<6;i++) {
					if(Weapons[i].getWeaponsID() == weaponNum) {
						
						if(dimensions.getVal(Weapons[i].getx(), Weapons[i].gety()) == 0) {
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}else {
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 0);
							Weapons[i].sety(dimensions.getY());
							Weapons[i].setx(dimensions.getX());
							dimensions.setVal(Weapons[i].getx(), Weapons[i].gety(), 47);
						}
					}
				}
				panel.reDraw();
			}
		}
	}
	
	public int playerMove() {
		int roomNum = 0;
		int playerNum = 0;
		int num = 0;
		int n = 0;
		for(int i=0;i<3;i++) {
			if(getAccuse().getAccuseList().get(i) >= 1 && getAccuse().getAccuseList().get(i) <= 6) {
				playerNum = getAccuse().getAccuseList().get(i);
			}
			if(getAccuse().getAccuseList().get(i) >= 11 && getAccuse().getAccuseList().get(i) <= 19) {
				roomNum = getAccuse().getAccuseList().get(i);
			}
			
		}
		for(int i =0;i<PlayersTwo.length;i++) {
			if(!(PlayersTwo[i] == null) && PlayersTwo[i].getPlayerId() == playerNum) {
				n = 2;
			}
		}
		for(int i =0;i<Players.length;i++) {
			if(!(Players[i] == null) && Players[i].getPlayerId() == playerNum) {
				n = 1;
			}
		}
		if(n == 1) {
			for(int i=0;i<Players.length;i++) {
				if(Players[i].getPlayerId() == playerNum) {
					num = i;
					if(roomNum == 15) {
						if(dimensions.checkPosAvailable(23,15,(20/10))) {
							Players[num].setDoor(20/10);
							dimensions.setVal(Players[num].getx(), Players[num].gety(), 0);
							Players[num].sety(dimensions.getY());
							Players[num].setx(dimensions.getX());
							dimensions.setVal(Players[num].getx(), Players[num].gety(), 47);
							rollNum = 0;
							panel.reDraw();
							return 0;
						}
						
					}else if(roomNum == 19) {
						if(dimensions.checkPosAvailable(23,5,(30/10))) {
							Players[num].setDoor(30/10);
							dimensions.setVal(Players[num].getx(), Players[num].gety(), 0);
							Players[num].sety(dimensions.getY());
							Players[num].setx(dimensions.getX());
							dimensions.setVal(Players[num].getx(), Players[num].gety(), 47);
							rollNum = 0;
							panel.reDraw();
							return 0;
						}
					}else if(roomNum == 12) {
						if(dimensions.checkPosAvailable(13,5,(40/10))) {
							Players[num].setDoor(40/10);
							dimensions.setVal(Players[num].getx(), Players[num].gety(), 0);
							Players[num].sety(dimensions.getY());
							Players[num].setx(dimensions.getX());
							dimensions.setVal(Players[num].getx(), Players[num].gety(), 47);
							rollNum = 0;
							panel.reDraw();
							return 0;
						}
					}else if(roomNum == 16){
						if(dimensions.checkPosAvailable(4,5,(50/10))) {
							PlayersTwo[num].setDoor(50/10);
							dimensions.setVal(Players[num].getx(), PlayersTwo[num].gety(), 0);
							PlayersTwo[num].sety(dimensions.getY());
							PlayersTwo[num].setx(dimensions.getX());
							dimensions.setVal(Players[num].getx(), PlayersTwo[num].gety(), 47);
							rollNum = 0;
							panel.reDraw();
							return 0;
						}
					}else if(roomNum == 11){
						if(dimensions.checkPosAvailable(5,14,(60/10))) {
							Players[num].setDoor(60/10);
							dimensions.setVal(Players[num].getx(), Players[num].gety(), 0);
							Players[num].sety(dimensions.getY());
							Players[num].setx(dimensions.getX());
							dimensions.setVal(Players[num].getx(), Players[num].gety(), 47);
							rollNum = 0;
							panel.reDraw();
							return 0;
						}
					}else if(roomNum == 17){
						if(dimensions.checkPosAvailable(4,22,(70/10))) {
							Players[num].setDoor(70/10);
							dimensions.setVal(Players[num].getx(), Players[num].gety(), 0);
							Players[num].sety(dimensions.getY());
							Players[num].setx(dimensions.getX());
							dimensions.setVal(Players[num].getx(), Players[num].gety(), 47);
							rollNum = 0;
							panel.reDraw();
							return 0;
						}
					}else if(roomNum == 14){
						if(dimensions.checkPosAvailable(11,24,(80/10))) {
							Players[num].setDoor(80/10);
							dimensions.setVal(Players[num].getx(), Players[num].gety(), 0);
							Players[num].sety(dimensions.getY());
							Players[num].setx(dimensions.getX());
							dimensions.setVal(Players[num].getx(), Players[num].gety(), 47);
							rollNum = 0;
							panel.reDraw();
							return 0;
						}
					}else if(roomNum == 18){
						if(dimensions.checkPosAvailable(17,24,(90/10))) {
							Players[num].setDoor(90/10);
							dimensions.setVal(Players[num].getx(), Players[num].gety(), 0);
							Players[num].sety(dimensions.getY());
							Players[num].setx(dimensions.getX());
							dimensions.setVal(Players[num].getx(), Players[num].gety(), 47);
							rollNum = 0;
							panel.reDraw();
							return 0;
						}
					}else if(roomNum == 100){
						if(dimensions.checkPosAvailable(24,22,(100/10))) {
							Players[num].setDoor(100/10);
							dimensions.setVal(Players[num].getx(), Players[num].gety(), 0);
							Players[num].sety(dimensions.getY());
							Players[num].setx(dimensions.getX());
							dimensions.setVal(Players[num].getx(), Players[num].gety(), 47);
							rollNum = 0;
							panel.reDraw();
							return 0;
						}
					}
				}
			}
		}
		
		if(n == 2) {
			for(int i=0;i<PlayersTwo.length;i++) {
				if(!(PlayersTwo[i] == null)) {
					if(PlayersTwo[i].getPlayerId() == playerNum) {
						num = i;
						if(roomNum == 15) {
							if(dimensions.checkPosAvailable(23,15,(20/10))) {
								PlayersTwo[num].setDoor(20/10);
								dimensions.setVal(PlayersTwo[num].getx(), PlayersTwo[num].gety(), 0);
								PlayersTwo[num].sety(dimensions.getY());
								PlayersTwo[num].setx(dimensions.getX());
								dimensions.setVal(PlayersTwo[num].getx(), PlayersTwo[num].gety(), 47);
								rollNum = 0;
								panel.reDraw();
								return 0;
							}
							
						}else if(roomNum == 19) {
							if(dimensions.checkPosAvailable(23,5,(30/10))) {
								PlayersTwo[num].setDoor(30/10);
								dimensions.setVal(PlayersTwo[num].getx(), PlayersTwo[num].gety(), 0);
								PlayersTwo[num].sety(dimensions.getY());
								PlayersTwo[num].setx(dimensions.getX());
								dimensions.setVal(PlayersTwo[num].getx(), PlayersTwo[num].gety(), 47);
								rollNum = 0;
								panel.reDraw();
								return 0;
							}
						}else if(roomNum == 12) {
							if(dimensions.checkPosAvailable(13,5,(40/10))) {
								PlayersTwo[num].setDoor(40/10);
								dimensions.setVal(PlayersTwo[num].getx(), PlayersTwo[num].gety(), 0);
								PlayersTwo[num].sety(dimensions.getY());
								PlayersTwo[num].setx(dimensions.getX());
								dimensions.setVal(PlayersTwo[num].getx(), PlayersTwo[num].gety(), 47);
								rollNum = 0;
								panel.reDraw();
								return 0;
							}
						}else if(roomNum == 16){
							if(dimensions.checkPosAvailable(4,5,(50/10))) {
								PlayersTwo[num].setDoor(50/10);
								dimensions.setVal(PlayersTwo[num].getx(), PlayersTwo[num].gety(), 0);
								PlayersTwo[num].sety(dimensions.getY());
								PlayersTwo[num].setx(dimensions.getX());
								dimensions.setVal(PlayersTwo[num].getx(), PlayersTwo[num].gety(), 47);
								rollNum = 0;
								panel.reDraw();
								return 0;
							}
						}else if(roomNum == 11){
							if(dimensions.checkPosAvailable(5,14,(60/10))) {
								PlayersTwo[num].setDoor(60/10);
								dimensions.setVal(Players[num].getx(), PlayersTwo[num].gety(), 0);
								PlayersTwo[num].sety(dimensions.getY());
								PlayersTwo[num].setx(dimensions.getX());
								dimensions.setVal(PlayersTwo[num].getx(), PlayersTwo[num].gety(), 47);
								rollNum = 0;
								panel.reDraw();
								return 0;
							}
						}else if(roomNum == 17){
							if(dimensions.checkPosAvailable(4,22,(70/10))) {
								PlayersTwo[num].setDoor(70/10);
								dimensions.setVal(PlayersTwo[num].getx(), PlayersTwo[num].gety(), 0);
								PlayersTwo[num].sety(dimensions.getY());
								PlayersTwo[num].setx(dimensions.getX());
								dimensions.setVal(PlayersTwo[num].getx(), PlayersTwo[num].gety(), 47);
								rollNum = 0;
								panel.reDraw();
								return 0;
							}
						}else if(roomNum == 14){
							if(dimensions.checkPosAvailable(11,24,(80/10))) {
								PlayersTwo[num].setDoor(80/10);
								dimensions.setVal(PlayersTwo[num].getx(), PlayersTwo[num].gety(), 0);
								PlayersTwo[num].sety(dimensions.getY());
								PlayersTwo[num].setx(dimensions.getX());
								dimensions.setVal(PlayersTwo[num].getx(), PlayersTwo[num].gety(), 47);
								rollNum = 0;
								panel.reDraw();
								return 0;
							}
						}else if(roomNum == 18){
							if(dimensions.checkPosAvailable(17,24,(90/10))) {
								PlayersTwo[playerNum].setDoor(90/10);
								dimensions.setVal(PlayersTwo[playerNum].getx(), PlayersTwo[playerNum].gety(), 0);
								PlayersTwo[playerNum].sety(dimensions.getY());
								PlayersTwo[playerNum].setx(dimensions.getX());
								dimensions.setVal(PlayersTwo[playerNum].getx(), PlayersTwo[current].gety(), 47);
								rollNum = 0;
								panel.reDraw();
								return 0;
							}
						}else if(roomNum == 100){
							if(dimensions.checkPosAvailable(24,22,(100/10))) {
								PlayersTwo[num].setDoor(100/10);
								dimensions.setVal(PlayersTwo[num].getx(), PlayersTwo[num].gety(), 0);
								PlayersTwo[num].sety(dimensions.getY());
								PlayersTwo[num].setx(dimensions.getX());
								dimensions.setVal(PlayersTwo[num].getx(), PlayersTwo[num].gety(), 47);
								rollNum = 0;
								panel.reDraw();
								return 0;
							}
						}
					}
				}
			}
		}
		return 0;

	}
	
	public void checkWin() {
		int matches = 0;
		for(int i=0;i<murderEnvelope.size();i++) {
			for(int j=0;j<getAccuse().getAccuseList().size();j++) {
				if(murderEnvelope.contains(getAccuse().getAccuseList().get(j))) {
					matches += 1;
				}
			}
		}
		
		if(matches/3 == 3) {
			inputPanel.message("Contgrats\nYou win");
		}else {
			inputPanel.message("no win");
			loser.add(current);
			getAccuse().reset();
			getAccuse().setBooleans();
			
			if(loser.size() == numOfPlayers-1) {
				for(int i=0;i<loser.size();i++) {
					for(int j=0;j<numOfPlayers;j++) {
						if(!(loser.contains(j))) {
							inputPanel.message(Players[j].getName()+" Wins the game");
							return;
						}
					}
				}
			}
		}
	}
	
	public void setShow(boolean t) {
		setShow = t;
	}

}