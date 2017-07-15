import org.lwjgl.Sys;
import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Window extends BasicGame {
    Player p;
    Input inp;
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    public Window(String title)  {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        p = new Player(100,100,walls);
        inp = gameContainer.getInput();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        for (Wall wall:walls) {
            if(wall.checkPlayer(p)){
                p.velocity = 0;
            }
        }
        p.update();
        if(inp.isKeyDown(Input.KEY_W)){
            p.axelerate(true);
        }
        if(inp.isKeyDown(Input.KEY_S)){
            p.axelerate(false);
        }
        if(inp.isKeyPressed(Input.KEY_D)){
                p.rotate(true);
        }
        if(inp.isKeyPressed(Input.KEY_A)){
                p.rotate(false);
        }

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        p.draw(graphics);
        for (Wall wall:walls) {
            wall.draw(graphics);
        }
    }


    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Window("Simple Slick Game"));
            appgc.setDisplayMode(1920, 1080, true);
            appgc.setShowFPS(false);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
