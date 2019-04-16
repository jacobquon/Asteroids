import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import javax.swing.JLabel;
import org.junit.Test;

public class GameTest {

    // Asteroids out of bounds
    @Test
    public void testLargeAsteroidOBLeft() {
        Asteroid asteroid = new Asteroid(-1, 0, 0, 0, GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, 
                AsteroidSize.LARGE);
        asteroid.move();
        assertTrue("Large asteroid OB Left", asteroid.isOutOfBounds());
    }
    
    @Test
    public void testLargeAsteroidOBTop() {
        Asteroid asteroid = new Asteroid(0, -1, 0, 0, GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, 
                AsteroidSize.LARGE);
        asteroid.move();
        assertTrue("Large asteroid OB Top", asteroid.isOutOfBounds());
    }
    
    @Test
    public void testLargeAsteroidOBRight() {
        Asteroid asteroid = new Asteroid(1, 0, GameCourt.COURT_WIDTH, 0, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.LARGE);
        asteroid.move();
        assertTrue("Large asteroid OB Right", asteroid.isOutOfBounds());
    }
    
    @Test
    public void testLargeAsteroidOBBottom() {
        Asteroid asteroid = new Asteroid(0, 1, 0, GameCourt.COURT_HEIGHT, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.LARGE);
        asteroid.move();
        assertTrue("Large asteroid OB Bottom", asteroid.isOutOfBounds());
    }
    
    @Test
    public void testMediumAsteroidOBLeft() {
        Asteroid asteroid = new Asteroid(-1, 0, 0, 0, GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, 
                AsteroidSize.MEDIUM);
        asteroid.move();
        assertTrue("Medium asteroid OB Left", asteroid.isOutOfBounds());
    }
    
    @Test
    public void testMediumAsteroidOBTop() {
        Asteroid asteroid = new Asteroid(0, -1, 0, 0, GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, 
                AsteroidSize.MEDIUM);
        asteroid.move();
        assertTrue("Medium asteroid OB Top", asteroid.isOutOfBounds());
    }
    
    @Test
    public void testMediumAsteroidOBRight() {
        Asteroid asteroid = new Asteroid(1, 0, GameCourt.COURT_WIDTH, 0, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.MEDIUM);
        asteroid.move();
        assertTrue("Medium asteroid OB Right", asteroid.isOutOfBounds());
    }
    
    @Test
    public void testMediumAsteroidOBBottom() {
        Asteroid asteroid = new Asteroid(0, 1, 0, GameCourt.COURT_HEIGHT, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.MEDIUM);
        asteroid.move();
        assertTrue("Medium asteroid OB Bottom", asteroid.isOutOfBounds());
    }
    
    @Test
    public void testSmallAsteroidOBLeft() {
        Asteroid asteroid = new Asteroid(-1, 0, 0, 0, GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, 
                AsteroidSize.SMALL);
        asteroid.move();
        assertTrue("Small asteroid OB Left", asteroid.isOutOfBounds());
    }
    
    @Test
    public void testSmallAsteroidOBTop() {
        Asteroid asteroid = new Asteroid(0, -1, 0, 0, GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, 
                AsteroidSize.SMALL);
        asteroid.move();
        assertTrue("Small asteroid OB Top", asteroid.isOutOfBounds());
    }
    
    @Test
    public void testSmallAsteroidOBRight() {
        Asteroid asteroid = new Asteroid(1, 0, GameCourt.COURT_WIDTH, 0, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.SMALL);
        asteroid.move();
        assertTrue("Small asteroid OB Right", asteroid.isOutOfBounds());
    }
    
    @Test
    public void testSmallAsteroidOBBottom() {
        Asteroid asteroid = new Asteroid(0, 1, 0, GameCourt.COURT_HEIGHT, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.SMALL);
        asteroid.move();
        assertTrue("Small asteroid OB Bottom", asteroid.isOutOfBounds());
    }
    
