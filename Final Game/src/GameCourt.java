import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another.
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

    // the state of the game logic
    private Shooter user;
    private int numLives = 0;
    private int points = 0;

    public boolean playing = false; // whether the game is running 
    private JLabel status; // Current status text, i.e. "Running..."
    private JLabel numLivesLabel;
    private JLabel pointsLabel;

    // Game constants
    public static final int COURT_WIDTH = 800;
    public static final int COURT_HEIGHT = 800;
    public static final int SHOOTER_VELOCITY = 4;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;
    
    // Collection for storing bullets and asteroids
    private Set<Bullet> bullets = new HashSet<Bullet>();
    private Set<Asteroid> asteroids = new HashSet<Asteroid>();
    private Set<PowerUp> powerUps = new HashSet<PowerUp>();

    public GameCourt(JLabel status, JLabel numLivesLabel, JLabel pointsLabel) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // The timer is an object which triggers an action periodically with the given INTERVAL. We
        // register an ActionListener with this timer, whose actionPerformed() method is called each
        // time the timer triggers. We define a helper method called tick() that actually does
        // everything that should be done in a single timestep.
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        // This key listener allows the square to move as long as an arrow key is pressed, by
        // changing the square's velocity accordingly. (The tick method below actually moves the
        // square.)
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    user.setVx(-SHOOTER_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_D) {
                    user.setVx(SHOOTER_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    user.setVy(SHOOTER_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_W) {
                    user.setVy(-SHOOTER_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_Q) {
                    user.rotateCCW();
                } else if (e.getKeyCode() == KeyEvent.VK_E) {
                    user.rotateCW();
                } else if (e.getKeyCode() == KeyEvent.VK_F) {
                    user.useShield();
                }
            }

            public void keyReleased(KeyEvent e) {
                user.setVx(0);
                user.setVy(0);
            }
        });
        
        addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {
               Set<Bullet> shotSet = user.shoot();
               for (Bullet bullet : shotSet) {
                   bullets.add(bullet);
                   
               }
           }
        });

        this.status = status;
        this.numLivesLabel = numLivesLabel;
        this.pointsLabel = pointsLabel;
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        user = new BasicShooter(0, 0, COURT_WIDTH / 2, COURT_HEIGHT / 2, COURT_WIDTH, COURT_HEIGHT, 
                Direction.UP);
        
        numLives = 3;
        points = 0;
        playing = true;
        status.setText("Running...");
        numLivesLabel.setText("Lives: " + numLives);
        pointsLabel.setText("Points: " + points);
        
        bullets.removeAll(bullets);
        asteroids.removeAll(asteroids);
        powerUps.removeAll(powerUps);

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }
    
    public int getPoints() {
        return points;
    }
    
    public Set<Bullet> getBullets() {
        return bullets;
    }
    
    public Set<Asteroid> getAsteroids() {
        return asteroids;
    }
    
    public Set<PowerUp> getPowerUps() {
        return powerUps;
    }
    
    public void addAsteroids(Asteroid asteroid) {
        asteroids.add(asteroid);
    }
    
    public void addBullets(Bullet bullet) {
        bullets.add(bullet);
    }
    
    public void addPowerUps(PowerUp powerUp) {
        powerUps.add(powerUp);
    }
    
    public int getNumAsteroids() {
        return asteroids.size();
    }
    
    public int getNumBullets() {
        return bullets.size();
    }
    
    public int getNumPowerUps() {
        return powerUps.size();
    }
    
    public void addShooter(Shooter shooter) {
        user = shooter;
    }
    
    public Shooter getShooter() {
        return user;
    }
    
    public void setNumLives(int numLives) {
        this.numLives = numLives;
    }
    
    public int getNumLives() {
        return numLives;
    }
    
    public void updatePoints(AsteroidSize size) {
        if (size == AsteroidSize.LARGE) {
            points += 15;
        } else if (size == AsteroidSize.MEDIUM) {
            points += 10;
        } else {
            points += 5;
        }
    }
    
    //Generates random value 1, 0, or -1
    public static int getRandomValue() {
        int[] arr = { 1, 0, -1 };
        int random = (int) (Math.random() * arr.length);
        return arr[random];
    }
    
    public static AsteroidSize getRandomSize() {
        AsteroidSize[] arr = { AsteroidSize.LARGE, AsteroidSize.MEDIUM, AsteroidSize.SMALL };
        int random = (int) (Math.random() * arr.length);
        return arr[random];
    }
    
    public static Set<Asteroid> spawnAsteroids() {
        Set<Asteroid> retSet = new HashSet<Asteroid>();
        int sidePick = (int) (Math.random() * 4);
        int velocity = getRandomValue();
        Asteroid asteroid = null;
        if (sidePick == 0) {
            asteroid = new Asteroid(1, velocity, 1, (int) (COURT_HEIGHT * Math.random()), 
                    COURT_WIDTH, COURT_HEIGHT, getRandomSize());
        } else if (sidePick == 1) {
            asteroid = new Asteroid(velocity, 1, (int) (COURT_WIDTH * Math.random()), 1, 
                    COURT_WIDTH, COURT_HEIGHT, getRandomSize());
        } else if (sidePick == 2) {
            asteroid = new Asteroid(-1, velocity, COURT_WIDTH - 1, 
                    (int) (COURT_HEIGHT * Math.random()), COURT_WIDTH, COURT_HEIGHT, 
                    getRandomSize());
        } else {
            asteroid = new Asteroid(velocity, -1, (int) (COURT_WIDTH * Math.random()), 
                    COURT_HEIGHT - 1, COURT_WIDTH, COURT_HEIGHT, getRandomSize());
        }
        retSet.add(asteroid);
        return retSet;
    }
    
    public static Set<PowerUp> spawnPowerUps() {
        Set<PowerUp> retSet = new HashSet<PowerUp>();
        
        // Where it is coming from and what direction
        int sidePick = (int) (Math.random() * 4);
        int velocity = getRandomValue();
        
        // What type of power up it will be
        int typePick = (int) (Math.random() * 3);
        Color type;
        if (typePick == 0) {
            type = Color.RED;
        } else if (typePick == 1) {
            type = Color.ORANGE;
        } else {
            type = Color.PINK;
        }
        
        PowerUp powerUp = null;
        if (sidePick == 0) {
            powerUp = new PowerUp(1, velocity, 1, (int) (COURT_HEIGHT * Math.random()), 
                    COURT_WIDTH, COURT_HEIGHT, type);
        } else if (sidePick == 1) {
            powerUp = new PowerUp(velocity, 1, (int) (COURT_WIDTH * Math.random()), 1, 
                    COURT_WIDTH, COURT_HEIGHT, type);
        } else if (sidePick == 2) {
            powerUp = new PowerUp(-1, velocity, COURT_WIDTH - 1, 
                    (int) (COURT_HEIGHT * Math.random()), COURT_WIDTH, COURT_HEIGHT, type);
        } else {
            powerUp = new PowerUp(velocity, -1, (int) (COURT_WIDTH * Math.random()), 
                    COURT_HEIGHT - 1, COURT_WIDTH, COURT_HEIGHT, type);
        }
        retSet.add(powerUp);
        return retSet;
    }
        
    public void userPowerUpInteraction() {
        for (Iterator<PowerUp> itr = powerUps.iterator(); itr.hasNext();) {
            PowerUp powerUp = itr.next();
            if (user.intersects(powerUp)) {
                if (powerUp.getType() == PowerUpType.EXPLOSIVE) {
                    user = new ExplosiveShooter(user.getVx(), user.getVy(), user.getPx(), 
                            user.getPy(), COURT_WIDTH, COURT_HEIGHT, user.getDirection());
                    itr.remove();
                } else if (powerUp.getType() == PowerUpType.SHOTGUN) {
                    user = new ShotgunShooter(user.getVx(), user.getVy(), user.getPx(), 
                            user.getPy(), COURT_WIDTH, COURT_HEIGHT, user.getDirection());
                    itr.remove();
                } else {
                    user = new ShieldShooter(user.getVx(), user.getVy(), user.getPx(), 
                            user.getPy(), COURT_WIDTH, COURT_HEIGHT, user.getDirection());
                    itr.remove();
                }
            }
        }
    }
    
    public void userAsteroidInteraction() {
        Set<Asteroid> deletedAsteroidsPlayer = new HashSet<Asteroid>();
        // Checking for player asteroid interaction
        for (Iterator<Asteroid> aItr = asteroids.iterator(); aItr.hasNext();) {
            Asteroid asteroid = aItr.next();
            if (user.intersects(asteroid)) {
                if (user.getShield()) {
                    user.destroyShield();
                    deletedAsteroidsPlayer.add(asteroid);
                    continue;
                }
                deletedAsteroidsPlayer.add(asteroid);
                numLives -= 1;
                numLivesLabel.setText("Lives: " + numLives);
            }
        }
        asteroids.removeAll(deletedAsteroidsPlayer);
    }
    
    public void outOfBoundsAsteroids() {
        Set<Asteroid> deletedAsteroids = new HashSet<Asteroid>();
        for (Iterator<Asteroid> aItr = asteroids.iterator(); aItr.hasNext();) {
            Asteroid asteroid = aItr.next();
            if (asteroid.isOutOfBounds()) {
                deletedAsteroids.add(asteroid);
            }
        }
        
        asteroids.removeAll(deletedAsteroids);
    }
    
    public void outOfBoundsBullets() {
        Set<Bullet> deletedBullets = new HashSet<Bullet>();
        for (Iterator<Bullet> itr = bullets.iterator(); itr.hasNext();) {
            Bullet bullet = itr.next();
            if (bullet.isOutOfBounds()) {
                deletedBullets.add(bullet);
            }
        }
        
        bullets.removeAll(deletedBullets);
    }
    
    public void bulletAsteroidInteraction() {
        Set<Bullet> deletedBullets = new HashSet<Bullet>();
        Set<Asteroid> deletedAsteroids = new HashSet<Asteroid>();
        Set<Asteroid> addedAsteroids = new HashSet<Asteroid>();
        for (Iterator<Asteroid> aItr = asteroids.iterator(); aItr.hasNext();) {
            Asteroid asteroid = aItr.next();            
            for (Bullet bullet : bullets) {
                if (bullet.intersects(asteroid)) {
                    deletedBullets.add(bullet);
                    deletedAsteroids.add(asteroid);
                    if (!bullet.isExplosive()) {
                        Set<Asteroid> newAsteroids = asteroid.splitAsteroid();
                        for (Asteroid newAsteroid : newAsteroids) {
                            addedAsteroids.add(newAsteroid);
                        }
                    }
                    updatePoints(asteroid.getSize());
                    pointsLabel.setText("Points: " + points);
                }
            }
            
        }
        
        bullets.removeAll(deletedBullets);
        asteroids.removeAll(deletedAsteroids);
        asteroids.addAll(addedAsteroids);
    }
    
    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
        if (playing) {
            // advance the user
            user.move();
            
            // checking if the user has shots/shields
            if (user.getNumShots() <= 0) {
                user = new BasicShooter(user.getVx(), user.getVy(), user.getPx(), 
                        user.getPy(), COURT_WIDTH, COURT_HEIGHT, user.getDirection());
            }
            
            if (user.getNumShields() <= 0 && !user.getShield() && !user.isBasic()) {
                user = new BasicShooter(user.getVx(), user.getVy(), user.getPx(), 
                        user.getPy(), COURT_WIDTH, COURT_HEIGHT, user.getDirection());
            }
            
            
            // Adding new asteroids
            // one every ~1 second
            int chanceOfAsteroidSpawn = (int) (Math.random() * 30);
            if (chanceOfAsteroidSpawn == 1) {
                Set<Asteroid> spawningAsteroids = spawnAsteroids();
                for (Asteroid asteroid : spawningAsteroids) {
                    asteroids.add(asteroid);
                }
            }
            
            // Adding new power ups
            // one every ~20 seconds
            int chanceOfPowerUpSpawn = (int) (Math.random() * 600);
            if (chanceOfPowerUpSpawn == 1) {
                Set<PowerUp> spawningPowerUps = spawnPowerUps();
                for (PowerUp powerUp : spawningPowerUps) {
                    powerUps.add(powerUp);
                }
            }
            
            for (Bullet bullet : bullets) {
                bullet.move();
            }
            
            for (Asteroid asteroid : asteroids) {
                asteroid.move();
            }
            
            for (PowerUp powerUp : powerUps) {
                powerUp.move();
            }

            userAsteroidInteraction();
            
            outOfBoundsAsteroids();
            outOfBoundsBullets();
            bulletAsteroidInteraction();
            
            userPowerUpInteraction();
            
            // Game end condition
            if (numLives <= 0) {
                playing = false;
                status.setText("You Lose!");
            }
            
            // update the display
            repaint();
        }
    }
    
    public static boolean isNewHighscore(int score, String filename) {
        Map<Integer, String> highscores = readHighscores(filename);
        
        for (Entry<Integer, String> entry : highscores.entrySet()) { 
            if (score > entry.getKey()) {
                return true;
            }
        }
        
        return false;
    }
    public static Map<Integer, String> readHighscores(String filename) {
        Map<Integer, String> retMap = new TreeMap<Integer, String>(Collections.reverseOrder());

        try {
            BufferedReader bReader = new BufferedReader(new FileReader(filename));

            for (int i = 0; i < 6; i++) {
                int score = Integer.parseInt(bReader.readLine().trim());
                String name = bReader.readLine().trim();
                retMap.put(score, name);
            }

            bReader.close();
            return retMap;
        } catch (FileNotFoundException ex) {
            System.out.println(filename + " Not Found");
            ex.printStackTrace();
        } catch(IOException ex) {
            System.out.println("Error reading " + filename);
        }
        return retMap;
    }
    
    public static void writeHighscores(String userName, int userScore, String filename) {
        TreeMap<Integer, String> highscoreMap = new TreeMap<Integer, String>();
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(filename));
            
            for (int i = 0; i < 6; i++) {
                int score = Integer.parseInt(bReader.readLine().trim());
                String name = bReader.readLine().trim();
                highscoreMap.put(score, name);
            }
            
            highscoreMap.put(userScore, userName);
            
            if (highscoreMap.size() > 6) {
                int lowestScore = highscoreMap.firstKey();
                highscoreMap.remove(lowestScore);
            }
            
            TreeMap<Integer, String> reverseMap = 
                    new TreeMap<Integer, String>(Collections.reverseOrder());
            
            for (Entry<Integer, String> entry : highscoreMap.entrySet()) {
                reverseMap.put(entry.getKey(), entry.getValue());
            }
            
            bReader.close();
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(filename, false));

            Set<Entry<Integer, String>> set = reverseMap.entrySet();
            Iterator<Entry<Integer, String>> itr = set.iterator();
            
            while(itr.hasNext()) {
                Entry<Integer, String> entry = (Entry<Integer, String>) itr.next();
                bWriter.write(entry.getKey().toString());
                bWriter.newLine();
                bWriter.write(entry.getValue());
                bWriter.newLine();
            }

            bWriter.flush();
            bWriter.close();
        } catch (FileNotFoundException ex) {
            System.out.println(filename + " Not Found");
            ex.printStackTrace();
        } catch(IOException ex) {
            System.out.println("Error reading " + filename);
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        user.draw(g);
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        for (Asteroid asteroid : asteroids) {
            asteroid.draw(g);
        }
        for (PowerUp powerUp : powerUps) {
            powerUp.draw(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}
