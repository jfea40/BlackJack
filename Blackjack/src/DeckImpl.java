import java.util.ArrayList;
import java.util.Random;

public class DeckImpl implements Deck {

  private ArrayList<Card> deck;
  private int cardCount;
  private String[] cardType = {"Hearts", "Diamonds", "Spades", "Clubs"};
  private CardTypes[] cardNumber = {
    CardTypes.ACE,
    CardTypes.TWO,
    CardTypes.THREE,
    CardTypes.FOUR,
    CardTypes.FIVE,
    CardTypes.SIX,
    CardTypes.SEVEN,
    CardTypes.EIGHT,
    CardTypes.NINE,
    CardTypes.TEN,
    CardTypes.JACK,
    CardTypes.QUEEN,
    CardTypes.KING
  };

  public DeckImpl() {
    cardCount = 52;
    deck = new ArrayList<>();
    for (int n = 0; n < 4; n++) {
      for (int c=0; c<13; c++){
        Card newCard = new CardImpl(cardType[n], cardNumber[c]);
        deck.add(newCard);
      }
    }
  }

  @Override
  public void removeCard(int index) {
    deck.remove(index);
    cardCount--;
  }


  @Override
  public Card drawCard() {
    Random rand = new Random();
    int r = rand.nextInt(cardCount);
    Card returnCard = deck.get(r);
    removeCard(r);
    return returnCard;
  }
}
