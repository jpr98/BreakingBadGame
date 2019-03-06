/*
 * BreakingBad class instantiates the game and starts it
 */
package breakingbad;

/**
 *
 * @author juanpabloramos
 */
public class BreakingBad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Game game = new Game("Breaking Bad", 700,550);
        game.start();
    }
    
}
