/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Doma & Moatassem
 */
public class TrainerRole {

    private MemberDatabase memberDatabase;
    private ClassDatabase classDatabase;
    private MemberClassRegistrationDatabase registrationDatabase;

    public TrainerRole(MemberDatabase memberDatabase, ClassDatabase classDatabase, MemberClassRegistrationDatabase registrationDatabase) {
        this.memberDatabase = memberDatabase;
        this.classDatabase = classDatabase;
        this.registrationDatabase = registrationDatabase;
    }
    public TrainerRole(){
    }

    public void addMember(String memberID, String name, String membershipType, String email, String phonenumber, String status) throws IOException {
        Member member = new Member(memberID, name, membershipType, email, phonenumber, status);
        memberDatabase.insertRecord(member);
    }

    public ArrayList<Record> getListOfMembers() {
        return memberDatabase.returnAllRecords();
    }

    public void addClass(String classID, String className, String trainerID, int duration, int maxParticipants) throws IOException {
        Class newClass = new Class(classID, className, trainerID, duration, maxParticipants);
        classDatabase.insertRecord(newClass);
    }

    public ArrayList<Record> getListOfClasses() {
        return classDatabase.returnAllRecords();
    }

    public boolean registerMemberForClass(String memberID, String classID, LocalDate registrationDate) throws IOException {
        Class record = (Class) classDatabase.getRecord(classID);
        int recordAvailableSeats = record.getAvailableSeats();
        if (recordAvailableSeats >= 1) {
            MemberClassRegistration memberClassRegistration = new MemberClassRegistration(memberID, classID, registrationDate);
            memberClassRegistration.setRegistrationStatus("Active");
            registrationDatabase.insertRecord(memberClassRegistration);
            record.setAvailableSeats(recordAvailableSeats - 1);
            return true;
        }
        return false;
    }

    public boolean cancelRegistration(String memberID, String classID) {
        if (registrationDatabase.contains(memberID + "|" + classID)) {
            MemberClassRegistration memberClass = (MemberClassRegistration) registrationDatabase.getRecord(memberID + "|" + classID);
            LocalDate dateRightNow = LocalDate.now();

            long daysBetween = ChronoUnit.DAYS.between(dateRightNow, memberClass.getRegistrationDate());
            if (dateRightNow.isAfter(memberClass.getRegistrationDate()) && daysBetween < 3) {
                System.out.println("Issuing Refund.");
                memberClass.setRegistrationStatus("canceled");
                return true;
            } else {
                System.out.println("Refund is not available because 3 or more days has passed.");
                memberClass.setRegistrationStatus("canceled");
                return true;
            }
        }

        return false;
    }

    public ArrayList<Record> getListOfRegistrations() {
        return registrationDatabase.returnAllRecords();
    }

    public void logout() throws IOException {
        registrationDatabase.saveToFile();

    }
}
