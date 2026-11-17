package hotel.structure;

import hotel.structure.Department;

public class Job {
    private double salary;
    private String position;
    private String jobTitle;
    private String company;
    private int priority;
    
    private Department department;
    
    public Job (double salary, String position, String jobTitle, String company, Department department, int priority){
        this.salary     = salary;
        this.position   = position;
        this.jobTitle   = jobTitle;
        this.company    = company;
        this.department = department;
        this.priority  = priority;
    }
    
    public double getSalary(){
        return salary;
    }
    
    public String getPosition(){
        return position;
    }
    
    public String getJobTitle(){
        return jobTitle;
    }
    
    public String getCompany(){
        return company;
    }
    
    public Department getDepartment(){
        return department;
    }
    
    public int getPriority(){
        return priority;
    }
}
