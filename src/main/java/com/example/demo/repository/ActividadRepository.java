package com.example.demo.repository;

import com.example.demo.entity.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ActividadRepository extends JpaRepository<Actividad,Integer> {



}
