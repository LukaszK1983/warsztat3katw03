package pl.coderslab.warsztat3katw03.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editExercise")
public class EditExercise extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String desc = req.getParameter("desc");
        getServletContext().getRequestDispatcher("/WEB-INF/views/editexercise.jsp?id=" + id + "&title=" +
                title + "&desc=" + desc + "").forward(req, resp);
    }
}
