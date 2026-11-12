package hotel;

public class Employee {
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    
    private Job job;
    
    public Employee (String firstName, String lastName, String gender, String email, Job job){
        this.firstName = firstName;
        this.lastName  = lastName;
        this.gender    = gender;
        this.email     = email;
        this.job       = job;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getGender(){
        return gender;
    }
    
    public Job getJob(){
        return job;
    }
}
