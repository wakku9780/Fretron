package chessboardpaths; // Declare that this class is part of the chessboardpaths package

import java.util.ArrayList; // Import ArrayList class
import java.util.Arrays; // Import Arrays class for filling
import java.util.List; // Import List interface
import java.util.Scanner; // Import Scanner class for user input

// The main class that will serve as the entry point for the application
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] board = new char[ChessboardPaths.BOARD_SIZE][ChessboardPaths.BOARD_SIZE];
        List<int[]> soldiers = new ArrayList<>();

        // Initialize the board
        for (int i = 0; i < ChessboardPaths.BOARD_SIZE; i++) {
            Arrays.fill(board[i], ChessboardPaths.EMPTY);
        }

        // Read soldier positions
        System.out.print("Enter number of soldiers: ");
        int numSoldiers = scanner.nextInt();
        for (int i = 0; i < numSoldiers; i++) {
            System.out.printf("Enter coordinates for soldier %d (row,col): ", i + 1);
            int row = scanner.nextInt() - 1; // Convert to 0-indexed
            int col = scanner.nextInt() - 1; // Convert to 0-indexed
            board[row][col] = ChessboardPaths.SOLDIER;
            soldiers.add(new int[]{row, col});
        }

        // Read castle position
        System.out.print("Enter the coordinates for your specialized castle (row,col): ");
        int castleRow = scanner.nextInt() - 1; // Convert to 0-indexed
        int castleCol = scanner.nextInt() - 1; // Convert to 0-indexed
        board[castleRow][castleCol] = ChessboardPaths.CASTLE;

        // Find all paths for the specialized castle
        List<List<String>> allPaths = ChessboardPaths.findAllPaths(board, castleRow, castleCol);

        // Print all unique paths
        System.out.println("Thanks. There are " + allPaths.size() + " unique paths for your specialized castle");
        int pathIndex = 1;
        for (List<String> path : allPaths) {
            System.out.println("Path " + pathIndex++);
            System.out.println("=======");
            for (String step : path) {
                System.out.println(step);
            }
        }
    }
}
