package br.com.saturno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.saturno.model.Usuario;;

public interface Usuarios extends JpaRepository<Usuario, Long> {

}
