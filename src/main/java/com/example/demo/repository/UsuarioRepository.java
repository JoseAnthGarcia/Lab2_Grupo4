package com.example.demo.repository;

import com.example.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    List<Usuario> findByIdarea(int id);

}
