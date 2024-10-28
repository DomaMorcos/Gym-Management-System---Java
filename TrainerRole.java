/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;

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

    private void addMember(String memberID, String name, String membershipType, String email, String phonenumber, String status) throws IOException {
        Member member = new Member(memberID, name, membershipType, email, phonenumber, status);
        memberDatabase.insertRecord(member);
    }

    private ArrayList<Record> getListOfMembers() {
        return memberDatabase.returnAllRecords();
    }

    private void addClass(String classID, String className, String trainerID, int duration, int maxParticipants) throws IOException {
        Class newClass = new Class(classID, className, trainerID, duration, maxParticipants);
        classDatabase.insertRecord(newClass);
    }

    private ArrayList<Record> getListOfClasses() {
        return classDatabase.returnAllRecords();
    }

    private boolean registerMemberForClass(String memberID, String classID, LocalDate registrationDate) throws IOException {
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
}
