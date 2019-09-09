package pl.coderslab.warsztat3katw03.servlet;

import pl.coderslab.warsztat3katw03.dao.GroupDAO;
import pl.coderslab.warsztat3katw03.dao.UserDAO;
import pl.coderslab.warsztat3katw03.model.Group;
import pl.coderslab.warsztat3katw03.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/userList")
public class UserList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        try {
            List<User> users = UserDAO.findAll();
            req.setAttribute("users", users);
            getServletContext().getRequestDispatcher("/WEB-INF/views/userlist.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User(id, name, email, password);
        resp.getWriter().println("User: " + user);
        try {
            UserDAO.updateUser(user, id);
            List<User> users = UserDAO.findAll();
            req.setAttribute("users", users);
            getServletContext().getRequestDispatcher("/WEB-INF/views/userlist.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
