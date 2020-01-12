package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class App
{
    public static final String[] features = {"rgb_to_grayscale","rgb_deepfry","invert","blur","edge_detect",
            "rgb_filters","glass_filter","wave_filter", "add_noise"};

    public static void main(String[] args) throws IOException
    {
        welcome();
        Scanner scan = new Scanner(System.in);

        new Thread(() -> {
            boolean shouldContinue = true;

            while(shouldContinue)
            {
                String nextCommand = scan.nextLine();
                if (nextCommand.equals("help")) displayFeatures(); //if person wants help
                else if (nextCommand.equals("end")) shouldContinue=false; //if person wants to end program
                else
                {
                    try {
                        parseLine(nextCommand);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    //Displays welcome text at start of program
    private static void welcome()
    {
        System.out.println("\nTo use this, enter the name of the feature you would like followed by the directory of the image you would like to transform: [command] [directory]\n+" +
                "The image should be in the \"images\" directory, and the directory should be similar to \"images/{img.jpeg}\"");
        System.out.println("-To see a full list of features, type \"help\" \n-To end the program, type \"end\"\n");
    }

    //Prints an array with features of program
    private static void displayFeatures()
    {
        System.out.println("Features: " + Arrays.toString(features));
        System.out.println("-\"grayscale_to_rgb\" and vice-versa, and \"rgb_deepfry\" and vice versa, are self-explanatory");
        System.out.println("-\"blur\" blurs an image. Can do multiple times to make picture very blurry");
        System.out.println("-\"line_detect\" creates a new image outlining the edges of the picture inputted\n");
    }

    //Parses the next line and isolates the command and the image
    private static void parseLine(String nextLine) throws IOException
    {
        //System.out.println(nextCommand);
        String[] commandAndDir = nextLine.split(" "); //command is meant to be 2 " " separated tokens

        if (commandAndDir.length == 1) //there was no space, only 1 token
        {
            System.out.println("Please enter a command followed by the directory of the image: [command] [directory]");
            return;
        }
        var command = commandAndDir[0];
        var directory = commandAndDir[1];

        Arrays.sort(features);
        if (Arrays.binarySearch(features,command) < 0) //if command is not in the array aka not supported by the app
        {
            System.out.println("Please enter a valid command: [command] [directory]");
            return;
        }

        //there are now two tokens, and the command is valid

        File folderInput = new File(directory);

        BufferedImage img = null;
        try {
            img = ImageIO.read(folderInput);
        } catch (IOException e){
            System.out.println("Specified file does not exist");
            System.out.println("Common tips:");
            System.out.println("-Make sure you include the CORRECT postfix (.png, .jpeg)");
            System.out.println("-Make sure you actually include the name of the file in the following format: \"images\"");
            return;
        }
        //System.out.println(folderInput + " command successful");

        processTasks(command,img);
    }

    //Constructs objects and calls methods to process the specified images
    private static void processTasks(String command, BufferedImage img)
    {
        Image_Transformations i = new Image_Transformations(command,img);

        switch(command) //when the methods are completed, fill in blanks of switch case
        {
            case "rgb_to_grayscale": i.rgb_to_grayscale();return;
            case "rgb_deepfry":i.rgb_deepfry();return;
            case "invert":i.invert();return;
            case "blur":return;
            case "edge_detect":return;
            case "rgb_filters": i.rgb_filters();return;
            case "glass_filter":i.glassFilter();return;
            case "wave_filter":;return;
            case "add_noise":i.addNoise();return;
            default: return;
        }
    }
}