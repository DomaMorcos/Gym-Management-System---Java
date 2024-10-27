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

    public MemberClassRegistration(String memberID, String classID, String status, LocalDate registrationDate) {
        this.memberID = memberID;
        this.classID = classID;
        this.registrationStatus = status;
        this.registrationDate = registrationDate;
    }

    public String getMemberID() {
        return this.memberID;
    }

    public String getClassID() {
        return this.classID;
    }

    public LocalDate getRegistrationDate() {
        return this.registrationDate;
    }
    
    public void setRegistrationStatus(String status){
        this.registrationStatus = status;
    }
    
    @Override
    public String lineRepresentation() {
        return this.memberID + "," + this.classID+ "," + this.registrationDate + "," + this.registrationStatus;
    }

    @Override
    public String getSearchKey() {
        return this.memberID + this.classID;
    }


}
