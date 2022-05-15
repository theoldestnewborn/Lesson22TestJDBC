package db;

import Entities.Hometown;
import Entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsRepository {
    Student student = new Student();
    HometownRepository hr;

    public void ChangeDB(String sql) {
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
                + student.getPayment() + ");" +
                "insert into hometown values (" + student.getId() + ", '" + student.getHometown() + "');";

    }

    public String update(Student student) {
        return "update students set payment = 500 where id_student =" + student.getId() + ";";
        }

    public String deleteOne(int id) {
        return  "delete from students where id_student =" + id + ";";
    }

    public List<Student> getAll() {
        List <Student> studentsList = new ArrayList<>();
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
            System.out.println("\n");
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



//    public void getNames() {
//        try (Connection cn = DriverManager.getConnection(DBConfig.URL, DBConfig.USER,
//                DBConfig.PASSWORD);
//             Statement st = cn.createStatement();
//             ResultSet rs = st.executeQuery("select id_student, " +
//                     "name from students order by id_student;")) {
//            while (rs.next()) {
//                System.out.println("ID: " + rs.getInt("id_student") +
//                        " NAME: " + rs.getString("name"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public void getOne(int id_student) {
//        try (Connection conn = DriverManager.getConnection(DBConfig.URL,
//                DBConfig.USER, DBConfig.PASSWORD);
//             Statement st = conn.createStatement();
//             ResultSet rs = st.executeQuery
//                     ("select * from students where id_student = ")) { // как?
//            while (rs.next()) {
//                System.out.println("Name:" + rs.getString("name"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
