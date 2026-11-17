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
        this.root = buildTree(availableEmployees, 1, 7); //First node
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
    
    public void levelOrderTraversal() {
        if (root == null) {
            System.out.println("[INFO] Binary Tree is empty.");
            return;
        }

        // Table header
        System.out.printf(
            "%-5s %-18s %-38s %-20s %-8s%n",
            "No.", "NAME", "JOB TITLE", "DEPARTMENT", "PRIORITY"
        );

        System.out.println("-".repeat(85));

        // First element
        ArrayList<TreeNode> queue = new ArrayList<>();
        queue.add(root);

        int index = 1;

        // While the list is not empty
        while (!queue.isEmpty()) {

            TreeNode current = queue.remove(0);

            System.out.printf(
                "%-5s %-18s %-38s %-20s %-8s%n",
                index,
                current.getEmployee().getFirstName() + " " + current.getEmployee().getLastName(),
                current.getEmployee().getJob().getJobTitle(),
                current.getEmployee().getDepartment(),
                current.getEmployee().getJob().getPriority()
            );

            index++;

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }

            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }

        System.out.println("-".repeat(85));
    }
}