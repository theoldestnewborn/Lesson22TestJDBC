package db;

import Entities.Hometown;
import Interfaces.DBChange;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HometownRepository implements DBChange {
    Hometown hometown;

    public HometownRepository() {
    }

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

    public String addHometown(int id, String town) {
        return "insert into hometown values (" + id + ", '" + town + "')";
    }

    public String deleteHometown(int id) {
        return "delete from hometown where id_student =" + id + ";";
    }

}
