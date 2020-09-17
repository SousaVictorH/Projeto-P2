package testesJUnit.Pesquisador;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pesquisadores.ControllerPesquisador;
import repositorios.RepositorioPesquisadores;

/**
 * @author Plácido Henrique - 119110389
 *
 */
class ControllerPesquisadorTest {
	private ControllerPesquisador controller;
	private RepositorioPesquisadores repositorio;
	
	@BeforeEach
	void testeSetup() {
		this.controller = new ControllerPesquisador();
		this.repositorio = new RepositorioPesquisadores();
		controller.cadastraPesquisador("O pesquisador", "pesquisar", "tamo ai", "pesquisador@gmail.com", "https://fotoDoPesquisador", repositorio);
	}

	@Test
	void testCadastraPesquisador() {
		//Excecoes nome.
		NullPointerException nomeNulo = assertThrows(NullPointerException.class, () -> {
			controller.cadastraPesquisador(null, "pesquisar", "é", "email", "foto", repositorio);
		});
		
		IllegalArgumentException nomeVazio = assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraPesquisador( "", null, "é", "email", "foto", repositorio);
		});
		
		assertEquals("Campo nome nao pode ser nulo ou vazio.", nomeNulo.getMessage());
		assertEquals("Campo nome nao pode ser nulo ou vazio.", nomeVazio.getMessage());
		
		
		
		//Excecoes funcao.
		NullPointerException funcaoNula = assertThrows(NullPointerException.class, () -> {
			controller.cadastraPesquisador( "O pesquisador", null, "", "email", "foto", repositorio);
		});
		
		IllegalArgumentException funcaoVazia = assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraPesquisador( "O pesquisador", "", null, "email", "foto", repositorio);
		});
		
		assertEquals( "Campo funcao nao pode ser nulo ou vazio.", funcaoNula.getMessage());
		assertEquals( "Campo funcao nao pode ser nulo ou vazio.", funcaoVazia.getMessage());
		
		
		//Excecoes biografia.
		NullPointerException biografiaNula = assertThrows(NullPointerException.class, () -> {
			controller.cadastraPesquisador( "O pesquisador", "pesquisar", null, "", "foto", repositorio);
		});
		IllegalArgumentException biografiaVazia = assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraPesquisador( "O pesquisador", "pesquisar", "", null, "foto", repositorio);
		});
		
		assertEquals( "Campo biografia nao pode ser nulo ou vazio.", biografiaNula.getMessage());
		assertEquals( "Campo biografia nao pode ser nulo ou vazio.", biografiaVazia.getMessage());
		
		
		
		//Excecoes email.
		NullPointerException emailNulo = assertThrows(NullPointerException.class, () -> {
			controller.cadastraPesquisador( "O pesquisador", "pesquisar", "é", null, "foto", repositorio);
		});
		
		IllegalArgumentException emailVazio = assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraPesquisador( "O pesquisador", "pesquisar", "é", "", "", repositorio);
		});
		
		IllegalArgumentException emailInvalido = assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraPesquisador( "O pesquisador", "pesquisar", "é", "kkkkk@kkk@kkk", "foto", repositorio);
		});
		
		assertEquals( "Campo email nao pode ser nulo ou vazio.", emailNulo.getMessage());
		assertEquals( "Campo email nao pode ser nulo ou vazio.", emailVazio.getMessage());
		assertEquals( "Formato de email invalido.", emailInvalido.getMessage());
		
		
		
		//Excecoes fotoURL.
		NullPointerException fotoURLnula = assertThrows(NullPointerException.class, () -> {
			controller.cadastraPesquisador( "O pesquisador", "pesquisar", "é", "email", null, repositorio);
		});
		
		IllegalArgumentException fotoURLvazia = assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraPesquisador( "O pesquisador", "pesquisar", "é", "email@e", "", repositorio);
		});
		
		IllegalArgumentException fotoURLinvalida =  assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraPesquisador( "O pesquisador", "pesquisar", "é", "email@e", "htp//", repositorio);
		});
		
		assertEquals( "Campo fotoURL nao pode ser nulo ou vazio.", fotoURLnula.getMessage());
		assertEquals( "Campo fotoURL nao pode ser nulo ou vazio.", fotoURLvazia.getMessage());
		assertEquals( "Formato de foto invalido.", fotoURLinvalida.getMessage());
	}
	
	

	
	
	@Test
	void testAlteraPesquisador() {
		//Excecoes email
		NullPointerException emailNulo = assertThrows(NullPointerException.class, () -> {
			controller.alteraPesquisador(null, null, null, repositorio);
		});
		
		IllegalArgumentException emailVazio = assertThrows(IllegalArgumentException.class, () -> {
			controller.alteraPesquisador("", null, null, repositorio);
		});
		
		IllegalArgumentException pesquisadorNaoEncontado = assertThrows(IllegalArgumentException.class, () -> {
			controller.alteraPesquisador("aleatorio@gmail.com","EMAIL", null, repositorio);
		});
		
		assertEquals("Email nao pode ser vazio ou nulo.", emailNulo.getMessage());
		assertEquals("Email nao pode ser vazio ou nulo.", emailVazio.getMessage());
		assertEquals("Pesquisador nao encontrado", pesquisadorNaoEncontado.getMessage());
		
		
		
		//Excecoes atributo.
		NullPointerException atributoNulo = assertThrows(NullPointerException.class, () -> {
			controller.alteraPesquisador("pesquisador@gmail.com", null, null, repositorio);
		});
		
		IllegalArgumentException atributoVazio = assertThrows(IllegalArgumentException.class, () -> {
			controller.alteraPesquisador("pesquisador@gmail.com", "", null, repositorio);
		});
		
		IllegalArgumentException atributoInvalido = assertThrows(IllegalArgumentException.class, () -> {
			controller.alteraPesquisador("pesquisador@gmail.com", "STATUS", null, repositorio);
		});
		
		assertEquals("Atributo nao pode ser vazio ou nulo.", atributoNulo.getMessage());
		assertEquals("Atributo nao pode ser vazio ou nulo.", atributoVazio.getMessage());
		assertEquals("Atributo invalido.", atributoInvalido.getMessage());
		
		
		
		//Excecoes novoValor foto.
		NullPointerException novaFotoNula = assertThrows(NullPointerException.class, () -> {
			controller.alteraPesquisador("pesquisador@gmail.com", "FOTO", null, repositorio);
		});
		
		IllegalArgumentException novaFotoVazia  = assertThrows(IllegalArgumentException.class, () -> {
			controller.alteraPesquisador("pesquisador@gmail.com","FOTO", "", repositorio);
		});
		
		IllegalArgumentException novaFotoInvalida  = assertThrows(IllegalArgumentException.class, () -> {
			controller.alteraPesquisador("pesquisador@gmail.com","FOTO", "kkKKkk", repositorio);
		});
		
		assertEquals("Campo fotoURL nao pode ser nulo ou vazio.", novaFotoNula.getMessage());
		assertEquals("Campo fotoURL nao pode ser nulo ou vazio.", novaFotoVazia.getMessage());
		assertEquals("Formato de foto invalido.", novaFotoInvalida.getMessage());
		
		
		
		//Excecoes novoValor email.
		NullPointerException novoEmailNulo = assertThrows(NullPointerException.class, () -> {
			controller.alteraPesquisador("pesquisador@gmail.com", "EMAIL", null, repositorio);
		});
		
		IllegalArgumentException novoEmailVazio = assertThrows(IllegalArgumentException.class, () -> {
			controller.alteraPesquisador("pesquisador@gmail.com", "EMAIL", "", repositorio);
		});
		
		IllegalArgumentException novoEmailInvalido1 = assertThrows(IllegalArgumentException.class, () -> {
			controller.alteraPesquisador("pesquisador@gmail.com", "EMAIL", "@email", repositorio);
		});
		
		IllegalArgumentException novoEmailInvalido2 = assertThrows(IllegalArgumentException.class, () -> {
			controller.alteraPesquisador("pesquisador@gmail.com", "EMAIL", "email@", repositorio);
		});
		
		IllegalArgumentException novoEmailInvalido3 = assertThrows(IllegalArgumentException.class, () -> {
			controller.alteraPesquisador("pesquisador@gmail.com", "EMAIL", "aleatorio@@email.com", repositorio);
		});
		
		assertEquals("Campo email nao pode ser nulo ou vazio.", novoEmailNulo.getMessage());
		assertEquals("Campo email nao pode ser nulo ou vazio.", novoEmailVazio.getMessage());
		assertEquals("Formato de email invalido.", novoEmailInvalido1.getMessage());
		assertEquals("Formato de email invalido.", novoEmailInvalido2.getMessage());
		assertEquals("Formato de email invalido.", novoEmailInvalido3.getMessage());
		
		
		
		//Excecoes novoValor biografia.
		NullPointerException novaBiografiaNula = assertThrows(NullPointerException.class, () -> {
			controller.alteraPesquisador("pesquisador@gmail.com", "BIOGRAFIA", null, repositorio);
		});
		
		IllegalArgumentException novaBiografiaVazia = assertThrows(IllegalArgumentException.class, () -> {
			controller.alteraPesquisador("pesquisador@gmail.com", "BIOGRAFIA", "", repositorio);
		});
		
		assertEquals("Campo biografia nao pode ser nulo ou vazio.", novaBiografiaNula.getMessage());
		assertEquals("Campo biografia nao pode ser nulo ou vazio.", novaBiografiaVazia.getMessage());
		
		
		
		//Excecoes novoValor funcao.
		NullPointerException novaFuncaoNula = assertThrows(NullPointerException.class, () -> {
			controller.alteraPesquisador("pesquisador@gmail.com", "FUNCAO", null, repositorio);
		});
		
		IllegalArgumentException novaFuncaoVazia = assertThrows(IllegalArgumentException.class, () -> {
			controller.alteraPesquisador("pesquisador@gmail.com", "FUNCAO", "", repositorio);
		});
		
		assertEquals("Campo funcao nao pode ser nulo ou vazio.", novaFuncaoNula.getMessage());
		assertEquals("Campo funcao nao pode ser nulo ou vazio.", novaFuncaoVazia.getMessage());
		
		
		//Excecoes novoValor nome.
		NullPointerException novoNomeNulo = assertThrows(NullPointerException.class, () -> {
			controller.alteraPesquisador("pesquisador@gmail.com", "NOME", null, repositorio);
		});
		
		IllegalArgumentException novoNomeVazio = assertThrows(IllegalArgumentException.class, () -> {
			controller.alteraPesquisador("pesquisador@gmail.com", "NOME", "", repositorio);
		});
		
		assertEquals("Campo nome nao pode ser nulo ou vazio.", novoNomeNulo.getMessage());
		assertEquals("Campo nome nao pode ser nulo ou vazio.", novoNomeVazio.getMessage());
		
		
		
		controller.alteraPesquisador("pesquisador@gmail.com", "EMAIL", "Opesquisador@gmail.com", repositorio);
		controller.alteraPesquisador("Opesquisador@gmail.com", "NOME", "O PESQUISADOR", repositorio);
		controller.alteraPesquisador("Opesquisador@gmail.com", "FUNCAO", "PESQUISAR", repositorio);
		controller.alteraPesquisador("Opesquisador@gmail.com", "BIOGRAFIA", "Ta dificil, mas tamo ai", repositorio);
		controller.alteraPesquisador("Opesquisador@gmail.com", "FOTO", "https://A FOTO.com", repositorio);
		assertEquals("O PESQUISADOR (PESQUISAR) - Ta dificil, mas tamo ai - Opesquisador@gmail.com - https://A FOTO.com",
		controller.exibePesquisador("Opesquisador@gmail.com", repositorio));
		
		
	}
	
	
	
	

	@Test
	void testDesativaPesquisador() {
		controller.desativaPesquisador("pesquisador@gmail.com", repositorio);
		
		//Excecoes email
		NullPointerException emailNulo = assertThrows(NullPointerException.class, () -> {
			controller.desativaPesquisador(null, repositorio);
		});
		
		IllegalArgumentException emailVazio = assertThrows(IllegalArgumentException.class, () -> {
			controller.desativaPesquisador("", repositorio);
		});
		
		IllegalArgumentException pesquisadorNaoEncontrado = assertThrows(IllegalArgumentException.class, () -> {
			controller.desativaPesquisador("aleatorio@gmail.com", repositorio);
		});
		
		IllegalArgumentException pesquisadorInativo = assertThrows(IllegalArgumentException.class, () -> {
			controller.desativaPesquisador("pesquisador@gmail.com", repositorio);
		});
		
		assertEquals("Email nao pode ser vazio ou nulo.", emailNulo.getMessage());
		assertEquals("Email nao pode ser vazio ou nulo.", emailVazio.getMessage());
		assertEquals("Pesquisador nao encontrado", pesquisadorNaoEncontrado.getMessage());
		assertEquals("Pesquisador inativo.", pesquisadorInativo.getMessage());
		
	}

	@Test
	void testAtivaPesquisador() {		
		//Excecoes email
		NullPointerException emailNulo = assertThrows(NullPointerException.class, () -> {
			controller.ativaPesquisador(null, repositorio);
		});
		
		IllegalArgumentException emailVazio = assertThrows(IllegalArgumentException.class, () -> {
			controller.ativaPesquisador("", repositorio);
		});
		
		IllegalArgumentException pesquisadorNaoEncontrado = assertThrows(IllegalArgumentException.class, () -> {
			controller.ativaPesquisador("aleatorio@gmail.com", repositorio);
		});
		
		IllegalArgumentException pesquisadorInativo = assertThrows(IllegalArgumentException.class, () -> {
			controller.ativaPesquisador("pesquisador@gmail.com", repositorio);
		});
		
		assertEquals("Email nao pode ser vazio ou nulo.", emailNulo.getMessage());
		assertEquals("Email nao pode ser vazio ou nulo.", emailVazio.getMessage());
		assertEquals("Pesquisador nao encontrado", pesquisadorNaoEncontrado.getMessage());
		assertEquals("Pesquisador ja ativado.", pesquisadorInativo.getMessage());
		
	}

	@Test
	void testExibePesquisador() {
		//Excecoes email.
		NullPointerException emailNulo = assertThrows(NullPointerException.class, () -> {
			controller.exibePesquisador(null, repositorio);
		});
		
		IllegalArgumentException emailVazio = assertThrows(IllegalArgumentException.class, () -> {
			controller.exibePesquisador("", repositorio);
		});
		
		IllegalArgumentException pesquisadorNaoEncontrado = assertThrows(IllegalArgumentException.class, () -> {
			controller.exibePesquisador("aleatorio@gmail.com", repositorio);
		});
		
		assertEquals("Campo email nao pode ser nulo ou vazio.", emailNulo.getMessage());
		assertEquals("Campo email nao pode ser nulo ou vazio.", emailVazio.getMessage());
		assertEquals("Pesquisador nao encontrado", pesquisadorNaoEncontrado.getMessage());
		assertEquals("O pesquisador (pesquisar) - tamo ai - pesquisador@gmail.com - https://fotoDoPesquisador",
				controller.exibePesquisador("pesquisador@gmail.com", repositorio));
		
	}

	@Test
	void testPesquisadorEhAtivo() {
		//Excecoes email.
		NullPointerException emailNulo = assertThrows(NullPointerException.class, () -> {
			controller.pesquisadorEhAtivo(null, repositorio);
		});
		
		IllegalArgumentException emailVazio = assertThrows(IllegalArgumentException.class, () -> {
			controller.pesquisadorEhAtivo("", repositorio);
		});
		
		IllegalArgumentException pesquisadorNaoEncontrado = assertThrows(IllegalArgumentException.class, () -> {
			controller.pesquisadorEhAtivo("aleatorio@gmail.com", repositorio);
		});
		
		assertEquals("Email nao pode ser vazio ou nulo.", emailNulo.getMessage());
		assertEquals("Email nao pode ser vazio ou nulo.", emailVazio.getMessage());
		assertEquals("Pesquisador nao encontrado", pesquisadorNaoEncontrado.getMessage());
		assertTrue(controller.pesquisadorEhAtivo("pesquisador@gmail.com", repositorio));
		
	}

}
