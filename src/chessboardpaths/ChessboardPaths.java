package chessboardpaths;

import java.util.*;

public class ChessboardPaths {

    static final int BOARD_SIZE = 8; // Define the size of the chessboard
    static final char EMPTY = '.'; // Represent an empty cell
    static final char SOLDIER = 'S'; // Represent a soldier
    static final char CASTLE = 'C'; // Represent the specialized castle

    // Directions for the castle movement
    static final int[] ROW_DIR = {0, -1}; // Moving up (row - 1)
    static final int[] COL_DIR = {1, 0}; // Moving right (col + 1)
    static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Right, Down, Left, Up

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
        List<int[]> soldiers = new ArrayList<>();

        // Initialize the board
        for (int i = 0; i < BOARD_SIZE; i++) {
            Arrays.fill(board[i], EMPTY);
        }

        // Read soldier positions
        System.out.print("Enter number of soldiers: ");
        int numSoldiers = scanner.nextInt();
        for (int i = 0; i < numSoldiers; i++) {
            System.out.printf("Enter coordinates for soldier %d (row,col): ", i + 1);
            int row = scanner.nextInt() - 1; // Convert to 0-indexed
            int col = scanner.nextInt() - 1; // Convert to 0-indexed
            board[row][col] = SOLDIER;
            soldiers.add(new int[]{row, col});
        }

        // Read castle position
        System.out.print("Enter the coordinates for your specialized castle (row,col): ");
        int castleRow = scanner.nextInt() - 1; // Convert to 0-indexed
        int castleCol = scanner.nextInt() - 1; // Convert to 0-indexed
        board[castleRow][castleCol] = CASTLE;

        // Find all paths for the specialized castle
        List<List<String>> allPaths = findAllPaths(board, castleRow, castleCol);

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

    // Method to find all possible paths for the castle
    public static List<List<String>> findAllPaths(char[][] board, int startRow, int startCol) {
        List<List<String>> allPaths = new ArrayList<>();
        findPaths(board, startRow, startCol, startRow, startCol, new ArrayList<>(), allPaths);
        return allPaths;
    }

    // Recursive method to explore all possible paths
    private static void findPaths(char[][] board, int row, int col, int startRow, int startCol,
                                  List<String> currentPath, List<List<String>> allPaths) {
        boolean foundPath = false;

        // Try to move in all 4 directions (Right, Down, Left, Up)
        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            // Check if the move is within bounds
            if (isValid(newRow, newCol) && board[newRow][newCol] != CASTLE) {
                // If it's a soldier, "kill" it and move
                if (board[newRow][newCol] == SOLDIER) {
                    // Mark the cell as empty
                    board[newRow][newCol] = EMPTY;
                    // Record the move
                    currentPath.add(String.format("Kill (%d,%d). Turn Left", newRow + 1, newCol + 1));
                    // Move to the new cell
                    findPaths(board, newRow, newCol, startRow, startCol, currentPath, allPaths);
                    // Backtrack
                    currentPath.remove(currentPath.size() - 1);
                    // Restore the cell
                    board[newRow][newCol] = SOLDIER;
                    foundPath = true;
                }
                // If it's an empty cell, move there if valid
                else if (board[newRow][newCol] == EMPTY) {
                    // Record the move
                    currentPath.add(String.format("Jump (%d,%d)", newRow + 1, newCol + 1));
                    // Move to the new cell
                    findPaths(board, newRow, newCol, startRow, startCol, currentPath, allPaths);
                    // Backtrack
                    currentPath.remove(currentPath.size() - 1);
                    foundPath = true;
                }
            }
        }

        // If no valid move was found, check if we've returned to the start position
        if (!foundPath && row == startRow && col == startCol) {
            // Record the arrival
            currentPath.add(String.format("Arrive (%d,%d)", startRow + 1, startCol + 1));
            // Add the path to the list of all paths
            allPaths.add(new ArrayList<>(currentPath));
            currentPath.remove(currentPath.size() - 1); // Remove the arrival for other potential paths
        }
    }

    // Method to check if a cell is within bounds
    private static boolean isValid(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }
}
