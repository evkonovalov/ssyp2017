import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Created by DNS on 15.07.2017.
 */
public abstract class Algo {
    Player player;
    public Algo(Player np) throws SlickException {
        player = np;
    }
    public abstract void doSomething();

    public void draw(Graphics graphics){
        player.draw(graphics);
    }
    public void update(){
        player.update();
    }
}
