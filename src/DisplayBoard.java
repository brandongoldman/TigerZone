/*
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DisplayBoard extends JFrame {
 
    
    public static void drawMap() {
        
        
        
        //Create and set up the window.
        JFrame map = new JFrame("GameMap");
        
        
        map.setSize(800,600);
        map.setTitle("TigerZone Map");
        map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel emptyLabel = new JLabel("");
        
        ImageIcon tile1 = new ImageIcon ("/src/tileImages/tile_0.png", "Tile 0");
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
}*/



import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class DisplayBoard extends JFrame {
    
    private BufferedImage bi;
    
    // File representing the folder that you select using a FileChooser
    static final File dir = new File("/Users/Adam/TigerZone/src/tileImages/");
    
    // created array of possible image extensions
    static final String[] EXTENSIONS = new String[]{
    "png" // can add other img formats if required -- only using PNGs currently
    };
    
    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
    
    @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
        }
        return (false);
        }
    };

    public static void main(String[] args) {
        
        
        if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : dir.listFiles(IMAGE_FILTER)) {
                BufferedImage img = null;
                
                try {
                    img = ImageIO.read(f);
                    

                    System.out.println("image: " + f.getName());
                    //System.out.println(" width : " + img.getWidth());
                    //System.out.println(" height: " + img.getHeight());
                    //System.out.println(" size  : " + f.length());
                } catch (final IOException e) {
                    // handle errors here
                }
            }
        }
        
        
        
        
        new DisplayBoard().setVisible(true);
    }
    
    public DisplayBoard() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            bi = ImageIO.read(new File("/Users/Adam/TigerZone/src/tileImages/tile_0.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        setSize(800, 1200);
        setTitle("TigerZone Map");
    }
    
    public void paint(Graphics g) {
        g.drawImage(bi, 100, 100, 50, 50, this);
    }
}
