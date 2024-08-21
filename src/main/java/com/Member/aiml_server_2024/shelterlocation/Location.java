package com.Member.aiml_server_2024.shelterlocation;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    private String name;
    private int age;
}
