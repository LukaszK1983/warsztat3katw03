package pl.coderslab.warsztat3katw03.servlet;

import pl.coderslab.warsztat3katw03.dao.ExerciseDAO;
import pl.coderslab.warsztat3katw03.dao.SolutionDAO;
import pl.coderslab.warsztat3katw03.dao.UserDAO;
import pl.coderslab.warsztat3katw03.model.Exercise;
import pl.coderslab.warsztat3katw03.model.Solution;
import pl.coderslab.warsztat3katw03.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int limit = Integer.parseInt(getServletContext().getInitParameter("number-solutions"));
        try {
            List<Solution> solutions = SolutionDAO.findRecent(limit);
            List<Solution> solutionList = new ArrayList<>();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (Solution sol : solutions) {
                Exercise ex = ExerciseDAO.read(sol.getExerciseId());
                int id = sol.getId();
                String title = ex.getTitle();
                User u = null;
                u = UserDAO.read(sol.getUserId());
                String authorName = u.getName();
//            String created = formatter.format(sol.getCreated());
                String created = sol.getCreated();

                solutionList.add(new Solution(id, title, authorName, created));
            }
            req.setAttribute("solutionList", solutionList);
            getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Błąd połączenia z bazą danych");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }
}