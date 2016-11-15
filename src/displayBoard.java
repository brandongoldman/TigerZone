
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class displayBoard extends JFrame {
    /**
     * this method should be invoked from the
     * event-dispatching thread.
     */
    
    public static void drawMap() {
        
        
        
        //Create and set up the window.
        JFrame map = new JFrame("GameMap");
        
        
        map.setSize(800,600);
        map.setTitle("TigerZone Map");
        map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel emptyLabel = new JLabel("");
        
        ImageIcon tile1 = new ImageIcon ("/src/testTile.png", "Tile 1");
        JLabel imageLabel = new JLabel(tile1);
        imageLabel.setBounds(10,10,400,400);
        imageLabel.setVisible(true);
        
        emptyLabel.setPreferredSize(new Dimension(800, 600));
        map.getContentPane().add(imageLabel, BorderLayout.CENTER);
        
        //Display the window.
        map.pack();
        map.setVisible(true);
    }
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                drawMap();
            }
        });
    }
}

