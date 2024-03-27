package com.example.hibernatejpademoapp.hibernate_session_context.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    private int id;

    private String city;

    private String street;

    private String apt;

    @ToString.Exclude
    @OneToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
