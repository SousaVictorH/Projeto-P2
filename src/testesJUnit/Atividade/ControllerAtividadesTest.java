package testesJUnit.Atividade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividade.ControllerAtividades;
import repositorios.RepositorioAtividades;


class ControllerAtividadesTest {
	private ControllerAtividades controA;
	private RepositorioAtividades repositorioA;
	
	@BeforeEach
	void criaController() {
		controA = new ControllerAtividades();
		controA.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.", repositorioA);
		
	}
	
	@Test
	void testCadastraAtividade() {
		assertEquals("Monitoramento de chats dos alunos de computacao do primeiro periodo. (BAIXO - Por se tratar de apenas um monitoramento, o risco nao e elevado.)", controA.exibeAtividade("A1", repositorioA));
	}

	@Test
	void testCadastraAtividadeDescricaoNull() {
		assertThrows(NullPointerException.class, () ->{
			controA.cadastraAtividade(null, "Sobre pessoas", "Pessoas sobre", repositorioA);
		});
	}
	
	@Test
	void testCadastraAtividadeNivelRiscoNull() {
		assertThrows(NullPointerException.class, () ->{
			controA.cadastraAtividade("Ta roxeda", null, "Pessoas sobre", repositorioA);
		});
	}
	
	@Test
	void testCadastraAtividadeDescricaoRiscoNull() {
		assertThrows(NullPointerException.class, () ->{
			controA.cadastraAtividade("ta roxeda demais", "Sobre pessoas", null, repositorioA);
		});
	}
	
	@Test
	void testCadastraAtividadeDescricaoVazia() {
		assertThrows(IllegalArgumentException.class, () ->{
			controA.cadastraAtividade("", "Sobre pessoas", "Pessoas sobre", repositorioA);
		});
	}
	
	@Test
	void testCadastraAtividadeNivelRiscoVazia() {
		assertThrows(IllegalArgumentException.class, () ->{
			controA.cadastraAtividade("Ta roxeda", "", "Pessoas sobre", repositorioA);
		});
	}
	
	@Test
	void testCadastraAtividadeDescricaoRiscoVazia() {
		assertThrows(IllegalArgumentException.class, () ->{
			controA.cadastraAtividade("ta roxeda demais", "Sobre pessoas", "", repositorioA);
		});
	}
	
	@Test
	void testApagaAtividade() {
		controA.apagaAtividade("A1", repositorioA);
		assertThrows(IllegalArgumentException.class, () ->{
			controA.exibeAtividade("A1", repositorioA);
		});
	}


	@Test
	void testApagaAtividadeCodigoNull() {
		assertThrows(NullPointerException.class, () ->{
			controA.apagaAtividade(null, repositorioA);
		});
	}
	
	@Test
	void testApagaAtividadeCodigoVazio() {
		assertThrows(IllegalArgumentException.class, () ->{
			controA.apagaAtividade("", repositorioA);
		});
	}

	@Test
	void testCadastraItem() {
		controA.cadastraItem("A1", "Monitoramento facebook/messenger", repositorioA);
		assertEquals(1, controA.contaItensPendentes("A1", repositorioA));
	}

	@Test
	void testExibeAtividade() {
		assertEquals("Monitoramento de chats dos alunos de computacao do primeiro periodo. (BAIXO - Por se tratar de apenas um monitoramento, o risco nao e elevado.)", controA.exibeAtividade("A1", repositorioA));
	}
	
	@Test
	void testExibeAtividadeNull() {
		assertThrows(NullPointerException.class, () ->{
			controA.exibeAtividade(null, repositorioA);
		});
	}
	
	@Test
	void testExibeAtividadeVazia() {
		assertThrows(IllegalArgumentException.class, () ->{
			controA.exibeAtividade("", repositorioA);
		});
	}

	@Test
	void testContaItensPendentes() {
		controA.cadastraItem("A1", "Monitoramento facebook/messenger", repositorioA);
		assertEquals(1, controA.contaItensPendentes("A1", repositorioA));
	}
	
	@Test
	void testContaItensPendentesNull() {
		assertThrows(NullPointerException.class, () ->{
			controA.contaItensPendentes(null, repositorioA);
		});
	}
	
	@Test
	void testContaItensPendentesVazia() {
		assertThrows(IllegalArgumentException.class, () ->{
			controA.contaItensPendentes("", repositorioA);
		});
	}

	@Test
	void testContaItensRealizados() {
		controA.cadastraItem("A1", "Monitoramento facebook/messenger", repositorioA);
		assertEquals(0, controA.contaItensRealizados("A1", repositorioA));
	}
	
	@Test
	void testContaItensRealizadosNull() {
		controA.cadastraItem("A1", "Monitoramento facebook/messenger", repositorioA);
		assertThrows(NullPointerException.class, () ->{
			controA.contaItensPendentes(null, repositorioA);
		});
	}
	
	@Test
	void testContaItensRealizadosVazia() {
		controA.cadastraItem("A1", "Monitoramento facebook/messenger", repositorioA);
		assertThrows(IllegalArgumentException.class, () ->{
			controA.contaItensRealizados("", repositorioA);
		});
	}
	
	@Test
	void testGetAtividade() {
		
		controA.cadastraAtividade("Monitoramento de chats", 
				"MEDIO", "Por se tratar de apenas um monitoramento, o risco e medio.", repositorioA);
	}
	
