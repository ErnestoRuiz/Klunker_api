package co.ReaperDev.service;

import co.ReaperDev.dto.CarDTO;
import co.ReaperDev.dto.ServiceDTO;
import co.ReaperDev.dto.UserDTO;
import co.ReaperDev.repository.ServiceRepository;
import co.ReaperDev.repository.entity.CarEntity;
import co.ReaperDev.repository.entity.ServiceEntity;
import co.ReaperDev.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ServiceService {
    private ServiceRepository repository;
    private MapperFacade mapper;

    public void createService(ServiceDTO serviceDTO){
        log.info("ServiceService.createService()");
        ServiceEntity entity = mapper.map(serviceDTO, ServiceEntity.class);
        repository.createService(entity);
    }

    public List<ServiceDTO> getServicesByCar(CarDTO carDTO){
        log.info("ServiceService.getServiceByCarId()");
        CarEntity carEntity = mapper.map(carDTO, CarEntity.class);
        List<ServiceEntity> entities = repository.getServicesByCar(carEntity);
        List<ServiceDTO> retval = new ArrayList<>();
        for(ServiceEntity s: entities){
            ServiceDTO serviceDTO = mapper.map(s, ServiceDTO.class);
            retval.add(serviceDTO);
        }
        return retval;
    }

    public List<ServiceDTO> getServicesByUser(UserDTO userDTO){
        log.info("ServiceService.getServiceByUser()");
        UserEntity userEntity = mapper.map(userDTO, UserEntity.class);
        List<ServiceEntity> entities = repository.getServicesByUser(userEntity);
        List<ServiceDTO> retval = new ArrayList<>();
        for(ServiceEntity s: entities){
            ServiceDTO serviceDTO = mapper.map(s, ServiceDTO.class);
            retval.add(serviceDTO);
        }
        return retval;
    }

    public void deleteService(ServiceDTO serviceDTO){
        log.info("deleteService endpoint hit");
        ServiceEntity entity = mapper.map(serviceDTO, ServiceEntity.class);
        repository.deleteService(entity);
    }
}
