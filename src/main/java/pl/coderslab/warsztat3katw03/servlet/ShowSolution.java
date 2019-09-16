package pl.coderslab.warsztat3katw03.servlet;

import pl.coderslab.warsztat3katw03.dao.SolutionDAO;
import pl.coderslab.warsztat3katw03.dao.UserDAO;
import pl.coderslab.warsztat3katw03.model.Solution;
import pl.coderslab.warsztat3katw03.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/showSolution")
public class ShowSolution extends HttpServlet {
    private SolutionDAO solutionDAO = SolutionDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int solutionID = Integer.parseInt(req.getParameter("id"));
            Solution solution = solutionDAO.loadById(solutionID);
            req.setAttribute("solution", solution);
            getServletContext().getRequestDispatcher("/WEB-INF/views/showsolution.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }
}
