package com.example.hibernatejpademoapp.hibernate_session_context.service;

import com.example.hibernatejpademoapp.hibernate_session_context.domain.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class HibernateSessionContext {

    private final EntityManagerFactory entityManagerFactory;

    public void launch() {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        final Person person = entityManager.find(Person.class, 1);
        final Person duplicate = entityManager.find(Person.class, 1);
        System.out.println(person);
        System.out.println(person == duplicate);
        entityManager.remove(person);

        final Person removed = entityManager.find(Person.class, 1);

        System.out.println(removed);
        entityManager.getTransaction().rollback();

        final Person rollback = entityManager.find(Person.class, 1);

        System.out.println(rollback);
        System.out.println(person == rollback);

        doInTheSession(entityManager1 -> {
            final var person1 = entityManager.find(Person.class, 1L);
            final var person1TheSame = entityManager.find(Person.class, 1L);

            System.out.println(person1 == person1TheSame);
        });

        var person1Session = doInTheSessionFunction(entityManager1 -> entityManager.find(Person.class, 1L));

        var person2Session = doInTheSessionFunction(entityManager1 -> entityManager.find(Person.class, 1L));

        System.out.println(person1Session == person2Session);

        entityManager.getTransaction().begin();
        var personByName = entityManager.createQuery(
            "SELECT p FROM PERSON p WHERE p.name = :name", Person.class
        ).setParameter("name", "Viktoriia")
            .getSingleResult();

        System.out.println(personByName == person1Session);
        personByName.setName("Vickie");
        entityManager.getTransaction().commit();

        //out of the session
        personByName.setName("Vickie update");

        //not executing because of belonging to other session
        doInTheSession(entityManager1 -> entityManager1.remove(personByName));

        doInTheSession(entityManager1 -> {
            final Person personToDelete = entityManager1.find(Person.class, 1);
            entityManager1.merge(personToDelete);
            entityManager1.remove(personToDelete);
        });
    }

    public void doInTheSession(final Consumer<EntityManager> entityManagerConsumer) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManagerConsumer.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public <T> T doInTheSessionFunction(final Function<EntityManager, T> entityManagerConsumer) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            var result = entityManagerConsumer.apply(entityManager);
            entityManager.getTransaction().commit();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
