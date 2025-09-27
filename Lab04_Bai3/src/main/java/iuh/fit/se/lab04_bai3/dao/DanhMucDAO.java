package iuh.fit.se.lab04_bai3.dao;

import iuh.fit.se.lab04_bai3.model.DanhMuc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DanhMucDAO {
    private Connection conn;

    public DanhMucDAO(Connection conn) {
        this.conn = conn;
    }

    public List<DanhMuc> getAllDanhMuc() throws SQLException {
        List<DanhMuc> list = new ArrayList<>();
        String sql = "SELECT maDM, tenDanhMuc FROM DanhMuc";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DanhMuc dm = new DanhMuc(rs.getInt("maDM"), rs.getString("tenDanhMuc"));
                list.add(dm);
            }
        }
        System.out.println("DEBUG: So danh muc lay duoc = " + list.size());
        return list;
    }
}
