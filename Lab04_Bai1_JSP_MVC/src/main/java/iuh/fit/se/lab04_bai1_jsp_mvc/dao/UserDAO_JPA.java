package iuh.fit.se.lab04_bai1_jsp_mvc.dao;

import iuh.fit.se.lab04_bai1_jsp_mvc.model.User_JPA;

import java.util.List;

public interface UserDAO_JPA {
    public void insertUser(User_JPA user);

    public List<User_JPA> getAllUsers();
}
