import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Point;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Window extends BasicGame {
    Algo p;
    Random r = new Random();
    Algo p2;
    Input inp;
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    Player pp;
    Player pp2;

    public Window(String title) {
        super(title);
    }

    private void initWalls() {
        walls.add(new Wall(new Point(0, 0), new Point(0, 1080)));
        walls.add(new Wall(new Point(0, 1080), new Point(1920, 1080)));
        walls.add(new Wall(new Point(1920, 1080), new Point(1920, 0)));
        walls.add(new Wall(new Point(1920, 0), new Point(0, 0)));
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        File file = new File("c:\\myclasses\\com\\mycompany\\");
        pp = new Player(1000, 800, walls);

        pp2 = new Player(100, 300, walls);
        try {
            // Convert File to a URL
            URL url = file.toURI().toURL();         // file:/c:/myclasses/
            URL[] urls = new URL[]{url};
            ClassLoader cl2 = Algo.class.getClassLoader();
            // Create a new class loader with the directory
            ClassLoader cl = new URLClassLoader(urls);

            // Load in the class; MyClass.class should be located in
            // the directory file:/c:/myclasses/com/mycompany
            Class<Algo> cls = (Class<Algo>) cl.loadClass("RandomPlayer");
            Constructor<Algo> ctor = cls.getDeclaredConstructor();
            p = ctor.newInstance();
            p2 = ctor.newInstance();
        } catch (MalformedURLException e) {
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        inp = gameContainer.getInput();
        initWalls();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        for (Wall wall : walls) {
            if (wall.checkPlayer(pp)) {
                pp.velocity = 0;
            }
            if (wall.checkPlayer(pp2)) {
                pp2.velocity = 0;
            }
        }
        p.update(pp);
        p.doSomething(pp);
        p2.update(pp2);
        p2.doSomething(pp2);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        p.draw(graphics, pp);
        p2.draw(graphics, pp2);
        for (Wall wall : walls) {
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
