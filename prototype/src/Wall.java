
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;


import static java.lang.Math.max;
import static java.lang.Math.min;

public class Wall {
    Point p1, p2;

    public Wall(Point np1, Point np2) {
        p1 = np1;
        p2 = np2;
    }

    public boolean checkPlayer(Player player) {
        float width = player.getSize().getX()/2;
        float height = player.getSize().getY()/2;
        Vector2f dir = player.getVec().scale(height);
        Vector2f perDir = player.getVec().getPerpendicular().scale(width);
        Point cor = player.getCor();
        Point back1 = new Point(cor.getX() - dir.getX() - perDir.getX(),
                cor.getY() - dir.getY() - perDir.getY());
        Point back2 = new Point(cor.getX() - dir.getX() + perDir.getX(),
                cor.getY() - dir.getY() + perDir.getY());
        Point forward1 = new Point(cor.getX() + dir.getX() - perDir.getX(),
                cor.getY() + dir.getY() - perDir.getY());
        Point forward2 = new Point(cor.getX() + dir.getX() + perDir.getX(),
                cor.getY() + dir.getY() + perDir.getY());
        return intersection(back1, back2, p1, p2) || intersection(back1, forward1, p1, p2) ||
                intersection(forward1, forward2, p1, p2) || intersection(back2, forward2, p1, p2);
    }

    private double area(Point a, Point b, Point c) {
        return (b.getX() - a.getX()) * (c.getY() - a.getY()) - (b.getY() - a.getY()) * (c.getX() - a.getX());
    }

    private boolean intersect_1(double a, double b, double c, double d) {
        double tmp;
        if (a > b) {
            tmp = a;
            a = b;
            b = tmp;
        }
        if (c > d) {
            tmp = c;
            c = d;
            d = tmp;
        }
        return max(a, c) <= min(b, d);
    }

    private boolean intersection(Point a, Point b, Point c, Point d) {
        return intersect_1(a.getX(), b.getX(), c.getX(), d.getX())
                && intersect_1(a.getY(), b.getY(), c.getY(), d.getY())
                && area(a, b, c) * area(a, b, d) <= 0
                && area(c, d, a) * area(c, d, b) <= 0;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setLineWidth(6);
        g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

}
