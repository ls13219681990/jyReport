package com.common.logger;

import java.lang.reflect.Method;

import org.hibernate.SessionFactory;
import org.springframework.aop.MethodBeforeAdvice;

public class LoggerManager implements MethodBeforeAdvice {
    private SessionFactory sessionFactory;

    @Override
    public void before(Method method, Object[] args, Object target)
            throws Throwable {
        if (method.getName().startsWith("find")) {//查询不做记录
            return;
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
