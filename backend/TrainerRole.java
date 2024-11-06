/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import constants.FileNames;

/**
 *
 * @author Doma & Moatassem
 */
public class TrainerRole {
    
    private MemberDatabase memberDatabase;
    private ClassDatabase classDatabase;
    private MemberClassRegistrationDatabase registrationDatabase;
    
    public TrainerRole() throws FileNotFoundException {
        this.memberDatabase = new MemberDatabase(FileNames.MEMBER_FILENAME);
        this.classDatabase = new ClassDatabase(FileNames.CLASS_FILENAME);
        this.registrationDatabase = new MemberClassRegistrationDatabase(FileNames.REGISTRATION_FILENAME);
    }
    
    public void addMember(String memberID, String name, String membershipType, String email, String phonenumber, String status) throws IOException {
        Member member = new Member(memberID, name, membershipType, email, phonenumber, status);
        memberDatabase.insertRecord(member);
    }
    
    public ArrayList<Member> getListOfMembers() {
        ArrayList<Member> records = new ArrayList();
        for (Record record : memberDatabase.returnAllRecords()) {
            records.add((Member) record);
        }
        return records;
    }
    
    public void addClass(String classID, String className, String trainerID, int duration, int maxParticipants) throws IOException {
        Class newClass = new Class(classID, className, trainerID, duration, maxParticipants);
        classDatabase.insertRecord(newClass);
    }
    
    public ArrayList<Class> getListOfClasses() {
        ArrayList<Class> records = new ArrayList();
        for (Record record : classDatabase.returnAllRecords()) {
            records.add((Class) record);
        }
        return records;
    }
    
    public boolean registerMemberForClass(String memberID, String classID, LocalDate registrationDate) throws IOException {
        if (!classDatabase.contains(classID)) {
            return false;
        }
        if(registrationDatabase.contains(memberID+classID)) return false;
        Class record = (Class) classDatabase.getRecord(classID);
        int recordAvailableSeats = record.getAvailableSeats();
        if (recordAvailableSeats >= 1) {
            MemberClassRegistration memberClassRegistration = new MemberClassRegistration(memberID, classID, registrationDate);
            memberClassRegistration.setRegistrationStatus("Active");
            registrationDatabase.insertRecord(memberClassRegistration);
            record.setAvailableSeats(recordAvailableSeats - 1);
            registrationDatabase.saveToFile();
            return true;
        }
        return false;
    }
    
    public boolean cancelRegistration(String memberID, String classID) throws IOException {
        if (registrationDatabase.contains(memberID + classID)) {
            MemberClassRegistration memberClass = (MemberClassRegistration) registrationDatabase.getRecord(memberID + classID);
            
            LocalDate dateRightNow = LocalDate.now();
            
            long daysBetween = ChronoUnit.DAYS.between(memberClass.getRegistrationDate(), dateRightNow);
            System.out.println("Days Between : " + daysBetween);
            if ((  dateRightNow.isEqual(memberClass.getRegistrationDate())) || (dateRightNow.isAfter(memberClass.getRegistrationDate()) && daysBetween < 3) || dateRightNow.isBefore(memberClass.getRegistrationDate())) {
                System.out.println("Issuing Refund.");
                memberClass.setRegistrationStatus("canceled");
                Class class1 = (Class) classDatabase.getRecord(classID);
                class1.setAvailableSeats(class1.getAvailableSeats() + 1);
               registrationDatabase.deleteRecord(memberClass.getSearchKey());
                return true;
            } else {
                System.out.println("Refund is not available because 3 or more days has passed.");
                return false;
            }
        } else {
            return false;
        }
    }
    
    public ArrayList<MemberClassRegistration> getListOfRegistrations() {
        ArrayList<MemberClassRegistration> records = new ArrayList();
        for (Record record : registrationDatabase.returnAllRecords()) {
            records.add((MemberClassRegistration) record);
        }
        return records;
    }
    
    public void logout() throws IOException {
        registrationDatabase.saveToFile();
        classDatabase.saveToFile();
        memberDatabase.saveToFile();
        
    }
}
