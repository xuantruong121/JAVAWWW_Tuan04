package iuh.fit.se.lab04_bai1_jsp_mvc.dao.daoImpl;

import iuh.fit.se.lab04_bai1_jsp_mvc.dao.UserDAO;
import iuh.fit.se.lab04_bai1_jsp_mvc.database.DBConnection;
import iuh.fit.se.lab04_bai1_jsp_mvc.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final DataSource dataSource;
    public UserDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insertUser(User user) {
        String sql = "INSERT INTO users(first_name, last_name, email, password, date_of_birth, gender) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            LocalDate localDate = LocalDate.parse(user.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            ps.setDate(5, java.sql.Date.valueOf(localDate));
            ps.setString(6, user.getGender());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT id, first_name, last_name, email, date_of_birth, gender FROM users";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                User u = new User();
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setEmail(rs.getString("email"));
                u.setDateOfBirth(rs.getString("date_of_birth"));
                u.setGender(rs.getString("gender"));
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
