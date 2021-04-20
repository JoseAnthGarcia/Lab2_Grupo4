package com.example.demo.repository;

import com.example.demo.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

    @Query(value = "select sum(peso) from actividades group by estado having estado = 1;",
            nativeQuery = true)
    int pesosActividadesFinalizados();

    @Query(value = "select sum(peso) from actividades;", nativeQuery = true)
    int pesosTodasActividades();
}
