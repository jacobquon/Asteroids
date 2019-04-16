
  Asteroid - extension of GameObj that contains all asteroid specific methods necessary to run
  AsteroidSize - Enum for the three different sizes of asteroids
  BasicShooter - Extension of Shooter that contains the normal shoot method and how to draw the 
  				 basic shooter
  Bullet - Extension of GameObj that tells the game how to draw bullets, what their hitbox should
  		   be and whether they are explosive or not
  Direction - Enum for the four different directions the shooter can face
  ExplosiveShooter - Extension of Shooter that contains the explosive shoot method and what color
  					 the explosive shooter should be
  Game - Used to run the actual game. Contains the code for all the buttons and labels
  GameCourt - the class used to store the state of the game. Keeps track of lives, points, 
  			  asteroids, bullets, powerups, shooter type, etc. Also updates the game state and is
  			  responsible for reading and writing highscores.
  GameObj - the base class used for all the objects in the game. Used primarily for movement, 
  			storing position, storing velocity, checking if out of bounds, and if there is 
  			intersection
  PowerUp - Extension of GameObj that tells the game how to draw powerup, what their hitbox should
  			be, and what type they are
  PowerUpType - Enum for the type of the power up
  ShieldShooter - Extension of Shooter that has the same shoot method as a basic shooter, but a 
  				  special shield method that allows you to use a shield. Stores how to draw the 
  				  shield shooter
  Shooter - Extension of GameObj that tells the game how to draw the shooter, as well as adds some
  			new methods shooters need (rotate, useShield, getNumShots, etc.). Also adds an
  			abstract shoot method which is implemented by all the different shooters to create
  			their own special way of shooting
  ShooterType - Enum for the type of the shooter
  ShotgunShooter - Extension of Shooter that tells the game how a shotgun shooter should shoot, and
  				   also what color the shotgun shooter should be
  GameTest - JUnit test class to test game state updates
  highscores.txt - used to store highscores
  highscoresTest.txt - used to test some highscore methods
