package com.example.ejercicio4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar el usuario en la base de datos
        Usuario usuario = entityManager.createQuery(
        		"SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Obtener los roles del usuario
        List<String> roles = entityManager.createQuery(
                "SELECT r.rol FROM Rol r WHERE r.usuarioId = :usuarioId", String.class)
                .setParameter("usuarioId", usuario.getId())
                .getResultList()
                .stream()
                .map(role -> {
                    String r = role.trim();
                    return r.startsWith("ROLE_") ? r.substring(5) : r;
                })
                .collect(Collectors.toList());

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword().trim())
                .roles(roles.toArray(new String[0]))
                .build();
    }
}