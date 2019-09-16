package pl.coderslab.warsztat3katw03.servlet;

import pl.coderslab.warsztat3katw03.dao.GroupDAO;
import pl.coderslab.warsztat3katw03.dao.SolutionDAO;
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
import java.util.Collections;
import java.util.List;

@WebServlet("/showUsers")
public class ShowUsers extends HttpServlet {
    private UserDAO userDAO = UserDAO.getInstance();
    private GroupDAO groupDAO = GroupDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String groupName = groupDAO.read(id).getName();
            List<User> users = userDAO.findAllByGroupId(id);
            req.setAttribute("users", users);
            getServletContext().getRequestDispatcher(String.format("/WEB-INF/views/showusers.jsp?name=%s", groupName)).forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }
}
