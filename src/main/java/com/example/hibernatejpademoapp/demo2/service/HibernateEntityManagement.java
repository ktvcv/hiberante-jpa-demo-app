package com.example.hibernatejpademoapp.demo2.service;

import com.example.hibernatejpademoapp.hibernate_session_context.LaunchServiceTask;
import com.example.hibernatejpademoapp.hibernate_session_context.domain.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.FlushModeType;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HibernateEntityManagement implements LaunchServiceTask {

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public void launch() {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.unwrap(Session.class)
            .setDefaultReadOnly(true); // turned off dirty-checking
        // property for setting when to  sync data with db
        entityManager.setFlushMode(FlushModeType.AUTO);
        final Person person = entityManager.find(Person.class, 2L);

      //  entityManager.detach(person);// removes from the session
        entityManager.merge(person); // set to session
        entityManager.refresh(person); // update person from db
        entityManager.clear();// remove all from session

    }
}
