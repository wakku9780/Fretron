package appledistributor;

import java.util.ArrayList; // Import ArrayList class
import java.util.Collections; // Import Collections class for sorting
import java.util.List; // Import List interface
import java.util.Scanner; // Import Scanner class for user input

public class AppleDistributor {

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
        distributeApples(appleWeights);
    }

    // Method to distribute apples among Ram, Sham, and Rahim
    public static void distributeApples(List<Integer> appleWeights) {
        // Sort the apples by weight in descending order
        Collections.sort(appleWeights, Collections.reverseOrder());

        // Calculate the total weight of all apples
        int totalWeight = appleWeights.stream().mapToInt(Integer::intValue).sum();

        // Calculate the proportionate weights to be allocated
        double ramShare = 0.5 * totalWeight; // Ram's share (50%)
        double shamShare = 0.3 * totalWeight; // Sham's share (30%)
        double rahimShare = 0.2 * totalWeight; // Rahim's share (20%)

        // Lists to store the apples allocated to each person
        List<Integer> ram = new ArrayList<>();
        List<Integer> sham = new ArrayList<>();
        List<Integer> rahim = new ArrayList<>();

        // Variables to keep track of the current total weight for each person
        double ramCurrent = 0, shamCurrent = 0, rahimCurrent = 0;

        // Loop to distribute apples based on the calculated shares
        for (int weight : appleWeights) {
            // Allocate apple to Ram if his current proportion is the smallest
            if (ramCurrent / ramShare <= shamCurrent / shamShare && ramCurrent / ramShare <= rahimCurrent / rahimShare) {
                ram.add(weight); // Add weight to Ram's list
                ramCurrent += weight; // Update Ram's current total weight
            }
            // Allocate apple to Sham if his current proportion is the smallest
            else if (shamCurrent / shamShare <= ramCurrent / ramShare && shamCurrent / shamShare <= rahimCurrent / rahimShare) {
                sham.add(weight); // Add weight to Sham's list
                shamCurrent += weight; // Update Sham's current total weight
            }
            // Allocate apple to Rahim if his current proportion is the smallest
            else {
                rahim.add(weight); // Add weight to Rahim's list
                rahimCurrent += weight; // Update Rahim's current total weight
            }
        }

        // Print the distribution result
        System.out.println("Distribution Result:");
        System.out.print("Ram: ");
        printList(ram); // Print Ram's apples
        System.out.print("Sham: ");
        printList(sham); // Print Sham's apples
        System.out.print("Rahim: ");
        printList(rahim); // Print Rahim's apples
    }

    // Helper method to print the list of apples
    public static void printList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) { // Loop through the list
            System.out.print(list.get(i)); // Print the current apple weight
            if (i < list.size() - 1) { // Print comma if not the last element
                System.out.print(", ");
            }
        }
        System.out.println(); // Print newline at the end
    }
}
