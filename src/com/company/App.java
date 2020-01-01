package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class App
{
    public static final String[] features = new String[]{"grayscale_to_rgb","rgb_to_grayscale","grayscale_deepfry","invert","blur","line_detect"};
    public static void main(String[] args)
    {
        welcome();
        Scanner scan = new Scanner(System.in);
        if (scan.nextLine().equals("help")) displayFeatures();
    }

    private static void welcome()
    {
        System.out.println("To use this, enter the name of the feature you would like followed by the path of the image you would like to transform.");
        System.out.println("To see a full list of features, type \"help\"");
    }

    private static void displayFeatures()
    {
        System.out.println("Features: " + Arrays.toString(features));
    }
}
