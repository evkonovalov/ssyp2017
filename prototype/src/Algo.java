import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Created by DNS on 15.07.2017.
 */
public abstract class Algo {
    public abstract void doSomething(Player player);
    public void draw(Graphics graphics, Player player){
        player.draw(graphics);
    }
    public void update(Player player) {
        player.update();
    }
}
