package com.generation.blogpessoal.repositories;

import com.generation.blogpessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRespository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByUsuario(String usuario);
}
