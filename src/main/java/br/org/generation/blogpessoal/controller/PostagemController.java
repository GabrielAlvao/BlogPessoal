package br.org.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogpessoal.model.Postagem;
import br.org.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagem")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository postagemRepository;
	//Criando metodo para buscar todas as postagens
	@GetMapping
	public ResponseEntity <List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	//Criando metodo para buscar postagens pelo id
	@GetMapping("idifelse/{id}")
	public ResponseEntity <Postagem> getByIdIfElse(@PathVariable long id){
		Optional<Postagem> postagem = postagemRepository.findById(id);
		//Fazendo um retorno de ok para postagens presentes
		if (postagem.isPresent()) {
			return ResponseEntity.ok(postagem.get());
		}
		//fazendo retorno de postagem não encontrada caso ocorrá
		return ResponseEntity.notFound().build();	
		}
	//Buscamos as postagens pelo id mas neste caso utilizamos lambda para simplificar o código acima ao invés de usar if
	@GetMapping("/{id}")
	public ResponseEntity <Postagem> getById(@PathVariable long id){
		return postagemRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	//Buscamos as Postagens pelo titulo
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity <List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	//Metodo para fazer uma postagem e salva-lá como um objeto no banco de dados
	@PostMapping
	public ResponseEntity <Postagem> postPostagem (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	}
	//Metodo para alterar uma postagem e salvar no nosso banco de dados
	@PutMapping
	public ResponseEntity <Postagem> putPostagem (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
	}
	//Metodo para deletar uma postagem atráves do id
	@DeleteMapping("/{id}")
	public void deletePostagem(@PathVariable long id) {
		postagemRepository.deleteById(id);
	}
		
}
