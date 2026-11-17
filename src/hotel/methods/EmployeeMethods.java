package hotel.methods;
import hotel.structure.Department;
import hotel.inheritance.Employee;
import hotel.inheritance.*;
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

            while ((lineRecord = reader.readLine()) != null) { //First record is different to null
                if (lineRecord.trim().isEmpty()) continue; //Record empty
                
                String[] fields = lineRecord.split(",", -1); //Line of the file
                
                //Record without with empty field
                if (fields.length != 9) {
                    System.out.println("[WARNING] Invalid record. Expected 9 fields.");
                    continue;
                }
                
                //Standar name of department
                String departmentName = fields[5].trim().replace(" ", "_");
                Department department = new Department(departmentName);
                
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
                
                //Standar name of job title
                String jobTitle = fields[7].trim();
                jobTitle = jobTitle.replace(" ", "_").replace("-", "_");
                
                
                int priority = 0;
                //We check de enum to get the priority with the name of the file
                for (JobTitleOptions option : JobTitleOptions.values()) {
                    if (option.name().equalsIgnoreCase(jobTitle)) {//enum name = jobtitle field
                        jobTitle = option.name();
                        priority = option.getPriority();
                        break;
                    }
                }
                
                //Standar name of job position
                String position = fields[6].trim().replace(" ", "_");
                
                //Clase jobe
                Job job = new Job(salary, position, jobTitle, fields[8].trim(), department, priority);
                Employee employee = createEmployee(fields[0].trim(), fields[1].trim(), fields[2].trim(), fields[3].trim(), job);
                
                employeesList.add(employee);
            }
            System.out.println("[SUCCESS] Employees loaded: " + employeesList.size());

        } catch (IOException e) {
            System.out.println("[ERROR] Could not read the file.");
        }
    }
    
    private Employee createEmployee(String firstName, String lastName, String gender, String email, Job job) {

        String jobTitle = job.getJobTitle();

        //Group jobs
        switch (jobTitle.toLowerCase().replace("_", " ").replace("-", " ")) {

            //Manager Group
            case "manager":
            case "senior manager":
            case "head manager":
                return new Manager(firstName, lastName, gender, email, job);

            //Assistant Manager Group
            case "assistant manager":
                return new AssistantManager(firstName, lastName, gender, email, job);

            //Team Leader Group
            case "team lead":
                return new TeamLeader(firstName, lastName, gender, email, job);

            //Backend Developer Group
            case "backend":
            case "backend developer":
            case "full stack":
            case "full stack developer":
            case "ai developer":
            case "devops":
                return new BackendDeveloper(firstName, lastName, gender, email, job);

            //Frontend Developer Group
            case "frontend developer":
            case "mobile developer":
                return new FrontendDeveloper(firstName, lastName, gender, email, job);

            //QA Specialist Group
            case "qa":
                return new QASpecialist(firstName, lastName, gender, email, job);

            //Finance Staff Group
            case "finance analyst":
            case "junior bookkeeper":
            case "bookkeeper":
                return new FinanceStaff(firstName, lastName, gender, email, job);

            //HR Staff Group
            case "hr specialist":
            case "hr analyst":
            case "client relations coordinator":
            case "client relations specialist":
                return new HRStaff(firstName, lastName, gender, email, job);

            //Marketing Staff Group
            case "marketing specialist":
            case "junior clerk":
            case "sales clerk":
            case "support clerk":
            case "clerk":
            case "white collar worker":
            case "office worker":
            case "desk jockey":
                return new MarketingStaff(firstName, lastName, gender, email, job);    

            default:
                return new Employee(firstName, lastName, gender, email, job);
        }
    }
    
    public void addEmployee(ArrayList<Employee> employessList, Scanner myScan){
        System.out.println("\n--- ADD NEW EMPLOYEE ---");
        
        //First name input and validation
        String firstName = "";
        while (true) {
            System.out.print("Enter first name: ");
            firstName = myScan.nextLine().trim();
            
            if (firstName.matches("[\\p{L}]+(?:[ '-][\\p{L}]+)*")) break;

            System.out.println("[ERROR] Please enter a valid name.");
        }
        
        //Last name input and validation
        String lastName = "";
        while (true) {
            System.out.print("Enter last name: ");
            lastName = myScan.nextLine().trim();

            if (lastName.matches("[\\p{L}]+(?:[ '-][\\p{L}]+)*")) break;

            System.out.println("[ERROR] Please enter a valid last name.");
        }
        
        //Gender input and validation
        String gender = "";
        while (true) {
            System.out.print("Enter gender (Male/Female/Other): ");
            gender = myScan.nextLine().trim();

            if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Other")) break;
            
            System.out.println("[ERROR] Please enter Male, Female or Other.");
        }
        
        //Email input and validation
        String email = "";
        while (true) {
            System.out.print("Enter email: ");
            email = myScan.nextLine().trim();

            if (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) break;

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
        
        //We get de index of the department inn the enum
        DepartmentOptions selectedDepartment = DepartmentOptions.values()[departmentOption - 1];
        String departmentName = selectedDepartment.name(); //We get the name in the enum

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
        
        //We get the index in de enum of the positions
        PositionOptions selectedPosition = PositionOptions.values()[positionOption - 1];
        String position = selectedPosition.name(); //Name of the position

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
        
        //We get the index enum with jobTitleOption-1
        JobTitleOptions selectedJobTitle = JobTitleOptions.values()[jobTitleOption - 1];
        String jobTitle = selectedJobTitle.name(); //We get the name enum

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
        
        //We get the index enum with CompanyOptions-1
        CompanyOptions selectedCompany = CompanyOptions.values()[companyOption - 1];
        String company = selectedCompany.name(); //We get the name of de company from de enum options

        System.out.println("[SUCCESS] Company selected: " + company);
        
        Department department = new Department(departmentName); //Class department
        int priority = selectedJobTitle.getPriority(); //We get the priority in the enum
        Job job = new Job(salary, position, jobTitle, company, department, priority); //Class job

        Employee employee = createEmployee(firstName, lastName, gender, email, job); //Class employee with the groups

        employessList.add(employee);

        System.out.println("[SUCCESS] Employee added successfully.");
        
    }
    
    public void insertionSort(ArrayList<Employee> employees, int i) {

        if (i >= employees.size()) return;

        Employee key = employees.get(i);
        int j = i - 1;

        while (j >= 0 && employees.get(j).getFirstName().compareToIgnoreCase(key.getFirstName()) > 0) {
            employees.set(j + 1, employees.get(j));
            j--;
        }

        employees.set(j + 1, key);
        insertionSort(employees, i + 1);
    }

    public int binarySearch(ArrayList<Employee> employees, String name, int left, int right) {

        //Elemento is not founded
        if (left > right) return -1;

        //Middle of the list
        int mid = (left + right) / 2;

        //Comparison with name in the middle
        int comparison = employees.get(mid).getFirstName().compareToIgnoreCase(name);

        //The name in the middle is the name that we are looking for
        if (comparison == 0) return mid;

        //Left side
        //The name in the middle is greater that the name that we are looking for, left side
        if (comparison > 0) return binarySearch(employees, name, left, mid - 1);

        //Right side
        //The name in the middle is less that the name that we are looking for, right side
        return binarySearch(employees, name, mid + 1, right);
    }

    public void listEmployees(ArrayList<Employee> employeesList) {

        System.out.println("\n--- EMPLOYEE LIST ---");

        System.out.printf(
            "%-5s %-12s %-12s %12s %-35s %-40s %-10s%n",
            "No.", "FIRST NAME", "LAST NAME", "SALARY",
            "EMAIL", "JOB TITLE", "PRIORITY"
        );

        System.out.println("-".repeat(140));

        int limit = Math.min(20, employeesList.size());

        for (int i = 0; i < limit; i++) {

            Employee employee = employeesList.get(i);

            System.out.printf(
                "%-5d %-12s %-12s %12.2f %-35s %-40s %-10d%n",
                i + 1,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJob().getSalary(),
                employee.getEmail(),
                employee.getJob().getJobTitle(),
                employee.getJob().getPriority()
            );
        }

        System.out.println("-".repeat(140));
        System.out.println("Total employees: " + employeesList.size());
    }
}
