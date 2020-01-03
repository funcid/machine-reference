package ru.func.machinereference.dao;

import ru.func.machinereference.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao {

    Optional<Car> findById(Integer id);

    List<Car> findByCompany(String company);

    Car save(Car car);
}
