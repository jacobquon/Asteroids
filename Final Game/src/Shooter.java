import java.awt.*;
import java.util.Set;

/**
 * An abstract class that is the basis of the user in the game.
 */

public abstract class Shooter extends GameObj {
    public static final int SIZE = 20;
    public static final boolean SHOULD_CLIP = true;
    private Direction forward;

    private Color color;

    // must be able to input initial vx, vy, px, and py for when the shooter type changes
    public Shooter(int vx, int vy, int px, int py, int courtWidth, int courtHeight, Color color, 
            Direction forward) {
        super(vx, vy, px, py, SIZE, SIZE, courtWidth, courtHeight, SHOULD_CLIP);

        this.forward = forward;
        this.color = color;
    }
    
    public Direction getDirection() {
        return this.forward;
    }

    public void rotateCW() {
        if (forward == Direction.UP) {
            this.forward = Direction.RIGHT;
        } else if (forward== Direction.DOWN) {
            this.forward = Direction.LEFT;
        } else if (forward== Direction.LEFT) {
            this.forward = Direction.UP;
        } else {
            this.forward = Direction.DOWN;
        }
    }
    
    public void rotateCCW() {
        if (forward == Direction.UP) {
            this.forward = Direction.LEFT;
        } else if (forward== Direction.DOWN) {
            this.forward = Direction.RIGHT;
        } else if (forward== Direction.LEFT) {
            this.forward = Direction.DOWN;
        } else {
            this.forward = Direction.UP;
        }
    }
    
    public abstract Set<Bullet> shoot();
    
    public abstract int getNumShots();
        
    public void useShield() {}
    
    public boolean getShield() {
        return false;
    }
    
    public int getNumShields() {
        return 1;
    }
    
    public void destroyShield() {};
    
    // is it a basic shooter?
    public boolean isBasic() {
        return false;
    }
    
    public abstract ShooterType getType();
    
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
        
        //Drawing a line to indicate the direction of the shooter
        g.setColor(Color.BLACK);
        if (forward == Direction.UP) {
            g.fillRect((int) (this.getPx() + this.getWidth() * .375), this.getPy(), 
                    this.getWidth() / 4, this.getWidth() / 2);
        } else if (forward == Direction.DOWN) {
            g.fillRect((int) (this.getPx() + this.getWidth() * .375), 
                    this.getPy() + this.getWidth() / 2, this.getWidth() / 4, this.getWidth() / 2);
        } else if (forward == Direction.LEFT) {
            g.fillRect(this.getPx(), (int) (this.getPy() + this.getWidth() * .375), 
                    this.getWidth() / 2, this.getWidth() / 4);
        } else {
            g.fillRect(this.getPx() + this.getWidth() / 2, 
                    (int) (this.getPy() + this.getWidth() * .375), this.getWidth() / 2, 
                    this.getWidth() / 4);
        }
    }
}
