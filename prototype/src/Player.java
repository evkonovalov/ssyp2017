import org.lwjgl.util.vector.Matrix2f;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;

public class Player {
    private double angle = 90;
    private double angNow = 0;
    private float cos = (float) Math.cos(Math.toRadians(angle));
    private float sin = (float) Math.sin(Math.toRadians(angle));
    private Point size = new Point(20, 50);
    private Point cor;
    private Vector2f vec;
    double velocity;
    private ArrayList<Wall> walls;
    private Wall curWall = null;
    public boolean lock = false;


    public Player(int x, int y, ArrayList<Wall> nWalls) throws SlickException {
        cor = new Point(x, y);
        vec = new Vector2f(1, 0);
        velocity = 0;
        walls = nWalls;
    }

    public void update() {
        Vector2f vecNow = getVec().scale((float) velocity);
        cor.setX(cor.getX() + vecNow.x);
        cor.setY(cor.getY() + vecNow.y);
        float width = size.getX()/2;
        float height = size.getY()/2 + 1;
        Vector2f dir = getVec().scale(height);
        Point back = new Point(cor.getX() - dir.getX(),
                cor.getY() - dir.getY());
        if (velocity != 0 && curWall == null) {
            curWall = new Wall(back, back);
            walls.add(curWall);
        } else if (curWall != null) {
            curWall.p2 = back;
        }
    }

    public void axelerate(boolean straight) {
        if (velocity < -0.1) {
            velocity = -0.1;
        } else if (velocity > 0.2) {
            velocity = 0.2;
        }
        if (straight) {
            velocity += 0.0001;
        } else {
            velocity -= 0.0001;
        }
    }

    public void rotate(boolean cloakwork) {
        float width = size.getX();
        float height = size.getY()/2 + 1;
        Vector2f nvec = new Vector2f(0,0);
        if (cloakwork) {
            nvec.x = vec.x * cos - vec.y * sin;
            nvec.y = vec.x * sin + vec.y * cos;
            vec = nvec.normalise();
            Vector2f dir = getVec().scale(height);
            Vector2f perDir = getVec().getPerpendicular().scale(width);
            Point forward1 = new Point(cor.getX() + dir.getX() + perDir.getX(),
                    cor.getY() + dir.getY() + perDir.getY());
            cor = forward1;
            angNow += angle;
        } else {
            nvec.x = vec.x * cos + vec.y * sin;
            nvec.y = -vec.x * sin + vec.y * cos;
            vec = nvec.normalise();
            Vector2f dir = getVec().scale(height);
            Vector2f perDir = getVec().getPerpendicular().scale(width);
            Point forward1 = new Point(cor.getX() + dir.getX() - perDir.getX(),
                    cor.getY() + dir.getY() - perDir.getY());
            cor = forward1;
            angNow -= angle;
        }
        Vector2f dir = getVec().scale(height);
        Point back = new Point(cor.getX() - dir.getX(),
                cor.getY() - dir.getY());
        if(curWall != null){
            curWall.p2 = back;
        }
        curWall = null;
    }

    public void draw(Graphics g) {
        Image img = Images.img;
        img.rotate((float) angNow);
        g.drawImage(img, cor.getX() - size.getY()/2, cor.getY() - size.getX()/2);
        img.rotate(-(float) angNow);
    }

    public Point getCor() {
        return new Point(cor.getX(), cor.getY());
    }

    public Point getSize() {
        return new Point(size.getX(), size.getY());
    }

    public Vector2f getVec() {
        return new Vector2f(vec.getX(), vec.getY());
    }
}
