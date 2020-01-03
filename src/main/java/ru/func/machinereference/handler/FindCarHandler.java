package ru.func.machinereference.handler;

import org.springframework.stereotype.Component;
import ru.func.machinereference.GlobalObject;
import ru.func.machinereference.entity.Car;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component("findCarHandler")
public class FindCarHandler extends AbstractCarHandler {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html; charset=UTF-8");

        final Integer id = Integer.valueOf(req.getParameter("id"));

        Optional<Car> optCar = GlobalObject.getContext().getBean("abstractCar", AbstractCarHandler.class).getCarDao().findById(id);

        if (optCar.isPresent()) {
            Car car = optCar.get();

            resp.getWriter().println("<html><body><b>Машина с ID " + id + ":<br>" + car.getDisplay() + " марки " + car.getCompany()
                    + "<br>" + "</b><br><a href=\"\\\">Вернуться</a></body></html>");
        } else {
            resp.getWriter().println("empty");
        }
    }
}
