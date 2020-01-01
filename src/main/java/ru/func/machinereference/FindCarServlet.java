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
import java.sql.ResultSet;

/**
 * @author func 01.01.2020
 */
@WebServlet("/findCar")
public class FindCarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html; charset=UTF-8");
        try {
            Connection connection = DatabaseConnection.initializeDatabase();
            PreparedStatement st = connection.prepareStatement(
                    "select * from cars where id='" + req.getParameter("id") + "'"
            );
            ResultSet rs = st.executeQuery();
            String string = "Машина с ID " + req.getParameter("id") + ":<br>";
            while(rs.next())
                string += rs.getString("display") + " марки " + rs.getString("company") + "<br>";
            rs.close();
            st.close();
            connection.close();
            resp.getWriter().println("<html><body><b>"+string+"</b><br><a href=\"\\\">Вернуться</a></body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
