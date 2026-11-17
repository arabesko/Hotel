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
        this.root = buildTree(availableEmployees, 1, 7);
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
        
        if (selectedEmployee == null) return null;//We didn't found a employee with the priority

        availableEmployees.remove(selectedEmployee); //We remove the employee of the available list

        TreeNode node = new TreeNode(selectedEmployee);

        node.setLeft(buildTree(availableEmployees, level + 1, maxLevel));
        node.setRight(buildTree(availableEmployees, level + 1, maxLevel));

        return node;
    }
    
    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(TreeNode node) {
        if (node == null) return 0;

        return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
    }
    
    public int height() {
        return height(root);
    }

    private int height(TreeNode node) {
        if (node == null) return 0;

        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }
}