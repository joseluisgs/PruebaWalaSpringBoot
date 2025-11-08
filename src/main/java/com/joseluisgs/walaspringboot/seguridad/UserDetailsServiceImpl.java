package com.joseluisgs.walaspringboot.seguridad;


import com.joseluisgs.walaspringboot.modelos.Usuario;
import com.joseluisgs.walaspringboot.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Indicamos que es uns ervicio de detalles de usuario
@Service("userDetailsService") // Es muy importante esta línea para decir que vamos a usar el servicio de usuarios Spring
public class UserDetailsServiceImpl implements UserDetailsService {

    // Indicamos que vamos a usar el repositorio de UsuarioRepository
    @Autowired
    UsuarioRepository repositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Buscamos el usuario
        Usuario usuario = repositorio.findFirstByEmail(username);

        // Construimos el builder los datos de acceso
       User.UserBuilder builder = null;

        if (usuario != null) {
            builder = User.withUsername(username); // Nuestro usernamoe
            builder.disabled(false); // No estemos desabilitados
            builder.password(usuario.getPassword()); //Nuestro password
            builder.authorities(new SimpleGrantedAuthority("ROLE_USER")); // Usamoes esta clase para no crear nostros las nuestras

        } else {
            // Si no lo encontramos lanzamos excepción
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return builder.build(); // Devolvemos el usuario contrsuido

    }
}
