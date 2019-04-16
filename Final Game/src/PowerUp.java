import java.awt.Color;
import java.awt.Graphics;

public class PowerUp extends GameObj{
    public static final boolean SHOULD_CLIP = false;
    public static final int SIZE = 20;

    private Color color;

    public PowerUp(int vx, int vy, int px, int py, int courtWidth, int courtHeight, Color color) {
                        
        super(vx, vy, px, py, SIZE, SIZE, courtWidth, courtHeight, SHOULD_CLIP);
        
        this.color = color;
    }
        
    public PowerUpType getType() {
        if (this.color == Color.ORANGE) {
            return PowerUpType.SHOTGUN;
        } else if (this.color == Color.RED) {
            return PowerUpType.EXPLOSIVE;
        } else {
            return PowerUpType.SHIELD;
        }
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}
