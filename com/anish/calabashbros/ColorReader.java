package com.anish.calabashbros;

import java.io.File;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ColorReader {
    public static final int row = 16;
    public static final int col = 16;
    public static int[][][] getColor(){
        int[][][]rgb = new int[row][col][3];
        
        BufferedImage bi = null;
        try{
            bi = ImageIO.read(new File("resources/c256.png"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        int width = bi.getWidth();
        int height = bi.getHeight();

        float w = (float)width/16;
        float h = (float)height/16;
        for(int i = 0;i < row;i++){
            for(int j = 0;j < col;j++){
                int x = (int)(w/2 + w * j);
                int y = (int)(h/2 + h * i);
                int pixel=bi.getRGB(x,y);
                //rgb[(i*ColorReader.row)+j];
                rgb[i][j][0] = (pixel &  0xff0000) >> 16;//r
                rgb[i][j][1] = (pixel &  0xff00) >> 8;//g
                rgb[i][j][2] = (pixel &  0xff);//b
                //System.out.print(rgb[i][j][0]+" " + rgb[i][j][1] + " " + rgb[i][j][2]);
                
            }
        }
        return rgb;
    }
}
