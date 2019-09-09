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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int solutionID = Integer.parseInt(req.getParameter("id"));
        Solution solution = SolutionDAO.loadById(solutionID);
        req.setAttribute("solution", solution);
        getServletContext().getRequestDispatcher("/WEB-INF/views/showsolution.jsp").forward(req, resp);
    }
}
