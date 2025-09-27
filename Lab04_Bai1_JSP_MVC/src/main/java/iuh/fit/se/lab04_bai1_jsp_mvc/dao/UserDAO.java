package iuh.fit.se.lab04_bai1_jsp_mvc.dao;

import iuh.fit.se.lab04_bai1_jsp_mvc.model.User;

import java.util.List;

public interface UserDAO {

    public void insertUser(User user);

    public List<User> getAllUsers();

}
