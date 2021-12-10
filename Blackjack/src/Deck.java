public interface Deck {

    //removes a card from the deck
    public void removeCard(int index);

    //calls a random card from the deck and then calls removeCard()
    public Card drawCard();
}
