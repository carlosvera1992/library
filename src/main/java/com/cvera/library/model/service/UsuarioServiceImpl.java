package com.cvera.library.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cvera.library.model.dao.UsuarioDAO;
import com.cvera.library.model.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> obtenerTodos(Pageable pageable) {
		return usuarioDAO.findAll(pageable);
	}

	@Override
	@Transactional
	public void guardarUsuario(Usuario usuario) {
		usuarioDAO.save(usuario);

	}

	@Override
	public Usuario buscarUsuarioPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarUsuarioPorId(Long id) {
		// TODO Auto-generated method stub

	}

}
