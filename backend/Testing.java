/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author Doma & Moatassem
 */
public class Testing {

    public static void main(String[] Args) throws FileNotFoundException, IOException {
        AdminRole admin = new AdminRole();
        admin.addTrainer("T001", "Ahmed", "hamada@gmail.com", "Crossfit", "0123456123");
        admin.addTrainer("T001", "Ahmed", "hamada@gmail.com", "Crossfit", "0123456123");
        admin.addTrainer("T002", "Yasser", "yasser12@gmail.com", "Boxing", "0123487123");
        admin.removeTrainer("T001");
        admin.logout();

        TrainerRole trainer = new TrainerRole();
        trainer.addMember("M001", "Abdelrahman", "Monthly", "boody@gmail.com", "0123654789", "active");
        trainer.addMember("M001", "Abdelrahman", "Monthly", "boody@gmail.com", "0123654789", "active");
        trainer.addClass("C001", "Crossfit", "T001", 60, 1);
        trainer.addClass("C001", "Crossfit", "T001", 60, 1);
        trainer.addMember("M004", "Emad", "Monthly", "Omda@hotmail.com", "01012548712", "Active");
        trainer.registerMemberForClass("M001", "C002", LocalDate.now());
        trainer.registerMemberForClass("M002", "C001", LocalDate.now());
        trainer.addMember("M002", "Ziad", "Monthly", "Ziad@yahoo.com", "01151289843", "Active");
        trainer.cancelRegistration("M002", "C002");

        trainer.logout();

    }
}
