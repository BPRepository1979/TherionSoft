package com.TherionSoft.servicios;

import com.TherionSoft.dto.RespuestaDTO;
import com.TherionSoft.dto.UsuarioDTO;
import com.TherionSoft.dto.UsuarioLoginDTO;

public interface UsuarioService {
	public RespuestaDTO login(UsuarioLoginDTO usuarioLoginDTO) throws Exception;

	public UsuarioDTO crear(UsuarioDTO usuarioDTO) throws Exception;
}
