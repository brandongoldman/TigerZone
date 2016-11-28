
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

public class DisplayBoard extends JPanel
{
    
    private BufferedImage img;
    private static final int IMAGE_TYPE = BufferedImage.TYPE_INT_RGB;
    
    BufferedImage screen = new BufferedImage(1500,1000,IMAGE_TYPE);
    private BufferedImage[] tiles = new BufferedImage[29];
    private int tTypes = 29;
    private int mapLength = 60;
    private int mapWidth = 35;
    private int tEdge = 25;
    private BufferedImage[][] map = new BufferedImage[mapLength][mapWidth];
    
    //constructor to call methods and test operation
    public DisplayBoard() /*throws IOException*/
    {
        
        
        JFrame frame = new JFrame("TigerZone");
        
        //DisplayBoard display = new DisplayBoard();
        
        loadTile();
        init();
        
        
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        //loadTile();
        //init();
        //setTile("TLTJD",0,0,0);
        
        //setTile(tile identifier, Tile.position(xCord), Tile.position(yCord), rotation amounts);
        //setTile("TLTJD",0,1,90);
        //setTile("TLTJD",0,2,180);
        //setTile("TLTJD",0,3,270);
        //TileGrid();
        
    }
    
    //translate the input tile STRING to INT identifier
    public int TranslateTile(String tileString){
        
        int tileID = 28;
        
        switch(tileString){
            case "JJJJ-": tileID = 0;
                break;
            case "JJJJX": tileID = 1;
                break;
            case "JJTJX": tileID = 2;
                break;
            case "TTTT-": tileID = 3;
                break;
            case "TJTJ-": tileID = 4;
                break;
            case "TJJT-": tileID = 5;
                break;
            case "TJTT-": tileID = 6;
                break;
            case "LLLL-": tileID = 7;
                break;
            case "JLLL-": tileID = 8;
                break;
            case "LLJJ-": tileID = 9;
                break;
            case "JLJL-": tileID = 10;
                break;
            case "LJLJ-": tileID = 11;
                break;
            case "LJJJ-": tileID = 12;
                break;
            case "JLLJ-": tileID = 13;
                break;
            case "TLJT-": tileID = 14;
                break;
            case "TLJTP": tileID = 15;
                break;
            case "JLTT-": tileID = 16;
                break;
            case "JLTTB": tileID = 17;
                break;
            case "TLTJ-": tileID = 18;
                break;
            case "TLTJD": tileID = 19;
                break;
            case "TLLL-": tileID = 20;
                break;
            case "TLTT-": tileID = 21;
                break;
            case "TLTTP": tileID = 22;
                break;
            case "TLLT-": tileID = 23;
                break;
            case "TLLTB": tileID = 24;
                break;
            case "LJTJ-": tileID = 25;
                break;
            case "LJTJD": tileID = 26;
                break;
            case "TLLLC": tileID = 27;
                break;
            default: tileID = 28;
                //default tile is blank tile to be placed
                System.out.println("NO TILE FOUND?!");
                break;
        }
        return tileID;
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
        
        System.out.println("Game tile images loaded successfully...");
        
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
    public void setTile (String t, int xCord, int yCord, int rotates) {
        
        int f = TranslateTile(t);
        
        int newX = xCord + mapLength/2;
        int newY = (-1*yCord) + mapWidth/2;
        
        if(newX <= mapLength && newY <= mapWidth){
            //int degrees = rotates;
            BufferedImage placeTile = tiles[f];
            
            if(rotates != 0){
            placeTile = CCRotate(placeTile, rotates);
            }
        
        System.out.println("Placed tile: " + t + " at Coordinates (" + xCord + "," + yCord + ") rotated " + rotates + " degrees");
        //map[xCord][yCord] = tiles[f];
        map[newX][newY] = placeTile;
        }else{
            System.out.println("Cannot place tile: OUT OF BOUNDS");
            
        }
        TileGrid();
    }
    
    //initialized a blank board with all empty slots
    public void init(){
        
        for(int x = 0; x < map.length; x++){
            
            for (int y = 0; y < map[x].length; y++){
                map[x][y] = tiles[28]; //all map locations are set to blank tiles
            }
        }
        System.out.println("Game board initialized successfully...");
        
    }
    
    
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
    
    
  /*
    public static void main(String[] args) {
        //creating the board JFrame
        JFrame frame = new JFrame("TigerZone");
        
        DisplayBoard display = new DisplayBoard();
        frame.add(display);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
    }
   */
    
    
}