	@Test
	void testExecutaAtividadeVazio() {
		controA.cadastraItem("A1", "Monitoramento facebook/messenger", repositorioA);
		
		assertThrows(IllegalArgumentException.class, () ->{
			controA.executaAtividade("", 1, 2, repositorioA);
		});
		
		controA.executaAtividade("A1", 1, 5, repositorioA);
	}
	
	@Test
	void testExxecutaAtividadeNull() {
		controA.cadastraItem("A1", "Monitoramento facebook/messenger", repositorioA);
		
		assertThrows(NullPointerException.class, () ->{
			controA.executaAtividade(null, 1, 0, repositorioA);
		});
	}
	@Test
	void testExecutaAtividadeIlegal() {
		controA.cadastraItem("A1", "Monitoramento facebook/messenger", repositorioA);
		
		assertThrows(IllegalArgumentException.class, () ->{
			controA.executaAtividade("A4", 1, 2, repositorioA);
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			controA.executaAtividade("A1", 0, 2, repositorioA);
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			controA.executaAtividade("A1", 1, 0, repositorioA);
		});
	}
	
	@Test
	void testCadastraResultadoVazio() {
		controA.cadastraResultado("A1", "Verificou-se um interesse no mercado.", repositorioA);
		
		assertThrows(IllegalArgumentException.class, () ->{
			controA.cadastraResultado("A1", "", repositorioA);
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			controA.cadastraResultado("", "Verificou-se um interesse no mercado.", repositorioA);
		});
	}
	
	@Test
	void testCadastraResultadoNull() {
		controA.cadastraResultado("A1", "Verificou-se um interesse no mercado.", repositorioA);
		
		assertThrows(NullPointerException.class, () ->{
			controA.cadastraResultado("A1", null, repositorioA);
		});
	}
	
	@Test
	void testCadastraResultadoIlegal() {
		controA.cadastraResultado("A1", "Verificou-se um interesse no mercado.", repositorioA);
		
		assertThrows(IllegalArgumentException.class, () ->{
			controA.cadastraResultado("A4", "Verificou-se um interesse no mercado.", repositorioA);
		});
	}
	
	@Test
	void testRemoveResultadoVazio() {
		
		assertThrows(IllegalArgumentException.class, () ->{
			controA.removeResultado("", 1, repositorioA);
		});
	}
	
	@Test
	void testRemoveResultadoNull() {
		assertThrows(NullPointerException.class, () ->{
			controA.removeResultado(null, 1, repositorioA);
		});
	}
	
	@Test
	void testRemoveResultadoInvalido() {
		assertThrows(IllegalArgumentException.class, () ->{
			controA.removeResultado("A5", 1, repositorioA);
		});
	}
	
	@Test
	void testRemoveResultado() {
		controA.cadastraResultado("A1", "Verificou-se um interesse no mercado.", repositorioA);
		
		assertEquals(controA.listaResultados("A1", repositorioA), "Verificou-se um interesse no mercado.");
		
		controA.removeResultado("A1", 1, repositorioA);
		
		assertEquals(controA.listaResultados("A1", repositorioA), "");
	}
	
	@Test
	void testListaResultadoVazio(){
		assertThrows(IllegalArgumentException.class, () ->{
			controA.listaResultados("", repositorioA);
		});
	}
	
	@Test
	void testListaResultadoNull(){
		assertThrows(NullPointerException.class, () ->{
			controA.listaResultados(null, repositorioA);
		});
	}
	
	@Test
	void testListaResultadoIlegal(){
		assertThrows(IllegalArgumentException.class, () ->{
			controA.listaResultados("A5", repositorioA);
		});
	}
	
	@Test
	void testListaResultado() {
		controA.cadastraResultado("A1", "Verificou-se um interesse no mercado.", repositorioA);
		
		assertEquals(controA.listaResultados("A1", repositorioA), "Verificou-se um interesse no mercado.");
		
		controA.cadastraResultado("A1", "Analise nao foi possivel.", repositorioA);
		
		assertEquals(controA.listaResultados("A1", repositorioA), "Verificou-se um interesse no mercado. | Analise nao foi possivel.");
		
		controA.cadastraResultado("A1", "Nilson sexta.", repositorioA);
		
		assertEquals(controA.listaResultados("A1", repositorioA), "Verificou-se um interesse no mercado. | Analise nao foi possivel. | Nilson sexta.");
	}
	
	@Test
	void testGetDuracaoVazio() {
		assertThrows(IllegalArgumentException.class, () ->{
			controA.getDuracao("", repositorioA);
		});
	}
	
	@Test
	void testGetDuracaoNull() {
		assertThrows(NullPointerException.class, () ->{
			controA.getDuracao(null, repositorioA);
		});
	}
	
	@Test
	void testGetDuracaoIllegal() {
		assertThrows(IllegalArgumentException.class, () ->{
			controA.getDuracao("A5", repositorioA);
		});
	}
	
	@Test
	void testGetDuracao() {
		controA.cadastraItem("A1", "Monitoramento facebook/messenger", repositorioA);
		controA.executaAtividade("A1", 1, 5, repositorioA);
		
		assertEquals(controA.getDuracao("A1", repositorioA), 5);
		
		controA.cadastraAtividade("Monitoramento de chats", "MEDIO", "Por se tratar de apenas um monitoramento, o risco e medio.", repositorioA);
		
		controA.cadastraItem("A2", "Monitoramento what's", repositorioA);
		controA.executaAtividade("A2", 1, 7, repositorioA);
		
		assertEquals(controA.getDuracao("A2", repositorioA), 7);
	}

}
