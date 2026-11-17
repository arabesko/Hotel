/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.structure;

import hotel.inheritance.Employee;
import java.util.ArrayList;

public class BinaryTree {

    private TreeNode root;

    //Contructor
    public BinaryTree(ArrayList<Employee> availableEmployees) {
        this.root = null;
    }
    
    private TreeNode buildTree(ArrayList<Employee> availableEmployees, int level, int maxLevel) {
        if (level > maxLevel) return null;
        
        Employee selectedEmployee = null;

        for (Employee employee : availableEmployees) {

            if (employee.getJob().getPriority() == level) {
                selectedEmployee = employee;
                break;
            }

        }
        
        return null;
    }
}