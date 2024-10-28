/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author Doma & Moatassem
 */
public class MemberClassRegistrationDatabase extends Database {
    public RegistrationDatabase(String filename) {
        super(filename);
    }

    public Record createRecordFrom(String line){
        String[] info = line.split(",");
        if (info.length == 4){
            return new MemberClassRegistration(info[0],info[1],info[2],(info[3]));
        }
        return null;
    }
}
