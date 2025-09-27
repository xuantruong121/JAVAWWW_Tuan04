package iuh.fit.se.lab04_bai3.controller;

import iuh.fit.se.lab04_bai3.dao.DanhSachTinTucQuanLy;
import iuh.fit.se.lab04_bai3.util.DBConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/QuanLyFormServlet")
public class QuanLyFormServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String maTTStr = request.getParameter("maTT");

        try(Connection conn = DBConnection.getConnection()) {
            DanhSachTinTucQuanLy dao = new DanhSachTinTucQuanLy(conn);

            if ("delete".equals(action) && maTTStr != null) {
                int maTT = Integer.parseInt(maTTStr);
                dao.xoaTin(maTT);
            }

            // sau khi xóa thì quay về trang danh sách quản lý
            RequestDispatcher rd = request.getRequestDispatcher("QuanLyForm.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
