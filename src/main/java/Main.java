package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Jeffrey Lima on 7/27/16.
 * Project 2
 * Given a set of integer lattice points in the plane write a java program to find the distance
 * between the closest pair of points. You must use the O(n lg n) time algorithm taught in class.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Project 2: Closest Pair of Points");
        Scanner scan = new Scanner(System.in);
        char ch;
        do {
            // Note: error will be thrown if input file only has one point.
            Closest2DPair closest = new Closest2DPair(readInput(readFile()));
            System.out.println("\u221A" + closest.getClosestDistance() + " is the closest distance from the points: "
                    + closest.getFirstPoint() + " and " + closest.getSecondPoint() + '.');

            System.out.print("Another file [y:n]? ");
            ch = scan.nextLine().charAt(0);
        } while (Character.toLowerCase(ch) == 'y');
        scan.close();
        System.exit(0);
    }

    // Read file from input.
    private static Scanner readFile() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        String inputFile;
        URL path;
        File file;

        System.out.print("Please enter input file name: ");
        inputFile = scan.nextLine().trim();

        path = Main.class.getResource("../resources/" + inputFile);
        if (path != null) {
            file = new File(path.getFile());
            Scanner input = new Scanner(file);
            if (!input.hasNext()) {
                System.out.println("Input file is empty. Please try again.");
            } else {
                return input;
            }
            input.close();
        } else {
            System.out.println("Input is not a file or does not exist. Please try again.");
        }

        scan.close();
        return scan;
    }

    // Read input from scanner
    private static List<Point> readInput(Scanner input) {
        List<Point> points = new ArrayList<>();
        Point temp = new Point();
        while (input.hasNextDouble()) {
            temp.setX(input.nextDouble());
            temp.setY(input.nextDouble());
            points.add(new Point(temp));
        }
        return points;
    }

}
