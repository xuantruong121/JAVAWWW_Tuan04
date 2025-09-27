package iuh.fit.se.lab04_bai3.dao;

import iuh.fit.se.lab04_bai3.model.TinTuc;

import java.sql.*;
import java.util.*;

public class DanhSachTinTucQuanLy {
    private Connection conn;

    public DanhSachTinTucQuanLy(Connection conn) {
        this.conn = conn;
    }

    public List<TinTuc> getAllTinTuc() throws SQLException {
        List<TinTuc> list = new ArrayList<>();
        String sql = "SELECT * FROM TINTUC";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            TinTuc tin = new TinTuc(
                    rs.getInt("MATT"),
                    rs.getString("TIEUDE"),
                    rs.getString("NOIDUNGTT"),
                    rs.getString("LIENKET"),
                    rs.getInt("MADM")
            );
            list.add(tin);
        }
        return list;
    }

    public List<TinTuc> getTinTheoDanhMuc(int maDM) throws SQLException {
        List<TinTuc> list = new ArrayList<>();
        String sql = "SELECT * FROM TINTUC WHERE MADM=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, maDM);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            TinTuc tin = new TinTuc(
                    rs.getInt("MATT"),
                    rs.getString("TIEUDE"),
                    rs.getString("NOIDUNGTT"),
                    rs.getString("LIENKET"),
                    rs.getInt("MADM")
            );
            list.add(tin);
        }
        return list;
    }

    public void themTin(TinTuc tin) throws SQLException {
        String sql = "INSERT INTO TINTUC (MATT, TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, tin.getMaTT());
        ps.setString(2, tin.getTieuDe());
        ps.setString(3, tin.getNoiDungTT());
        ps.setString(4, tin.getLienKet());
        ps.setInt(5, tin.getMaDM());
        ps.executeUpdate();
    }

    public void xoaTin(int maTT) throws SQLException {
        String sql = "DELETE FROM TINTUC WHERE MATT=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, maTT);
        ps.executeUpdate();
    }
}
