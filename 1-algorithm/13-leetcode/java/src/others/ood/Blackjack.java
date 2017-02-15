package others.ood;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CCI Deck of Cards and Blackjack game.
 */
public class Blackjack {

    public static void main(String[] args) {
        BlackjackHand player1 = new BlackjackHand();
        BlackjackHand player2 = new BlackjackHand();
        Deck<BlackjackCard> deck = new Deck<>();
    }
}

// OOD for Blackjack Game
class BlackjackHand extends Hand<BlackjackCard> {

    @Override
    public int score() {
        return 0; //...
    }

}

class BlackjackCard extends Card {

    public BlackjackCard(int value, Suit suit) {
        super(value, suit);
    }

    @Override
    public int getValue() {
        if (isAce()) return 1;
        if (11 <= value && value <= 13) return 10;
        else return value;
    }

    private boolean isAce() {
        return value == 1;
    }
}

// OOD for Deck of Cards
class Hand<T extends Card> {
    protected List<T> cards = new ArrayList<>();

    public int score() {
        int score = 0;
        for (T c : cards) {
            score += c.getValue();
        }
        return score;
    }

    public void addCard(T card) {
        cards.add(card);
    }
}

class Deck<T extends Card> {
    private List<T> cards = new ArrayList<>();
    private int dealtIdx;

    public Deck() {
        for (Suit s : Suit.values()) {
            for (int val = 1; val <= 13; val++) {
                //cards.add(new BlackjackCard(val, s)); // Note!
            }
        }
        shuffle();
    }

    public void shuffle() {
        dealtIdx = 0;
        Collections.shuffle(cards);
    }

    public int remaining() {
        return cards.size() - dealtIdx;
    }

    public T deal() {
        return cards.get(dealtIdx++);
    }
}

abstract class Card {
    protected int value;
    protected Suit suit;

    public Card(int value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }
}

enum Suit {
    /* 草花，方片，红桃，黑桃 */
    Club, Diamond, Heart, Spade
}
