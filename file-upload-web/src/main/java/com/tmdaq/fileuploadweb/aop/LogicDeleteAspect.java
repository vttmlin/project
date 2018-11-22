package com.tmdaq.fileuploadweb.aop;

import com.tmdaq.fileuploadweb.common.BaseBean;
import com.tmdaq.fileuploadweb.common.Code;
import com.tmdaq.fileuploadweb.common.ResultDo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.Table;

@Aspect
@Component
public class LogicDeleteAspect {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LogicDeleteAspect(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Pointcut("execution(* com.tmdaq.fileuploadweb.service.*Service.logicDelete(..))")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            Object[] args = joinPoint.getArgs();
            Object proceed = joinPoint.proceed();
            if (proceed != null) {
                BaseBean arg = (BaseBean) args[0];
                String name = arg.getClass().getAnnotation(Table.class).name();
                int update = jdbcTemplate.update("UPDATE " + name + " SET is_delete=FALSE WHERE ID='" + arg + "'");
                if (update == 1) {
                    result= new ResultDo<BaseBean>(arg, Code.SUCCESS);
                }else {
                    result= new ResultDo<BaseBean>(arg, Code.ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
