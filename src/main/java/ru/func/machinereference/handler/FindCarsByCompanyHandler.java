package ru.func.machinereference.handler;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component("findCarsByCompanyHandler")
public class FindCarsByCompanyHandler extends AbstractCarHandler {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
