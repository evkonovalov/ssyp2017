import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Point;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Window extends BasicGame {
    RandomPlayer p;
    Random r = new Random();
    RandomPlayer p2;
    Input inp;
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    Player pp;
    Player pp2;
    public Window(String title)  {
        super(title);
    }

    private void initWalls(){
        walls.add(new Wall(new Point(0,0), new Point (0,1080)));
        walls.add(new Wall(new Point(0,1080), new Point (1920,1080)));
        walls.add(new Wall(new Point(1920,1080), new Point (1920,0)));
        walls.add(new Wall(new Point(1920,0), new Point (0,0)));
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        pp = new Player(1000,800,walls);
        p = new RandomPlayer(pp);
        pp2 = new Player(100,300,walls);
        p2 = new RandomPlayer(pp2);
        inp = gameContainer.getInput();
        initWalls();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        for (Wall wall:walls) {
            if(wall.checkPlayer(p.player)){
                pp = new Player(1000 + r.nextInt() % 400 - 200,800 + r.nextInt() % 400 - 200,walls);
                p = new RandomPlayer(pp);
            }
            if(wall.checkPlayer(p2.player)){
                pp2 = new Player(100 + r.nextInt() % 400 - 200 ,300 + r.nextInt() % 400 - 200,walls);
                p2 = new RandomPlayer(pp2);
            }
        }
        p.update();
        p.doSomething();
        p2.update();
        p2.doSomething();
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        p.draw(graphics);
        p2.draw(graphics);
        for (Wall wall:walls) {
            wall.draw(graphics);
        }
    }


    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Window("Simple Slick Game"));
            appgc.setDisplayMode(1920, 1080, true);
            appgc.setShowFPS(true);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
