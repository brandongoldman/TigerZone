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

/*

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class DisplayBoard extends JFrame {
    
    private BufferedImage bi;
    private BufferedImage img;
    
    final int TILE_TYPES = 27;
    
    BufferedImage[] tiles = new BufferedImage[TILE_TYPES];
    
    // File representing the folder that you select using a FileChooser
    static final File dir = new File("/src/tileImages/");
    
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

    public void init (){
        private BufferedImage empty;
        empty = ImageIO.read(new File("/src/tileImages/BLANK.png"));



    }

    public void placeTile(Graphics g, int f, xCord, yCord){
        img = ImageIO.read(new File("/src/tileImages/tile_" + f + ".png"));


    g.drawImage(
        //add corect tile to correct place in 2d array then call drawMap
    }

}
*/

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.io.File;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DisplayBoard
{
    //placeholder for each tile
    public BufferedImage[] tiles = new BufferedImage[27];
    
    //array of tile types
    int[][] map = new int[32][32];
    //position coordinates
    public static int posX, posY;
    //Edge of map Cooridinates
    public static int sx, sy;
    public String fileName;
    
    
     public static void main(String[] args) {
         
         DisplayBoard();
         
     }
    
    public DisplayBoard() {
         img = new BufferedImage( 450, 350, this);
    }
    
    public void paint(Graphics g) {
        g.drawImage(bi, 100, 100, 50, 50, this);
    }


    
    public DisplayBoard(int posX, int posY, int f) throws IOException
    {
        this.fileName = fileName;
        int x = 0, y = 0;
        //this.f = f;
        this.posX = posX;
        this.posY = posY;
        this.sx = map.length * 32;
        this.sy = map.length * 32;
        /*BufferedReader in = new ImageIO.read(new File("/src/tileImages/tile_" + f + ".png"));
        
        String line;
        
        while((line = in.readLine()) != null)
        {
            String[] values = line.split(",");
            
            for(String str : values)
            {
                int str_int = Integer.parseInt(str);
                map[x][y] = str_int;
                y += 1;
            }
            x += 1;
            y = 0;
        }
        
        in.close();*/
    }
    
    public void update()
    {
    }
    
    //Game.mapX and Game.mapY are the position variables in the main Game class
    public void render(Graphics g)
    {
        for(int x = 0; x < 32; x++)
        {
            for(int y = 0; y < 32; y++)
            {
                int textureType = map[x][y];
                loadTile();
                BufferedImage texture = tiles[textureType];
                g.drawImage(texture, 25, 25,  posX, posY, null);
                posY += 32;
            }
            posX += 32;
            //posY = Game.mapY;
        }
        //posX = Game.mapX;
        //posY = Game.mapY;
        
    }
    
    
    public static void loadTile()
    {
        
        
        for(int i = 0; i < 26; i++){
            tiles[i] = ImageIO.read(new File("/src/tileImages/tile_" + i + ".png"));
            //System.out.println("image: " + tiles[i].getName());
        }
        /*tiles[0] = ImageIO.read(new File("/src/tileImages/tile_0.png"));
        tiles[1] = ImageIO.read(new File("/src/tileImages/tile_1.png"));
        tiles[2] = ImageIO.read(new File("/src/tileImages/tile_" + 2 + ".png"));
        tiles[3] = ImageIO.read(new File("/src/tileImages/tile_" + 3 + ".png"));
        tiles[4] = ImageIO.read(new File("/src/tileImages/tile_" + 4 + ".png"));
        tiles[5] = ImageIO.read(new File("/src/tileImages/tile_" + 5 + ".png"));
        tiles[6] = ImageIO.read(new File("/src/tileImages/tile_" + 6 + ".png"));
        tiles[7] = ImageIO.read(new File("/src/tileImages/tile_" + 7 + ".png"));
        tiles[8] = ImageIO.read(new File("/src/tileImages/tile_" + 8 + ".png"));
        tiles[9] = ImageIO.read(new File("/src/tileImages/tile_" + 9 + ".png"));
        tiles[10] = ImageIO.read(new File("/src/tileImages/tile_" + 10 + ".png"));
        tiles[11] = ImageIO.read(new File("/src/tileImages/tile_" + 11 + ".png"));
        tiles[12] = ImageIO.read(new File("/src/tileImages/tile_" + 12 + ".png"));
        tiles[13] = ImageIO.read(new File("/src/tileImages/tile_" + 13 + ".png"));
        tiles[14] = ImageIO.read(new File("/src/tileImages/tile_" + 14 + ".png"));
        tiles[15] = ImageIO.read(new File("/src/tileImages/tile_" + 15 + ".png"));
        tiles[16] = ImageIO.read(new File("/src/tileImages/tile_" + 16 + ".png"));
        tiles[17] = ImageIO.read(new File("/src/tileImages/tile_" + 17 + ".png"));
        tiles[18] = ImageIO.read(new File("/src/tileImages/tile_" + 18 + ".png"));
        tiles[19] = ImageIO.read(new File("/src/tileImages/tile_" + 19 + ".png"));
        tiles[20] = ImageIO.read(new File("/src/tileImages/tile_" + 20 + ".png"));
        tiles[21] = ImageIO.read(new File("/src/tileImages/tile_" + 21 + ".png"));
        tiles[22] = ImageIO.read(new File("/src/tileImages/tile_" + 22 + ".png"));
        tiles[23] = ImageIO.read(new File("/src/tileImages/tile_" + 23 + ".png"));
        tiles[24] = ImageIO.read(new File("/src/tileImages/tile_" + 24 + ".png"));
        tiles[25] = ImageIO.read(new File("/src/tileImages/tile_" + 25 + ".png"));
        tiles[26] = ImageIO.read(new File("/src/tileImages/tile_" + 26 + ".png"));*/
    }
}

