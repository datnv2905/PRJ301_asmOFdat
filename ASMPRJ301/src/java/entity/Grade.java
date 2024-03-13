/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author admin
 */
public class Grade {
    private String graid;
    private Exam exam;
    private Students students;
    private float score;
    private Lecturer lecturer;
    private String comments;
    
    public Grade() {
    }

    public Grade(String graid, Exam exam, Students students, float score, Lecturer lecturer, String comments) {
        this.graid = graid;
        this.exam = exam;
        this.students = students;
        this.score = score;
        this.lecturer = lecturer;
        this.comments = comments;
    }

    public String getGraid() {
        return graid;
    }

    public void setGraid(String graid) {
        this.graid = graid;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    
}
