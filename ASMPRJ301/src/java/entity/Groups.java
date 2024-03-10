/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Groups {
    private String gid;
    private String gname;
    private ArrayList<Students> students = new ArrayList<>();
    private Lecturer lecturer;
    private Subjects Subjects;

    public Groups() {
    }

    public Groups(String gid, String gname, Lecturer lecturer, Subjects Subjects) {
        this.gid = gid;
        this.gname = gname;
        this.lecturer = lecturer;
        this.Subjects = Subjects;
    }

    

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public ArrayList<Students> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Students> students) {
        this.students = students;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Subjects getSubjects() {
        return Subjects;
    }

    public void setSubjects(Subjects Subjects) {
        this.Subjects = Subjects;
    }

    
    
}
