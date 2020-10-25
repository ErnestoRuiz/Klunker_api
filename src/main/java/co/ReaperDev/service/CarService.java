package co.ReaperDev.service;

import co.ReaperDev.dto.CarDTO;
import co.ReaperDev.dto.ServicesMetrics;
import co.ReaperDev.dto.UserDTO;
import co.ReaperDev.repository.CarRepository;
import co.ReaperDev.repository.entity.CarEntity;
import co.ReaperDev.repository.entity.CostEntity;
import co.ReaperDev.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CarService {
    private MapperFacade mapper;
    private CarRepository repository;

    public void createCar(CarDTO carDTO){
        log.info("CarService.createCar()");
        CarEntity carEntity = mapper.map(carDTO, CarEntity.class);
        repository.createCar(carEntity);
    }

    public List<CarDTO> getCarsByUser(UserDTO userDTO){
        log.info("CarService.getCarsByUser()");
        UserEntity userEntity = mapper.map(userDTO, UserEntity.class);
        List<CarEntity> entities = repository.getCarsByUser(userEntity);
        List<CarDTO> retval = new ArrayList<>();
        for (CarEntity c: entities){
            CarDTO carDTO = mapper.map(c, CarDTO.class);
            retval.add(carDTO);
        }
        return retval;
    }

    public void deleteCar(CarDTO carDTO){
        log.info("CarService.deleteCar()");
        CarEntity carEntity = mapper.map(carDTO, CarEntity.class);
        repository.deleteCar(carEntity);
    }

    public static double totalCost(List<CostEntity> list){
        double retval = 0;
        for(CostEntity c: list){
            retval = retval + c.getCost();
        }
        BigDecimal bigDecimal = new BigDecimal(retval).setScale(2, RoundingMode.HALF_UP);
        double rounded = bigDecimal.doubleValue();
        return rounded;
    }

    public static double getMonthlyAverageOfYear(double d){
        double retval = d/12;
        BigDecimal bigDecimal = new BigDecimal(retval).setScale(2, RoundingMode.HALF_UP);
        double rounded = bigDecimal.doubleValue();
         return rounded;
    }

    public ServicesMetrics getCostOfServices(CarDTO carDTO){
        log.info("CarService.getCostOfServices()");
        CarEntity carEntity = mapper.map(carDTO, CarEntity.class);
        List<CostEntity> totalCostList = repository.getListOfTotalCost(carEntity.getCarId());
        List<CostEntity> totalYearCostList = repository.getListOfYearCost(carEntity.getCarId());

        ServicesMetrics servicesMetrics = new ServicesMetrics();
        servicesMetrics.setTotalCost(totalCost(totalCostList));
        servicesMetrics.setYearTotalCost(totalCost(totalYearCostList));
        servicesMetrics.setMonthlyAverageOfYear(getMonthlyAverageOfYear(totalCost(totalYearCostList)));

        return servicesMetrics;

    }
}
