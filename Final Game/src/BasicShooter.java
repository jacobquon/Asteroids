import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class BasicShooter extends Shooter {
    public static final int BULLET_VELOCITY = 10;
    private int bulletVX;
    private int bulletVY;
    private int bulletPX;
    private int bulletPY;
    private int numberOfShots = 100; //arbitrary value, never decreases
    
    public BasicShooter(int vx, int vy, int px, int py, int courtWidth, int courtHeight, 
            Direction forward) {
        super(vx, vy, px, py, courtWidth, courtHeight, Color.BLUE, forward);
    }
    
    public Set<Bullet> shoot() {
        if (this.getDirection() == Direction.UP) {
            this.bulletVX = 0;
            this.bulletVY = -BULLET_VELOCITY;
            this.bulletPX = this.getPx() + this.getWidth() / 2;
            this.bulletPY = this.getPy();
        } else if (this.getDirection() == Direction.DOWN) {
            this.bulletVX = 0;
            this.bulletVY = BULLET_VELOCITY;
            this.bulletPX = this.getPx() + this.getWidth() / 2;
            this.bulletPY = this.getPy() + this.getHeight();
        } else if (this.getDirection() == Direction.LEFT) {
            this.bulletVX = -BULLET_VELOCITY;
            this.bulletVY = 0;
            this.bulletPX = this.getPx();
            this.bulletPY = this.getPy() + this.getHeight() / 2;
        } else {
            this.bulletVX = BULLET_VELOCITY;
            this.bulletVY = 0;
            this.bulletPX = this.getPx() + this.getWidth();
            this.bulletPY = this.getPy() + this.getHeight() / 2;
        }
        
        Bullet bullet = new Bullet(bulletVX, bulletVY, bulletPX, bulletPY, 
                this.getCourtWidth(), this.getCourtHeight(), false);
        
        Set<Bullet> returnSet = new HashSet<Bullet>();
        returnSet.add(bullet);
        
        return returnSet;
    }
    
    public int getNumShots() {
        return numberOfShots;
    }
    
    public boolean isBasic() {
        return true;
    }
    
    public ShooterType getType() {
        return ShooterType.BASIC;
    }
    
}
