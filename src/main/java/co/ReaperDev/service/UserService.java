package co.ReaperDev.service;

import co.ReaperDev.dto.UserDTO;
import co.ReaperDev.repository.UserRepository;
import co.ReaperDev.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private UserRepository repository;
    private MapperFacade mapper;

    @Autowired
    public UserService(UserRepository r){
        this.repository = r;
    }

    public void createUser(UserDTO userDTO){
        log.info("UserService.createUser(" + userDTO.toString() + ")");
        UserEntity entity = new UserEntity(userDTO.getEmail(), userDTO.getUserName(), userDTO.getPassword());
        repository.createUser(entity);
    }
    public UserDTO userLogin(UserDTO userDTO){
        log.info("UserService.userLogin()");
        log.info(userDTO.toString());
        UserEntity entity =  new UserEntity(userDTO.getEmail(), userDTO.getUserName(), userDTO.getPassword());
        log.info(entity.toString());
        log.info("entity mapped");

        UserDTO retval = new UserDTO(repository.userLogin(entity).getUserId(), repository.userLogin(entity).getUserName() );
        return retval;
    }
}
