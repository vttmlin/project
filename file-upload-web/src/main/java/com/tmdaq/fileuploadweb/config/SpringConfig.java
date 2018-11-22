package com.tmdaq.fileuploadweb.config;

import com.tmdaq.fileuploadweb.common.BaseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class SpringConfig {
    private final EntityManager entityManager;

    @Autowired
    public SpringConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Bean
    public SimpleJpaRepository<BaseBean,Long> getSimpleJpaRepository(EntityManager entityManager) {
        return new SimpleJpaRepository<BaseBean,Long>(BaseBean.class, entityManager);
    }
}
