import java.awt.Color;
import java.awt.Graphics;

// Extension of game object controls the bullets the user shoots
public class Bullet extends GameObj {
    public static final int SIZE = 5;
    public static final boolean SHOULD_CLIP = false;
    private boolean isExplosive;
    
    public Bullet(int vx, int vy, int px, int py, int courtWidth, int courtHeight, 
            boolean isExplosive) {
        super(vx, vy, px, py, SIZE, SIZE, courtWidth, courtHeight, SHOULD_CLIP);
        this.isExplosive = isExplosive;
    }
        
    @Override
    public void draw(Graphics g) {
        if (this.isExplosive) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLACK);
        }
        g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
    
    public boolean isExplosive() {
        return this.isExplosive;
    }
}
