package Online.sebo.aplicacao.Controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Online.sebo.aplicacao.Modelos.Login;
import Online.sebo.aplicacao.Modelos.Usuario;
import Online.sebo.aplicacao.Servicos.UsuarioServico;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/usuario")
public class UsuarioControler {
    private final UsuarioServico usuarioServico;

    public UsuarioControler(UsuarioServico usuarioServico) {
        this.usuarioServico = usuarioServico;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioServico.findAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id) {
        Usuario usuario = usuarioServico.findUsuarioById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        Usuario novousuario = usuarioServico.addUsuario(usuario);
        return new ResponseEntity<>(novousuario, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {
        Usuario updateusuario = usuarioServico.upodateUsuario(usuario);
        return new ResponseEntity<>(updateusuario, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") Long id) {
        usuarioServico.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<Usuario> loginUsuario(@RequestBody Login login) {
        Usuario logado = usuarioServico.validaLogin(login);
        return new ResponseEntity<>(logado, HttpStatus.OK);
    }
}
