package br.org.generation.blogpessoal.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "O atributo Nome é Obrigatório!")
	private String nome;

	@NotNull(message = "O atributo Usuário é Obrigatório!")
	private String usuario;

	/**
	 * A anotação @Size está definida apenas com o valor min
	 * porque ao criptografar a senha a mesma terá uma tamanho
	 * muito maior (em numero de caracteres) do que a senha
	 * não ciptografada.
	 * 
	 * Exemplo: admin123 -> 8 caracteres
	 * admin123 criptografado -> 60 caracteres
	 */
	@NotNull(message = "O atributo Senha é Obrigatório!")
	@Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
	private String senha;

	/**
	 * CascadeType.REMOVE -> Ele propaga a operação de remoção de um objeto Pai para um 
	 * objeto Filho. 
	 * Apenas quando remover a Entidade Usuario, também será removida todas as entidades 
	 * Postagens associadas. Nas demais operações não haverá a propagação.
	 * 
	 * CascadeType.ALL -> Ele propaga todas a operações (Inserir, Listar, Atualizar e Apagar)
	 * de um objeto Pai para um objeto Filho. 
	 */
	@Column(name = "data_nascimento")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "O atributo Data de Nascimento é obrigatório")
	private LocalDate dataNascimento;
	
	private String foto;
	
	private String tipo;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;

	public Usuario(long id,String nome,String usuario,String senha) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public Usuario() {}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

}