import java.util.ArrayList;

public class Accusation {
	
	Players[] players;
	ArrayList<Integer> accuse = new ArrayList<Integer>();
	ArrayList<String> questions = new ArrayList<String>();
	ArrayList<String> answers = new ArrayList<String>();

	boolean player = false;
	boolean weapon = false;
	boolean room = false;
	GameMechanics game;
	Frame frame;
	int playerWithCard = 0;
	
	public Accusation(Players[] players,GameMechanics game) {
		this.players = players;
		this.game = game;
	}
	public Players getCurrentPlayer(int i) {
		return players[i];
	}
	
	public int size() {
		return players.length;
	}
	public ArrayList<Integer> getAccuseList() {
		return accuse;
	}
	public boolean accuseFull() {
		return accuse.size() == 3;
	}
	public void accuseAdditionRoom(int number) {
		if(!(room)) {
			
			if(game.getGameState() == 7) {
				if(number == 15) {
					accuse.add(15);
				}else if(number == 19) {
					accuse.add(19);
				}else if(number == 12) {
					accuse.add(12);
				}else if(number == 16) {
					accuse.add(16);
				}else if(number == 11) {
					accuse.add(11);
				}else if(number == 17) {
					accuse.add(17);
				}else if(number == 14) {
					accuse.add(14);
				}else if(number == 18) {
					accuse.add(18);
				}else if(number == 13) {
					accuse.add(13);
				}
			}else {
				if(number == 2) {
					accuse.add(15);
				}else if(number == 3) {
					accuse.add(19);
				}else if(number == 4) {
					accuse.add(12);
				}else if(number == 5) {
					accuse.add(16);
				}else if(number == 6) {
					accuse.add(11);
				}else if(number == 7) {
					accuse.add(17);
				}else if(number == 8) {
					accuse.add(14);
				}else if(number == 9) {
					accuse.add(18);
				}else if(number == 10) {
					accuse.add(13);
				}
			}
			room = true;
			if(game.getAccuse().accuseFull() && game.getGameState() == 7) {
				game.setCurrentGameState(2);
				
				game.getPanel().reDraw();
				game.checkWin();
			}
			game.getPanel().reDraw();
		}
	}
	public void accuseAdditionWeapon(int number) {
		
		if(!(weapon)) {
			accuse.add(number);
			weapon = true;
			if(accuseFull()) {
				game.getInputPanel().message("Accusation finished\ntype done");
				game.setAccuseCurrent(game.getCurrentPlayerNum());
			}
			game.getPanel().reDraw();
		}else {
			game.getInputPanel().message("weapon already choosen");
		}
		
		if(game.getAccuse().accuseFull() && game.getGameState() == 7) {
			game.setCurrentGameState(2);
			game.getPanel().reDraw();
			game.checkWin();
		}
	}
	public void accuseAdditionPlayer(int number) {
		
		if(!(player)) {
			accuse.add(number);
			player = true;
			if(accuseFull()) {
				game.getInputPanel().message("Accusation finished\ntype done");
				game.setAccuseCurrent(game.getCurrentPlayerNum());
			}
			game.getPanel().reDraw();
		}else {
			game.getInputPanel().message("Player already choosen");
		}
		
		if(game.getAccuse().accuseFull() && game.getGameState() == 7) {
			game.setCurrentGameState(2);
			game.getPanel().reDraw();
			game.checkWin();
		}
	}
	public void addQuestion(String name) {
		String q = name + "questioned:";
		for(int i=0;i<3;i++) {
			int number = accuse.get(i);
			q.concat(",");
			if(number >=1 && number <=6) {
				switch(number) {
					case 1:
						q.concat("crazy cat lady");
						break;
					case 2:
						q.concat("fat tony");
						break;
					case 3:
						q.concat("hanz");
						break;
					case 4:
						q.concat("homer");
						break;
					case 5:
						q.concat("maggie");
						break;
					case 6:
						q.concat("moe");
						break;
				}
			}else if(number >=11 && number <20) {
				switch(number) {
					case 11:
						q.concat("Burns Mansion");
						break;
					case 12:
						q.concat("Spring Field School");
						break;
					case 13:
						q.concat("Frying Dutchman");
						break;
					case 14:
						q.concat("Flanders House");
						break;
					case 15:
						q.concat("Krusty Burger");
						break;
					case 16:
						q.concat("Comic Book Store");
						break;
					case 17:
						q.concat("Kwik-E-Mart");
						break;
					case 18:
						q.concat("Simpsons House");
						break;
					case 19:
						q.concat("Moes Tavern");
						break;
				}
			}else if(number >=21 && number <27) {
				switch(number) {
					case 21:
						q.concat("Axe");
						break;
					case 22:
						q.concat("Atomic Bomb");
						break;
					case 23:
						q.concat("Chainsaw");
						break;
					case 24:
						q.concat("Gun");
						break;
					case 25:
						q.concat("Knife");
						break;
					case 26:
						q.concat("Sling Shot");
						break;
				}	
			}
		}
	}
	public boolean checkCard(int num) {
		return accuse.contains(num);
	}
	public boolean checkMatch(int p) {
		for(int i=0;i<3;i++) {
			for(int j=0;j<game.diff;j++) {
				if(accuse.get(i) == players[p].cards.get(j)) {
					return true;
				}
			}
		}
		return false;
	}
	public void reset() {
		accuse.clear();
		game.setAccuseCurrent(0);
	}
	public void addAnswer(String name) {
		answers.add(name + " showed a card");
	}
	public int getPlayerWithCard() {
		return playerWithCard;
	}
	
	public ArrayList<String> getQuestions() {
		return questions;
	}
	public ArrayList<String> getAswers() {
		return answers;
	}
	public void setBooleans() {
		player = false;
		weapon = false;
		room = false;
	}
	
	public void clearAll() {
		accuse.clear();
	}
}
