package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

//will process incoming images and write results to the images folder
public class Image_Transformations
{
    private static String command = null;
    private static BufferedImage img = null;
    private static String fileName = null;
    private static int height = 0;
    private static int width = 0;
    private static BufferedImage transformed = null;

    public Image_Transformations(String command, BufferedImage img)
    {
        this.command = command;
        this.img = img;
        height = this.img.getHeight();
        width = this.img.getWidth();
        transformed = img;
    }

    //will process a rgb pic and return a grayscale image to the images folder
    public static void rgb_to_grayscale()
    {
        for (int h = 0; h < height; h++)
        {
            for (int w = 0; w < width; w++)
            {
                Color c = new Color(img.getRGB(w, h));
                int red = (int) (c.getRed() * .3);
                int blue = (int) (c.getBlue() * .11);
                int green = (int) (c.getGreen() * .59);

                Color newRGB = new Color(red+green+blue, red+green+blue, red+green+blue);
                transformed.setRGB(w,h,newRGB.getRGB());
            }
        }

        File newFile = new File("images/rgb_to_grayscale");
        try{
            ImageIO.write(transformed,"jpeg",newFile);
        } catch(Exception yeet){
            System.out.println(yeet);
        }
        System.out.println("rgb_to_grayscale transformation successful! Check the images folder for a \"rgb_to_grayscale\" image\n");
    }

    public static void rgb_filters()
    {
        BufferedImage redFilter = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage greenFilter = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage blueFilter = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int h = 0; h < height; h++)
        {
            for (int w = 0; w < width; w++)
            {
                Color preRgb = new Color(img.getRGB(w,h));
                int r = preRgb.getRed();
                int g = preRgb.getGreen();
                int b = preRgb.getBlue();

                blueFilter.setRGB(w,h,new Color(0,0,b).getRGB());
                redFilter.setRGB(w,h,new Color(r,0,0).getRGB());
                greenFilter.setRGB(w,h,new Color(0,g,0).getRGB());
            }
        }

        File newRed = new File("images/redFilter");
        File newGreen = new File("images/greenFilter");
        File newBlue = new File("images/blueFilter");
        try{
            ImageIO.write(greenFilter,"jpeg",newGreen);
            ImageIO.write(blueFilter,"jpeg",newBlue);
            ImageIO.write(redFilter,"jpeg",newRed);
        } catch(Exception yeet){
            System.out.println(yeet);
        }
        System.out.println("rgb_filters transformations successful! Check the images folder for a \"red_filter\", a \"green_filter\", and a \"blue filter\" image\n");
    }
}
