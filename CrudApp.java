import java.sql.*;

public class CrudApp {
    static final String URL = "jdbc:mysql://localhost:3306/testdb";
    static final String USER = "root";
    static final String PASSWORD = "password";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // CREATE
            String insert = "INSERT INTO students (id, name) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insert)) {
                ps.setInt(1, 1);
                ps.setString(2, "John");
                ps.executeUpdate();
                System.out.println("Record Inserted");
            }

            // READ
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }

            // UPDATE
            String update = "UPDATE students SET name=? WHERE id=?";
            try (PreparedStatement ps = conn.prepareStatement(update)) {
                ps.setString(1, "John Updated");
                ps.setInt(2, 1);
                ps.executeUpdate();
                System.out.println("Record Updated");
            }

            // DELETE
            String delete = "DELETE FROM students WHERE id=?";
            try (PreparedStatement ps = conn.prepareStatement(delete)) {
                ps.setInt(1, 1);
                ps.executeUpdate();
                System.out.println("Record Deleted");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
