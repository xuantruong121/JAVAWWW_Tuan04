package iuh.fit.se.lab04_bai3.controller;

import iuh.fit.se.lab04_bai3.dao.DanhSachTinTucQuanLy;
import iuh.fit.se.lab04_bai3.model.TinTuc;
import iuh.fit.se.lab04_bai3.util.DBConnection; // nhớ import

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/DanhSachTinTucServlet")
public class DanhSachTinTucServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String madmStr = request.getParameter("madm");
        int madm = madmStr != null ? Integer.parseInt(madmStr) : 1; // default = 1

        try (Connection conn = DBConnection.getConnection()) { // tự lấy connection
            DanhSachTinTucQuanLy dao = new DanhSachTinTucQuanLy(conn);

            List<TinTuc> list = dao.getTinTheoDanhMuc(madm);
            request.setAttribute("listTinTuc", list);

            RequestDispatcher rd = request.getRequestDispatcher("DanhSachTinTuc.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Lỗi khi lấy danh sách tin tức", e);
        }
    }
}
