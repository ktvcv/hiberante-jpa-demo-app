package com.example.hibernatejpademoapp.hibernate_session_context.service;

import com.example.hibernatejpademoapp.hibernate_session_context.LaunchServiceTask;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LaunchService {

    private final List<LaunchServiceTask> services;

    public void launch() {
        services
        .forEach(LaunchServiceTask::launch);
    }

}
