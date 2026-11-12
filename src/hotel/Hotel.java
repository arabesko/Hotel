/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hotel;


import java.util.Scanner;
import java.util.InputMismatchException; // NUEVO: importar la excepción
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Hotel {

    private static ArrayList<Employee> employeesList = new ArrayList<>();
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int option = 0;
        MenuOptions optionSelected = null; //Enum
        boolean fileLoaded = false;
        String fileName = "None";
        String selectedFile;
        
        EmployeeMethods myMethods = new EmployeeMethods();
        
        
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

                    //Im verifying if the file exist and if is a file (no directory)
                    if (myFile.exists() && myFile.isFile()) {
                        System.out.println("\n[SUCCESS] File Found");
                        
                        //We are trying to open the file
                        try (BufferedReader reader = new BufferedReader(new FileReader(myFile))) {
                            //Is possible to open
                            fileLoaded = true;
                            fileName = myFile.getName();

                            System.out.println("\n----------------------------------------");
                            System.out.println("[SUCCESS] File Opened Successfully");
                            System.out.println("----------------------------------------");
                            System.out.println("File: " + fileName);
                            System.out.println("----------------------------------------");
                            
                            myMethods.loadEmployees(reader, employeesList);

                        } catch (IOException e) {
                            //Is  not possible to open or close
                            System.out.println("\n----------------------------------------");
                            System.out.println("[ERROR] Unable to Open File");
                            System.out.println("----------------------------------------");
                            System.out.println(e.getMessage());
                            System.out.println("----------------------------------------");
                        }
                        
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