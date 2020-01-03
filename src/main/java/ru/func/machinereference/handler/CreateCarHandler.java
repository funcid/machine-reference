package ru.func.machinereference.handler;

import org.springframework.stereotype.Component;
import ru.func.machinereference.entity.Car;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component("createCar")
public class CreateCarHandler extends AbstractCarHandler {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
