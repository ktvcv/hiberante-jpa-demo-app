package com.example.hibernatejpademoapp.hibernate_session_context.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "PERSON")
@Table(name = "person")
@Accessors(chain = true)
@ToString
public class Person {

    @Id
    // database generates id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    // sequence generated
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secGen")
//    @SequenceGenerator(name = "secGen", sequenceName = "seq_person", allocationSize = 1)

    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "person",
        cascade = CascadeType.PERSIST,
        fetch = FetchType.EAGER
    )
    @ToString.Exclude
    private List<Note> noteList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(final List<Note> noteList) {
        this.noteList = noteList;
    }
}
