package testesJUnit.Atividade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividade.Atividade;
import atividade.IDatividade;


class AtividadeTest {
	
	private Atividade atividade;
	
	@BeforeEach
	void criaAtividade() {
	atividade = new Atividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO", 
			"Por se tratar de apenas um monitoramento, o risco nao e elevado.", new IDatividade("id00"));
	}

	@Test
	void testToString() {
		assertEquals("Monitoramento de chats dos alunos de computacao do primeiro periodo. (BAIXO - Por se tratar de apenas um monitoramento, "
				+ "o risco nao e elevado.)", atividade.toString());
	}

	@Test
	void testCadastraItem() {
		atividade.cadastraItem("BBMP");
		assertEquals(1, atividade.getItensPendentes());
	}

	@Test
	void testGetItensPendentes() {
		atividade.cadastraItem("BBMP");
		assertEquals(1, atividade.getItensPendentes());
	}

	@Test
	void testGetItensRealizados() {
		atividade.cadastraItem("BBMP");
		assertEquals(0, atividade.getItensRealizados());
	}
	
	@Test
	void testCadastraResultado() {
		atividade.cadastraResultado("sem conclusões.");
		assertEquals(atividade.listaResultados(), "sem conclusões.");
		
		atividade.cadastraResultado("Verificou-se um interesse no mercado.");
		assertEquals(atividade.listaResultados(), "sem conclusões. | Verificou-se um interesse no mercado.");	
	}
	
	@Test
	void testRemoveResultado() {
		atividade.cadastraResultado("sem conclusões.");
		atividade.cadastraResultado("Verificou-se um interesse no mercado.");
		
		assertEquals(atividade.listaResultados(), "sem conclusões. | Verificou-se um interesse no mercado.");	
		
		atividade.removeResultado(1);
		
		assertEquals(atividade.listaResultados(), "Verificou-se um interesse no mercado.");
		
		atividade.removeResultado(2);
		
		assertEquals(atividade.listaResultados(), "");
	}
	
	@Test
	void testListaResultado() {
		atividade.cadastraResultado("sem conclusões.");
		assertEquals(atividade.listaResultados(), "sem conclusões.");
		
		atividade.cadastraResultado("Verificou-se um interesse no mercado.");
		assertEquals(atividade.listaResultados(), "sem conclusões. | Verificou-se um interesse no mercado.");	
		
		atividade.removeResultado(1);
		assertEquals(atividade.listaResultados(), "Verificou-se um interesse no mercado.");
	}
	
	@Test
	void testExecutaItem() {
		atividade.cadastraItem("BBMP");
		atividade.executaItem(1, 9);
		assertEquals(atividade.getDuracao(), 9);
		atividade.cadastraItem("FMCC");
		atividade.executaItem(2, 17);
		assertEquals(atividade.getDuracao(), 26);
	}
	
	@Test
	void testGetDuracao() {
		assertEquals(atividade.getDuracao(), 0);
		atividade.cadastraItem("BBMP");
		atividade.executaItem(1, 9);
		assertEquals(atividade.getDuracao(), 9);
		atividade.cadastraItem("FMCC");
		atividade.executaItem(2, 17);
		assertEquals(atividade.getDuracao(), 26);
	}
	
	@Test
	void testGetDescricao() {
		assertEquals(atividade.getDescricao(), "Monitoramento de chats dos alunos de computacao do primeiro periodo.");
		
		Atividade atividade2 = new Atividade("Monitoramento de chats.", 
				"MEDIO", "Por se tratar de apenas um monitoramento, o risco nao e medio.", new IDatividade("id1"));
		
		assertEquals(atividade2.getDescricao(), "Monitoramento de chats.");
	}

	@Test
	void testGetDescricaoRisco() {
		assertEquals(atividade.getDescricaoRisco(), "Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		
		Atividade atividade2 = new Atividade("Monitoramento de chats.", 
				"MEDIO", "Por se tratar de apenas um monitoramento, o risco nao e medio.", new IDatividade("id2"));
		
		assertEquals(atividade2.getDescricaoRisco(), "Por se tratar de apenas um monitoramento, o risco nao e medio.");
	}
	
	@Test
	void testSetSubsequente() {
		
		Atividade atividade2 = new Atividade("Monitoramento de chats.", 
				"MEDIO", "Por se tratar de apenas um monitoramento, o risco nao e medio.", new IDatividade("id3"));
		
		Atividade atividade3 = new Atividade("Monitoramento de wpp.", 
				"MEDIO", "Por se tratar de apenas um monitoramento de conversas, o risco nao e medio.", new IDatividade("id4"));
		
		Atividade atividade4 = new Atividade("Monitoramento de instagram.", 
				"MEDIO", "Por se tratar de apenas um monitoramento de fotos, o risco nao e medio.", new IDatividade("id5"));
		
		atividade.setSubsequente(atividade2);
		
		// Já tem subsequente
		assertThrows(IllegalArgumentException.class, () ->{
			atividade.setSubsequente(atividade2);
		});
		
		//Loop imediato
		assertThrows(IllegalArgumentException.class, () ->{
			atividade2.setSubsequente(atividade);
		});
		
		//Loop longo
		
		atividade2.setSubsequente(atividade3);
		
		atividade3.setSubsequente(atividade4);
		
		assertThrows(IllegalArgumentException.class, () ->{
			atividade4.setSubsequente(atividade);
		});
	}
	
}
