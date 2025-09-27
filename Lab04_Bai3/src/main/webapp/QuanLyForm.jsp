<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, iuh.fit.se.lab04_bai3.model.TinTuc, iuh.fit.se.lab04_bai3.dao.DanhSachTinTucQuanLy,
iuh.fit.se.lab04_bai3.util.DBConnection" %>
<%@ page import="java.sql.SQLException" %>
<jsp:include page="layout.jspf" />

<h3>Quản lý tin tức</h3>
<table border="1" cellpadding="5">
    <tr><th>Mã TT</th><th>Tiêu đề</th><th>Xóa</th></tr>
    <%
        DanhSachTinTucQuanLy dao =
                null;
        try {
            dao = new DanhSachTinTucQuanLy(DBConnection.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<TinTuc> list = null; // tạm fix MADM=1, bạn có thể load all
        try {
            list = dao.getTinTheoDanhMuc(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (TinTuc tin : list) {
    %>
    <tr>
        <td><%= tin.getMaTT() %></td>
        <td><%= tin.getTieuDe() %></td>
        <td><a href="QuanLyFormServlet?action=delete&maTT=<%= tin.getMaTT() %>">Xóa</a></td>
    </tr>
    <% } %>
</table>
