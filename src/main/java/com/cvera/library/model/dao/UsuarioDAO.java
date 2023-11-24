package com.cvera.library.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvera.library.model.entity.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {

}
