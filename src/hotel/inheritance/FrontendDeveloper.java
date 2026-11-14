/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package hotel.inheritance;

import hotel.structure.Job;

public class FrontendDeveloper extends TechnicalStaff {

    public FrontendDeveloper(String firstName, String lastName, String gender, String email, Job job) {

        super(firstName, lastName, gender, email, job);
    }

    public void developFrontend() {
        System.out.println("Developing user interfaces.");
    }
}
