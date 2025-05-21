//315099184 Oran Shaki

import Game.DirectHit;
import Game.GameFlow;
import Game.Green3;
import Game.LevelInformation;
import Game.WideEasy;

import java.util.ArrayList;

/**
 * Represents the game itself.
 */
public class Ass6Game {
    /**
     * The activation of the game.
     *
     * @param args the given input
     */
    public static void main(String[] args) {
        ArrayList<LevelInformation> levels = new ArrayList<>();
        if (args.length == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
        } else {
            int i = 0;
            while (i != args.length) {
                if (args[i].equals("1")) {
                    levels.add(new DirectHit());
                } else if (args[i].equals("2")) {
                    levels.add(new WideEasy());
                } else if (args[i].equals("3")) {
                    levels.add(new Green3());
                }
                i++;
            }
        }
        GameFlow gf = new GameFlow();
        gf.runLevels(levels);
    }
}
