package co.ReaperDev.service;

import co.ReaperDev.dto.CarDTO;
import co.ReaperDev.dto.UserDTO;
import co.ReaperDev.repository.CarRepository;
import co.ReaperDev.repository.entity.CarEntity;
import co.ReaperDev.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import javax.swing.text.Caret;
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
}
