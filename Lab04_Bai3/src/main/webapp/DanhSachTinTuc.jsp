<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, iuh.fit.se.lab04_bai3.model.TinTuc" %>
<jsp:include page="layout.jspf" />

<h3>Danh sách tin tức</h3>
<table border="1" cellpadding="5">
    <tr><th>Mã TT</th><th>Tiêu đề</th><th>Nội dung</th><th>Liên kết</th></tr>
    <%
        List<TinTuc> list = (List<TinTuc>) request.getAttribute("listTinTuc");
        if (list != null) {
            for (TinTuc tin : list) {
    %>
    <tr>
        <td><%= tin.getMaTT() %></td>
        <td><%= tin.getTieuDe() %></td>
        <td><%= tin.getNoiDungTT() %></td>
        <td><a href="<%= tin.getLienKet() %>">Xem</a></td>
    </tr>
    <% } } %>
</table>
