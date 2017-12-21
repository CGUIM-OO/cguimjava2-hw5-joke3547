import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;
	private ArrayList<Card> usedCard;
	private ArrayList openCard; 
	public int nUsed;

	public Deck(int nDeck) {
		cards = new ArrayList<Card>();
		usedCard = new ArrayList<Card>();

		for (int n = 1; n <= nDeck; n++) {
			for (Card.Suit s : Card.Suit.values()) {
				for (int r = 1; r <= 13; r++) {
					Card card = new Card(s, r);
					cards.add(card);
				}
			}
		}
		shuffle();
	}

	public void printDeck() {

		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).printCard();
		}
	}

	public ArrayList<Card> getAllCards() {
		return cards;
	}

	public void shuffle() {
		Random ran = new Random();
		 for(int i = 0; i < cards.size(); i++){
			 int b = ran.nextInt(cards.size()+1);
			 Card a = cards.get(0);
			cards.add(b,a);
			cards.remove(0);
		 }
		nUsed = 0;
		for (int i = 0; i < usedCard.size(); i++) {
			usedCard.remove(i);
		}
		openCard = new ArrayList<Card>();
		for (int i = 0; i < openCard.size(); i++) {
			openCard.remove(i);
		}
	}

	public Card getOneCard(boolean isOpened) {
		Card onecard = null;
		if(usedCard.size() == cards.size())
			{shuffle();
		     getOneCard(isOpened);
		     }
		else
		{onecard = cards.get(nUsed);
		 nUsed++;
		 usedCard.add(onecard);
		 if(isOpened){
				openCard.add(onecard);
			}
		 }
		 return onecard;
		 
	}
	public ArrayList getOpenedCard(){
		return openCard;
	}
}
