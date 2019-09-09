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
import java.util.Collections;
import java.util.List;

@WebServlet("/showUsers")
public class ShowUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int group_id = Integer.parseInt(req.getParameter("id"));
        String groupName = GroupDAO.loadAllByGrupId(group_id).getName();
        try {
            List<User> users = UserDAO.findAllByGroupId(group_id);
            req.setAttribute("users", users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/showusers.jsp?name=" + groupName + "").forward(req, resp);
    }
}
