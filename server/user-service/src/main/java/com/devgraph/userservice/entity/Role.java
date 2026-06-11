package com.devgraph.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false,unique=true)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private java.util.Set<User> users = new java.util.HashSet<>();

}
