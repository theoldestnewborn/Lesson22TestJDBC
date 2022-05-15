package org.example;
import Entities.Student;
import db.HometownRepository;
import db.StudentsRepository;

public class Main {
    public static void main(String[] args) {
        StudentsRepository sr = new StudentsRepository();
        HometownRepository htr = new HometownRepository();
        sr.DBChange(sr.deleteStudent(7));
        htr.DBChange(htr.deleteHometown(1));
        htr.DBChange(htr.deleteHometown(2));

        sr.printAll();

        htr.DBChange(htr.addHometown(1, "Baranovichi"));
        htr.DBChange(htr.addHometown(2, "Minsk"));
        sr.getOne(1);
        htr.DBChange(htr.deleteHometown(1));
        sr.getOne(1);

        sr.DBChange(sr.addStudent(new Student(7,"Y-Student", "Y-Java",
                "F", 1000, "Y-Town")));
        sr.printAll();
        sr.DBChange(sr.update(7));
        sr.getOne(7);
        sr.printAll();
    }
}
