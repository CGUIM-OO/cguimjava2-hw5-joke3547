import java.util.ArrayList;

public class Table {
	final int MAXPLAYER = 4;
	private Deck d;
	private Player[] players;
	private Dealer dealer;
	private int[] pos_betArray;
	public Table(int nDeck){
		d = new Deck(nDeck);
		players = new Player[MAXPLAYER];
	}
	public void set_player(int pos, Player p){
		players[pos]=p;
	}
	public Player[] get_player(){
		return players;
	}
	public void set_dealer(Dealer d){
		dealer = d;
	}
	public Card get_face_up_card_of_dealer(){
		return dealer.getOneRoundCard().get(1);
	}
	private void ask_each_player_about_bets(){
		for (Player p : players){
			p.sayHello();
			p.makeBet();
			for(int i=0 ; i < 4 ; i++){
				int[] pos_betArray = new int[MAXPLAYER];
				pos_betArray[i] = players[i].makeBet();
			}
		}		
	}
	private void distribute_cards_to_dealer_and_players(){
		for (int i = 0; i < MAXPLAYER; i++){
			ArrayList temp = new ArrayList<Card>();
			   temp.add(d.getOneCard(true));
			   temp.add(d.getOneCard(true));
			   players[i].setOneRoundCard(temp);
		}
		ArrayList tempD = new ArrayList<Card>();
		  tempD.add(d.getOneCard(false));
		  tempD.add(d.getOneCard(true));
		  dealer.setOneRoundCard(tempD);
		  System.out.print("Dealer's face up card is ");
		  get_face_up_card_of_dealer().printCard();
	}
	private void ask_each_player_about_hits(){
		for (int i = 0; i < MAXPLAYER; i++) {
		   ArrayList<Card> temp = new ArrayList<Card>();//new一個暫存卡片的temp
		   temp = players[i].getOneRoundCard();         //用temp暫存各個玩家一局的牌
		   boolean hit_me = false;                      //先設為false
		   do{
			   hit_me = players[i].hit_me(this);
		       if (hit_me) {                            //回傳boolean值
		          temp.add(d.getOneCard(true));         //暫存中加入1張打開的牌
		          players[i].setOneRoundCard(temp);     //讓玩家這一局的牌為暫存中的牌
		          System.out.print("Hit!" + players[i].getName() + "'s cards now:");
		        for (int a = 0; a < players[i].getOneRoundCard().size(); a++) {
		          players[i].getOneRoundCard().get(a).printCard();//print出玩家手牌
		        }
		      }else
		    	  System.out.println(players[i].getName() + "  Pass hit!");
		   } while (hit_me);
		}
	}
	private void ask_dealer_about_hits(){
		boolean hit_me = false;
		 do {
			 hit_me = dealer.hit_me(this);
			   if (hit_me){
			    ArrayList<Card> tempD = new ArrayList<Card>();
			    tempD = dealer.getOneRoundCard();
			    tempD.add(d.getOneCard(true));
			   dealer.hit_me(this);
			   }
			  } while (hit_me == true);
			  System.out.println("Dealer's hit is over!");
	}
	private void calculate_chips(){
		System.out.print("Dealer's card value is "+ dealer.getTotalValue()+ " ,Cards:");
		  dealer.printAllCard();
		  for(int i = 0; i < MAXPLAYER;i++){
			  int pv = players[i].getTotalValue();
			  int dv = dealer.getTotalValue();
		   System.out.println(players[i].getName()+" card value is "+ pv);
		   if(dv > pv && dv <= 21 && pv <= 21){
		    players[i].increaseChips(-players[i].makeBet());
		    System.out.println(", Loss " + players[i].makeBet() + " Chips, the Chips now is: " + players[i].getCurrentChips());
		   }
		   if(dv < pv && dv <= 21 && pv <= 21){
		    players[i].increaseChips(players[i].makeBet());
		    System.out.println(", Get " + players[i].makeBet() + " Chips, the Chips now is: " + players[i].getCurrentChips());
		    
		   }
		   if(pv > 21){
		    players[i].increaseChips(-players[i].makeBet());
		    System.out.println(", Loss " + players[i].makeBet() + " Chips, the Chips now is: " + players[i].getCurrentChips());
		   }
		   if(dv > 21 && pv < 21){
		    players[i].increaseChips(players[i].makeBet());
		    System.out.println(", Get " + players[i].makeBet() + " Chips, the Chips now is: " + players[i].getCurrentChips());  
		   }
		   if(dv == pv && dv<=21 && pv<=21){
		    players[i].increaseChips(0);
		    System.out.println(",chips have no change! The Chips now is: " + players[i].getCurrentChips());  
		   }
		   }
	}
	public int[] get_palyers_bet(){
		 int[] get_palyers_bet = new int[MAXPLAYER];
		  for(int i = 0; i < MAXPLAYER;i++){
		   get_palyers_bet[i] = players[i].makeBet();
		  }
		  return get_palyers_bet;
	}
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
}
