package hotel.methods;
import hotel.structure.Department;
import hotel.inheritance.Employee;
import hotel.structure.Job;
import hotel.enums.DepartmentOptions;
import hotel.enums.CompanyOptions;
import hotel.enums.PositionOptions;
import hotel.enums.JobTitleOptions;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class EmployeeMethods {
    public void loadEmployees(BufferedReader reader, ArrayList<Employee> employeesList){
        try {
            employeesList.clear(); //Clear the list before to start
            String lineRecord; //
            reader.readLine(); // Titles

            while ((lineRecord = reader.readLine()) != null) {
                if (lineRecord.trim().isEmpty()) {
                    //Record empty
                    continue;
                }
                
                String[] fields = lineRecord.split(",", -1); //Line of the file
                
                //Record without with empty field
                if (fields.length != 9) {
                    System.out.println("[WARNING] Invalid record. Expected 9 fields.");
                    continue;
                }
                
                Department department = new Department(fields[5].trim());
                
                double salary;

                try {
                    salary = Double.parseDouble(fields[4].trim());
                } catch (NumberFormatException e) {
                    System.out.println("[WARNING] Invalid salary. Record skipped.");
                    continue;
                }
                
                if (!Double.isFinite(salary) || salary < 0) {
                    System.out.println("[WARNING] Invalid salary. Record skipped.");
                    continue;
                }
                
                Job job = new Job(salary, fields[6].trim(), fields[7].trim(), fields[8].trim(), department);
                Employee employee = new Employee(fields[0].trim(), fields[1].trim(), fields[2].trim(), fields[3].trim(), job);
                
                employeesList.add(employee);
            }
            System.out.println("[SUCCESS] Employees loaded: " + employeesList.size());

        } catch (IOException e) {
            System.out.println("[ERROR] Could not read the file.");
        }
    }
    
    public void addEmployee(ArrayList<Employee> employessList, Scanner myScan){
        System.out.println("\n--- ADD NEW EMPLOYEE ---");
        
        //First name input and validation
        String myText = "";
        while (true) {
            System.out.print("Enter first name: ");
            myText = myScan.nextLine().trim();
            
            if (myText.matches("[\\p{L}]+(?:[ '-][\\p{L}]+)*")) break;

            System.out.println("[ERROR] Please enter a valid name.");
        }
        
        //Last name input and validation
        myText = "";
        while (true) {
            System.out.print("Enter last name: ");
            myText = myScan.nextLine().trim();

            if (myText.matches("[\\p{L}]+(?:[ '-][\\p{L}]+)*")) break;

            System.out.println("[ERROR] Please enter a valid last name.");
        }
        
        //Gender input and validation
        myText = "";
        while (true) {
            System.out.print("Enter gender (Male/Female/Other): ");
            myText = myScan.nextLine().trim();

            if (myText.equalsIgnoreCase("Male") || myText.equalsIgnoreCase("Female") || myText.equalsIgnoreCase("Other")) break;
            
            System.out.println("[ERROR] Please enter Male, Female or Other.");
        }
        
        //Email input and validation
        myText = "";
        while (true) {
            System.out.print("Enter email: ");
            myText = myScan.nextLine().trim();

            if (myText.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) break;

            System.out.println("[ERROR] Please enter a valid email.");
        }
        
        //Salary validatioin
        double salary;
       
        while (true) {

            System.out.print("Enter salary: ");
            String salaryInput = myScan.nextLine().trim();

            try {

                salary = Double.parseDouble(salaryInput);

                if (Double.isFinite(salary) && salary > 0) break;

                System.out.println("[ERROR] Salary must be a valid positive number more than zero.");

            } catch (NumberFormatException e) {

                System.out.println("[ERROR] Please enter a numeric salary.");
            }
        }
        
        //Department selecction
        System.out.println("\n========================================");
        System.out.println("           SELECT DEPARTMENT            ");
        System.out.println("========================================");
        System.out.println("  1. Finance");
        System.out.println("  2. IT Development");
        System.out.println("  3. Sales");
        System.out.println("  4. Human Resources (HR)");
        System.out.println("  5. Marketing");
        System.out.println("  6. Operations");
        System.out.println("  7. Customer Service");
        System.out.println("  8. Accounting");
        System.out.println("  9. IT Support");
        System.out.println(" 10. Quality Assurance");
        System.out.println("----------------------------------------");
        
        int departmentOption;

        while (true) {

            System.out.print("Select department (1-10): ");

            try {
                departmentOption = Integer.parseInt(myScan.nextLine().trim());

                if (departmentOption >= 1 && departmentOption <= 10) break;

                System.out.println("[ERROR] Please select an option between 1 and 10.");

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Please enter a valid number.");
            }
        }
        
        DepartmentOptions selectedDepartment = DepartmentOptions.values()[departmentOption - 1];
        String departmentName;

        switch (selectedDepartment) {

            case Finance:
                departmentName = "Finance";
                break;

            case IT_Development:
                departmentName = "IT Development";
                break;

            case Sales:
                departmentName = "Sales";
                break;

            case HR:
                departmentName = "HR";
                break;

            case Marketing:
                departmentName = "Marketing";
                break;

            case Operations:
                departmentName = "Operations";
                break;

            case Customer_Service:
                departmentName = "Customer Service";
                break;

            case Accounting:
                departmentName = "Accounting";
                break;

            case IT_Support:
                departmentName = "IT Support";
                break;

            case Quality_Assurance:
                departmentName = "Quality Assurance";
                break;

            default:
                departmentName = "No data";
                break;
        }

        System.out.println("[SUCCESS] Department selected: " + departmentName);
        
        //Position input
        System.out.println("========================================");
        System.out.println("            SELECT POSITION");
        System.out.println("========================================");
        System.out.println("  1. Senior");
        System.out.println("  2. Middle");
        System.out.println("  3. Junior");
        System.out.println("  4. Intern");
        System.out.println("  5. Contract");
        System.out.println("  6. Trainee");
        System.out.println("  7. Temporary");
        System.out.println("  8. Part Time");
        System.out.println("  9. Not Specified");
        System.out.println("----------------------------------------");
        
        int positionOption;

        while (true) {

            System.out.print("Select position (1-9): ");

            try {
                positionOption = Integer.parseInt(myScan.nextLine().trim());

                if (positionOption >= 1 && positionOption <= 9) break;
                System.out.println("[ERROR] Select a number between 1 and 9.");

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Please enter a valid number.");
            }
        }
        
        PositionOptions selectedPosition;
        String position;

        selectedPosition = PositionOptions.values()[positionOption - 1];

        switch (selectedPosition) {

            case Senior:
                position = "senior";
                break;

            case Middle:
                position = "middle";
                break;

            case Junior:
                position = "junior";
                break;

            case Intern:
                position = "intern";
                break;

            case Contract:
                position = "contract";
                break;

            case Trainee:
                position = "trainee";
                break;

            case Temporary:
                position = "temporary";
                break;

            case Part_Time:
                position = "part time";
                break;

            case Not_Specified:
                position = "";
                break;

            default:
                position = "No data";
                break;
        }

        System.out.println("[SUCCESS] Position selected: " + (position.isEmpty() ? "Not Specified" : position));
        
        //Job Title Input
        System.out.println("================================================================");
        System.out.println("                       SELECT JOB TITLE");
        System.out.println("================================================================");

        System.out.printf("%-34s %s%n", " 1. Manager", "16. Finance Analyst");
        System.out.printf("%-34s %s%n", " 2. Assistant Manager", "17. HR Specialist");
        System.out.printf("%-34s %s%n", " 3. Senior Manager", "18. HR Analyst");
        System.out.printf("%-34s %s%n", " 4. Head Manager", "19. Client Relations Coordinator");
        System.out.printf("%-34s %s%n", " 5. Team Lead", "20. Client Relations Specialist");
        System.out.printf("%-34s %s%n", " 6. QA", "21. Junior Clerk");
        System.out.printf("%-34s %s%n", " 7. Backend", "22. Sales Clerk");
        System.out.printf("%-34s %s%n", " 8. Backend Developer", "23. Support Clerk");
        System.out.printf("%-34s %s%n", " 9. Frontend Developer", "24. Clerk");
        System.out.printf("%-34s %s%n", "10. Full Stack", "25. Junior Bookkeeper");
        System.out.printf("%-34s %s%n", "11. Full Stack Developer", "26. Bookkeeper");
        System.out.printf("%-34s %s%n", "12. Mobile Developer", "27. White-collar Worker");
        System.out.printf("%-34s %s%n", "13. AI Developer", "28. Office Worker");
        System.out.printf("%-34s %s%n", "14. DevOps", "29. Desk Jockey");
        System.out.println("15. Marketing Specialist");

        System.out.println("================================================================");
        
        
        int jobTitleOption;
        while (true) {
            System.out.print("Select job title (1-29): ");

            try {
                jobTitleOption = Integer.parseInt(myScan.nextLine().trim());

                if (jobTitleOption >= 1 && jobTitleOption <= 29) break;
                System.out.println("[ERROR] Select a number between 1 and 29.");

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Please enter a valid number.");
            }
        }
        
        JobTitleOptions selectedJobTitle = JobTitleOptions.values()[jobTitleOption - 1];
        String jobTitle;

        switch (selectedJobTitle) {

            case Manager:
                jobTitle = "Manager";
                break;

            case Assistant_Manager:
                jobTitle = "Assistant Manager";
                break;

            case Senior_Manager:
                jobTitle = "Senior Manager";
                break;

            case Head_Manager:
                jobTitle = "Head Manager";
                break;

            case Team_Lead:
                jobTitle = "Team Lead";
                break;

            case QA:
                jobTitle = "QA";
                break;

            case Backend:
                jobTitle = "backend";
                break;

            case Backend_Developer:
                jobTitle = "Backend Developer";
                break;

            case Frontend_Developer:
                jobTitle = "Frontend Developer";
                break;

            case Full_Stack:
                jobTitle = "Full-stack";
                break;

            case Full_Stack_Developer:
                jobTitle = "Full-stack Developer";
                break;

            case Mobile_Developer:
                jobTitle = "Mobile Developer";
                break;

            case AI_Developer:
                jobTitle = "AI Developer";
                break;

            case DevOps:
                jobTitle = "DevOps";
                break;

            case Marketing_Specialist:
                jobTitle = "Marketing Specialist";
                break;

            case Finance_Analyst:
                jobTitle = "Finance Analyst";
                break;

            case HR_Specialist:
                jobTitle = "HR Specialist";
                break;

            case HR_Analyst:
                jobTitle = "HR Analyst";
                break;

            case Client_Relations_Coordinator:
                jobTitle = "Client Relations Coordinator";
                break;

            case Client_Relations_Specialist:
                jobTitle = "Client Relations Specialist";
                break;

            case Junior_Clerk:
                jobTitle = "Junior Clerk";
                break;

            case Sales_Clerk:
                jobTitle = "Sales Clerk";
                break;

            case Support_Clerk:
                jobTitle = "Support Clerk";
                break;

            case Clerk:
                jobTitle = "Clerk";
                break;

            case Junior_Bookkeeper:
                jobTitle = "Junior Bookkeeper";
                break;

            case Bookkeeper:
                jobTitle = "Bookkeeper";
                break;

            case White_Collar_Worker:
                jobTitle = "White-collar Worker";
                break;

            case Office_Worker:
                jobTitle = "Office Worker";
                break;

            case Desk_Jockey:
                jobTitle = "Desk Jockey";
                break;

            default:
                jobTitle = "No data";
                break;
        }

        System.out.println("[SUCCESS] Job title selected: " + jobTitle);
        
        //Company input
        System.out.println("============================================================");
        System.out.println("                     SELECT COMPANY");
        System.out.println("============================================================");

        System.out.printf("%-30s %s%n", " 1. TechInnovators", " 6. Netwise");
        System.out.printf("%-30s %s%n", " 2. CodeSolutions", " 7. TechWorks");
        System.out.printf("%-30s %s%n", " 3. DataVision", " 8. SysDevelopers");
        System.out.printf("%-30s %s%n", " 4. Infinisoft", " 9. InfoSphere");
        System.out.printf("%-30s %s%n", " 5. DevsUnited", "10. ByteWare");

        System.out.println("============================================================");
        
        int companyOption;

        while (true) {

            System.out.print("Select company (1-10): ");

            try {
                companyOption = Integer.parseInt(myScan.nextLine().trim());

                if (companyOption >= 1 && companyOption <= 10) break;
                System.out.println("[ERROR] Select a number between 1 and 10.");

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Please enter a valid number.");
            }
        }
        
        CompanyOptions selectedCompany = CompanyOptions.values()[companyOption - 1];
        String company;

        switch (selectedCompany) {

            case TechInnovators:
                company = "TechInnovators";
                break;

            case CodeSolutions:
                company = "CodeSolutions";
                break;

            case DataVision:
                company = "DataVision";
                break;

            case Infinisoft:
                company = "Infinisoft";
                break;

            case DevsUnited:
                company = "DevsUnited";
                break;

            case Netwise:
                company = "Netwise";
                break;

            case TechWorks:
                company = "TechWorks";
                break;

            case SysDevelopers:
                company = "SysDevelopers";
                break;

            case InfoSphere:
                company = "InfoSphere";
                break;

            case ByteWare:
                company = "ByteWare";
                break;

            default:
                company = "No data";
                break;
        }

        System.out.println("[SUCCESS] Company selected: " + company);
    }
}
