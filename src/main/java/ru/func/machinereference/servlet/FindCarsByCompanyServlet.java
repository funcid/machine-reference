package ru.func.machinereference.servlet;

import ru.func.machinereference.GlobalObject;
import ru.func.machinereference.handler.AbstractCarHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author func 01.01.2020
 */
@WebServlet("/findCarsByCompany")
public class FindCarsByCompanyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        AbstractCarHandler carServlet = GlobalObject.context.getBean("findCarsByCompanyHandler", AbstractCarHandler.class);

        carServlet.doPost(req, resp);
    }
}
