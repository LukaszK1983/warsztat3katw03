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
import java.util.List;

@WebServlet("/deleteExercise")
public class DeleteExercise extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ExerciseDAO.delete(id);
        List<Exercise> exercises = ExerciseDAO.findAll();
        req.setAttribute("exercises", exercises);
        getServletContext().getRequestDispatcher("/WEB-INF/views/exerciselist.jsp").forward(req, resp);
    }
}
