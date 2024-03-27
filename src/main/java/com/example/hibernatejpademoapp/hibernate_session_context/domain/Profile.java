package com.example.hibernatejpademoapp.hibernate_session_context.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "profile")
@Getter
@Setter
@ToString
public class Profile {

    @Id
    private long id;

    private String photoUrl;

    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    @ToString.Exclude
    private User user;

}
