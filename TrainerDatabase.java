/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author Doma & Moatassem
 */
public class TrainerDatabase extends Database {
    public TrainerDatabase(String filename){
        super(filename);
    }

    @Override
    public Trainer createRecordFrom(String line){
        String[] info = line.split(",");
        if (info.length == 5){
            return new Trainer(info[0],info[1],info[2],info[3],info[4]);
        }
        return null;
    }

}
