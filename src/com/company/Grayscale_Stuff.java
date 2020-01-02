package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

//will process and return images with commands: "grayscale_to_rgb","rgb_to_grayscale","grayscale_deepfry"
public class Grayscale_Stuff
{
    private static String command = null;
    private static BufferedImage img = null;
    private static String fileName = null;

    public Grayscale_Stuff(String command, BufferedImage img)
    {
        this.command = command;
        this.img = img;
    }

    //will process a grayscale picture and write a rgb picture to the images folder
    public static void grayscale_to_rgb()
    {

    }

    //will process a rgb pic and return a grayscale image to the images folder
    public static void rgb_to_grayscale()
    {
        int height = img.getHeight();
        int width = img.getWidth();
        BufferedImage transformed = img;

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

    //will deepfry a gray scale image
    public static void grayscale_deepfry()
    {

    }
}
