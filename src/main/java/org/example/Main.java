package org.example;

import Entities.Hometown;
import Entities.Student;
import db.HometownRepository;
import db.StudentsRepository;

public class Main {
    public static void main(String[] args) {
        StudentsRepository sr = new StudentsRepository();
        HometownRepository htr = new HometownRepository();

        htr.HometownDBChange(htr.deleteHometown(1));
        htr.HometownDBChange(htr.deleteHometown(2));

        sr.printAll();
        htr.HometownDBChange(htr.addHometown(1, "Baranovichi"));
        htr.HometownDBChange(htr.addHometown(2, "Minsk"));
        sr.getOne(1);

        sr.printAll();

        htr.HometownDBChange(htr.deleteHometown(2));
        htr.HometownDBChange(htr.deleteHometown(1));
        sr.getOne(2);

        sr.ChangeDB(sr.addStudent(new Student(7,"Y-Student", "Y-Java",
                "F", 1000, "Y-Town")));
        sr.printAll();

        htr.HometownDBChange(htr.deleteHometown(7));
        sr.getOne(7);
        sr.ChangeDB(sr.deleteOne(7));
        sr.printAll();
    }
}
