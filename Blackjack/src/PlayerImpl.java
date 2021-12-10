import java.util.ArrayList;

public class PlayerImpl implements Player {
  private final String name;
  private int balance;
  private Card firstCard;
  private Card secondCard;
  private ArrayList<Card> playerHand = new ArrayList<>();

  public PlayerImpl(String name, int balance) {
    if (balance > 250 || balance <= 0) {
      throw new IllegalArgumentException("balance is too large/small");
    }
    this.name = name;
    this.balance = balance;
  }

  //Intended for the dealer's player object
  public PlayerImpl(String name) {
    this.name = name;
    balance = 1000000;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Card getFirstCard() {
    return firstCard;
  }

  @Override
  public Card getSecondCard() {
    return secondCard;
  }

  @Override
  public int getTotalValue() {
    int total = 0;
    int aceCount = 0;
    for (int n = 0; n < playerHand.size(); n++) {
      if (playerHand.get(n).getCardValue() == -1) {
        aceCount++;
      } else {
        total += playerHand.get(n).getCardValue();
      }
    }
    total += aceValue(aceCount, total);
    return total;
  }

  @Override
  public void setFirstCard(Card firstCard) {
    playerHand.add(firstCard);
    if (name.equals("dealer")) {
      System.out.println(
          "Dealer's first card: " + firstCard.getCard() + " of " + firstCard.getSuit());
    }
  }

  @Override
  public void setSecondCard(Card secondCard) {
    playerHand.add(secondCard);
  }

  @Override
  public void setCard(Card nextCard) {
    playerHand.add(nextCard);
    System.out.println(name + "'s new card: " + nextCard.getCard() + " of " + nextCard.getSuit());
    System.out.println(name + "'s total value: " + getTotalValue());
  }

  @Override
  public int getBalance() {
    return balance;
  }

  @Override
  public void changeBalance(int bet) {
    balance += bet;
  }

  @Override
  public void printCards() {
    for (int n = 0; n < playerHand.size(); n++) {
      Card currentCard = playerHand.get(n);
      System.out.println(
          name + "'s cards: " + currentCard.getCard() + " of " + currentCard.getSuit());
    }
    System.out.println(name + "'s total value: " + getTotalValue());
  }

  @Override
  public int aceValue(int aceNumber, int total) {
    switch (aceNumber) {
      case 0:
        return 0;
      case 1:
        if (total + 11 > 21) {
          return 1;
        } else {
          return 11;
        }
      case 2:
        if (total + 12 > 21) {
          return 2;
        } else {
          return 12;
        }
      case 3:
        if (total + 13 > 21) {
          return 3;
        } else {
          return 13;
        }
      case 4:
        if (total + 14 > 21) {
          return 4;
        } else {
          return 14;
        }
    }
    return -1;
  }

  @Override
  public void resetCards() {
    playerHand.clear();
  }
}
