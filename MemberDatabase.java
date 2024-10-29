/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.io.FileNotFoundException;

/**
 *
 * @author Doma & Moatassem
 */
public class MemberDatabase extends Database {
    public MemberDatabase (String filename) throws FileNotFoundException {
        super(filename);
    }

    @Override
    public Member createRecordFrom(String line){
        String[] info = line.split(",");
        if (info.length == 6){
            return new Member(info[0],info[1],info[2],info[3],info[4],info[5]);
        }
        return null;
    }
    
}
