package com.TherionSoft.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.TherionSoft.servicios.UsuarioService;
import com.TherionSoft.dto.PermisosDTO;
import com.TherionSoft.dto.RespuestaDTO;
import com.TherionSoft.dto.UsuarioDTO;
import com.TherionSoft.dto.UsuarioLoginDTO;
import com.TherionSoft.jwt.JwtProvider;
import com.TherionSoft.modelos.Rol;
import com.TherionSoft.modelos.Usuario;
import com.TherionSoft.modelos.repositorios.RolRepository;
import com.TherionSoft.modelos.repositorios.UsuarioRepository;
import com.TherionSoft.servicio.mapper.UsuarioMapper;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioMapper usuarioMapper;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
    private RolRepository rolRepository;

	@Override
	public UsuarioDTO crear(UsuarioDTO usuarioDTO) throws Exception  {

		Usuario usuario = usuarioMapper.toUsuario(usuarioDTO);
		usuario.setClave(passwordEncoder.encode(usuarioDTO.getClave()));
		Rol rol = rolRepository.findByNombreRol(usuarioDTO.getRol()).orElseThrow(()-> new Exception("No existe el rol en la base de datos, inserte primero"));
		usuario.setRol(rol);
		usuario = usuarioRepository.save(usuario);
		return usuarioMapper.toUsuarioDTO(usuario);
		
	}


	@Override
	public RespuestaDTO login(UsuarioLoginDTO usuarioLoginDTO) throws Exception  {
		
		UsuarioDTO usuarioDTO=new UsuarioDTO();
		RespuestaDTO respuestaDTO=new RespuestaDTO();
		
		try {
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getNombreUsuario(), usuarioLoginDTO.getClave()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		String token = JwtProvider.generarTokenJWT(usuarioLoginDTO.getNombreUsuario());
        Usuario usuario = usuarioRepository.buscarPorNombreUsuario(usuarioLoginDTO.getNombreUsuario()).orElse(null);
		 usuarioDTO = usuarioMapper.toUsuarioDTO(usuario);
		 usuarioDTO.setToken(token);
		 usuarioDTO.setMensaje("correcto");
		 //PermisosDTO permisosDTO=new PermisosDTO();
		// permisosDTO.setPermiso("administrado");
		// usuarioDTO.setPermisosDTO(permisosDTO);
		 respuestaDTO.setUsuarioDTO(usuarioDTO);
		 respuestaDTO.setToken(token);
		 
		}
		catch (BadCredentialsException e) {
			//usuarioDTO.setMensaje("incorrecto");
			
			// respuestaDTO.setUsuarioDTO(usuarioDTO);
			// respuestaDTO.setToken("");
			//return respuestaDTO;
            throw new Exception("Invalid username or password", e);
            
        }
		
		return respuestaDTO;
	}	

}

