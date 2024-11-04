/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author Doma & Moatassem
 */
public class Trainer implements Record {

    private String trainerID;
    private String name;
    private String email;
    private String speciality;
    private String phoneNumber;

    public Trainer(String trainerID, String name, String email, String speciality, String phoneNumber) {
        this.trainerID = trainerID;
        this.name = name;
        this.email = email;
        this.speciality = speciality;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String lineRepresentation() {
        return String.join(",", trainerID, name, email, speciality, phoneNumber);
    }

    @Override
    public String getSearchKey() {
        return trainerID;
    }
}
