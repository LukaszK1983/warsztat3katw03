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

@WebServlet("/addGroup")
public class AddGroup extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/addgroup.jsp").forward(req, resp);
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int group_id = GroupDAO.getLastGroupId().getGroup_id();
        group_id++;
        int id = GroupDAO.getLastGroupId().getId();
        id++;
        Group group = new Group(id, group_id, name);
        GroupDAO.addGroup(group);
        List<Group> groups = GroupDAO.findAll();
        req.setAttribute("groups", groups);
        getServletContext().getRequestDispatcher("/WEB-INF/views/grouplist.jsp").forward(req, resp);
    }
}
