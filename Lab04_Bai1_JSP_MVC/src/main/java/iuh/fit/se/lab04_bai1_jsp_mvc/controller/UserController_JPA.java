package iuh.fit.se.lab04_bai1_jsp_mvc.controller;

import iuh.fit.se.lab04_bai1_jsp_mvc.dao.daoImpl.UserDAOImpl_JPAVersion;
import iuh.fit.se.lab04_bai1_jsp_mvc.model.User_JPA;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import util.EntityManagerFactoryUtil;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController_JPA", urlPatterns = {"/user"}) //JPA
public class UserController_JPA extends HttpServlet {

    private UserDAOImpl_JPAVersion userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.userDAO = new UserDAOImpl_JPAVersion(EntityManagerFactoryUtil.getEntityManager());
    }

    /**
     * GET -> Hiển thị danh sách user
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User_JPA> users = userDAO.getAllUsers();
        request.setAttribute("users", users);

        RequestDispatcher rd = request.getRequestDispatcher("listUsers_ver2.jsp");
        rd.forward(request, response);
    }

    /**
     * POST -> Xử lý đăng ký user mới
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // Lấy dữ liệu từ form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String reEmail = request.getParameter("reEmail");
        String password = request.getParameter("password");
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");

        if (month != null && month.length() == 1) month = "0" + month;
        if (day != null && day.length() == 1) day = "0" + day;

        String birthday = null;
        if (year != null && month != null && day != null) {
            birthday = year + "-" + month + "-" + day;
        }

        String gender = request.getParameter("gender");

        // Kiểm tra email nhập lại
        if (email != null && reEmail != null && !email.equals(reEmail)) {
            request.setAttribute("error", "Email nhập lại không khớp!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Tạo User object và lưu vào DB
        if (email != null && !email.isEmpty()) {
            User_JPA user = new User_JPA(firstName, lastName, email, password, birthday, gender);
            userDAO.insertUser(user);
        }

        // Sau khi đăng ký xong -> redirect về /user (sẽ gọi doGet)
        response.sendRedirect("user");
    }
}
