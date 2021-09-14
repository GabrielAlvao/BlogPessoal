package br.org.generation.blogpessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//Declarando que se trata de uma classe model e nomeando a tabela
@Entity
@Table(name = "tb_postagem")
public class Postagem {
	//Declarando  a variavel como id 
	@Id
	//fazendo anotação para ocorrer auto-incrementação no id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//Obrigando a preencher com a anotação notnull e declarando um valor minimo e maximo com a anotação size
	@NotNull(message = "O atributo título é obrigatório!")
	@Size(min = 5, max = 100, message = "O atributo título deve ter no mínimo 5 caracteres e no máximo 100")
	private String titulo;
	
	@NotNull(message = "O atributo texto é obrigatório!")
	@Size(min = 5, max = 1000, message = "O atributo texto deve ter no mínimo 5 caracteres e no máximo 1000")
	private String texto;
	//anotação para mostrar que se trata de um timestamp no banco de dados
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());
	//Fazendo o relacionamento entre as outras tabelas
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

}
