import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class ShotgunShooter extends Shooter {
    public static final int BULLET_VELOCITY = 10;
    private int bulletMidVX;
    private int bulletMidVY;
    private int bulletLeftVX;
    private int bulletLeftVY;
    private int bulletRightVX;
    private int bulletRightVY;
    private int bulletPX;
    private int bulletPY;
    private int numberOfShots = 20;

    
    public ShotgunShooter(int vx, int vy, int px, int py, int courtWidth, int courtHeight, 
            Direction forward) {
        super(vx, vy, px, py, courtWidth, courtHeight, Color.ORANGE, forward);
    }
    
    public Set<Bullet> shoot() {
        if (this.getDirection() == Direction.UP) {
            this.bulletMidVX   =  0;
            this.bulletMidVY   = -BULLET_VELOCITY;
            this.bulletLeftVX  = -BULLET_VELOCITY / 4;
            this.bulletLeftVY  = -BULLET_VELOCITY;
            this.bulletRightVX =  BULLET_VELOCITY / 4;
            this.bulletRightVY = -BULLET_VELOCITY;
            this.bulletPX = this.getPx() + this.getWidth() / 2;
            this.bulletPY = this.getPy();
        } else if (this.getDirection() == Direction.DOWN) {
            this.bulletMidVX   =  0;
            this.bulletMidVY   =  BULLET_VELOCITY;
            this.bulletLeftVX  = -BULLET_VELOCITY / 4;
            this.bulletLeftVY  =  BULLET_VELOCITY;
            this.bulletRightVX =  BULLET_VELOCITY / 4;
            this.bulletRightVY =  BULLET_VELOCITY;
            this.bulletPX = this.getPx() + this.getWidth() / 2;
            this.bulletPY = this.getPy() + this.getHeight();
        } else if (this.getDirection() == Direction.LEFT) {
            this.bulletMidVX   = -BULLET_VELOCITY;
            this.bulletMidVY   =  0;
            this.bulletLeftVX  = -BULLET_VELOCITY;
            this.bulletLeftVY  =  BULLET_VELOCITY / 4;
            this.bulletRightVX = -BULLET_VELOCITY;
            this.bulletRightVY = -BULLET_VELOCITY / 4;
            this.bulletPX = this.getPx();
            this.bulletPY = this.getPy() + this.getHeight() / 2;
        } else {
            this.bulletMidVX   =  BULLET_VELOCITY;
            this.bulletMidVY   =  0;
            this.bulletLeftVX  =  BULLET_VELOCITY;
            this.bulletLeftVY  =  BULLET_VELOCITY / 4;
            this.bulletRightVX =  BULLET_VELOCITY;
            this.bulletRightVY = -BULLET_VELOCITY / 4;
            this.bulletPX = this.getPx() + this.getWidth();
            this.bulletPY = this.getPy() + this.getHeight() / 2;
        }
        
        Bullet bulletMid = new Bullet(bulletMidVX, bulletMidVY, bulletPX, bulletPY, 
                this.getCourtWidth(), this.getCourtHeight(), false);
        Bullet bulletLeft = new Bullet(bulletLeftVX, bulletLeftVY, bulletPX, bulletPY, 
                this.getCourtWidth(), this.getCourtHeight(), false);
        Bullet bulletRight = new Bullet(bulletRightVX, bulletRightVY, bulletPX, bulletPY, 
                this.getCourtWidth(), this.getCourtHeight(), false);
        
        Set<Bullet> returnSet = new HashSet<Bullet>();
        if (numberOfShots > 0) {
            returnSet.add(bulletMid);
            returnSet.add(bulletLeft);
            returnSet.add(bulletRight);
            
            numberOfShots -= 1;
        }
        return returnSet;
    }
    
    public int getNumShots() {
        return numberOfShots;
    }
    
    public ShooterType getType() {
        return ShooterType.SHOTGUN;
    }
    
}

