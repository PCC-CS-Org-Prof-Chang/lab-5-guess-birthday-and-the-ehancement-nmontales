package edu.pasadena.cs.cs03b;

import java.util.Scanner;

public class BinaryGuessing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an integer between 1 and 255:");
        int chosenInteger = scanner.nextInt();

        // Compute N as the ceiling of log2 of the chosen integer
        int N = (int)Math.ceil(Math.log(chosenInteger) / Math.log(2));

        // Array of arrays to hold the N sets
        int[][] sets = new int[N][];
        for (int i = 0; i < N; i++) {
            sets[i] = generateSet(i, 255); // Generate sets up to 255
        }

        int guessedNumber = 0;

        // Iterate through each set and display it
        for (int i = 0; i < N; i++) {
            System.out.println("Set " + (i + 1) + ":");
            for (int j = 0; j < sets[i].length; j++) {
                System.out.print(sets[i][j] + " ");
                if (j > 0 && j % 10 == 0) System.out.println(); // New line for readability
            }
            System.out.println(); // New line after each set

            boolean isInSet = secretHelper(chosenInteger, sets[i]);
            System.out.println("Checking if your chosen number appears in this set...");

            // Automatic response based on the secret helper function
            if (isInSet) {
                guessedNumber += Math.pow(2, i);
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
            System.out.println(); // New line for separation between sets
        }

        System.out.println("The program has guessed your chosen number as: " + guessedNumber);
    }

    // Function to generate a set based on the binary digit's position
    private static int[] generateSet(int position, int max) {
        int[] set = new int[(max + 1) / 2]; // Maximum size of the set
        int index = 0;

        for (int i = 1; i <= max; i++) {
            if (getBit(i, position) == 1) {
                set[index++] = i;
            }
        }

        // Trim the array to the correct size
        int[] trimmedSet = new int[index];
        for (int i = 0; i < index; i++) {
            trimmedSet[i] = set[i];
        }
        return trimmedSet;
    }

    // Function to manually get a specific bit of a binary number
    private static int getBit(int number, int position) {
        for (int i = 0; i < position; i++) {
            number /= 2;
        }
        return number % 2;
    }

    // "Secret" helper function to check if the chosen number is in the set
    private static boolean secretHelper(int chosenNumber, int[] set) {
        for (int i = 0; i < set.length; i++) {
            if (set[i] == chosenNumber) {
                return true; // The chosen number is in the set
            }
        }
        return false; // The chosen number is not in the set
    }
}
