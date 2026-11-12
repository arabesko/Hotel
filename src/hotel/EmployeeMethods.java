package hotel;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;

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
}
