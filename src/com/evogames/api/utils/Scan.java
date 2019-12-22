package com.evogames.api.utils;

import java.io.File;
import java.util.Scanner;
public class Scan
{
    public static void main(String[] args) throws Exception
    {
        // pass the path to the file as a parameter
        File file = new File("C:\\Users\\pablo\\test.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine())
            System.out.println(sc.nextLine());
    }
}
