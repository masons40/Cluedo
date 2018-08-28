import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class InputPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private static JTextField textField;
	private static JTextArea textArea;

	int nameCount = 0;
	
	int cardNumber = 0;

	GameMechanics mech;
	CollisonTesting cTest;
	Accusation acc;
	int spaces = 0;
	int firstRoll = 0;
	int i = 0;
	int number = 0;
	boolean player = true;
	boolean room = true;
	boolean weapon = true;
	int input = 0;
	int counter = 0;

	DiceRoll roll = new DiceRoll();
	KeyManager keyManager;
	
	public InputPanel(int width, int height,GameMechanics mech,Accusation accuse) {
		this.mech = mech;
		this.acc = accuse;
		cTest = mech.getCollision();
		keyManager = new KeyManager(mech,cTest);
		textField = new JTextField(5);
		textField.setPreferredSize(new Dimension(194,672));
		textField.addKeyListener(keyManager);
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(194,672));
        textArea.setEditable(false);

        GridBagLayout gridBag = new GridBagLayout();
        setLayout(gridBag);
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        gridBag.setConstraints(textField, c);
        add(textField);
        textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text = textField.getText();
				text = text.toLowerCase();
				text = text.trim();
				textTest(text);
				textField.setText("");
				
			}
        	
        });
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        gridBag.setConstraints(textArea, c);
        add(textArea);
	}
	
	private void textTest(String text) {
		
		if(mech.getGameState() == 2) {
			if (text.equals("roll") && i == 0) {
				spaces = Roll();
				textArea.append("press u,d,l,r to move character\n or click on the board and\n use the arrow keys\n");
				i++;
				mech.setRoll(spaces);
				mech.setDone(0);
			}else {
				if (text.equals("quit")) {
					System.exit(0);
				}else if (text.equals("notes")) {
					mech.table();
				}else if (text.equals("help")) {
					help();
				}else if (text.equals("rules")) {
					rules();
				}else if (text.equals("cheat")) {
					message("cheat command");
					mech.setCurrentGameState(4);
					mech.getPanel().reDraw();
				}else if (text.equals("question") && mech.getOb().getDoor() > 0) {
					mech.setCurrentGameState(5);
					mech.getPanel().reDraw();
				}else if (text.equals("names")) {
					for(int i=0;i<mech.getNumOfPlayers();i++) {
						message(mech.getPlayers()[i].getPlayerId() + "\n");
						message(mech.getPlayers()[i].getName() + "\n");
					}
					
				}else if(text.equals("log")) {
					message(mech.getAccuse().getAswers().toString());
				}else if(text.equals("answers")) {
					message(mech.getAccuse().getAswers().toString());
				}else if(text.equals("list")){
					message(mech.getcurrent().getSeen().toString());
				}else {
					playerMove(text);
				}
			}
		}else if(mech.getGameState() == 1) {
			if(text.matches(".*\\d+.*")){
				if(mech.getGameState() == 1 && input == 0){
					input++;
					if(input == 1 && Integer.parseInt(text) <= 6 && Integer.parseInt(text) >= 2) {
						message("Number of players:" + text);
						mech.setMax(Integer.parseInt(text));
						input++;
					}else {
						input = 0;
						message("Enter a number between 2-6");
					}
					if(input == 2) {
						getNames();
					}
				}
			}else if(!(text.matches(".*\\d+.*"))){
				if(input == 2 && counter < mech.getNumOfPlayers()){
					mech.names.add(text);
					counter+=1;
					message("Name Entered:" + text);
					if(counter == mech.getNumOfPlayers()) {
						mech.setCurrentGameState(8);
						mech.getPanel().reDraw();
					}
				}
			}
		}else if(mech.getGameState() == 7) {
			if(text.matches(".*\\d+.*")){
				int x =  Integer.parseInt(text);
				if(x>=1 && x <=6 && !(mech.getAccuse().getAccuseList().contains(x))) {
					mech.getAccuse().accuseAdditionPlayer(x);
				}else if(x> 10 && x < 20 && !(mech.getAccuse().getAccuseList().contains(x))) {
					mech.getAccuse().accuseAdditionRoom(x);
				}else if(x > 20 && x < 27 && !(mech.getAccuse().getAccuseList().contains(x))) {
					mech.getAccuse().accuseAdditionWeapon(x);
				}else {
					message("Final Accusation already\ncontains this card");
				}
			}else if (text.equals("cheat")) {
				message("cheat command");
				mech.setCurrentGameState(2);
				mech.getPanel().reDraw();
			}
		}else if(mech.getGameState() == 5) {
			if(text.matches(".*\\d+.*")){
				switch(text) {
					case "1":
						acc.accuseAdditionPlayer(1);
						break;
					case "2":
						acc.accuseAdditionPlayer(2);
						break;
					case "3":
						acc.accuseAdditionPlayer(3);
						break;
					case "4":
						acc.accuseAdditionPlayer(4);
						break;
					case "5":
						acc.accuseAdditionPlayer(5);
						break;
					case "6":
						acc.accuseAdditionPlayer(6);
						break;
	
					case "21":
						acc.accuseAdditionWeapon(21);
						break;
					case "22":
						acc.accuseAdditionWeapon(22);
						break;
					case "23":
						acc.accuseAdditionWeapon(23);
						break;
					case "24":
						acc.accuseAdditionWeapon(24);
						break;
					case "25":
						acc.accuseAdditionWeapon(25);
						break;
					case "26":
						acc.accuseAdditionWeapon(26);
						break;
				}	
			}else if(text.equals("quit")) {
				System.exit(0);
			}else if(text.equals("done") && acc.accuseFull()) {
				
				mech.setCurrentGameState(6);
				mech.setAccuseCurrent(mech.current + 1);
				mech.getAccuse().addQuestion(mech.Players[mech.current].getName());
				mech.getPanel().reDraw();
				mech.weaponMove();
				mech.playerMove();
				message("Do you have any\nmatching cards?Enter the value\nof the card");
			}else if (text.equals("cheat")) {
				message("cheat command");
				mech.setCurrentGameState(4);
				mech.getPanel().reDraw();
			}
		}else if(mech.getGameState() == 6) {
			if(text.matches(".*\\d+.*")){
				if(acc.checkCard(Integer.parseInt(text))) {
					cardNumber = Integer.parseInt(text);
					acc.addAnswer(mech.getPlayers()[mech.getAccuseCurrent()].getName());
					acc.reset();
					acc.setBooleans();
					mech.setCurrentGameState(2);
					message(mech.getAccuse().getAswers().toString());
					mech.getPanel().reDraw();
					mech.getcurrent().getSeen().add(Integer.parseInt(text));
				}else {
					message("Number doesnt match\nPlease enter again");
				}
			}else if(text.equals("quit")) {
				System.exit(0);
			}else if(text.equals("yes")) {
				if(mech.getAccuse().checkMatch(mech.getAccuseCurrent())) {
					message("Please enter Card Number");
				}else {
					mech.setAccuseCurrent(mech.getAccuseCurrent() + 1);
				}
			}else if(text.equals("no")) {
				if(mech.getAccuse().checkMatch(mech.getAccuseCurrent())) {
					mech.setAccuseCurrent(mech.getAccuseCurrent());
					mech.getPanel().reDraw();
				}else {
					mech.setAccuseCurrent(mech.getAccuseCurrent() + 1);
					message(mech.getAccuseCurrent() + "");
					if(mech.getAccuseCurrent() == mech.current) {
						acc.addAnswer("Nobody");
						acc.reset();
						acc.setBooleans();
						mech.setCurrentGameState(2);
						mech.getPanel().reDraw();
					}
					mech.getPanel().reDraw();
				}
			}else if (text.equals("cheat")) {
				message("cheat command");
				mech.setCurrentGameState(4);
				mech.getPanel().reDraw();
			}
		}else {
			if (text.equals("quit")) {
				System.exit(0);
			}else if (text.equals("notes")) {
				mech.table();
			}else if (text.equals("help")) {
				help();
			}else if (text.equals("rules")) {
				rules();
			}else if (text.equals("cheat")) {
				mech.setCurrentGameState(4);
			}
		}
	}
	
	public int Roll() {
		roll.Diceroll();
		spaces = roll.getTotal();
		textArea.append("you got " + spaces + "\n");
		return spaces;
	}

	public void playerMove(String text) {
		
		if (text.equals("u")) {
			if (cTest.testMove("u", mech.getOb())) {
				System.out.println("got here");
				mech.setInput("u");
				textArea.append("Moved player up" + "\n");
			}
		}else if (text.equals("d")) {
			if (cTest.testMove(text, mech.getOb())) {
				mech.setInput("d");
				textArea.append("Moved player down" + "\n");
			}
		}else if (text.equals("r")) {
			if (cTest.testMove("r", mech.getOb())) {
				mech.setInput("r");
				textArea.append("Moved player right" + "\n");
			}
		}else if (text.equals("done")) {
			textArea.append("Next player turn. Type 'roll' to roll the dice" + "\n");
			mech.setDone(1);
			i = 0;
			mech.setPassword(0);
			clear();
			mech.getPanel().reDraw();
		}else if (text.equals("l")) {
			if (cTest.testMove("l", mech.getOb())) {
				mech.setInput("l");
				textArea.append("Moved player left" + "\n");
			}
		}else if (text.equals("1")) {
			setExit(1);
			textArea.append("Player exited through exit 1" + "\n");
			spaces--;
		}else if (text.equals("2")) {
			setExit(2);
			textArea.append("Player exited through exit 2" + "\n");
		}else if (text.equals("3")) {
			setExit(3);
			textArea.append("Player exited through exit 3" + "\n");
		}else if (text.equals("4")) {
			setExit(4);
			textArea.append("Player exited through exit 4" + "\n");
		}else if (text.equals("passage")) {
			setExit(5);
			textArea.append("Player exited through exit secret passage way" + "\n");
		}
	}

	public void setExit(int num) {
		mech.setExitNum(num);
	}

	public void errorMessages(int num) {
		if (num == 1) {
			textArea.append("Type 'quit' to end the game" + "\n");
			textArea.append("Player has a choice to \nexit by the door or to exit by \nthe secret passage way\n");
			textArea.append("To exit by the door enter\n the value of the door you \nwould like to exit out of\n");
			textArea.append("Or to exit by the secret \npassage way type 'passage'\n");
		} else if (num == 2) {
			textArea.append("Player has a choice \nto exit by the door\n");
			textArea.append("To exit by the door enter \nthe value of the door you \nwould like to exit out of\n");
		} else if (num == 3) {
			textArea.append("No more moves\n");
			textArea.append("Type 'done' to end your turn\n");
		}
	}

	public void Messages(String name) {
		textArea.append(name + ":please roll" + "\n");
	}

	public void message(String message) {
		textArea.append(message + "\n");
	}

	public int getSpaces() {
		return spaces;
	}

	public void setSpaces() {
		spaces = 0;
	}

	public void help() {
		textArea.append(
				"--------------------------------------------------------------------------------------------\n'DONE' ends your turn\n"
						+ "'QUIT' closes the game\n'ROLL' rolls the dice\n'RULES' shows game rules\n"
						+ "'U','D','L','R' moves player up down left or right(must ROLL first)\n"
						+ "'NUMBER e.g(1)' to leave by that door(must be in room)\n"
						+ "'PASSAGE' to leave through secret passage(must be in room)\n"
						+ "-----------------------------------------------------------------------------------------");
	}

	public void rules() {
		if (!java.awt.Desktop.isDesktopSupported()) {
			System.err.println("Desktop is not supported (fatal)");
			System.exit(1);
		}
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
			System.err.println("Desktop doesn't support the browse action (fatal)");
			System.exit(1);
		}

		try {
			java.net.URI uri = new java.net.URI("https://www.hasbro.com/common/instruct/Clue_(2002).pdf");
			desktop.browse(uri);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public boolean checkValue(int value) {
		if(value <= 6 && player) {
			player = false;
			return false;
		}else if(value >= 11 && value <= 19 && room) {
			room = false;
			return false;
		}else if(value >= 21 && value <= 26 && weapon) {
			weapon = false;
			return false;
		}else if(!(room)) {
			message("Accusation already contains a room");
			return true;
		}else if(!(weapon)) {
			message("Accusation already contains a weapon");
			return true;
		}else if(!(player)) {
			message("Accusation already contains a player");
			return true;
		}
		return false;
	}
	
	public void getNames() {
		message("Please Enter the Names:");
	}
	
	public void clear() {
		textArea.setText("");
	}
	
	public int getCardNumber() {
		return cardNumber;
	}
}