package pl.coderslab.warsztat3katw03.servlet;

import pl.coderslab.warsztat3katw03.dao.ExerciseDAO;
import pl.coderslab.warsztat3katw03.dao.GroupDAO;
import pl.coderslab.warsztat3katw03.model.Exercise;
import pl.coderslab.warsztat3katw03.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/exerciseList")
public class ExerciseList extends HttpServlet {
    private ExerciseDAO exerciseDAO = ExerciseDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        try {
            List<Exercise> exercises = exerciseDAO.findAll();
            req.setAttribute("exercises", exercises);
            getServletContext().getRequestDispatcher("/WEB-INF/views/exerciselist.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String desc = req.getParameter("desc");
        try {
            Exercise exercise = new Exercise(id, title, desc);
            exerciseDAO.update(exercise, id);
            List<Exercise> exercises = exerciseDAO.findAll();
            req.setAttribute("exercises", exercises);
            getServletContext().getRequestDispatcher("/WEB-INF/views/exerciselist.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }
}
