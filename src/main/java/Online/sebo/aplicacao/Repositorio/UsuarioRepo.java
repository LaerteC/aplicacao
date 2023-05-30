package Online.sebo.aplicacao.Repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Online.sebo.aplicacao.Modelos.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Long>{

    void deleteUsuarioById(Long id);

    Optional<Usuario> findUsuarioById(Long id);

    Optional<Usuario> findUsuarioByEmail(String usuario);
    
}
