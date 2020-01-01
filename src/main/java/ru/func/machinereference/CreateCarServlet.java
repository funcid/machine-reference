package ru.func.machinereference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author func 01.01.2020
 */
@WebServlet("/createCar")
public class CreateCarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html; charset=UTF-8");

        try {
            Connection connection = DatabaseConnection.initializeDatabase();
            PreparedStatement st = connection.prepareStatement("insert into cars(display, company) values (?, ?)");


            st.setString(1, req.getParameter("name"));
            st.setString(2, req.getParameter("company"));
            st.executeUpdate();

            st.close();
            connection.close();
            resp.getWriter().println("<html><body><b>Машина создана!</b><br><a href=\"\\\">Вернуться</a></body></html>");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
