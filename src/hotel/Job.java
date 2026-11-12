package hotel;

public class Job {
    private double salary;
    private String position;
    private String jobTitle;
    private String company;
    
    private Department department;
    
    public Job (double salary, String position, String jobTitle, String company, Department department){
        this.salary     = salary;
        this.position   = position;
        this.jobTitle   = jobTitle;
        this.company    = company;
        this.department = department;
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
}
