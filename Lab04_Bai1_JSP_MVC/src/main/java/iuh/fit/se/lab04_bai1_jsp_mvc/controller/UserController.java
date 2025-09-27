package iuh.fit.se.lab04_bai1_jsp_mvc.controller;

import iuh.fit.se.lab04_bai1_jsp_mvc.dao.daoImpl.UserDAOImpl;
import iuh.fit.se.lab04_bai1_jsp_mvc.model.User;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet(name = "UserController", urlPatterns = {"/user-old"}) //JDBC
public class UserController extends HttpServlet {

    @Resource(name = "jdbc/dstaikhoan")
    private DataSource dataSource;

    private UserDAOImpl userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.userDAO = new UserDAOImpl(dataSource);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        ValidatorFactory factory = jakarta.validation.Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Lấy dữ liệu từ form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String reEmail = request.getParameter("reEmail");
        String password = request.getParameter("password");
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");

// đảm bảo định dạng 2 chữ số cho tháng và ngày
        if (month.length() == 1) month = "0" + month;
        if (day.length() == 1) day = "0" + day;

// birthday chuẩn yyyy-MM-dd
        String birthday = year + "-" + month + "-" + day;

        String gender = request.getParameter("gender");

        // Kiểm tra email nhập lại có khớp không (basic)
        if (!email.equals(reEmail)) {
            request.setAttribute("error", "Email nhập lại không khớp!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Tạo User object
        User user = new User(firstName, lastName, email, password, birthday, gender);

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            request.setAttribute("errors", violations);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Register successful!");
            request.getRequestDispatcher("/success.jsp").forward(request, response);
        }

        // Lưu vào DB
        userDAO.insertUser(user);

        // Lấy danh sách user từ DB
        List<User> users = userDAO.getAllUsers();

        // Forward sang trang listUsers.jsp
        request.setAttribute("users", users);
        RequestDispatcher rd = request.getRequestDispatcher("listUsers.jsp");
        rd.forward(request, response);
    }
}
