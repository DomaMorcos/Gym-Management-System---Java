/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Doma & Moatassem
 */
public class AdminRole {
    private TrainerDatabase database;

    public AdminRole(TrainerDatabase database){
        this.database = database;
    }

    public void addTrainer(Trainer trainer) throws IOException{
        database.insertRecord(trainer);
    }

    public ArrayList<Record> getListOfTrainers () {
        return database.returnAllRecords();
    }

    public void removeTrainer(String searchKey) throws IOException{
        database.deleteRecord(searchKey);
    }

    public void logout() {

    }
}
