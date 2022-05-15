package db;

import Entities.Hometown;
import Entities.Student;
import Interfaces.DBChange;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsRepository implements DBChange {
    Student student = new Student();
    HometownRepository hr;

    @Override
    public void DBChange(String sql) {
        try (Connection conn = DriverManager.getConnection(
                DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String addStudent(Student student) {
        return "insert into students values (" + student.getId() +
                ", '" + student.getName() + "', " +
                "'" + student.getLanguage() + "', " +
                "'" + student.getGender() + "', " +
                +student.getPayment() + ");" +
                "insert into hometown values (" + student.getId() + ", '" + student.getHometown() + "');";

    }

    public String deleteStudent(int id) {
        return "delete from students where id_student =" + id + ";"
                + "delete from hometown where id_student =" + id + ";";
    }

    public void printAll() {
        try (Connection conn = DriverManager.getConnection(DBConfig.URL,
                DBConfig.USER, DBConfig.PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery
                     ("select * from students s " +
                             "full outer join hometown ht " +
                             "on s.id_student = ht.id_student " +
                             "order by s.id_student")) {
            while (rs.next()) {
                System.out.println("ID - " + rs.getInt("id_student")
                        + ", NAME - " + rs.getString("name")
                        + ", GENDER - " + rs.getString("gender")
                        + ", LANGUAGE - " + rs.getString("language")
                        + ", PAYMENT - " + rs.getInt("payment")
                        + ", HOMETOWN - " + rs.getString("hometown"));
            }
            System.out.println("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Student getOne(int id) {
        Student student1 = new Student();
        try (Connection conn = DriverManager.getConnection(DBConfig.URL,
                DBConfig.USER, DBConfig.PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery
                     ("select * from students s " +
                             "full outer join hometown ht " +
                             "on s.id_student = ht.id_student " +
                             "where s.id_student =" + id + ";")) {
            while (rs.next()) {
                student1.setId(id);
                student1.setName(rs.getString("name"));
                student1.setGender(rs.getString("gender"));
                student1.setLanguage(rs.getString("language"));
                student1.setPayment(rs.getInt("payment"));
                student1.setHometown(new Hometown(
                        id, rs.getString("hometown")));
            }
            System.out.println(student1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student1;
    }

    public String update(int id) {
        return "update students set payment = 500 where id_student =" + id + ";";
    }

    public List<Student> getAll() {
        List<Student> studentsList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DBConfig.URL,
                DBConfig.USER, DBConfig.PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery
                     ("select * from students order by id_student")) {
            while (rs.next()) {
                Student student1 = new Student();
                student1.setId(rs.getInt("id_student"));
                student1.setName(rs.getString("name"));
                student1.setGender(rs.getString("gender"));
                student1.setLanguage(rs.getString("language"));
                student1.setPayment(rs.getInt("payment"));
                studentsList.add(student1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentsList;
    }

}
