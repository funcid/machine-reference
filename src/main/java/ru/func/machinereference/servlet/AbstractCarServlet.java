package ru.func.machinereference.servlet;

import ru.func.machinereference.DatabaseConnection;
import ru.func.machinereference.dao.CarDao;

import javax.servlet.http.HttpServlet;

public abstract class AbstractCarServlet extends HttpServlet {
    protected CarDao carDao = DatabaseConnection.getCarDao();
}
