import java.util.ArrayList;

public class Player extends Person{
	private String name;
	private int chips;
	private int bet;
	
	public Player(String name, int chips){
		this.name = name;
		this.chips = chips;
	}
	public String getName(){
		return name;
	}
	public int makeBet(){
		bet = 50;
		if(chips <= 0){
			return 0;
		}else
			return bet;		
	}
	public boolean hit_me(Table table){
		if(getTotalValue() <= 16){
			return true;
		}else
			return false;
	}
	public int getCurrentChips(){
		return chips;
	}
	public void increaseChips (int diff){
		chips += diff;
	}
	public void sayHello() {
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}
}
