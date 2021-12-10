public interface Player {

    //returns the player's name
    public String getName();

    //returns the player's first card
    public Card getFirstCard();

    //returns the player's second card
    public Card getSecondCard();

    //returns the value the cards add up to
    public int getTotalValue();

    //sets player's first card
    public void setFirstCard(Card firstCard);

    //sets player's second card
    public void setSecondCard(Card secondCard);

    //sets cards after the first and second
    public void setCard(Card secondCard);

    //returns the player's balance
    public int getBalance();

    //changes the player's balance based on bet amount
    public void changeBalance(int bet);

    //prints the player's cards
    public void printCards();

    //determines if an ace will be an 1 or 11 based on the player's current total
    public int aceValue(int aceNumber, int total);

    //resets the player's hand
    public void resetCards();
}
