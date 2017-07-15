import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Images {
    public static Image img;

    static {
        try {
            img = new Image("res/kek.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}