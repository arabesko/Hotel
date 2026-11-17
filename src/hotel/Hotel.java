/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hotel;


import hotel.inheritance.Employee;
import hotel.enums.MenuOptions;
import static hotel.enums.MenuOptions.Create_binary_tree;
import java.util.Scanner;
import java.util.InputMismatchException; // NUEVO: importar la excepción
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import hotel.methods.EmployeeMethods;
import hotel.structure.BinaryTree;

public class Hotel {

    private static ArrayList<Employee> employeesList = new ArrayList<>();
    
    public static void main(String[] args) {

        Scanner myScan = new Scanner(System.in);
        EmployeeMethods myMethods = new EmployeeMethods();

        int option = 0;
        MenuOptions optionSelected = null; //Enum
        boolean fileLoaded = false;
        String fileName = "None";
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
            System.out.println("3. Sort Records by Name");
            System.out.println("4. Search Records");
            System.out.println("5. Create Binary Tree");
            System.out.println("6. List Employess");
            System.out.println("7. Exit");

            System.out.println("----------------------------------------");
            System.out.print("Enter your choice: ");

            // VERIFY THE OPTION
            try {
                option = myScan.nextInt();
                myScan.nextLine();

            } catch (InputMismatchException e) {
                System.out.println("\n----------------------------------------");
                System.out.println("[ERROR] Invalid Menu Selection");
                System.out.println("----------------------------------------");
                System.out.println("The value entered is not valid");
                System.out.println("----------------------------------------");

                myScan.nextLine();
                continue;
            }
            
            // Validate menu range for enum
            if (option < 1 || option > MenuOptions.values().length) {
                System.out.println("[ERROR] Invalid menu selection.");
                System.out.println("Please select an option between 1 and 7.");
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
                    selectedFile = myScan.nextLine().trim(); //Input new string 

                    File myFile = new File(selectedFile); //Save the file in myFile

                    //Im verifying if the file exist and if is a file (no a directory)
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
                        System.out.println("Please check the file name and try again");
                    }
                    break;

                case Add:
                    //ADD NEW RECORDS
                    
                    //The file was selected before
                    if (!fileLoaded) {
                        System.out.println("[WARNING] Please select a file first");
                        break;
                    }

                    myMethods.addEmployee(employeesList, myScan);
                    System.out.println("[INFO] Add new employee");
                    
                    break;

                case Sort:
                    //SORT FILE
                    
                    //The file was selected before
                    if (!fileLoaded) {
                        System.out.println("[WARNING] Please select a file first");
                        break;
                    }
                    
                    myMethods.insertionSort(employeesList, 1);
                    
                    myMethods.listEmployees(employeesList);
                    
                    System.out.println("\n----------------------------------------");
                    System.out.println("[SUCCESS] Employees Sorted Successfully");
                    System.out.println("----------------------------------------");
                    System.out.println("Records sorted: " + employeesList.size());
                    
                    break;
                case Search:
                    //SEARCH A NAME IN THE FILE
                    
                    //The file was selected before
                    if (!fileLoaded) {
                        System.out.println("[WARNING] Please select a file first");
                        break;
                    }
                    
                    System.out.println("\n----------------------------------------");
                    System.out.println("           SEARCH EMPLOYEE");
                    System.out.println("----------------------------------------");

                    String searchName;

                    do {
                        System.out.print("Enter employee first name: ");
                        searchName = myScan.nextLine().trim();

                        if (!searchName.matches("[a-zA-ZÀ-ÿ' -]+")) {
                            System.out.println("[ERROR] Invalid name. Please enter a valid name");
                        } else {
                          break;
                        }

                    } while (true);

                    //Temporary list sorted
                    ArrayList<Employee> sortedEmployees = new ArrayList<>(employeesList);
                    myMethods.insertionSort(sortedEmployees, 1);
                    
                    int foundIndex = myMethods.binarySearch(sortedEmployees, searchName, 0, sortedEmployees.size() - 1);
                    
                    if (foundIndex == -1) {
                        System.out.println("[WARNING] Employee not found.");
                    } else {
                        Employee foundEmployee = sortedEmployees.get(foundIndex);

                        System.out.println("\n[SUCCESS] Employee found.");
                        System.out.println("First Name  : " + foundEmployee.getFirstName());
                        System.out.println("Last Name   : " + foundEmployee.getLastName());
                        System.out.println("Job Title   : " + foundEmployee.getJob().getJobTitle());
                        System.out.printf("Salary      : %.2f%n", foundEmployee.getJob().getSalary());
                        System.out.println("Department  : " + foundEmployee.getDepartment());
                    }
                    
                    break;
                    
                    
                case Create_binary_tree:
                    //CREATING A BINARY TREE
                    
                    //The file was selected before
                    if (!fileLoaded) {
                        System.out.println("[WARNING] Please select a file first");
                        break;
                    }
                    
                    ArrayList<Employee> availableEmployees = new ArrayList<>(employeesList);
                    BinaryTree binaryTree = new BinaryTree(availableEmployees);
                    
                    System.out.println("[INFO] Total nodes created: " + binaryTree.countNodes());
                    System.out.println("[INFO] Tree height: " + binaryTree.height());
                    
                    System.out.println("\n--- BINARY TREE LEVEL-ORDER TRAVERSAL ---");
                    binaryTree.levelOrderTraversal();
                    
                    break;
                    
                case List_employees:
                    //LIST ALL EMPLOYEES
                    
                    //The file was selected before
                    if (!fileLoaded) {
                        System.out.println("[WARNING] Please select a file first");
                        break;
                    }
                    myMethods.listEmployees(employeesList);
                    break;

                case Exit:
                    //EXIT OF THE SOFTWARE
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

        } while (option != 7);

        myScan.close();
    }
}