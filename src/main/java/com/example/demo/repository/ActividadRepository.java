package com.example.demo.repository;

import com.example.demo.entity.Actividad;
import com.example.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Integer> {

    List<Actividad> findByIdproyecto(int id);

}
