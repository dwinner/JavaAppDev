// Класс карт
public abstract class Card {
	
	public static final String[] LABELS = new String[] {
		"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"
	};
	
	protected String suit;
	protected String label;
	
	public Card(int ind) {
		label = LABELS[ind % 13];
		suit = setSuit();
	}
	
	// Factory-метод
	public static Card getCard(int ind) {
		/* if (ind < 0 || ind >= 52)
			return null; */
		switch (ind / 13) {
			case 0:
				return new ClubsCard(ind);
			case 1:
				return new DiamondsCard(ind);
			case 2:
				return new HeartsCard(ind);
			case 3:
				return new SpadesCard(ind);
			default:
				return null;
		}
	}
	
	abstract public String setSuit();
	
	public String showCard() {
		return suit + label;
	}
	
}

// Трефы
class ClubsCard extends Card {
	
	ClubsCard(int ind) {
		super(ind);
	}
	
	public String setSuit() {
		return " C_";
	}
	
}

// Бубны
class DiamondsCard extends Card {
	
	DiamondsCard(int ind) {
		super(ind);
	}
	
	public String setSuit() {
		return " D_";
	}
	
}

// Черви
class HeartsCard extends Card {
	
	HeartsCard(int ind) {
		super(ind);
	}
	
	public String setSuit() {
		return " H_";
	}
	
}

// Пики
class SpadesCard extends Card {
	
	SpadesCard(int ind) {
		super(ind);
	}
	
	public String setSuit() {
		return " S_";
	}
	
}
