package com.msvc.usuario.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private String userId;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "info")
    private String info;

    //esta etiqueta indica que no se va a guardar en base de datos
    @Transient
    private List<Calification> calificationList = new ArrayList<>();
}
