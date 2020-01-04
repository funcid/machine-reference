package ru.func.machinereference.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ru.func.machinereference.dao.CarDao;
import ru.func.machinereference.entity.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CarDaoImpl implements CarDao {
    private static final String SELECT_BY_ID = "select * from cars where id = ?";
    private static final String SELECT_BY_COMPANY = "select * from cars where company = ?";
    private static final String INSERT = "insert into cars(display, company) values (?, ?)";

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Car> findById(Integer id) {
        AtomicReference<Car> refCar = new AtomicReference<>();

        jdbcOperations.query(SELECT_BY_ID,
                ps -> ps.setInt(1, id),
                rs -> { refCar.set(convert(rs)); }
        );

        return Optional.ofNullable(refCar.get());
    }

    @Override
    public List<Car> findByCompany(String company) {
        return jdbcOperations.query(SELECT_BY_COMPANY,
                ps -> ps.setString(1, company),
                (rs, i) -> convert(rs)
        );
    }

    @Override
    public Car save(Car car) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int affectedRow = jdbcOperations.update(psc -> {
            try (PreparedStatement ps = psc.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, car.getDisplay());
                ps.setString(2, car.getCompany());

                return ps;
            }
        }, keyHolder);

        if (affectedRow > 0) {
            car.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
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
