package Entities;

public class Hometown {
    public int id_student;
    public String hometown;

    public Hometown () {
    }

    public Hometown(int id_student, String hometown) {
        this.id_student = id_student;
        this.hometown = hometown;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }


}
