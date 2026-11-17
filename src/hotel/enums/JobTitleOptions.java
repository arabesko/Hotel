/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package hotel.enums;

public enum JobTitleOptions {
    Manager(3),
    Assistant_Manager(4),
    Senior_Manager(2),
    Head_Manager(1),
    Team_Lead(5),

    QA(6),
    Backend(6),
    Backend_Developer(6),
    Frontend_Developer(6),
    Full_Stack(6),
    Full_Stack_Developer(6),
    Mobile_Developer(6),
    AI_Developer(6),
    DevOps(6),
    Marketing_Specialist(6),
    Finance_Analyst(6),
    HR_Specialist(6),
    HR_Analyst(6),
    Client_Relations_Coordinator(6),
    Client_Relations_Specialist(6),

    Junior_Clerk(7),
    Sales_Clerk(7),
    Support_Clerk(7),
    Clerk(7),
    Junior_Bookkeeper(7),
    Bookkeeper(6),
    White_collar_Worker(7),
    Office_Worker(7),
    Desk_Jockey(7);

    private final int priority;

    JobTitleOptions(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
