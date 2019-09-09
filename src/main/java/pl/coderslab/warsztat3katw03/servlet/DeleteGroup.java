package pl.coderslab.warsztat3katw03.servlet;

import pl.coderslab.warsztat3katw03.dao.GroupDAO;
import pl.coderslab.warsztat3katw03.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/deleteGroup")
public class DeleteGroup extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int group_id = Integer.parseInt(req.getParameter("id"));
        GroupDAO.delete(group_id);
        List<Group> groups = GroupDAO.findAll();
        req.setAttribute("groups", groups);
        getServletContext().getRequestDispatcher("/WEB-INF/views/grouplist.jsp").forward(req, resp);
    }
}
