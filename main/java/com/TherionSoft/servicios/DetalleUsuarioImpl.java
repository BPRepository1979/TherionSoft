package com.TherionSoft.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.TherionSoft.modelos.Usuario;
import com.TherionSoft.modelos.repositorios.UsuarioRepository;

@Service
public class DetalleUsuarioImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		
		Usuario	usuario = usuarioRepository.buscarPorNombreUsuario(username).orElseThrow(() -> new UsernameNotFoundException("Credenciales no son corretas"));
		
		 
		return usuario;
	}
}
