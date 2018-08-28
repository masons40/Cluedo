import java.util.Random;


public class DiceRoll {
	
	//creating necessary variables
	int dice1;
	int dice2;
	int total;
	Random rand = new Random();
	
	
	public void Diceroll()
	{
		//generating random dice roll number between 1 and 6
		dice1 = rand.nextInt(6)+1;
		dice2 = rand.nextInt(6)+1;
		total = dice1+dice2;
	}
	
	//returning the dice total
	public int getTotal()
	{
		return total;
		
	}
	

}