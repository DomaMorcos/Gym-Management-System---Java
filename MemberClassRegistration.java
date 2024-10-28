/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.time.LocalDate;

/**
 *
 * @author Doma & Moatassem
 */
public class MemberClassRegistration implements Record{

    private String memberID;
    private String classID;
    private String registrationStatus;
    private LocalDate registrationDate;

    public MemberClassRegistration(String memberID, String classID, String registrationStatus, LocalDate registrationDate) {
        this.memberID = memberID;
        this.classID = classID;
        this.registrationStatus = registrationStatus;
        this.registrationDate = registrationDate;
    }

    public String getMemberID() {
        return memberID;
    }

    public String getClassID() {
        return classID;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationStatus(String registrationStatus){
        this.registrationStatus = registrationStatus;
    }
    
    @Override
    public String lineRepresentation() {
        return String.join(",", memberID,classID,registrationStatus,registrationDate);
    }

    @Override
    public String getSearchKey() {
        return memberID + "|" + classID;
    }
}
