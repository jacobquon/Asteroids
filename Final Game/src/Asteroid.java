import java.awt.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * A basic game object starting in the upper left corner of the game court. It is displayed as a
 * circle of a specified color.
 */
public class Asteroid extends GameObj {
    public static final boolean SHOULD_CLIP = false;
    private AsteroidSize size;

    private Color color = Color.GRAY;

    public Asteroid(int vx, int vy, int px, int py, int courtWidth, int courtHeight, 
            AsteroidSize size) {
                        
        super(vx, vy, px, py, asteroidWH(size), asteroidWH(size), courtWidth, courtHeight,
                SHOULD_CLIP);
        
        this.size = size;
    }

    public AsteroidSize getSize() {
        return size;
    }
    public static int asteroidWH(AsteroidSize size) {
        int asteroidWH;
        if (size == AsteroidSize.LARGE) {
            asteroidWH = 40;
        } else if (size == AsteroidSize.MEDIUM) {
            asteroidWH = 30;
        } else {
            asteroidWH = 15;
        }
        return asteroidWH;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
    
    public Set<Asteroid> splitAsteroid() {
        Set<Asteroid> retSet = new HashSet<Asteroid>();
        
        int randomVal1 = getRandomValue();
        int randomVal2;
        if (randomVal1 == 0) {
            randomVal2 = getOneOrNegOne();
        } else {
            randomVal2 = getRandomValue();
        }
        
        // if large make 2 medium
        if (this.size == AsteroidSize.LARGE) {
            Asteroid asteroid1 = new Asteroid(randomVal1, randomVal2, this.getPx(), 
                    this.getPy(), this.getCourtWidth(), this.getCourtHeight(), AsteroidSize.MEDIUM);
            Asteroid asteroid2 = new Asteroid(-randomVal1, -randomVal2, this.getPx(), 
                    this.getPy(), this.getCourtWidth(), this.getCourtHeight(), AsteroidSize.MEDIUM);
            retSet.add(asteroid1);
            retSet.add(asteroid2);
        // if medium make 2 small
        } else if (this.size == AsteroidSize.MEDIUM) {
            Asteroid asteroid1 = new Asteroid(randomVal1, randomVal2, this.getPx(), 
                    this.getPy(), this.getCourtWidth(), this.getCourtHeight(), AsteroidSize.SMALL);
            Asteroid asteroid2 = new Asteroid(-randomVal1, -randomVal2, this.getPx(), 
                    this.getPy(), this.getCourtWidth(), this.getCourtHeight(), AsteroidSize.SMALL);
            retSet.add(asteroid1);
            retSet.add(asteroid2);
        } 
        // if small return empty
        return retSet;
    }
    
    //Generates random value 1, 0, or -1
    public static int getRandomValue() {
        int[] arr = { 1, 0, -1 };
        int random = (int) (Math.random() * arr.length);
        return arr[random];
    }
    
    //Generates random value 1 or -1
    public static int getOneOrNegOne() {
        Random random = new Random();
        Boolean randomBool = random.nextBoolean();
        if (randomBool) {
            return -1;
        }
        return 1;
    }
}