package com.company;

import java.awt.image.BufferedImage;

//will process and return images with commands: "grayscale_to_rgb","rgb_to_grayscale","grayscale_deepfry"
public class Grayscale_Stuff
{
    private static String command = null;
    private static BufferedImage img = null;
    public Grayscale_Stuff(String command, BufferedImage img)
    {
        this.command = command;
        this.img = img;
    }

    //will process a rgb picture and return grayscale picture
    public static void grayscale_to_rgb()
    {

    }

    //will process a gray scale pic and return an rgb one
    public static void rgb_to_grayscale()
    {

    }

    //will deepfry a gray scale image
    public static void grayscale_deepfry()
    {

    }
}
