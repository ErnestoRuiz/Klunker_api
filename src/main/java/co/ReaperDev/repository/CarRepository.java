package co.ReaperDev.repository;

import co.ReaperDev.repository.entity.CarEntity;
import co.ReaperDev.repository.entity.CostEntity;
import co.ReaperDev.repository.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class CarRepository {
    private NamedParameterJdbcTemplate template;

    @Autowired
    public CarRepository(NamedParameterJdbcTemplate t){
        this.template = t;
    }
    public void createCar(CarEntity carEntity){
        log.info("CarRepository.createCar()");
        String query = "INSERT INTO car(userId, make, model, year, color, mpg) values(:userId, :make, :model, :year, :color, :mpg)";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", carEntity.getUserId());
        params.put("make", carEntity.getMake());
        params.put("model", carEntity.getModel());
        params.put("year", carEntity.getYear());
        params.put("color", carEntity.getColor());
        params.put("mpg", carEntity.getMpg());

        template.update(query, params);
    }

    public List<CarEntity> getCarsByUser(UserEntity userEntity){
        log.info("CarRepository.getCarsByUser()");
        String query = "select carId, userId, make, model, year, mpg from car where userId = :userId";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userEntity.getUserId());
        RowMapper <CarEntity> rowMapper = new BeanPropertyRowMapper<>(CarEntity.class);

        return template.query(query, params, rowMapper);
    }

    public void deleteCar(CarEntity carEntity){
        log.info("CarRepository.deleteCar()");
        String query = "delete from car where carId = :carId";
        Map<String, Object> params = new HashMap<>();
        params.put("carId", carEntity.getCarId());

        template.update(query, params);
    }

    public List<CostEntity> getListOfTotalCost(int carId){
        log.info("CarRepository.getTotalCosts()");
        String query = "select cost from service where carId = :carId";
        Map<String, Object> params = new HashMap<>();
        params.put("carId", carId);
        RowMapper<CostEntity> rowMapper = new BeanPropertyRowMapper<>(CostEntity.class);

        return template.query(query, params, rowMapper);
    }

    public List<CostEntity> getListOfYearCost(int carId){
        log.info("CarRepository.getMonthlyAverageOfYear()");
        LocalDate localDate = LocalDate.now();
        String query = "select cost from service where carId = :carId and date > date_add(:localDate, interval -1 year);";
        Map<String, Object> params = new HashMap<>();
        params.put("carId", carId);
        params.put("localDate", localDate);
        RowMapper<CostEntity> rowMapper = new BeanPropertyRowMapper<>(CostEntity.class);

        return template.query(query, params, rowMapper);
    }
}
