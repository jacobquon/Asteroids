import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

public class ShieldShooter extends Shooter {
    public static final int BULLET_VELOCITY = 10;
    private int bulletVX;
    private int bulletVY;
    private int bulletPX;
    private int bulletPY;
    private int numberOfShields = 1;
    private int numberOfShots = 100; //never goes down
    private boolean shieldOn = false;
    
    public ShieldShooter(int vx, int vy, int px, int py, int courtWidth, int courtHeight, 
            Direction forward) {
        super(vx, vy, px, py, courtWidth, courtHeight, Color.PINK, forward);
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
    
    public void useShield() {
        if (numberOfShields > 0 && !shieldOn) {
            this.shieldOn = true;
            numberOfShields -= 1;
        }
    }
    public boolean getShield() {
        return shieldOn;
    }
    
    public void destroyShield() {
        this.shieldOn = false;
    }
    
    public int getNumShots() {
        return numberOfShots;
    }
    
    public int getNumShields() {
        return numberOfShields;
    }
    
    public ShooterType getType() {
        return ShooterType.SHIELD;
    }
    
    @Override
    public void draw(Graphics g) {
        //drawing the shield
        if (this.shieldOn) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(this.getPx() - 8, getPy() - 8, this.getWidth() + 16, this.getHeight() + 16);
        }
        
        g.setColor(Color.PINK);
        g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
        
        //Drawing a line to indicate the direction of the shooter
        g.setColor(Color.BLACK);
        if (this.getDirection() == Direction.UP) {
            g.fillRect((int) (this.getPx() + this.getWidth() * .375), this.getPy(), 
                    this.getWidth() / 4, this.getWidth() / 2);
        } else if (this.getDirection() == Direction.DOWN) {
            g.fillRect((int) (this.getPx() + this.getWidth() * .375), 
                    this.getPy() + this.getWidth() / 2, this.getWidth() / 4, this.getWidth() / 2);
        } else if (this.getDirection() == Direction.LEFT) {
            g.fillRect(this.getPx(), (int) (this.getPy() + this.getWidth() * .375), 
                    this.getWidth() / 2, this.getWidth() / 4);
        } else {
            g.fillRect(this.getPx() + this.getWidth() / 2, 
                    (int) (this.getPy() + this.getWidth() * .375), this.getWidth() / 2, 
                    this.getWidth() / 4);
        }
        
    }
}
