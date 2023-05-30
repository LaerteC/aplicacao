package Online.sebo.aplicacao.Servicos;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Online.sebo.aplicacao.Erros.UserNotFoundException;
import Online.sebo.aplicacao.Modelos.Login;
import Online.sebo.aplicacao.Modelos.Usuario;
import Online.sebo.aplicacao.Repositorio.UsuarioRepo;

@Service
public class UsuarioServico {
    private final UsuarioRepo usuarioRepo;

    @Autowired
    public UsuarioServico(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public Usuario addUsuario(Usuario usuario) {
        usuario.setCodUsu(UUID.randomUUID().toString());
        return usuarioRepo.save(usuario);
    }

    public List<Usuario> findAllUsuarios() {
        return usuarioRepo.findAll();
    }

    public Usuario upodateUsuario(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepo.deleteUsuarioById(id);
    }

    public Usuario findUsuarioById(Long id) {
        return usuarioRepo.findUsuarioById(id).orElseThrow(() -> new UserNotFoundException("Usuario não encontrado"));
    }

    public Usuario validaLogin(Login login) {
        Usuario usu = usuarioRepo.findUsuarioByEmail(login.getUsuario())
                .orElseThrow(() -> new UserNotFoundException("Usuario nao encontrado"));
        if(usu.getSenha().equals(login.getSenha())){
            return usu;
        }
        usu = null;
        return usu;
    }
}