    //Asteroid player collision
    @Test
    public void testAsteroidBasicShooterCollision() {
        Asteroid asteroid = new Asteroid(0, 1, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.LARGE);
        Shooter user = new BasicShooter(0, 0, 50, 51, GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, 
                Direction.UP);
        asteroid.move();
        assertTrue("Asteroid intersect Basic", asteroid.intersects(user));
    }
    
    @Test
    public void testAsteroidShotgunShooterCollision() {
        Asteroid asteroid = new Asteroid(0, 1, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.LARGE);
        Shooter user = new ShotgunShooter(0, 0, 50, 51, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, Direction.UP);
        asteroid.move();
        assertTrue("Asteroid intersect Shotgun", asteroid.intersects(user));
    }
    
    @Test
    public void testAsteroidExplosiveShooterCollision() {
        Asteroid asteroid = new Asteroid(0, 1, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.LARGE);
        Shooter user = new ExplosiveShooter(0, 0, 50, 51, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, Direction.UP);
        asteroid.move();
        assertTrue("Asteroid intersect Explosive", asteroid.intersects(user));
    }
    
    @Test
    public void testAsteroidShieldShooterCollision() {
        Asteroid asteroid = new Asteroid(0, 1, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.LARGE);
        Shooter user = new ShieldShooter(0, 0, 50, 51, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, Direction.UP);
        asteroid.move();
        assertTrue("Asteroid intersect Shield", asteroid.intersects(user));
    }
    
    //Highscores
    @Test
    public void testIsNewHighscoreFalse() {
        String filename = "files/highscoresTest.txt";
        assertFalse("Not a new Highscore", GameCourt.isNewHighscore(100, filename));
    }
    
    @Test
    public void testIsNewHighscoreTrue() {
        String filename = "files/highscoresTest.txt";
        assertTrue("Not a new Highscore", GameCourt.isNewHighscore(210, filename));
    }
    
