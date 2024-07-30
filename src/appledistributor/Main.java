package appledistributor; // Declare that this class is part of the appledistributor package

import java.util.ArrayList; // Import ArrayList class
import java.util.List; // Import List interface
import java.util.Scanner; // Import Scanner class for user input

// The main class that will serve as the entry point for the application
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input
        List<Integer> appleWeights = new ArrayList<>(); // Create a list to store apple weights

        // Loop to get apple weights from the user
        while (true) {
            System.out.print("Enter apple weight in grams (-1 to stop): ");
            int weight = scanner.nextInt(); // Read next integer input
            if (weight == -1) { // Break the loop if the input is -1
                break;
            }
            appleWeights.add(weight); // Add the weight to the list
        }

        // Call the method to distribute apples
        AppleDistributor.distributeApples(appleWeights); // Use the AppleDistributor class to distribute apples
    }
}
