package testesJUnit.Pesquisa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividade.Atividade;
import atividade.IDatividade;
import objetivo.Objetivo;
import pesquisa.Pesquisa;
import pesquisadores.Pesquisador;


class PesquisaTest {

	private Pesquisa pesquisa1;

	
	@BeforeEach
	void criaPesquisa() {
		
		String interesse = "computacao, homofobia";
		
		String[] interesses = interesse.split(", ");
		
		pesquisa1 = new Pesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", interesses, "COM1");
		
	}

	@Test
	void testToString() {
		
		assertEquals(pesquisa1.toString(), "COM1 - Homofobia em mensagens online de alunos de computacao do primeiro periodo. - computacao, homofobia");
		
	}

	@Test
	void testSetDescricao() {
		
		pesquisa1.setDescricao("NOVA DESCRICAO");
		
		assertEquals(pesquisa1.toString(), "COM1 - NOVA DESCRICAO - computacao, homofobia");
		
	}

	@Test
	void testSetInteresse() {
		
		String interesse = "interesse1, interesse2";
		
		String[] interesses = interesse.split(", ");
		
		pesquisa1.setInteresse(interesses);
		
		assertEquals(pesquisa1.toString(), "COM1 - Homofobia em mensagens online de alunos de computacao do primeiro periodo. - interesse1, interesse2");
	}

	@Test
	void testEncerraPesquisa() {
		
		assertEquals(pesquisa1.ehAtiva(), true);
		
		pesquisa1.encerraPesquisa("Pesquisa concluida");
		
		assertEquals(pesquisa1.ehAtiva(), false);
		
	}

	@Test
	void testAtivaPesquisa() {
		
		assertEquals(pesquisa1.ehAtiva(), true);
		
		pesquisa1.encerraPesquisa("Pesquisa concluida");
		
		assertEquals(pesquisa1.ehAtiva(), false);
		
		pesquisa1.ativaPesquisa();
		
		assertEquals(pesquisa1.ehAtiva(), true);
		
	}

	@Test
	void testEhAtiva() {
		
		assertEquals(pesquisa1.ehAtiva(), true);
		
		pesquisa1.encerraPesquisa("Pesquisa concluida");
		
		assertEquals(pesquisa1.ehAtiva(), false);
		
	}
	
	@Test
	void testProximaAtividade() {
		
		Atividade a1 = new Atividade("monitoramento baixo", "BAIXO", "baixo pq e monitoramento", new IDatividade("A1"));
		Atividade a2 = new Atividade("monitoramento medio", "MEDIO", "medio pq e monitoramento", new IDatividade("A2"));
		
		pesquisa1.associaAtividade("A1", a1);
		pesquisa1.associaAtividade("A2", a2);
		
		a2.cadastraItem("item1");
		a2.cadastraItem("item2");
		a2.cadastraItem("item3");
		a1.cadastraItem("item4");
		
		assertEquals(pesquisa1.proximaAtividade("MAIS_ANTIGA"), "A1");
		
		assertEquals(pesquisa1.proximaAtividade("MENOS_PENDENCIAS"), "A1");
		
		assertEquals(pesquisa1.proximaAtividade("MAIOR_RISCO"), "A2");
		
		assertEquals(pesquisa1.proximaAtividade("MAIOR_DURACAO"), "A1");
	}
	
	@Test
	void testAssociaPesquisador() {
		
		Pesquisador p1 = new Pesquisador("2pac", "tupacador", "tupacou tudo", "2pac@ccc.br", "www.2pac.com");
		
		
		pesquisa1.associaPesquisador(p1);
		
		assertEquals(pesquisa1.temPesquisador(p1), true);
	}
	
	@Test
	void testDesassociaPesquisador() {
		
		Pesquisador p1 = new Pesquisador("2pac", "tupacador", "tupacou tudo", "2pac@ccc.br", "www.2pac.com");
		
		pesquisa1.associaPesquisador(p1);
		pesquisa1.desassociaPesquisador(p1);
		
		assertEquals(pesquisa1.temPesquisador(p1), false);
	}
	
	@Test
	void testAssociaAtividade() {
		Atividade a1 = new Atividade("monitoramento baixo", "BAIXO", "baixo pq e monitoramento", new IDatividade("A1"));
		
		assertEquals(pesquisa1.associaAtividade("A1", a1), true);
		
		assertEquals(pesquisa1.associaAtividade("A1", a1), false);
	}
	
