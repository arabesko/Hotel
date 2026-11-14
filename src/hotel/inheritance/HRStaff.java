/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.inheritance;

import hotel.structure.Job;

public class HRStaff extends OfficeStaff {

    public HRStaff(String firstName, String lastName, String gender, String email, Job job) {

        super(firstName, lastName, gender, email, job);
    }

    public void manageEmployeeRecords() {
        System.out.println("Managing employee records.");
    }
}
