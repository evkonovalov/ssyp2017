import org.newdawn.slick.SlickException;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by DNS on 15.07.2017.
 */
public class RandomPlayer extends Algo {
    long last = System.currentTimeMillis();
    Random r = new Random();
    public RandomPlayer(Player np) throws SlickException {
        super(np);
    }

    @Override
    public void doSomething() {
        player.axelerate(true);
        if(System.currentTimeMillis() - last > 500){
            last = System.currentTimeMillis();
            int a = r.nextInt();
            if(a % 3 == 0){
                player.rotate(true);
            } else  if(a % 3 == 1) {
                player.rotate(false);
            }
        }
    }
}
