package com.joseluisgs.walaspringboot.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/** EXPLICACION
 * Cada vez que queramos usar la autentificación
 * usara la implementación UserDetailService (UserDetailServiceImpl)
 * que localizará al usuario en la base de datos
 * Lo hara usando la contraseña cifrada por BCrypt
 */



@Configuration
@EnableWebSecurity
public class SeguridadConfig extends WebSecurityConfigurerAdapter {

    // Cableamos, o usamos nuestro servicio de deatlles de usuario
    @Autowired
    UserDetailsService userDetailsService;

    // Para la contraseña
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Indicamos los path o rutas que permitimos conectarse sin loguarse
                // Ojo hay que meter los CSS, imagenes y cosas estaticas
                .authorizeRequests()
                    .antMatchers("/", "/webjars/**","/images/**", "/css/**", "/h2-console/**", "/public/**", "/auth/**", "/files/**").permitAll()
                    .anyRequest().authenticated()
                    .and()

                // Indicamos que para cualquier otro nos vamos a identificar
                .formLogin()
                    .loginPage("/auth/login") // Ruta donde estará el formulario
                    .defaultSuccessUrl("/public/index", true) // Nos redirecciona si login correcto
                    .loginProcessingUrl("/auth/login-post") //Controlador para manejar el login
                    .permitAll()
                    .and()

                // Al realizar logout
                .logout()
                    .logoutUrl("/auth/logout") // Donde será la url para logout
                    .logoutSuccessUrl("/public/index"); // Donde nos llevará una vez hacer logoout

        // Desabilitamos porque queremos acceder a la consola de H2
        http.csrf().disable(); // Se
        http.headers().frameOptions().disable();
    }
}
