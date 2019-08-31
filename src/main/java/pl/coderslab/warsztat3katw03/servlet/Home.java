package pl.coderslab.warsztat3katw03.servlet;

import pl.coderslab.warsztat3katw03.db.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final Connection connection = DbUtil.getConnection();
            resp.getWriter().println("Test ok!");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Test failed!");
        }
    }
}
