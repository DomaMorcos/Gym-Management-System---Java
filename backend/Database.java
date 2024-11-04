/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Doma & Moatassem
 */
public abstract class Database {

    private ArrayList<Record> records;
    private String filename;

    public Database(String filename) throws FileNotFoundException {
        this.records = new ArrayList<>();
        this.filename = filename;
        readFromFile();

    }

    public void readFromFile() throws FileNotFoundException {
        File file = new File(filename);
        if (file.length() == 0) {
            System.out.println("File is empty");
            return;
        }
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Record record = createRecordFrom(line);
            if (record != null) {
                records.add(record);
            }

        }
        scan.close();
    }

    public abstract Record createRecordFrom(String line);

    public ArrayList<Record> returnAllRecords() {
        return records;
    }

    public boolean contains(String searchKey) {
        for (Record record : records) {
            if (searchKey.equals(record.getSearchKey())) {
                return true;
            }
        }
        return false;
    }

    public Record getRecord(String searchKey) {
        for (Record record : records) {
            if (searchKey.equals(record.getSearchKey())) {
                return record;
            }
        }
        return null;
    }

    public void insertRecord(Record record) throws IOException {
        if (this.contains(record.getSearchKey())) {
            return;
        }
        records.add(record);
        this.saveToFile();
    }

    public void deleteRecord(String searchKey) throws IOException {
        records.removeIf(record -> record.getSearchKey().equals(searchKey));
        this.saveToFile();
    }

    public void saveToFile() throws IOException {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, false))) {
            for (Record record : records) {
                writer.println(record.lineRepresentation());
            }
            writer.flush();
            writer.close();
        }

    }
}
