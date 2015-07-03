
public class DeckTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        //If you must increment i to add to player deck.  
        int i = 0;
        while (deck.size()>0){
            playerOneDeck.add(0, null);
            playerTwoDeck.add(0, null);
            playerOneDeck.set(i, deck.get(0));
            deck.remove(0);        
            playerTwoDeck.set(i, deck.get(0));
            deck.remove(0);
            System.out.printf("New card #%s: %s\n",i ,playerOneDeck.get(i)._face);
            i++;   
        }

        
        
    }

}
