/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Doma & Moatassem
 */
public class MemberClassRegistrationDatabase extends Database {

    public MemberClassRegistrationDatabase(String filename) {
        super(filename);
    }
    
    @Override
    public MemberClassRegistration createRecordFrom(String line) {
        String[] info = line.split(",");
        if (info.length == 4) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(info[3], formatter);
            return new MemberClassRegistration(info[0], info[1], info[2],date);
        }
        return null;
    }
}
