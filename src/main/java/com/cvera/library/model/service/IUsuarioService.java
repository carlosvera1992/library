package com.cvera.library.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cvera.library.model.entity.Usuario;


public interface IUsuarioService {
	public Page<Usuario> obtenerTodos(Pageable pageable);

	public void guardarUsuario(Usuario usuario);

	public Usuario buscarUsuarioPorId(Long id);

	public void eliminarUsuarioPorId(Long id);
}
