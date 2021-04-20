package com.example.demo.entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idproyecto;
    private String nombreproyecto;
    @Column(nullable = false)
    private String usuario_owner;

}
