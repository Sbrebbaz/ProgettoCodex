package base;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        System.out.print("TEST");
        List<Card> cards = new ArrayList<>();
        List<Deck> deck = new ArrayList<>(19);
        Deck dk = new Deck(cards);
        Deck dk2 = new Deck(cards);
        deck.add(dk);
        deck.add(dk2);
        List<Symbol> d = new ArrayList<>();
        Corner[] c = new Corner[1];

        Side s = new GenericSide(d,c);
        Card cr = new Card(Symbol.ANIMALS,s,s,CardType.OBJECTIVE);
        cards.add(cr);
        dk.addCard(cr);
        deck.forEach(System.out::println);
        cards.forEach(System.out::println);

        Player p = new Player(1,"Luca",Color.BLUE);
        p.setHand(cards);
        System.out.println(p.ToStringPlayerHand());

        Card cr2 = new Card(Symbol.ANIMALS,s,s,CardType.STARTING);
        Map m = new Map();

        m.placeStartingCard(cr2);
        
        System.out.println(m);
    }
}
