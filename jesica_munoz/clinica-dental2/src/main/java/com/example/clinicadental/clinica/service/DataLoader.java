package com.example.clinicadental.clinica.service;

import com.example.clinicadental.clinica.model.Usuario;
import com.example.clinicadental.clinica.model.UsuarioRol;
import com.example.clinicadental.clinica.repository.UsuarioRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UsuarioRepository usuarioRepository;

    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //encriptar password
        BCryptPasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();
        String password1 = passwordEncoder1.encode("password1");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String password2 = passwordEncoder2.encode("password2");


        //crear 2 usuarios
        Usuario usuario1 = new Usuario("Admin", "admin", "admin@digital.com", password1, UsuarioRol.ADMIN);
        Usuario usuario2 = new Usuario("User", "user", "user@digital.com", password2, UsuarioRol.USER);
        this.usuarioRepository.save(usuario1);
        this.usuarioRepository.save(usuario2);


    }
}
