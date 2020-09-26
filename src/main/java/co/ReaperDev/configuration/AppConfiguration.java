package co.ReaperDev.configuration;

import co.ReaperDev.dto.UserDTO;
import co.ReaperDev.repository.entity.UserEntity;
import ma.glasnost.orika.DefaultFieldMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public MapperFactory mapperFactory(){
        DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .dumpStateOnException(false)
                .useBuiltinConverters(true)
                .build();

        mapperFactory.classMap(UserDTO.class, UserEntity.class)
                .mapNulls(true)
                .byDefault()
                .register();

        mapperFactory.classMap(UserEntity.class, UserDTO.class)
                .mapNulls(true)
                .byDefault()
                .register();

        return mapperFactory;
    }

    @Bean
    public MapperFacade mapper(MapperFactory mapperFactory) {
        return mapperFactory.getMapperFacade();
    }

}
