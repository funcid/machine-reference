package ru.func.machinereference.handler;

import ru.func.machinereference.DatabaseConnection;
import ru.func.machinereference.dao.CarDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractCarHandler {
    protected CarDao carDao = DatabaseConnection.getCarDao();

    public abstract void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    static {
        try {
            Class.forName("ru.func.machinereference.GlobalObject");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
