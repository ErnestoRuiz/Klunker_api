package co.ReaperDev.repository;

import co.ReaperDev.repository.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class UserRepository {
    private NamedParameterJdbcTemplate template;

    @Autowired
    public UserRepository(NamedParameterJdbcTemplate t){
        this.template = t;
    }

    public void createUser(UserEntity entity){
        log.info("UserRepository.createUser()");
        String query = "INSERT into user(userName, password) VALUES(:email, :password)";
        Map<String, Object> params = new HashMap<>();
        params.put("email", entity.getUserName());
        params.put("password", entity.getPassword());

        template.update(query, params);
    }

    public UserEntity userLogin(UserEntity entity){
        log.info("UserRepository.userLogin()");
        String query = "select userId, userName from user WHERE userName = :userName and password = :password";
        Map<String, Object> params = new HashMap<>();
        params.put("userName", entity.getUserName());
        params.put("password", entity.getPassword());
        RowMapper<UserEntity> rowMapper = new BeanPropertyRowMapper<>(UserEntity.class);

        return template.queryForObject(query, params, rowMapper);
    }
}
