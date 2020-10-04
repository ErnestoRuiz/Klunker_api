package co.ReaperDev.repository;

import co.ReaperDev.repository.entity.CarEntity;
import co.ReaperDev.repository.entity.ServiceEntity;
import co.ReaperDev.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
