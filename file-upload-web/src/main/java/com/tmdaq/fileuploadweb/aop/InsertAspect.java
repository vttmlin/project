package com.tmdaq.fileuploadweb.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;

@Aspect
@Component
public class InsertAspect {

    private final SimpleJpaRepository repository;
    private final EntityManager entityManager;

    @Autowired
    public InsertAspect(SimpleJpaRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Pointcut("execution(* com.tmdaq.fileuploadweb.service.*Service.save(..))")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    @Transactional
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;

        try {
            Object[] args = joinPoint.getArgs();
            Object proceed = joinPoint.proceed();
            if (proceed != null) {
//                Object save = get(args[0].getClass()).save(args[0]);
//                System.out.println(save);
                entityManager.merge(args[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public <T> SimpleJpaRepository get(Class<T> cls) {
        TransactionSynchronizationManager.setActualTransactionActive(true);
        return new SimpleJpaRepository<T, Long>(cls, entityManager);
    }
}
