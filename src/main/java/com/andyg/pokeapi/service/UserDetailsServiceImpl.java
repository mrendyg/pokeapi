package com.andyg.pokeapi.service;

import com.andyg.pokeapi.persistence.entity.users.UserEntity;
import com.andyg.pokeapi.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; //procesamos los datos de nuestros usuarias a traves de
    // nuestra interface

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Configuracion de user details

        UserEntity userEntity = userRepository.findUserEntityByusername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username
                        + "no existe"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        //Tomamos los roles y los convertimos en objetos que obtiene spring security
        userEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_"
                        .concat(role.getRoleEnum().name()))));

        //hacemos lo mismo con authorities
        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));


        //devolvemos el ususario en un objeto que entienda spring scurity

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnable(),
                userEntity.isAccountNoLocked(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                authorityList);
    }
}