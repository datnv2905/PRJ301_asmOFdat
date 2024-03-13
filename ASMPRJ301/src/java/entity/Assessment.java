/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author admin
 */
public class Assessment {
    private int assmid;
    private String name;
    private Subjects subjects;
    private float weght;

    public Assessment() {
    }

    public Assessment(int assmid, String name, Subjects subjects, float weght) {
        this.assmid = assmid;
        this.name = name;
        this.subjects = subjects;
        this.weght = weght;
    }

    public int getAssmid() {
        return assmid;
    }

    public void setAssmid(int assmid) {
        this.assmid = assmid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subjects getSubjects() {
        return subjects;
    }

    public void setSubjects(Subjects subjects) {
        this.subjects = subjects;
    }

    public float getWeght() {
        return weght;
    }

    public void setWeght(float weght) {
        this.weght = weght;
    }
    
}
