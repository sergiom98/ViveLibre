package com.vivelibre.prueba.vivelibre.repository;

import com.vivelibre.prueba.vivelibre.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

    Usuario findFirstByCorreoAndPassword(String correo, String password);
}
