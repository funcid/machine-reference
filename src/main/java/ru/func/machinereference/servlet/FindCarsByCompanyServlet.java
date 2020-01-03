package ru.func.machinereference.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * @author func 01.01.2020
 */
@WebServlet("/findCarsByCompany")
public class FindCarsByCompanyServlet extends AbstractCarServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html; charset=UTF-8");

        final String company = req.getParameter("name");

        String resultString = carDao.findByCompany(company).stream()
                .map(car -> String.format("%d - %s<br>", car.getId(), car.getDisplay()))
                .collect(Collectors.joining());

        resp.getWriter().println("<html><body><b>Машины марки "+company+":<br>"+resultString+"</b><br><a href=\"\\\">Вернуться</a></body></html>");
    }
}
