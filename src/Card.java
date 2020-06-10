import java.sql.SQLOutput;
import java.util.Random;

public class Card {
    private int cards[] = new int[13];
    private int numOfCards[] = new int[13];

    public Card () {
        for(int i = 0; i < 13; i++){
            cards[i] = i + 1;
            numOfCards[i] = 4;
        }
    }

    public int selectCard () {
        Random r = new Random();
        int card = cards[r.nextInt(13)];
        if(numOfCards[card - 1] == 0) this.selectCard();
        numOfCards[card - 1] -= 1;
        return card;
    }


}
