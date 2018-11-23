package com.tmdaq.fileuploadweb.aop;

import com.tmdaq.fileuploadweb.common.PageUtil;
import com.tmdaq.fileuploadweb.common.ResultDo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Aspect
@Component
public class CountAspect {
    final
    EntityManager entityManager;
    final
    ApplicationContext applicationContext;
    final
    JpaRepository jpaRepository;

    @Autowired
    public CountAspect(EntityManager entityManager, ApplicationContext applicationContext, JpaRepository jpaRepository) {
        this.entityManager = entityManager;
        this.applicationContext = applicationContext;
        this.jpaRepository = jpaRepository;
    }

    @Pointcut("execution(* com.tmdaq.fileuploadweb.service.*.findAll(..))")
    public void pointCut() {

    }

    @Around(value = "pointCut()")
    public Object count(ProceedingJoinPoint joinPoint) {
        Object result = null;
        Object o = joinPoint.getArgs()[0];
        long count = 0L;
        if (o instanceof PageUtil) {
            Object data = ((PageUtil) o).getQuery();
            count = jpaRepository.count(Example.of(data));
            try {
                result = joinPoint.proceed();
                if (result instanceof ResultDo) {
                    PageUtil pageUtil = (PageUtil) ((ResultDo) result).getData();
                    pageUtil.setCount(count);
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return result;
    }
}
