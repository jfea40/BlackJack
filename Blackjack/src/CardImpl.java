public class CardImpl implements Card{
    private final String suit;
    private final CardTypes card;

    public CardImpl (String suit, CardTypes card){
        this.suit = suit;
        this.card = card;
    }

    @Override
    public String getSuit() {
        return suit;
    }

    @Override
    public String getCard() {
        return card.toString();
    }

    @Override
    public int getCardValue() {
        switch (card) {
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            case ACE:
                    return -1;

        }
        return 0;
    }
}
