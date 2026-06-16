package com.devgraph.userservice.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name="profiles")
@Data
public class Profile {
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String bio;
    private String githubUrl;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",nullable=false)
    private User user;
}
