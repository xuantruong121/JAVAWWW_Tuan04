package iuh.fit.se.lab04_bai3.controller;

import iuh.fit.se.lab04_bai3.dao.DanhMucDAO;
import iuh.fit.se.lab04_bai3.dao.DanhSachTinTucQuanLy;
import iuh.fit.se.lab04_bai3.model.DanhMuc;
import iuh.fit.se.lab04_bai3.model.TinTuc;
import iuh.fit.se.lab04_bai3.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/TinTucFormServlet")
public class TinTucFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("DEBUG: doGet của TinTucFormServlet đã chạy");
        try (Connection conn = DBConnection.getConnection()) {
            DanhMucDAO dmDao = new DanhMucDAO(conn);
            List<DanhMuc> listDM = dmDao.getAllDanhMuc();
            System.out.println("DEBUG: Số danh mục lấy được = " + listDM.size());

            request.setAttribute("danhMucList", listDM);
            request.getRequestDispatcher("TinTucForm.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            int maTT = Integer.parseInt(request.getParameter("maTT"));
            String tieuDe = request.getParameter("tieuDe");
            String noiDungTT = request.getParameter("noiDungTT");
            String lienKet = request.getParameter("lienKet");
            int maDM = Integer.parseInt(request.getParameter("maDM"));

            TinTuc tin = new TinTuc(maTT, tieuDe, noiDungTT, lienKet, maDM);

            DanhSachTinTucQuanLy dao = new DanhSachTinTucQuanLy(conn);
            dao.themTin(tin);

            response.sendRedirect("DanhSachTinTucServlet?madm=" + maDM);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
