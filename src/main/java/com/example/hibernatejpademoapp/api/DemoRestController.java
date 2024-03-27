package com.example.hibernatejpademoapp.api;

import com.example.hibernatejpademoapp.hibernate_session_context.domain.Guild;
import com.example.hibernatejpademoapp.service.GuildService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoRestController {

    private GuildService guildService;

    public List<Guild> getAllGuilds(){


    }
}
