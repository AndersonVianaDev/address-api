package com.anderson.address_api.shared.config;

import com.anderson.address_api.adapters.address.repositories.AddressAdapterRepository;
import com.anderson.address_api.adapters.address.services.ConsultZipCodeAdapter;
import com.anderson.address_api.core.services.AddressService;
import com.anderson.address_api.core.services.impl.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class BeanConfiguration {

    @Value("${spring.host.redis}")
    private String hostRedis;

    @Value("${spring.port.redis}")
    private int portRedis;

    @Bean
    public AddressService addressService(AddressAdapterRepository repository, ConsultZipCodeAdapter consultZipCode) {
        return new AddressServiceImpl(repository, consultZipCode);
    }

    @Bean
    public Jedis jedis() {
        return new Jedis(hostRedis, portRedis);
    }
}
