package com.example.hibernatejpademoapp;

import com.example.hibernatejpademoapp.hibernate_session_context.service.HibernateSessionContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LaunchService {

    private final HibernateSessionContext hibernateSessionContext;

    public void launch() {
        hibernateSessionContext.launch();
    }

}
