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
import java.sql.SQLException;

/**
 * @author func 01.01.2020
 */
@WebServlet("/findCarsByCompany")
public class FindCarsByCompanyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html; charset=UTF-8");

        try {
            Connection connection = DatabaseConnection.initializeDatabase();
            PreparedStatement st = connection.prepareStatement(
                    "select * from cars where company='" + req.getParameter("name") + "'"
            );
            ResultSet rs = st.executeQuery();
            StringBuilder stringBuilder = new StringBuilder("Машины марки ");
            stringBuilder.append(req.getParameter("name"));
            stringBuilder.append(":<br>");
            while(rs.next()){
                stringBuilder.append(rs.getInt("id"));
                stringBuilder.append(" - ");
                stringBuilder.append(rs.getString("display"));
                stringBuilder.append("<br>");
            }
            rs.close();
            st.close();
            connection.close();
            resp.getWriter().println("<html><body><b>"+stringBuilder.toString()+"</b><br><a href=\"\\\">Вернуться</a></body></html>");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
