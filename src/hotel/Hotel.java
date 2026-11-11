/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hotel;

import java.util.Scanner;
import java.util.InputMismatchException; // NUEVO: importar la excepción

/**
 *
 * @author arabesko
 */
public class Hotel {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int option = 0;
        boolean fileLoaded = false;
        String fileName = "None";
        int recordsLoaded = 0;

        do {
            // MAIN MENU
            System.out.println("\n========================================");
            System.out.println("        HOTEL MANAGEMENT SYSTEM");
            System.out.println("========================================");

            System.out.println("FILE STATUS: [" +
                    (fileLoaded ? "LOADED" : "NOT LOADED") + "]");

            System.out.println("Data File name: " + fileName);

            System.out.println("----------------------------------------");

            System.out.println("1. Select File");
            System.out.println("2. Add New Record");
            System.out.println("3. Sort Records");
            System.out.println("4. Search Records");
            System.out.println("5. Create Binary Tree");
            System.out.println("6. Exit");

            System.out.println("----------------------------------------");
            System.out.print("Enter your choice: ");

            // VERIFY THE OPTION
            try {
                option = scanner.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("\n----------------------------------------");
                System.out.println("[ERROR] Invalid Menu Selection");
                System.out.println("----------------------------------------");
                System.out.println("The value entered is not valid.");
                System.out.println("----------------------------------------");

                scanner.nextLine();
                continue;
            }

            switch (option) {

                case 1:
                    System.out.println("Opening file...");
                    break;

                case 2:
                    System.out.println("Showing information...");
                    break;

                case 3:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("\n----------------------------------------");
                    System.out.println("[ERROR] Invalid Menu Selection");
                    System.out.println("----------------------------------------");
                    System.out.println("The selected option is not available.");
                    System.out.println("Please select an option between 1 and 6.");
                    System.out.println("----------------------------------------");
                    break;
            }

        } while (option != 6);

        scanner.close();
    }
}