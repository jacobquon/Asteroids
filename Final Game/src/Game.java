// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("Asteroids");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel numLives = new JLabel("Lives: ");
        status_panel.add(numLives);
        final JLabel blank1 = new JLabel("                                      ");
        status_panel.add(blank1);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);
        final JLabel blank2 = new JLabel("                                      ");
        status_panel.add(blank2);
        final JLabel points = new JLabel("Points: ");
        status_panel.add(points);

        // Main playing area
        final GameCourt court = new GameCourt(status, numLives, points);
        frame.add(court, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        final String instructionString = ("Welcome to Asteroids! \n\nHow To Play:\n You control the"
                + "square that starts in the center of the screen. The black line indicates the "
                + "direction you will shoot. \n  -Use WASD to move around \n  -Left click to shoot "
                + "\n  -Use Q & E to rotate your shooter\n The gray circles are the asteroids, if "
                + "you hit one, you will lose a life. If you get to 0 lives you lose.\n You can "
                + "shoot the asteroids to gain points.\n  -Large asteroids = 15 Points\n  -Medium "
                + "asteroids = 10 Points\n  -Small asteroids = 5 Points\n Occasionally, different"
                + " colored circles will fly by on the screen. If you touch one, you will get a "
                + "power-up.\n You will have a limited number of shots with the power up, and when"
                + " you are out, you will go back to your basic form.\n  -Red circles give you"
                + " explosive rounds which completely destroy asteroids\n  -Orange circles give "
                + "you a shotgun\n  -Pink circles give you a shield that can be used by "
                + "pressing F\n The input highscore button only works when the game is paused"
                + " or you have lost.");
        
        final JButton instructions = new JButton("Instructions");
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, instructionString, "INSTRUCTIONS:", 
                        JOptionPane.PLAIN_MESSAGE);
                court.requestFocusInWindow();
            }
        });
        
        final JButton highscoreInput = new JButton("Input Highscore");
        highscoreInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!court.playing) {
                    if (GameCourt.isNewHighscore(court.getPoints(), 
                            "files/highscores.txt")) {
                        String userName = JOptionPane.showInputDialog("User Name");
                        GameCourt.writeHighscores(userName, court.getPoints(), 
                                "files/highscores.txt");
                        
                    } else {
                        JOptionPane.showMessageDialog(frame, "Your Score is Not High Enough");
                    }
                }
                court.requestFocusInWindow();
            }
        });
        
        final JButton highscores = new JButton("Highscores");
        highscores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Map<Integer, String> highscoresRead = 
                        GameCourt.readHighscores("files/highscores.txt");
                
                String highscoresString = "";
                Set<Entry<Integer, String>> set = highscoresRead.entrySet();
                Iterator<Entry<Integer, String>> itr = set.iterator();
                    
                while(itr.hasNext()) {
                    Entry<Integer, String> entry = (Entry<Integer, String>) itr.next(); 
                    highscoresString += entry.getValue();
                    highscoresString += "     ";
                    highscoresString += entry.getKey().toString();
                    highscoresString += "\n";
                }
                    
                JOptionPane.showMessageDialog(frame, highscoresString, "Highscores", 
                        JOptionPane.PLAIN_MESSAGE);
                court.requestFocusInWindow();
            }
        });
        
        final JButton pause = new JButton("Play/Pause");
        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (court.playing) { 
                    court.playing = false;
                } else {
                    court.playing = true;
                }
                court.requestFocusInWindow();
            }
        });
        
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        
        control_panel.add(pause);
        control_panel.add(reset);
        control_panel.add(instructions);
        control_panel.add(highscores);
        control_panel.add(highscoreInput);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        court.reset();
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}