    //Deleting asteroids and bullets
    @Test
    public void testAsteroidOBDelete() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        Asteroid asteroid = new Asteroid(1, 0, GameCourt.COURT_WIDTH, 0, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.LARGE);
        court.addAsteroids(asteroid);
        assertEquals("Small asteroid OB", 1, court.getNumAsteroids());
        asteroid.move();
        court.outOfBoundsAsteroids();
        assertEquals("Small asteroid OB", 0, court.getNumAsteroids());
    }
    
    @Test
    public void testBulletOBDelete() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        Bullet bullet = new Bullet(1, 0, GameCourt.COURT_WIDTH, 0, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, false);
        court.addBullets(bullet);
        assertEquals("Bullet OB", 1, court.getNumBullets());
        bullet.move();
        court.outOfBoundsBullets();
        assertEquals("Bullet OB", 0, court.getNumBullets());
    }
    
    @Test
    public void testExplosiveBulletOBDelete() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        Bullet bullet = new Bullet(1, 0, GameCourt.COURT_WIDTH, 0, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, true);
        court.addBullets(bullet);
        assertEquals("Explosive Bullet OB", 1, court.getNumBullets());
        bullet.move();
        court.outOfBoundsBullets();
        assertEquals("Explosive Bullet OB", 0, court.getNumBullets());
    }
    
    @Test
    public void testBulletSmallAsteroidDelete() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        Bullet bullet = new Bullet(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, false);
        court.addBullets(bullet);
        
        Asteroid asteroid = new Asteroid(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.SMALL);
        court.addAsteroids(asteroid);
        
        assertEquals("Small Asteroid Bullet Interaction", 1, court.getNumAsteroids());
        assertEquals("Small Asteroid Bullet Interaction", 1, court.getNumBullets());
        court.bulletAsteroidInteraction();
        assertEquals("Small Asteroid Bullet Interaction", 0, court.getNumAsteroids());
        assertEquals("Small Asteroid Bullet Interaction", 0, court.getNumBullets());
    }
    
    @Test
    public void testBulletMediumAsteroidDelete() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        Bullet bullet = new Bullet(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, false);
        court.addBullets(bullet);
        
        Asteroid asteroid = new Asteroid(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.MEDIUM);
        court.addAsteroids(asteroid);
        
        assertEquals("Med Asteroid Bullet Interaction", 1, court.getNumAsteroids());
        assertEquals("Med Asteroid Bullet Interaction", 1, court.getNumBullets());
        court.bulletAsteroidInteraction();
        assertEquals("Med Asteroid Bullet Interaction", 2, court.getNumAsteroids());
        assertEquals("Med Asteroid Bullet Interaction", 0, court.getNumBullets());
    }
    
    @Test
    public void testBulletLargeAsteroidDelete() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        Bullet bullet = new Bullet(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, false);
        court.addBullets(bullet);
        
        Asteroid asteroid = new Asteroid(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.LARGE);
        court.addAsteroids(asteroid);
        
        assertEquals("Large Asteroid Bullet Interaction", 1, court.getNumAsteroids());
        assertEquals("Large Asteroid Bullet Interaction", 1, court.getNumBullets());
        court.bulletAsteroidInteraction();
        assertEquals("Large Asteroid Bullet Interaction", 2, court.getNumAsteroids());
        assertEquals("Large Asteroid Bullet Interaction", 0, court.getNumBullets());
    }
    
    @Test
    public void testExplosiveBulletMedAsteroidDelete() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        Bullet bullet = new Bullet(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, true);
        court.addBullets(bullet);
        
        Asteroid asteroid = new Asteroid(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.MEDIUM);
        court.addAsteroids(asteroid);
        
        assertEquals("Med Asteroid Bullet Interaction", 1, court.getNumAsteroids());
        assertEquals("Med Asteroid Bullet Interaction", 1, court.getNumBullets());
        court.bulletAsteroidInteraction();
        assertEquals("Med Asteroid Bullet Interaction", 0, court.getNumAsteroids());
        assertEquals("Med Asteroid Bullet Interaction", 0, court.getNumBullets());
    }
    
    @Test
    public void testExplosiveBulletLargeAsteroidDelete() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        Bullet bullet = new Bullet(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, true);
        court.addBullets(bullet);
        
        Asteroid asteroid = new Asteroid(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.LARGE);
        court.addAsteroids(asteroid);
        
        assertEquals("Large Asteroid Bullet Interaction", 1, court.getNumAsteroids());
        assertEquals("Large Asteroid Bullet Interaction", 1, court.getNumBullets());
        court.bulletAsteroidInteraction();
        assertEquals("Large Asteroid Bullet Interaction", 0, court.getNumAsteroids());
        assertEquals("Large Asteroid Bullet Interaction", 0, court.getNumBullets());
    }
    
    //player asteroid interaction
    @Test
    public void testBasicShooterHitsAsteroid() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        court.setNumLives(3);
        
        Shooter user = new BasicShooter(0, 0, 50, 50, GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT,
                Direction.UP);
        court.addShooter(user);
        
        Asteroid asteroid = new Asteroid(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.LARGE);
        court.addAsteroids(asteroid);
        
        assertEquals("Asteroid Shooter Interaction", 1, court.getNumAsteroids());
        assertEquals("Asteroid Shooter Interaction", 3, court.getNumLives());
        court.userAsteroidInteraction();
        assertEquals("Asteroid Shooter Interaction", 0, court.getNumAsteroids());
        assertEquals("Asteroid Shooter Interaction", 2, court.getNumLives());
    }
    
    @Test
    public void testShieldShooterHitsAsteroid() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        court.setNumLives(3);
        
        Shooter user = new ShieldShooter(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, Direction.UP);
        court.addShooter(user);
        
        Asteroid asteroid = new Asteroid(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.LARGE);
        court.addAsteroids(asteroid);
        
        assertEquals("Asteroid Shooter Interaction", 1, court.getNumAsteroids());
        assertEquals("Asteroid Shooter Interaction", 3, court.getNumLives());
        court.userAsteroidInteraction();
        assertEquals("Asteroid Shooter Interaction", 0, court.getNumAsteroids());
        assertEquals("Asteroid Shooter Interaction", 2, court.getNumLives());
    }
    
    @Test
    public void testShieldShooterHitsAsteroidShieldOn() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        court.setNumLives(3);
        
        Shooter user = new ShieldShooter(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, Direction.UP);
        court.addShooter(user);
        
        Asteroid asteroid = new Asteroid(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.LARGE);
        court.addAsteroids(asteroid);
        
        court.getShooter().useShield();
        assertEquals("Asteroid Shooter Interaction", 1, court.getNumAsteroids());
        assertEquals("Asteroid Shooter Interaction", 3, court.getNumLives());
        assertTrue("Asteroid Shooter Interaction", court.getShooter().getShield());
        court.userAsteroidInteraction();
        assertEquals("Asteroid Shooter Interaction", 0, court.getNumAsteroids());
        assertEquals("Asteroid Shooter Interaction", 3, court.getNumLives());
        assertFalse("Asteroid Shooter Interaction", court.getShooter().getShield());
    }
    
    @Test
    public void testShotgunShooterHitsAsteroid() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        court.setNumLives(3);
        
        Shooter user = new ShotgunShooter(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, Direction.UP);
        court.addShooter(user);
        
        Asteroid asteroid = new Asteroid(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.LARGE);
        court.addAsteroids(asteroid);
        
        assertEquals("Asteroid Shooter Interaction", 1, court.getNumAsteroids());
        assertEquals("Asteroid Shooter Interaction", 3, court.getNumLives());
        court.userAsteroidInteraction();
        assertEquals("Asteroid Shooter Interaction", 0, court.getNumAsteroids());
        assertEquals("Asteroid Shooter Interaction", 2, court.getNumLives());
    }
    
    @Test
    public void testExplosiveShooterHitsAsteroid() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        court.setNumLives(3);
        
        Shooter user = new ExplosiveShooter(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, Direction.UP);
        court.addShooter(user);
        
        Asteroid asteroid = new Asteroid(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, AsteroidSize.LARGE);
        court.addAsteroids(asteroid);
        
        assertEquals("Asteroid Shooter Interaction", 1, court.getNumAsteroids());
        assertEquals("Asteroid Shooter Interaction", 3, court.getNumLives());
        court.userAsteroidInteraction();
        assertEquals("Asteroid Shooter Interaction", 0, court.getNumAsteroids());
        assertEquals("Asteroid Shooter Interaction", 2, court.getNumLives());
    }
    
    //Player powerup interaction
    @Test
    public void testExplosiveShooterHitsPowerUp() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        Shooter user = new BasicShooter(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, Direction.UP);
        court.addShooter(user);
        
        PowerUp powerUp = new PowerUp(0, 0, 50, 50, GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT,
                Color.RED);
        court.addPowerUps(powerUp);
        
        assertEquals("PowerUp Shooter Interaction", ShooterType.BASIC, 
                court.getShooter().getType());
        assertEquals("PowerUp Shooter Interaction", 1, court.getNumPowerUps());
        court.userPowerUpInteraction();
        assertEquals("PowerUp Shooter Interaction", ShooterType.EXPLOSIVE, 
                court.getShooter().getType());
        assertEquals("PowerUp Shooter Interaction", 0, court.getNumPowerUps());
    }
    
    @Test
    public void testShotgunShooterHitsPowerUp() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);

        Shooter user = new BasicShooter(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, Direction.UP);
        court.addShooter(user);
        
        PowerUp powerUp = new PowerUp(0, 0, 50, 50, GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT,
                Color.ORANGE);
        court.addPowerUps(powerUp);
        
        assertEquals("PowerUp Shooter Interaction", ShooterType.BASIC, 
                court.getShooter().getType());
        assertEquals("PowerUp Shooter Interaction", 1, court.getNumPowerUps());
        court.userPowerUpInteraction();
        assertEquals("PowerUp Shooter Interaction", ShooterType.SHOTGUN, 
                court.getShooter().getType());
        assertEquals("PowerUp Shooter Interaction", 0, court.getNumPowerUps());
    }
    
    @Test
    public void testShieldShooterHitsPowerUp() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        Shooter user = new BasicShooter(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, Direction.UP);
        court.addShooter(user);
        
        PowerUp powerUp = new PowerUp(0, 0, 50, 50, GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT,
                Color.PINK);
        court.addPowerUps(powerUp);
        
        assertEquals("PowerUp Shooter Interaction", ShooterType.BASIC, 
                court.getShooter().getType());
        assertEquals("PowerUp Shooter Interaction", 1, court.getNumPowerUps());
        court.userPowerUpInteraction();
        assertEquals("PowerUp Shooter Interaction", ShooterType.SHIELD, 
                court.getShooter().getType());
        assertEquals("PowerUp Shooter Interaction", 0, court.getNumPowerUps());
    }
    
    @Test
    public void testShieldShooterHitsExplosivePowerUp() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        Shooter user = new ShieldShooter(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, Direction.UP);
        court.addShooter(user);
        
        PowerUp powerUp = new PowerUp(0, 0, 50, 50, GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT,
                Color.RED);
        court.addPowerUps(powerUp);
        
        
        assertEquals("PowerUp Shooter Interaction", ShooterType.SHIELD, 
                court.getShooter().getType());
        assertEquals("PowerUp Shooter Interaction", 1, court.getNumPowerUps());
        court.userPowerUpInteraction();
        assertEquals("PowerUp Shooter Interaction", ShooterType.EXPLOSIVE, 
                court.getShooter().getType());
        assertEquals("PowerUp Shooter Interaction", 0, court.getNumPowerUps());
    }
    
    //Testing PoweredUp Shots
    @Test
    public void testShieldShooterNumShieldsDecreases() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        Shooter user = new ShieldShooter(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, Direction.UP);
        court.addShooter(user);
        
        assertEquals("PowerUp Shooter Interaction", 1, court.getShooter().getNumShields());
        court.getShooter().useShield();
        assertEquals("PowerUp Shooter Interaction", 0, court.getShooter().getNumShields());
    }
    
    @Test
    public void testShieldShooterNumShotsStays() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        Shooter user = new ShieldShooter(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, Direction.UP);
        court.addShooter(user);
        
        assertEquals("PowerUp Shooter Interaction", 100, court.getShooter().getNumShots());
        court.getShooter().shoot();
        assertEquals("PowerUp Shooter Interaction", 100, court.getShooter().getNumShots());
    }
    
    @Test
    public void testExplosiveShooterNumShotsDecreases() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        Shooter user = new ExplosiveShooter(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, Direction.UP);
        court.addShooter(user);
        
        assertEquals("PowerUp Shooter Interaction", 20, court.getShooter().getNumShots());
        court.getShooter().shoot();
        assertEquals("PowerUp Shooter Interaction", 19, court.getShooter().getNumShots());
    }
    
    @Test
    public void testShotgunShooterNumShotsDecreases() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        Shooter user = new ShotgunShooter(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, Direction.UP);
        court.addShooter(user);
        
        assertEquals("PowerUp Shooter Interaction", 20, court.getShooter().getNumShots());
        court.getShooter().shoot();
        assertEquals("PowerUp Shooter Interaction", 19, court.getShooter().getNumShots());
    }
    
    @Test
    public void testBasicShooterNumShotsDecreases() {
        final JLabel numLives = new JLabel("Lives: ");
        final JLabel status = new JLabel("Running...");
        final JLabel points = new JLabel("Points: ");

        final GameCourt court = new GameCourt(status, numLives, points);
        
        Shooter user = new BasicShooter(0, 0, 50, 50, GameCourt.COURT_WIDTH, 
                GameCourt.COURT_HEIGHT, Direction.UP);
        court.addShooter(user);
        
        assertEquals("PowerUp Shooter Interaction", 100, court.getShooter().getNumShots());
        court.getShooter().shoot();
        assertEquals("PowerUp Shooter Interaction", 100, court.getShooter().getNumShots());
    }
    
}