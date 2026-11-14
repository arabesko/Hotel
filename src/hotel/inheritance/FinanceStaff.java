/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.inheritance;

import hotel.structure.Job;

public class FinanceStaff extends OfficeStaff {

    public FinanceStaff(String firstName, String lastName, String gender, String email, Job job) {

        super(firstName, lastName, gender, email, job);
    }

    public void prepareFinancialReport() {
        System.out.println("Preparing financial reports.");
    }
}
