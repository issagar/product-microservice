package com.msvc.usuario.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Calification {
    private String calificationId;
    private String userId;
    private String hotelId;
    private int calification;
    private String observations;

    private Hotel hotel;
}
