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

@WebServlet("/addExercise")
public class AddExercise extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/addexercise.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String desc = req.getParameter("desc");
        int id = ExerciseDAO.getLastExerciseId().getId();
        id++;
        Exercise exercise = new Exercise(id, title, desc);
        ExerciseDAO.create(exercise);
        List<Exercise> exercises = ExerciseDAO.findAll();
        req.setAttribute("exercises", exercises);
        getServletContext().getRequestDispatcher("/WEB-INF/views/exerciselist.jsp").forward(req, resp);
    }
}
