package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

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

        writeToFile("rgb_to_grayscale",transformed);

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

        writeToFile("redFilter",redFilter);
        writeToFile("greenFilter",greenFilter);
        writeToFile("blueFilter",blueFilter);

        System.out.println("rgb_filters transformations successful! Check the images folder for a \"red_filter\", a \"green_filter\", and a \"blue filter\" image\n");
    }

    //inverts an image
    public static void invert()
    {
        for (int h = 0; h < height; h++)
        {
            for (int w = 0; w < width; w++)
            {
                Color preRGB = new Color(img.getRGB(w,h));
                int R = 255-preRGB.getRed();
                int G = 255-preRGB.getGreen();
                int B = 255-preRGB.getBlue();

                Color postRGB = new Color(R,G,B);
                transformed.setRGB(w,h,postRGB.getRGB());
            }
        }

        writeToFile("invert",transformed);

        System.out.println("invert transformation successful! Check the images folder for a \"invert\" image\n");
    }

    //deepfries an rgb image
    public static void rgb_deepfry()
    {

        /*writeToFile("rgb_deepfry",transformed);
        System.out.println("rgb_deepfry successfull. Check the images folder for a \"rgb_deepfry\" image"); */
    }

    //following should be in a diff branch for changing 'shapes': blurring, edge_detect, glass_filter, wave_filter
    public static void glassFilter()
    {
        for (int h = 0; h < height; h++)
        {
            for (int w = 0; w < width; w++)
            {
                //picking a random w and h that is at most 5 away from curr location
                int randW = pickRandom(w,width,5);
                int randH = pickRandom(h,height,5);
                int randColor = img.getRGB(randW,randH);

                /*System.out.println("w: " + w + "   h: " + h + "    width: " + width + "    height: " + height);
                System.out.println("randW: " + randW);
                System.out.println("randH: " + randH); */
                transformed.setRGB(w,h,randColor);
            }
        }

        writeToFile("glass_filter",transformed);
        System.out.println("glass_filter transformation successful! Check the images folder for a \"glass_filter\" image");
    }

    public static void addNoise()
    {
        for (int h = 0; h < height; h++)
        {
            for (int w = 0; w < width; w++)
            {
                int randW = pickRandom(w,width,10);
                int randH = pickRandom(h,height,10);

                Color randColor = null;
                switch((int)(Math.random()*4))
                {
                    case 0: randColor = Color.BLACK;
                    case 1: randColor = Color.GRAY;
                    case 2: randColor = Color.LIGHT_GRAY;
                    case 3: randColor = Color.DARK_GRAY;
                }
                transformed.setRGB(randW,randH,randColor.getRGB());
            }
        }

        writeToFile("add_noise",transformed);
        System.out.println("add_noise transformation completed! Check out the images folder for a \"add_noise\" image");
    }

    //returns a random int that is at most radius away from the param, and within the bounds
    private static int pickRandom(int param, int bound, int radius)
    {
        //bounds are 0 to bound
        ArrayList<Integer> possibleValues = new ArrayList();
        for (int i = param-radius; i<=param && i>=0; i++) //adding possible values for 5 lower
            possibleValues.add(i);
        for (int i = param+radius; i>=param && i<=bound-1; i--) //adding possible values for 5 upper
            possibleValues.add(i);

        Random r = new Random();
        return possibleValues.get(r.nextInt(possibleValues.size()));
    }

    private static void writeToFile(String fileName, BufferedImage transformed)
    {
        File newFile = new File("images/"+fileName+".jpeg");
        try{
            ImageIO.write(transformed,"jpeg",newFile);
        } catch(Exception yeet){
            System.out.println(yeet);
        }
    }
}
