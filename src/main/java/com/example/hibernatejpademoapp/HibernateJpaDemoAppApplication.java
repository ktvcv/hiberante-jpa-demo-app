package com.example.hibernatejpademoapp;

import com.example.hibernatejpademoapp.hibernate_session_context.service.LaunchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@SpringBootApplication
@RequiredArgsConstructor
@Component
public class HibernateJpaDemoAppApplication implements ApplicationContextAware {

    @Autowired
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(HibernateJpaDemoAppApplication.class, args);

        final LaunchService launchService = applicationContext.getBean(LaunchService.class);

        launchService.launch();

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
