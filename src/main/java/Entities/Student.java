package Entities;

public class Student {
    public int id, payment;
    public String name, language, gender;
    Hometown hometown;

    public Student(int id, String name, String language, String gender, int payment, String hometown) {
        this.id = id;
        this.payment = payment;
        this.name = name;
        this.language = language;
        this.gender = gender;
        this.hometown = new Hometown(id, hometown);
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student - " +
                "id=" + id +
                ", name=" + name +
                ", language=" + language +
                ", gender=" + gender +
                ", payment=" + payment +
                ", hometown=" + hometown.getHometown()
                + ".\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHometown() {
        return hometown.getHometown();
    }

    public void setHometown(Hometown hometown) {
        this.hometown = hometown;
    }
}
