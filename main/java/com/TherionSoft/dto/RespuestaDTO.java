package com.TherionSoft.dto;

public class RespuestaDTO {
	
	private UsuarioDTO usuarioDTO;
	private String token;
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	

}
