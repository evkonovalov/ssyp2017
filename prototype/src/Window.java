import org.newdawn.slick.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by DNS on 09.07.2017.
 */
public class Window extends BasicGame {
    public Window(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

    }


    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Window("Simple Slick Game"));
            appgc.setDisplayMode(640, 480, false);
            appgc.setShowFPS(false);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
