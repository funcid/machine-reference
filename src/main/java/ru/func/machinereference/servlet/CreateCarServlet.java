package ru.func.machinereference.servlet;

import ru.func.machinereference.entity.Car;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author func 01.01.2020
 */
@WebServlet("/createCar")
public class CreateCarServlet extends AbstractCarServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html; charset=UTF-8");

        carDao.save(Car.builder()
                .display(req.getParameter("name"))
                .company(req.getParameter("company"))
                .build()
        );

        resp.getWriter().println("<html><body><b>Машина создана!</b><br><a href=\"\\\">Вернуться</a></body></html>");
    }
}
