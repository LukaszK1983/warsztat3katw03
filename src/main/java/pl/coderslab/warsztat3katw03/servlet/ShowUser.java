package pl.coderslab.warsztat3katw03.servlet;

import pl.coderslab.warsztat3katw03.dao.ExerciseDAO;
import pl.coderslab.warsztat3katw03.dao.GroupDAO;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/showUser")
public class ShowUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            String name = UserDAO.read(id).getName();
            String email = UserDAO.read(id).getEmail();
            List<Solution> solutions = SolutionDAO.findAllByUserId(id);
            List<Solution> solutionList = new ArrayList<>();
            for(Solution sol : solutions){
                Exercise ex = ExerciseDAO.read(sol.getExerciseId());
                int solId = sol.getId();
                String title = ex.getTitle();
                String created = sol.getCreated();
                solutionList.add(new Solution(solId, title, created));
            }
            req.setAttribute("solutions", solutionList);
            getServletContext().getRequestDispatcher(String.format("/WEB-INF/views/showuser.jsp?name=%s&email=%s", name, email)).forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
