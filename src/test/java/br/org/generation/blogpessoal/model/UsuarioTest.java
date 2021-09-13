package br.org.generation.blogpessoal.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;



/* Se estiver com aplicação rodando procurara outra porta
 * livre para realizar os testes e nao terá de ficar 
 * configurando para usar outra porta
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioTest {
	/*criar objetos por não ter o spring para isso
	 * (por isso criamos construtores para instanciar objetos
	 * nesta área de testes)
	 * primeiro objeto usado o construtor com parametro
	 * e o segundo usado o construtor sem parametros
	 */
	public Usuario usuario;
	public Usuario usuarioErro = new Usuario();
	
	//Injeção de dependencia 
	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	//Instanciando objeto para receber factory acima(criando lista com validações do Usuario0
	Validator validator = factory.getValidator();
	
	//Criando metodo para começar o objeto antes do teste
	@BeforeEach
	public void start() {
		
		usuario = new Usuario(0L, "Gabriel Alves", "albuquerque@gmail.com", "12345678");
		
	}
		
	@Test
	@DisplayName("✔ Valida Atributos Não Nulos")
	void testValidaAtributos() {
	Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario);
		
	System.out.println(violacao.toString());

	assertTrue(violacao.isEmpty());
	}
	
	  @Test
		@DisplayName("✖ Não Valida Atributos Nulos")
		void  testNaoValidaAtributos() {

			Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioErro);
			System.out.println(violacao.toString());

			assertTrue(violacao.isEmpty());
		} 

}

	