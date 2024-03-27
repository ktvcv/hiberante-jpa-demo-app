package com.example.hibernatejpademoapp.hibernate_session_context.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany
    @JoinTable(
        name = "employee_guilds",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "guild_id")
    )
    private List<Guild> guilds = new ArrayList<>();

    public void addGuild(final Guild guild){
        this.guilds.add(guild);
        guild.getEmployees().add(this);
    }
}
