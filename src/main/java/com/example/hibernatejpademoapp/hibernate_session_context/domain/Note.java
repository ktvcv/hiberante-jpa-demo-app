package com.example.hibernatejpademoapp.hibernate_session_context.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "note")
@Table(name = "note")
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Note {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private int id;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;


}
