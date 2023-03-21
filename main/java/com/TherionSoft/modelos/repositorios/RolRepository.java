package com.TherionSoft.modelos.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.TherionSoft.modelos.NombreRol;
import com.TherionSoft.modelos.Rol;

public interface RolRepository  extends CrudRepository<Rol, Long>{
	Optional<Rol> findByNombreRol(NombreRol nombreRol);

}
