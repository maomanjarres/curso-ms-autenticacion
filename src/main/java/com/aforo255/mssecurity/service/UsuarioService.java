package com.aforo255.mssecurity.service;


import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aforo255.mssecurity.dao.UsuarioDao;
import com.aforo255.mssecurity.model.Usuario;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioDao client;
    private Logger log = org.slf4j.LoggerFactory.getLogger(UsuarioService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Usuario usuario = client.findByUsername(username);
        if (usuario==null ) {throw new UsernameNotFoundException("Error login");}

        List<GrantedAuthority> authorities = usuario.getRoles().stream()

                .map(role-> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority-> log.info("Role: "+ authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(),true, true, true ,
                authorities);
    }

}