
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

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class DisplayBoard extends JPanel
{
    
    private BufferedImage img;
    private static final int IMAGE_TYPE = BufferedImage.TYPE_INT_RGB;
    
    BufferedImage screen = new BufferedImage(1500,1000,IMAGE_TYPE);
    public BufferedImage[] tiles = new BufferedImage[29];
    private int tTypes = 29;
    private int mapLength = 60;
    private int mapWidth = 35;
    private int tEdge = 25;
    public BufferedImage[][] map = new BufferedImage[mapLength][mapWidth];
    
    //constructor to call methods and test operation
    public DisplayBoard() /*throws IOException*/
    {
        
        
        
        loadTile();
        init();
        setTile(19,0,0,0);
        
        //for(int q = 0; q<20; q++){
            
        //    setTile(q,q,q,0);
            
        //}
        //setTile(tile identifier, Tile.position(xCord), Tile.position(yCord), rotation amounts);
        setTile(19,0,1,90);
        setTile(19,0,2,180);
        setTile(19,0,3,270);

        TileGrid();
        
    }
    
    
    //method to load all tile types into BufferedImage array to be chosen to place onto board
    public void loadTile()
    {
        //Graphics g = screen.getGraphics();
        for(int i = 0; i < tTypes; i++){
            try {
                tiles[i] = ImageIO.read(new File("../src/tileImages/tile_" + i + ".png"));
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
        
        xCord = xCord + mapLength/2;
        yCord = yCord + mapWidth/2;
        
        if(xCord <= mapLength && yCord <= mapWidth){
            //int degrees = rotates;
            BufferedImage placeTile = tiles[f];
            
            if(rotates != 0){
            placeTile = CCRotate(placeTile, rotates);
            }
        
        System.out.println("Placed tile: " + f + " at Coordinates (" + xCord + "," + yCord + ") rotated " + rotates + " degrees");
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
                map[x][y] = tiles[28];
            }
        }
        
    }
    
    //method that allows tiles to be rotated by 90 degree
    /*public BufferedImage RotateBy90(BufferedImage bi) {
        
        int width = bi.getWidth();
        int height = bi.getHeight();
        
        BufferedImage biFlip = new BufferedImage(height, width, bi.getType());
        
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                biFlip.setRGB(height-1-j, width-1-i, bi.getRGB(i, j));
            }
        }
        return biFlip;
    }*/
    
    public BufferedImage CCRotate(BufferedImage bi, int degrees){
        
        int width = bi.getWidth();
        int height = bi.getHeight();
        
        //double radians = ((degrees + 180 ) * Math.PI) / 180;
        double radians = 0;
        
        if (degrees == 90){
            radians = (3*Math.PI)/2;
        }
        else if (degrees == 180){
            radians = (Math.PI);
        }
        else if(degrees == 270){
            radians = (Math.PI/2);
        }
        else{
            System.out.println("INVALID ROTATION VARIABLE");
        }
        
        BufferedImage bufferedImage = new BufferedImage(height, width, bi.getType());
        
        bufferedImage = bi;
        
        AffineTransform tx = new AffineTransform();
        tx.rotate(radians, bi.getWidth() / 2, bi.getHeight() / 2);
        //rotating by radians counterclockwise about center of tile
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        bufferedImage = op.filter(bufferedImage, null);
        
        return bufferedImage;

        
    }
    
    //draws each tile currently in the map array to the board
    public void TileGrid(){
        Graphics g = screen.getGraphics();
        
        for(int x = 0; x < map.length; x++){
            
            for (int y = 0; y < map[x].length; y++){
                drawTile(g, map[x][y], x*tEdge, y*tEdge);
            }
        }
    }
    
    
    
    public static void main(String[] args) {
        //creating the board JFrame
        JFrame frame = new JFrame("TigerZone");
        
        DisplayBoard display = new DisplayBoard();
        frame.add(display);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
    }
    
    
}
