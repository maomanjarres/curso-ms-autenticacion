package com.aforo255.mssecurity.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aforo255.mssecurity.model.Usuario;

public interface UsuarioDao  extends CrudRepository<Usuario, Long>{

    public Usuario findByUsername (@Param ("nombre") String Username);
}