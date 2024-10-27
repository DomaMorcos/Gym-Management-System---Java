/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author Doma & Moatassem
 */
public class Class implements Record {
    private String classID;
    private String className;
    private String trainerId;
    private int duration;
    private int availableSeats;

    public Class(String classID, String className, String trainerId, int duration, int availableSeats) {
        this.classID = classID;
        this.className = className;
        this.trainerId = trainerId;
        this.duration = duration;
        this.availableSeats = availableSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String lineRepresentation(){
        return this.classID + "," + this.className + "," + this.trainerId + "," + this.duration + "," + this.availableSeats;
    }

    @Override
    public String getSearchKey() {
        return "";
    }


}
