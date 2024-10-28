/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author Doma & Moatassem
 */
public class MemberDatabase extends Database {
    public MemberDatabase (String filename) {
        super(filename);
    }

    @Override
    public Record createRecordFrom(String line){
        String[] info = line.split(",");
        if (info.length == 6){
            return new Member(info[0],info[1],info[2],info[3],info[4],info[5]);
        }
        return null;
    }
    
}
