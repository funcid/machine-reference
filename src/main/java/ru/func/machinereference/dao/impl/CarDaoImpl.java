package ru.func.machinereference.dao.impl;

import ru.func.machinereference.DatabaseConnection;
import ru.func.machinereference.GlobalObject;
import ru.func.machinereference.dao.CarDao;
import ru.func.machinereference.entity.Car;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {
    private Connection connection = GlobalObject.getContext().getBean("connection", DatabaseConnection.class).getConnection();

    @Override
    public Optional<Car> findById(Integer id) {
        Optional<Car> optionalCar = Optional.empty();

        try (PreparedStatement st = connection.prepareStatement("select * from cars where id=?")) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    optionalCar = Optional.of(convert(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); //FIXME
        }

        return optionalCar;
    }

    @Override
    public List<Car> findByCompany(String company) {
        List<Car> resultList = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement("select * from cars where company=?")) {
            st.setString(1, company);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    resultList.add(convert(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); //FIXME
        }

        return resultList;
    }

    @Override
    public Car save(Car car) {
        try (PreparedStatement st = connection.prepareStatement(
                "insert into cars(display, company) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, car.getDisplay());
            st.setString(2, car.getCompany());

            st.executeUpdate();

            try (ResultSet rs = st.getGeneratedKeys()) {
                rs.next();
                car.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return car;
    }

    private Car convert(ResultSet resultSet) throws SQLException {
        return Car.builder()
                .id(resultSet.getInt("id"))
                .company(resultSet.getString("company"))
                .display(resultSet.getString("display"))
                .build();
    }
}
