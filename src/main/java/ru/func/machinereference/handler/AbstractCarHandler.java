package ru.func.machinereference.handler;

import org.springframework.stereotype.Component;
import ru.func.machinereference.DatabaseConnection;
import ru.func.machinereference.dao.CarDao;
import ru.func.machinereference.dao.impl.CarDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("abstractCar")
public abstract class AbstractCarHandler {

    private CarDao carDao;

    public abstract void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    static {
        try {
            Class.forName("ru.func.machinereference.GlobalObject");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    CarDao getCarDao() {
        if (carDao == null) {
            carDao = new CarDaoImpl();
        }
        return carDao;
    }
}
