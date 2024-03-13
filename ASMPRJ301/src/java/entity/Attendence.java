/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Attendence {
    private int atdid;
    private Students student;
    private Lession lession;
    private String description;
    private boolean present;
    private Date time;

    public Attendence() {
    }

    public Attendence(int atdid, Students student, Lession lession, String description, boolean present, Date time) {
        this.atdid = atdid;
        this.student = student;
        this.lession = lession;
        this.description = description;
        this.present = present;
        this.time = time;
    }

    public int getAtdid() {
        return atdid;
    }

    public void setAtdid(int atdid) {
        this.atdid = atdid;
    }

    

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    public Lession getLession() {
        return lession;
    }

    public void setLession(Lession lession) {
        this.lession = lession;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

  
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


}
