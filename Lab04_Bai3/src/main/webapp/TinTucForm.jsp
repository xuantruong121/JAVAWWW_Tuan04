<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="layout.jspf" />

<style>
    body {
        font-family: Arial, sans-serif;
        margin: 40px;
        background-color: #f9f9f9;
    }
    h3 {
        margin-bottom: 20px;
    }
    .form-classic {
        border: 1px solid #ccc;
        border-collapse: collapse;
        background: #fff;
        width: 600px;
    }
    .form-classic th, .form-classic td {
        border: 1px solid #ccc;
        padding: 8px 12px;
    }
    .form-classic th {
        text-align: right;
        background: #f0f0f0;
        width: 180px;
    }
    .form-classic input, .form-classic textarea {
        width: 100%;
        padding: 6px;
        border: 1px solid #aaa;
        box-sizing: border-box;
        font-size: 14px;
    }
    .form-actions {
        margin-top: 15px;
        margin-left: 200px;
    }
    .form-actions input, .form-actions a {
        padding: 6px 18px;
        font-size: 14px;
        margin: 0 5px;
    }
</style>

<h3>Thêm tin tức mới</h3>
<form action="TinTucFormServlet" method="post" onsubmit="return validateForm()">
    <table class="form-classic">
        <tr>
            <th>Mã TT:</th>
            <td><input type="text" name="maTT" required></td>
        </tr>
        <tr>
            <th>Tiêu đề:</th>
            <td><input type="text" name="tieuDe" required></td>
        </tr>
        <tr>
            <th>Nội dung:</th>
            <td><textarea name="noiDungTT" maxlength="255" rows="3" required></textarea></td>
        </tr>
        <tr>
            <th>Liên kết:</th>
            <td><input type="text" name="lienKet" placeholder="http://..." required></td>
        </tr>
        <tr>
            <th>Mã danh mục:</th>
            <td>
                <select id="maDM" name="maDM" required>
                    <c:forEach var="dm" items="${danhMucList}">
                        <option value="${dm.maDM}">${dm.tenDanhMuc}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>

    <div class="form-actions">
        <a href="DanhSachTinTucServlet"><input type="button" value="⬅ Quay lại"></a>
        <input type="submit" value="➕ Thêm tin">
    </div>
</form>

<script>
    function validateForm() {
        const link = document.querySelector("[name=lienKet]").value;
        const noiDung = document.querySelector("[name=noiDungTT]").value;

        if (!/^http:\/\/.*/.test(link)) {
            alert("Liên kết phải bắt đầu bằng http://");
            return false;
        }
        if (noiDung.length > 255) {
            alert("Nội dung không được quá 255 ký tự");
            return false;
        }
        return true;
    }
</script>
