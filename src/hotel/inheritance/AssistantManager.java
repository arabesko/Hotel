/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.inheritance;

import hotel.structure.Job;

public class AssistantManager extends Management {

    public AssistantManager(String firstName, String lastName, String gender, String email, Job job) {

        super(firstName, lastName, gender, email, job);
    }
    
    public void assistManager() {
        System.out.println("Assisting the manager.");
    }   
}