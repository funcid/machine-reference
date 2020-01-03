package ru.func.machinereference.servlet;

import ru.func.machinereference.entity.Car;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @author func 01.01.2020
 */
@WebServlet("/findCar")
public class FindCarServlet extends AbstractCarServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html; charset=UTF-8");

        final Integer id = Integer.valueOf(req.getParameter("id"));

        Optional<Car> optCar = carDao.findById(id);

        if (optCar.isPresent()) {
            Car car = optCar.get();

            resp.getWriter().println("<html><body><b>Машина с ID " + id + ":<br>" + car.getDisplay() + " марки " + car.getCompany()
                    + "<br>" + "</b><br><a href=\"\\\">Вернуться</a></body></html>");
        } else {
            resp.getWriter().println("empty");
        }
    }
}
