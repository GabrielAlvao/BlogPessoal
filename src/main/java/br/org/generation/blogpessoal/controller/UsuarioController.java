package br.org.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogpessoal.model.Usuario;
import br.org.generation.blogpessoal.model.UsuarioLogin;
import br.org.generation.blogpessoal.repository.UsuarioRepository;
import br.org.generation.blogpessoal.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	/**
	 * Faz uma injeção de dependência da classe de Serviço UsuarioService
	 * para ter acesso aso métodos do CRUD
	 */
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 * Executa o método autenticarUsuario da classe de serviço para efetuar
	 * o login na api. O método da classe Controladora checa se deu certo e
	 * exibe as mensagens (Response Status) pertinentes. 
	 * 
	 * Caso o login tenha sido bem sucedido, os dados do usuário e o token 
	 * são exibidos.
	 */
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> login(@RequestBody Optional<UsuarioLogin> user) {
		return usuarioService.logarUsuario(user)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	/**
	 * Executa o método cadastrarUsuario da classe de serviço para criar
	 * um novo usuário na api. O método da classe Controladora checa se 
	 * deu certo e exibe as mensagens (Response Status) pertinentes. 
	 * 
	 * Caso cadastro tenha sido bem sucedido, os dados do usuário são 
	 * exibidos.
	 */
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario) {

		Optional<Usuario> usuarioResp = usuarioService.cadastrarUsuario(usuario);
		try {
			return ResponseEntity.ok(usuarioResp.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	/**
	 * Executa o método atualizarUsuario da classe de serviço para atualizar
	 * os dados de um usuário na api. O método da classe Controladora checa 
	 * se deu certo e exibe as mensagens (Response Status) pertinentes. 
	 * 
	 * Caso a atualização tenha sido bem sucedida, os dados do usuário 
	 * atualizados são exibidos.
	 */
	@PutMapping("/alterar")
	public ResponseEntity<Usuario> putUsuario(@RequestBody Usuario usuario) {
		Optional<Usuario> usuarioResp = usuarioService.atualizarUsuario(usuario);
		try {
			return ResponseEntity.ok(usuarioResp.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	@GetMapping("/all")
	public ResponseEntity <List<Usuario>> getUsuario(){
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
}