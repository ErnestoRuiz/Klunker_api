package co.ReaperDev.repository;

import co.ReaperDev.repository.entity.CarEntity;
import co.ReaperDev.repository.entity.CostEntity;
import co.ReaperDev.repository.entity.ServiceEntity;
import co.ReaperDev.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Repository
public class ServiceRepository {
    private NamedParameterJdbcTemplate template;

    public void createService(ServiceEntity serviceEntity){
        log.info("ServiceRepository.createService()");
        String query = "insert into service(carId, servDetail, date, cost, servMileage) values(:carId, :servDetail, :date, :cost, :servMileage)";
        Map<String, Object> params = new HashMap<>();
        params.put("carId", serviceEntity.getCarId());
        params.put("servDetail", serviceEntity.getServDetail());
        params.put("date", serviceEntity.getDate());
        params.put("cost", serviceEntity.getCost());
        params.put("servMileage", serviceEntity.getServMileage());

        template.update(query, params);
    }

    public List<ServiceEntity> getServicesByCar(CarEntity carEntity){
        log.info("ServiceRepository.getServicesByCar");
        String query = "select serviceId, carId, servDetail, date, cost, servMileage from service where carId = :carId";
        Map<String, Object> params = new HashMap<>();
        params.put("carId", carEntity.getCarId());
        RowMapper<ServiceEntity> rowMapper = new BeanPropertyRowMapper<>(ServiceEntity.class);

        return template.query(query, params, rowMapper);
    }

    public List<ServiceEntity> getServicesByUser(UserEntity userEntity){
        log.info("ServiceRepository.setServicesByUser()");
        String query = "select service.serviceId, service.carId, service.servDetail, service.date, service.cost, service.servMileage" +
                " from service" +
                " left join car" +
                " on service.carId = car.carId" +
                " where car.userId = :userId";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userEntity.getUserId());
        RowMapper<ServiceEntity> rowMapper = new BeanPropertyRowMapper<>(ServiceEntity.class);

        return template.query(query, params, rowMapper);
    }

    public void deleteService(ServiceEntity entity){
        log.info("ServiceRepository.deleteService()");
        String query = "delete from service where serviceId = :serviceId";
        Map<String, Object> params = new HashMap<>();
        params.put("serviceId", entity.getServiceId());

        template.update(query, params);
    }

    public List<CostEntity> getListOfTotalCost(int carId){
        log.info("ServiceRepository.getTotalCosts()");
        String query = "select cost from service where carId = :carId";
        Map<String, Object> params = new HashMap<>();
        params.put("carId", carId);
        RowMapper<CostEntity> rowMapper = new BeanPropertyRowMapper<>(CostEntity.class);

        return template.query(query, params, rowMapper);
    }

    public List<CostEntity> getListOfPastYearCost(int carId){
        log.info("ServiceRepository.getMonthlyAverageOfYear()");
        LocalDate localDate = LocalDate.now();
        String query = "select cost from service where carId = :carId and date > date_add(:localDate, interval -1 year)";
        Map<String, Object> params = new HashMap<>();
        params.put("carId", carId);
        params.put("localDate", localDate);
        RowMapper<CostEntity> rowMapper = new BeanPropertyRowMapper<>(CostEntity.class);

        return template.query(query, params, rowMapper);
    }

    public List<CostEntity> getListOfCostForYear(int carId, int n){
        log.info("ServiceRepository.getListOfCostForYear()");

        log.info(String.valueOf(carId));

        LocalDate localDate = LocalDate.now();
        int currentYear = localDate.getYear();
        log.info("currentYear: " + currentYear);
        int year = currentYear - n;
        log.info("year:" + year);

        String query = "select cost from service where carId = :carId and year(date) = :year";

        Map<String, Object> params = new HashMap<>();
        params.put("carId", carId);
        params.put("year", year);

        RowMapper<CostEntity> rowMapper = new BeanPropertyRowMapper<>(CostEntity.class);

        log.info("template: " + template.query(query, params, rowMapper).toString());
        return template.query(query, params, rowMapper);
    }

    public List<CostEntity> currentYearCostList(int carId){
        log.info("ServiceService.currentYearCostList()");
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();

        String query = "select cost from service where carId = :carId and year(date) = :year";

        Map<String, Object> params = new HashMap<>();
        params.put("carId", carId);
        params.put("year", year);

        RowMapper<CostEntity> rowMapper = new BeanPropertyRowMapper<>(CostEntity.class);
        log.info("currentYearCostList: " + template.query(query, params, rowMapper).toString());
        return template.query(query, params, rowMapper);
    }

    public List<CostEntity> fiveYearTotalCost(int carId) {
        log.info("ServiceService.fiveYearTotalCost()");
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear() - 5;

        String query = "select cost from service where carId = :carId and date between :year and :localDate";

        Map<String, Object> params = new HashMap<>();
        params.put("carId", carId);
        params.put("year", year);
        params.put("localDate", localDate);

        RowMapper<CostEntity> rowMapper = new BeanPropertyRowMapper<>(CostEntity.class);

        return template.query(query, params, rowMapper);
    }
}
