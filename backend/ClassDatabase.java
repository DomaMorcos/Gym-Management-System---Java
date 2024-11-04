/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.io.FileNotFoundException;

/**
 *
 * @author Doma & Moatassem
 */
public class ClassDatabase extends Database {
    public ClassDatabase (String filename) throws FileNotFoundException{
        super(filename);
    }

    @Override
    public Class createRecordFrom(String line){
        String[] info = line.split(",");
        if (info.length == 5){
            return new Class(info[0],info[1],info[2],Integer.parseInt(info[3]),Integer.parseInt(info[4]));
        }
        return null;
    }
}
