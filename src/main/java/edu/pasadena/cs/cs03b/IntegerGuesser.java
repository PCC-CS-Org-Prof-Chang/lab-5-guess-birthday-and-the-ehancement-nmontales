package edu.pasadena.cs.cs03b;

import java.util.Scanner;

public class IntegerGuesser {
    public static void main(String[] args) {
        // Create a Scanner object for reading input from the console
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an integer between 1 and 255:");
        // Read the chosen integer from the user
        int chosenInteger = scanner.nextInt();

        // Compute N as the ceiling of log2 of the chosen integer to determine the number of sets needed
        int N = (int)Math.ceil(Math.log(chosenInteger) / Math.log(2));

        // Initialize the array to hold N sets
        int[][] sets = new int[N][];
        // Generate each set based on the binary representation of numbers up to 255
        for (int i = 0; i < N; i++) {
            sets[i] = generateSet(i, 255); // Generate sets up to 255
        }

        int guessedNumber = 0; // This will hold the guessed number based on the user's input

        // Iterate through each set
        for (int i = 0; i < N; i++) {
            // Display the current set to the user
            System.out.println("Set " + (i + 1) + ":");
            for (int j = 0; j < sets[i].length; j++) {
                System.out.print(sets[i][j] + " ");
                // Break the line after every 10 numbers for better readability
                if (j > 0 && j % 10 == 0) System.out.println();
            }
            System.out.println(); // New line after displaying the set

            // Use the secretHelper function to automatically check if the chosen number is in the set
            boolean isInSet = secretHelper(chosenInteger, sets[i]);
            System.out.println("Checking if your chosen number appears in this set...");

            // If the chosen number is in the set, update the guessed number accordingly
            if (isInSet) {
                guessedNumber += Math.pow(2, i); // Update guessedNumber by adding 2^i
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
            System.out.println(); // New line for separation between sets
        }

        // Display the guessed number after checking all sets
        System.out.println("The program has guessed your chosen number as: " + guessedNumber);
    }

    // Generates a set of numbers based on the specified binary position and maximum value
    private static int[] generateSet(int position, int max) {
        // Initialize the set with a maximum possible size
        int[] set = new int[(max + 1) / 2];
        int index = 0; // Index to keep track of the number of elements added to the set

        // Iterate through numbers from 1 to max to determine which belong to the set
        for (int i = 1; i <= max; i++) {
            // If the number has a 1 at the specified binary position, add it to the set
            if (getBit(i, position) == 1) {
                set[index++] = i;
            }
        }

        // Trim the array to the actual number of elements added
        int[] trimmedSet = new int[index];
        for (int i = 0; i < index; i++) {
            trimmedSet[i] = set[i];
        }
        return trimmedSet;
    }

    // Determines the value of a specific bit in the binary representation of a number
    private static int getBit(int number, int position) {
        // Divide the number by 2 repeatedly to get the bit at the desired position
        for (int i = 0; i < position; i++) {
            number /= 2;
        }
        // Return the least significant bit of the resulting number
        return number % 2;
    }

    // Secret helper function to check if the chosen number is present in a given set
    private static boolean secretHelper(int chosenNumber, int[] set) {
        // Iterate through the set to find the chosen number
        for (int i = 0; i < set.length; i++) {
            // If the chosen number is found in the set, return true
            if (set[i] == chosenNumber) {
                return true;
            }
        }
        // If the chosen number is not found in the set, return false
        return false;
    }
}
