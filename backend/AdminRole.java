/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import constants.FileNames;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Doma & Moatassem
 */
public class AdminRole {

    private TrainerDatabase database;

    public AdminRole() throws FileNotFoundException {
        this.database = new TrainerDatabase(FileNames.TRAINER_FILENAME);
    }

    public void addTrainer(String trainerID, String name, String email, String speciality, String phoneNumber) throws IOException {
        Trainer trainer = new Trainer(trainerID, name, email, speciality, phoneNumber);
        database.insertRecord(trainer);
    }

    public ArrayList<Trainer> getListOfTrainers() {
        ArrayList<Trainer> records = new ArrayList();
        for (Record record : database.returnAllRecords()) {
            records.add((Trainer) record);
        }
        return records;
    }

    public void removeTrainer(String searchKey) throws IOException {
        database.deleteRecord(searchKey);
    }

    public void logout() throws IOException {
        database.saveToFile();

    }
}
