public class Card {
	private Suit suit;
	private int rank;

	public enum Suit {
		Clubs, Diamonds, Hearts, Spades
	}

	public Card(Suit s, int r) {
		suit = s;
		rank = r;
	}

	public void printCard() {
		if (rank == 1)
			System.out.println(suit + " ,Ace");
		else
			System.out.println(suit + "," + rank);
	}

	public Suit getSuit() {
		return suit;
	}

	public int getRank() {
		return rank;
	}
}
