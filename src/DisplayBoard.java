
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.FilenameFilter;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class DisplayBoard extends JPanel
{
    
    private BufferedImage img;
    private static final int IMAGE_TYPE = BufferedImage.TYPE_INT_RGB;
    
    BufferedImage screen = new BufferedImage(1500,1000,IMAGE_TYPE);
    public BufferedImage[] tiles = new BufferedImage[28];
    public JLabel[] tileLabel = new JLabel[28];
    private int tTypes = 28;
    private int mapLength = 35;
    private int mapWidth = 20;
    private int tEdge = 40;
    public BufferedImage[][] map = new BufferedImage[mapLength][mapWidth];
    
    //constructor to call methods and test operation
    public DisplayBoard() /*throws IOException*/
    {
        
        
        
        loadTile();
        init();
        //setTile(8,3,5);
        setTile(19,18,9,0);
        setTile(24,19,9,1);
        for(int q = 0; q<20; q++){
            
            setTile(q,q,q,0);
            
        }
        
        //setTile(tile identifier, Tile.position(xCord), Tile.position(yCord), rotation amounts);
        
        //tiles[8]=RotateBy90(tiles[8]);
        //setTile(8,3,6);
        //setTile(RotateBy90(tiles[8]),3,6);
        setTile(5,8,9,1);
        setTile(16,18,10,1);
        TileGrid();
        
    }
    
    
    //method to load all tile types into BufferedImage array to be chosen to place onto board
    public void loadTile()
    {
        //Graphics g = screen.getGraphics();
        for(int i = 0; i < tTypes; i++){
            try {
               // tiles[i] = ImageIO.read(new File("/Users/Adam/TigerZone/src/tileImages/tile_" + i + ".png"));
                tiles[i] = ImageIO.read(new File("../TigerZone/src/tileImages/tile_" + i + ".png"));   // ADAM.. I CHANGED THE DIRECTORY SO CHANGE BACK IF YOU HAVE PROBLEMS
                
                //tileLabel[i] = new JLabel(new ImageIcon(tiles[i]));
                
                //System.out.println("image:  " + i + tiles[i] );
            }
            catch (final IOException e) {
                System.out.println(e);
            }
        }
    }
    
    //method to paint background BOARD image that all tiles will be painted over
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(screen,0,0,null);
        
    }
    
    //will draw the tile image in the loaction that it is called
    public void drawTile(Graphics g, BufferedImage img, int xCord, int yCord) {
        g.drawImage(img, xCord, yCord, (tEdge-2), (tEdge-2), null);
    }
    
    //allows certain tiles to be placed in the map 2D array
    public void setTile (int f, int xCord, int yCord, int rotates) {
        
        if(xCord <= mapLength && yCord <= mapWidth){
            int num = rotates;
            BufferedImage placeTile = tiles[f];
        
                while(num != 0){
                    placeTile = RotateBy90(placeTile);
                    System.out.println("Rotated tile: " + f + " / " + rotates + " times" );
                    num--;
                }
        
        System.out.println("Placed tile: " + f + " at Coordinates (" + xCord + "," + yCord + ")");
        //map[xCord][yCord] = tiles[f];
        map[xCord][yCord] = placeTile;
        }else{
            System.out.println("Cannot place tile: OUT OF BOUNDS");
            
        }
    
}
    
    //initialized a blank board with all empty slots
    public void init(){
        
        for(int x = 0; x < map.length; x++){
            
            for (int y = 0; y < map[x].length; y++){
                map[x][y] = tiles[27];
            }
        }
        
    }
    
    //public void update{
    //}
    
    //method that allows tiles to be rotated by 90 degree
    public BufferedImage RotateBy90(BufferedImage bi) {
        
        int width = bi.getWidth();
        int height = bi.getHeight();
        
        BufferedImage biFlip = new BufferedImage(height, width, bi.getType());
        
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                biFlip.setRGB(height-1-j, width-1-i, bi.getRGB(i, j));
            }
        }
        return biFlip;
        
        //--TODO-- allow image to continually be rotated again by 90 degrees instead of flipping back
    }
    
    //draws each tile currently in the map array to the board
    public void TileGrid(){
        Graphics g = screen.getGraphics();
        //Graphics g = screen.getGraphics();
        
        for(int x = 0; x < map.length; x++){
            
            for (int y = 0; y < map[x].length; y++){
                drawTile(g, map[x][y], x*tEdge, y*tEdge);
                //drawTile(g, tiles[3], 0, x*30);
                //System.out.println("swag");
            }
        }
    }
    
    
    
    public static void main(String[] args) {
        //creating the board JFrame
        JFrame frame = new JFrame("TigerZone");
        
        DisplayBoard display = new DisplayBoard();
        frame.add(display);
        //frame.setSize(800,800);
        //frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
    }
    
    
}
