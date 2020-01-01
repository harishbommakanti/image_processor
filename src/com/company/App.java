package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class App
{
    public static final String[] features = new String[]{"grayscale_to_rgb","rgb_to_grayscale","grayscale_deepfry","rgb_deepfry","invert","blur","line_detect"};

    public static void main(String[] args)
    {
        welcome();
        Scanner scan = new Scanner(System.in);

        new Thread(() -> {
            boolean shouldContinue = true;

            do {
                String nextCommand = scan.nextLine();
                if (nextCommand.equals("help")) displayFeatures(); //if person wants help
                else if (nextCommand.equals("end")) shouldContinue=false; //if person wants to end program
                else
                {
                    processTasks(nextCommand);
                }
            } while(shouldContinue);
        }).start();
    }

    //Displays welcome text at start of program
    private static void welcome()
    {
        System.out.println("\nTo use this, enter the name of the feature you would like followed by the directory of the image you would like to transform.");
        System.out.println("-To see a full list of features, type \"help\" \n-To end the program, type \"end\"\n");
    }

    //Prints an array with features of program
    private static void displayFeatures()
    {
        System.out.println("Features: " + Arrays.toString(features));
        System.out.println("\"grayscale_to_rgb\" and vice-versa, and \"rgb_deepfry\" and vice versa, are self-explanatory");
        System.out.println("-\"blur\" blurs an image. Can do multiple times to make picture very blurry");
        System.out.println("-\"line_detect\" creates a new image outlining the edges of the picture inputted\n");
    }

    //Constructs objects and makes method calls to process the images
    private static void processTasks(String nextLine) throws IOException
    {
        //System.out.println(nextCommand);
        String[] commandAndDir = nextLine.split(" "); //command is meant to be 2 " " separated tokens
        var command = commandAndDir[0];
        var directory = commandAndDir[1];
        if (command.equals(directory)) //there was no space, only 1 token
        {
            System.out.println("Please enter a command followed by the directory of the image");
        } else if (Arrays.binarySearch(features,command) > -1) //if command is not in the array aka not supported by the app
        {
            System.out.println("Please enter a valid command.");
        }

        //there are now two tokens, and the command is valid
    }
}
