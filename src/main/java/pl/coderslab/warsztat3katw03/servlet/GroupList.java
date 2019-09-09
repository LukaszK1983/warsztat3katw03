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

@WebServlet("/groupList")
public class GroupList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        List<Group> groups = GroupDAO.findAll();
        req.setAttribute("groups", groups);
        getServletContext().getRequestDispatcher("/WEB-INF/views/grouplist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Group group = new Group(name);
        GroupDAO.updateName(group, id);
        List<Group> groups = GroupDAO.findAll();
        req.setAttribute("groups", groups);
        getServletContext().getRequestDispatcher("/WEB-INF/views/grouplist.jsp").forward(req, resp);
    }
}