	@Test
	void testDesassociaAtividade() {
		Atividade a1 = new Atividade("monitoramento baixo", "BAIXO", "baixo pq e monitoramento", new IDatividade("A1"));
		
		assertEquals(pesquisa1.associaAtividade("A1", a1), true);
		
		assertEquals(pesquisa1.desassociaAtividade("A1"), true);
		assertEquals(pesquisa1.desassociaAtividade("A1"), false);
	}
	
	@Test
	void testSetCodigoProblema() {
		pesquisa1.setCodigoProblema("Bahia");
		assertEquals(pesquisa1.getCodigoProblema(), "Bahia");
	}
	
	@Test
	void testGetCodigoProblema() {
		pesquisa1.setCodigoProblema("Bahia");
		assertEquals(pesquisa1.getCodigoProblema(), "Bahia");
	}
	
	@Test
	void testAssociaObjetivo() {
		pesquisa1.associaObjetivo("diamantina");
		assertEquals(pesquisa1.getObjetivosSize(), 1);
	}
	
	@Test
	void testGetObjetivosSize() {
		pesquisa1.associaObjetivo("diamantina");
		pesquisa1.associaObjetivo("bota");
		pesquisa1.associaObjetivo("brecou");
		
		assertEquals(pesquisa1.getObjetivosSize(), 3);
	}
	
	@Test
	void testDesassociaObjetivo() {
		pesquisa1.associaObjetivo("diamantina");
		pesquisa1.associaObjetivo("bota");
		assertEquals(pesquisa1.getObjetivosSize(), 2);
		
		pesquisa1.desassociaObjetivo("bota");
		assertEquals(pesquisa1.getObjetivosSize(), 1);
	}
	
	@Test
	void testGetMaiorObjetivo() {
		Objetivo obj1 = new Objetivo("balde", "typu", "descricao", 4, 3);
		Objetivo obj2 = new Objetivo("code2", "typu2", "descricao2", 1, 2);
		
		pesquisa1.associaObjetivo("balde");
		pesquisa1.associaObjetivo("code2");
		
		assertEquals(pesquisa1.getMaiorObjetivo(), "");
	}
	
	@Test
	void testGetDescricao() {
		assertEquals(pesquisa1.getDescricao(), "Homofobia em mensagens online de alunos de computacao do primeiro periodo.");
	}
	
	@Test
	void testGetCamposDeInteresse() {
		assertEquals(pesquisa1.getCamposDeInteresse(), "computacao, homofobia");
	}
	
	@Test
	void testTemPesquisador() {
		Pesquisador p1 = new Pesquisador("2pac", "tupacador", "tupacou tudo", "2pac@ccc.br", "www.2pac.com");
		
		pesquisa1.associaPesquisador(p1);
		
		assertEquals(pesquisa1.temPesquisador(p1), true);
		
		pesquisa1.desassociaPesquisador(p1);
		
		assertEquals(pesquisa1.temPesquisador(p1), false);
	}
	
	@Test
	void testTemPendencias() {
		Atividade a1 = new Atividade("monitoramento baixo", "BAIXO", "baixo pq e monitoramento", new IDatividade("A1"));
		pesquisa1.associaAtividade("A1", a1);
		a1.cadastraItem("item");
		assertEquals(pesquisa1.temPendencias(), true);
		
	}
	
	@Test
	void testRetornaPesquisadores() {
		Pesquisador p1 = new Pesquisador("2pac", "tupacador", "tupacou tudo", "2pac@ccc.br", "www.2pac.com");
		pesquisa1.associaPesquisador(p1);
		assertEquals(pesquisa1.retornaPesquisadores(), "\n        - 2pac (tupacador) - tupacou tudo - 2pac@ccc.br - www.2pac.com");
	}
	
	@Test
	void testRetornaAtividades() {
		Atividade a1 = new Atividade("monitoramento baixo", "BAIXO", "baixo pq e monitoramento", new IDatividade("A1"));
		pesquisa1.associaAtividade("A1", a1);
		a1.cadastraItem("item");
		assertEquals(pesquisa1.retornaAtividades(), "        - monitoramento baixo (BAIXO - baixo pq e monitoramento)\n            - PENDENTE - ITEM1\n");

	}
	
	@Test
	void testRetornaItensResultados() {
		Atividade a1 = new Atividade("monitoramento baixo", "BAIXO", "baixo pq e monitoramento", new IDatividade("A1"));
		pesquisa1.associaAtividade("A1", a1);
		a1.cadastraItem("item");
		assertEquals(pesquisa1.retornaItensResultados(), "        - monitoramento baixo\n");

	}


}
