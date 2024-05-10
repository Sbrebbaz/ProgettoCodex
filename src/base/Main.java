package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        List<Card> cards = new ArrayList<Card>();
        Corner[] c = new Corner[2];
        List<Corner> cl = new ArrayList<Corner>();
        cl.add(new Corner(Symbol.ANIMALS));
        cl.forEach(System.out::println);

        Side s = new GenericSide(Collections.singletonList(Symbol.ANIMALS), c);
        cards.add(new Card(Symbol.ANIMALS, s, s, CardType.STARTING) {
        });

        cards.forEach(System.out::println);

        List<Symbol> symbols = new ArrayList<>();
        List<PlaceRequirementSide> placeRequirements = new ArrayList<>();
        placeRequirements.add(new PlaceRequirementSide(symbols, c, symbols));

        placeRequirements.forEach(System.out::println);

        Map m = new Map();
        System.out.print(m.toString());
    }
}
