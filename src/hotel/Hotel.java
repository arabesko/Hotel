/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hotel;

import static hotel.MenuOptions.Add;
import static hotel.MenuOptions.Create_binary_tree;
import static hotel.MenuOptions.Exit;
import static hotel.MenuOptions.Search;
import static hotel.MenuOptions.Select_file;
import static hotel.MenuOptions.Sort;
import java.util.Scanner;
import java.util.InputMismatchException; // NUEVO: importar la excepción
import java.io.File;

/**
 *
 * @author arabesko
 */
public class Hotel {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int option = 0;
        MenuOptions optionSelected = null;
        boolean fileLoaded = false;
        String fileName = "None";
        int recordsLoaded = 0;
        String selectedFile;

        do {
            // MAIN MENU
            System.out.println("\n========================================");
            System.out.println("        HOTEL MANAGEMENT SYSTEM");
            System.out.println("========================================");

            System.out.println("FILE STATUS: [" +
                    (fileLoaded ? "LOADED" : "NOT LOADED") + "]");

            System.out.println("Data File Name: " + fileName);

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
            
            optionSelected = MenuOptions.values()[option -1]; //Enum option
            switch (optionSelected) {

                case Select_file:
                    //SELECT FILE
                    
                    System.out.println("\n----------------------------------------");
                    System.out.println("           SELECT DATA FILE");
                    System.out.println("----------------------------------------");
                    System.out.print("Enter the file name: ");
                    scanner.nextLine(); // Consume the newline left by nextInt() before
                    selectedFile = scanner.nextLine().trim(); //Input new string 

                    File myFile = new File(selectedFile); //Save the file in myFile

                    if (myFile.exists() && myFile.isFile()) {
                        System.out.println("\n[SUCCESS] File Found");
                        fileLoaded = true;
                        fileName = myFile.getName();
                    } else {

                        System.out.println("\n[ERROR] File Not Found");
                        System.out.println("Please check the file name and try again.");
                    }
                    break;

                case Add:
                    //ADD NEW RECORDS
                    break;

                case Sort:
                    //SORT DE FILE
                    break;
                case Search:
                    //SEARCH SOMETHING IN THE FILE
                case Create_binary_tree:
                    //CREATING A BINARY TREE
                case Exit:
                    //EXIT OF THE SOFTWARE
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