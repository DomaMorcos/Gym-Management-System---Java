/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package lab4;

import java.util.ArrayList;

/**
 *
 * @author Doma & Moatassem
 */
public abstract class Database {
    private ArrayList<Record> records;
    private String filename;

    public Database(String filename){
        this.records = new ArrayList<>();
        this.filename = filename;
    }

    public void readFromFile(){

    }

    public abstract Record createRecordFrom(String line);

    public ArrayList<Record>  returnAllRecords() {
        return new ArrayList<>(records);
    }

    public boolean contains(String searchKey){
        for (Record record:records){
            if (searchKey.equals(record.getSearchKey()))
                return true;
        }
        return false;
    }

    public Record getRecord(String searchKey) {
        for (Record record : records) {
            if (searchKey.equals(record.getSearchKey()))
                return record;
        }
        return null;
    }

    public void insertRecord(Record record){
        //if (record.getSearchKey())
        records.add(record);
        saveToFile();
    }

    public void deleteRecord(String searchKey){
        records.removeIf(record -> record.getSearchKey().equals(searchKey));
        saveToFile();
    }

    public void saveToFile(){

    }
}
