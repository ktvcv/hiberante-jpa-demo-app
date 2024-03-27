package com.example.hibernatejpademoapp.hibernate_session_context.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class Guild {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "guilds", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(final Employee employee){
        this.employees.add(employee);
        employee.getGuilds().add(this);
    }
}
