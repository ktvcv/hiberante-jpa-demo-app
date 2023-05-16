package com.example.hibernatejpademoapp.relations3;

import com.example.hibernatejpademoapp.hibernate_session_context.LaunchServiceTask;
import com.example.hibernatejpademoapp.hibernate_session_context.domain.Note;
import com.example.hibernatejpademoapp.hibernate_session_context.domain.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class RelationsService implements LaunchServiceTask {

    private final EntityManagerFactory entityManagerFactory;

    public void launch() {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        doInTheSession(entityManage1 -> {
            var person1 = new  Person();
            person1.setName("Julia");

            var note = new Note();
            note.setText("Test");
            person1.getNoteList().add(note);

            entityManage1.persist(person1);

        });

        doInTheSession(entityManage1 -> {
            var personByName = entityManage1.createQuery(
                "SELECT p FROM PERSON p WHERE p.name = :name", Person.class
            ).setParameter("name", "Julia")
                .getSingleResult();

            personByName.getNoteList()
                .forEach(System.out::println);
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
}
