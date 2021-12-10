import java.util.Scanner;

public class Main {

  public static void main(String args[]) {
    String userName;
    boolean playAgain = true;
    boolean userBust = false;
    PlayerImpl dealer = new PlayerImpl("dealer");
    Scanner scan = new Scanner(System.in);

    //This section establishes the user's player object
    System.out.println("Welcome to Blackjack, what is your name?");
    userName = scan.nextLine();
    System.out.println("You will start with $250!");

    PlayerImpl user = new PlayerImpl(userName, 250);

    System.out.println(
        "Good! Let's begin! Your balance is: "
            + user.getBalance()
            + ". Enter your bet (must be an increment of 10)");
    int betAmount = 0;
    //This loops repeats until the player wishes to stop playing or they run out of money
    while (playAgain) {
      Deck deck = new DeckImpl(); //creates a new deck after every round
      userBust = false; //Turns true when the user busts
      boolean betCorrect = false;

      //This loop will repeat until the user inputs a viable bet
      while (!betCorrect) {
        betAmount = scan.nextInt();
        if ((betAmount % 10 == 0) && (betAmount <= user.getBalance()) && (betAmount >= 0)) {
          betCorrect = true;
        } else if (betAmount > user.getBalance()) { //if the user doesn't have enough money for the bet
          System.out.println(
              "You don't have enough money for that bet, your balance is: "
                  + user.getBalance()
                  + ". Try Again");
        } else {
          System.out.println("bet is invalid, try again");
        }
      }
      initialCardDraw(dealer, user, deck);

      boolean stay = false;
      boolean correctInput = false;
      String ans = null;
      while (!stay) { //loops until the user inputs stay
        correctInput = false;
        while (!correctInput) { //loops until user inputs "stay" or "hit"
          if (user.getTotalValue() == 21) {//if user gets 21, automatically break from the loop
            stay = true;
            ans = "stay";
            break;
          } else if (user.getTotalValue() > 21) { //if the user busts, automatically break from the loop
            stay = true;
            ans = "stay";
            userBust = true;
            break;
          }
          System.out.println("Would you like to hit or stay? The dealer stays at 18");
          String fullAns = scan.next();
          ans = fullAns;
          if (ans.equals("hit") || ans.equals("stay")) { //if the input is valid, exit the !correctInput loop
            correctInput = true;
          } else {
            System.out.println("incorrect answer");
          }
        }

        if (ans.equals("hit")) {
          hit(user, deck);
        } else if (ans.equals("stay")) {
          stay = true;
        }
      }
      if (!userBust) { //activates the dealer's turn if the user hasn't already busted
        dealer.printCards();
        hit(dealer, deck);
      }
      winCondition(user, dealer, betAmount);
      if (user.getBalance() < 10) {//ends the game if the user's new balance is below 10
        System.out.println("Sorry " + user.getName() + " , you lost. Better luck next time!");
        playAgain = false;
      } else {
        boolean ansCor = false;
        while (!ansCor) { //loops until a valid input is put in
          System.out.println("Would you like to continue playing? (Enter 1 for no, 2 for yes)");
          String cont = scan.next();
          if (cont.equals("1")) {
            playAgain = false;
            ansCor = true;
          } else if (cont.equals("2")) { //if user wants to play again resets the users and dealers hands
            playAgain = true;
            ansCor = true;
            user.resetCards();
            dealer.resetCards();
            System.out.println("Your balance is: " + user.getBalance() + ". Enter your bet amount");
          } else {
            System.out.println("invalid answer");
          }
        }
      }
    }
  }

  //sets the user and dealer's first and second cards, will also print the dealer's first card and the user's cards
  public static void initialCardDraw(Player dealer, Player user, Deck deck) {
    user.setFirstCard(deck.drawCard());
    dealer.setFirstCard(deck.drawCard());
    user.setSecondCard(deck.drawCard());
    dealer.setSecondCard(deck.drawCard());
    user.printCards();
  }

  //Adds a card to the user hand if they hit, Adds to the dealer's hand until the bust or get at least 18
  public static void hit(Player player, Deck deck) {
    if (player.getName().equals("dealer")) {
      if ((player.getTotalValue() > 17) && (player.getTotalValue() < 22)) {
      } else if (player.getTotalValue() < 21) {
        player.setCard(deck.drawCard());
        hit(player, deck);
      }
    } else {
      player.setCard(deck.drawCard());
    }
  }

  //determines if the user got blackjack, busted, lost to the dealer, or won and updates the users balance in accordance to their bet
  public static void winCondition(Player user, Player dealer, int betAmount) {
    if (user.getTotalValue() > 21) {
      System.out.println(user.getName() + " busted, better luck next time!");
      user.changeBalance(-betAmount);
    } else if (dealer.getTotalValue() > 21) {
      System.out.println("Congrats " + user.getName() + ", you won!");
      user.changeBalance(betAmount);
      System.out.println(user.getName() + "'s new balance is: " + user.getBalance());
    } else if (user.getTotalValue() == 21 && dealer.getTotalValue() != 21) {
      System.out.println("Congrats " + user.getName() + ", you got Blackjack!");
      user.changeBalance(betAmount);
      System.out.println(user.getName() + "'s new balance is: " + user.getBalance());
    } else if (user.getTotalValue() > dealer.getTotalValue()) {
      System.out.println("Congrats " + user.getName() + " , you won!");
      user.changeBalance(betAmount);
      System.out.println(user.getName() + "'s new balance is: " + user.getBalance());
    } else if (user.getTotalValue() < dealer.getTotalValue()) {
      System.out.println("Sorry " + user.getName() + ", you lost :(");
      user.changeBalance(-betAmount);
      System.out.println(user.getName() + "'s new balance is: " + user.getBalance());
    } else if (user.getTotalValue() == dealer.getTotalValue()) {
      System.out.println("We have a tie! You keep your money");
      System.out.println(user.getName() + "'s balance is: " + user.getBalance());
    }
  }
}
