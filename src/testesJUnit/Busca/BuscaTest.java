package testesJUnit.Busca;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividade.ControllerAtividades;
import busca.Busca;
import objetivo.ControllerObjetivo;
import pesquisa.ControllerPesquisas;
import pesquisadores.ControllerPesquisador;
import problema.ControllerProblema;
import repositorios.RepositorioAtividades;
import repositorios.RepositorioObjetivos;
import repositorios.RepositorioPesquisadores;
import repositorios.RepositorioPesquisas;
import repositorios.RepositorioProblemas;

/**
 * @author Placido Henrique - 119110389.
 *
 */
class BuscaTest {
	private Busca buscas;
	
	private RepositorioPesquisas repositorioPesquisas = new RepositorioPesquisas();
	private RepositorioPesquisadores repositorioPesquisadores = new RepositorioPesquisadores();
	private RepositorioProblemas repositorioProblemas = new RepositorioProblemas();
	private RepositorioObjetivos repositorioObjetivos = new RepositorioObjetivos();
	private RepositorioAtividades repositorioAtividades = new RepositorioAtividades();
	
	private ControllerPesquisas controllerPesquisas = new ControllerPesquisas();
	private ControllerPesquisador controllerPesquisador = new ControllerPesquisador();
	private ControllerProblema controllerProblema = new ControllerProblema();
	private ControllerObjetivo controllerObjetivo = new ControllerObjetivo();
	private ControllerAtividades controllerAtividades = new ControllerAtividades();

	@BeforeEach
	void testeSetup() {
		this.buscas = new Busca();
		this.controllerPesquisas.cadastraPesquisa("estudo sobre computacao", "compiuter", repositorioPesquisas);
		this.controllerPesquisador.cadastraPesquisador("O pesquisador", "pesquisador", "mestrado em computacao", "pesquisador@gmail.com", "https://foto", repositorioPesquisadores);		
		this.controllerProblema.cadastraProblema("dificuldades em computacao", 5, repositorioProblemas);
		this.controllerObjetivo.cadastraObjetivo("GERAL", "melhorar o curso de computacao", 5, 5, repositorioObjetivos);
		this.controllerAtividades.cadastraAtividade("Atualizar a grade curricular de computacao", "BAIXO" , "ta de boa", repositorioAtividades);
	}

	@Test
	void testBuscaTermo() {
		// Excecoes termo.
		IllegalArgumentException termoVazio = assertThrows(IllegalArgumentException.class, () -> {
			this.buscas.buscaTermo("", repositorioAtividades, repositorioObjetivos, repositorioPesquisadores, repositorioPesquisas, repositorioProblemas);
		});
		NullPointerException termoNulo = assertThrows(NullPointerException.class, () -> {
			this.buscas.buscaTermo(null, repositorioAtividades, repositorioObjetivos, repositorioPesquisadores, repositorioPesquisas, repositorioProblemas);
		});

		assertEquals("Campo termo nao pode ser nulo ou vazio.", termoVazio.getMessage());
		assertEquals("Campo termo nao pode ser nulo ou vazio.", termoNulo.getMessage());

		assertEquals("COM1: estudo sobre computacao | pesquisador@gmail.com: mestrado em computacao | P1: dificuldades em computacao | O1: melhorar o curso de computacao | "
				+ "A1: Atualizar a grade curricular de computacao",
				buscas.buscaTermo("computacao", repositorioAtividades, repositorioObjetivos, repositorioPesquisadores, repositorioPesquisas, repositorioProblemas));
	}

	@Test
	void testContaResultadosBusca() {
		// Exececoes termo.
		IllegalArgumentException termoVazio = assertThrows(IllegalArgumentException.class, () -> {
			this.buscas.contaResultadosBusca("", null, null, null, null, null);
		});
		NullPointerException termoNulo = assertThrows(NullPointerException.class, () -> {
			this.buscas.buscaTermo(null, null, null, null, null, null);
		});

		assertEquals("Campo termo nao pode ser nulo ou vazio.", termoVazio.getMessage());
		assertEquals("Campo termo nao pode ser nulo ou vazio.", termoNulo.getMessage());

		assertEquals(5, buscas.contaResultadosBusca("computacao", repositorioAtividades, repositorioObjetivos, repositorioPesquisadores, repositorioPesquisas, repositorioProblemas));
	}

	@Test
	void testBuscaTermoPosicao() {
		// Excecoes termo.
		IllegalArgumentException termoVazio = assertThrows(IllegalArgumentException.class, () -> {
			this.buscas.buscaTermoPosicao("", 20, null, null, null, null, null);
		});
		NullPointerException termoNulo = assertThrows(NullPointerException.class, () -> {
			this.buscas.buscaTermoPosicao(null, 4, null, null, null, null, null);
		});

		assertEquals("Campo termo nao pode ser nulo ou vazio.", termoVazio.getMessage());
		assertEquals("Campo termo nao pode ser nulo ou vazio.", termoNulo.getMessage());

		// Excecoes posicao.
		IllegalArgumentException posNeg = assertThrows(IllegalArgumentException.class, () -> {
			this.buscas.buscaTermoPosicao("ok", -1, null, null, null, null, null);
		});
		IllegalArgumentException posInv = assertThrows(IllegalArgumentException.class, () -> {
			this.buscas.buscaTermoPosicao("ok", 100, repositorioAtividades, repositorioObjetivos, repositorioPesquisadores, repositorioPesquisas, repositorioProblemas);
		});

		assertEquals("Numero do resultado nao pode ser negativo", posNeg.getMessage());
		assertEquals("Entidade nao encontrada.", posInv.getMessage());

		assertEquals("COM1: estudo sobre computacao", buscas.buscaTermoPosicao("computacao", 1,  repositorioAtividades, repositorioObjetivos, repositorioPesquisadores, repositorioPesquisas, repositorioProblemas));
		assertEquals("O1: melhorar o curso de computacao", buscas.buscaTermoPosicao("computacao", 4, repositorioAtividades, repositorioObjetivos, repositorioPesquisadores, repositorioPesquisas, repositorioProblemas));
	}

}
