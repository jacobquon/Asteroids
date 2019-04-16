=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: jquon
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Collections
  		Collections are used in many places in my game. They are used to store the asteroids,
  		bullets, and powerups present in the game. They are also used when generating new asteroids
  		and bullets. 
  		My collections are all sets and are appropriate for their use because order does not matter
  		for any of them. Sets also make it quick to add and delete any of the objects.

  2. File I/O
  		I used file I/O to keep track of high scores in the game
  		High scores were an appropriate use of file i/o, as they require the program to read in a 
  		.txt file with the high scores, compare them, and then write out the new high scores to the
  		file

  3. Inheritance/Subtyping For Dynamic Dispatch
  		I used inheritance for almost all the classes in my game.
  			Bullet extends GameObj
  			Asteroid extends GameObj
  			PowerUp extends GameObj
  			Shooter extends GameObj
  				BasicShooter extends Shooter
  				ShotgunShooter extends Shooter
  				ExplosiveShooter extends SHooter
  				ShieldShooter extends Shooter
  		My heirarchy makes sense as bullets, asteroids, powerups, and shooters are all different
  		types of objects in my game with new and often complex methods added on to the base of
  		GameObj (such as splitAsteroid and shoot). The heirarchy of shooters also makes sense as 
  		they come in 4 different types which must extend the base class to implement their 
  		different shoot/shield methods.
  		
  		Dynamic Dispatch was used primarily for the shooting method of shooters. It allowed all the
  		shooters to have basic knowledge of their direction, position, and speed, while also 
  		allowing them to each have specific abilities.

  4. Testable Component
  		I used JUnit tests to test if my game state was updating correctly. 
  		This was an appropriate use of testing as it allowed me a good way to test different edge
  		cases that may come up in my game. Without JUnit, these edge cases would be hard to test
  		as some of them occur very rarely in the actual game window.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
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
  


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  I had trouble with File I/O and deleting/adding things to my collection while the game was
  running


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

  My design is alright, but could definitely be improved upon. I would probably seperate 
  functionality even more from the GameCourt class to make it much tidier in their.
  Private state is encapsulated pretty well, most fields are private with getters and setters and
  methods are made public.
  For refactoring, I would delete some of the unused methods in GameObj, combine some the 
  ShooterType and PowerUpType enums, and as previously stated, make GameCourt tidier.
  
========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  https://beginnersbook.com/2014/07/how-to-iterate-treemap-in-reverse-order-in-java/
  https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
  JavaDocs for various things such as reader, writer, maps, sets, etc.
  The super helpful TA's :)
