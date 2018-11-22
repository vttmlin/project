package com.tmdaq.fileuploadweb.aop;

import com.tmdaq.fileuploadweb.common.BaseBean;
import com.tmdaq.fileuploadweb.common.Code;
import com.tmdaq.fileuploadweb.common.ResultDo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import java.io.Serializable;

@Aspect
@Component
public class InsertAspect {

    private final EntityManager manager;

    @Autowired
    public InsertAspect(EntityManager manager) {
        this.manager = manager;
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
                Session session = manager.unwrap(Session.class);
                Serializable save = session.save(args[0]);
                if(save instanceof Long){
                    result = new ResultDo<BaseBean>((new BaseBean().setId((Long) save)));
                }else {
                    result = new ResultDo<BaseBean>(null, Code.ERROR);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
