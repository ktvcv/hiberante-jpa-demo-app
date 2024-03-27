package com.example.hibernatejpademoapp.repo;

import com.example.hibernatejpademoapp.hibernate_session_context.domain.Guild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuildRepository extends JpaRepository<Guild, Integer> {
}
