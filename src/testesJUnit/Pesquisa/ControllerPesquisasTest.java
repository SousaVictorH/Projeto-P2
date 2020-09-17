package testesJUnit.Pesquisa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividade.ControllerAtividades;
import objetivo.ControllerObjetivo;
import pesquisa.ControllerPesquisas;
import pesquisadores.ControllerPesquisador;
import problema.ControllerProblema;
import repositorios.RepositorioAtividades;
import repositorios.RepositorioObjetivos;
import repositorios.RepositorioPesquisadores;
import repositorios.RepositorioPesquisas;
import repositorios.RepositorioProblemas;

class ControllerPesquisasTest {

	private ControllerPesquisas cp;
	private ControllerProblema cpb;
	private ControllerObjetivo co;
	private ControllerPesquisador cdr;
	private ControllerAtividades ca;
	
	private RepositorioPesquisas repositorioP = new RepositorioPesquisas();
	private RepositorioProblemas repositorioProblem = new 	RepositorioProblemas();
	private RepositorioObjetivos repositorioObjetivos = new RepositorioObjetivos();
	private RepositorioPesquisadores repositorioPesquisadores = new RepositorioPesquisadores();
	private RepositorioAtividades repositorioAtividades = new RepositorioAtividades();


	
	@BeforeEach
	void criaController() {
		this.cp = new ControllerPesquisas();
		this.cpb = new ControllerProblema();
		this.co = new ControllerObjetivo();
		this.cdr = new ControllerPesquisador();
		this.ca = new ControllerAtividades();
	}

	@Test
	void testCadastraPesquisaVazio() {
		assertThrows(IllegalArgumentException.class, () ->{
			cp.cadastraPesquisa("", "psicologia, sistema juridico, alienacao parental, brasil",repositorioP);
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			cp.cadastraPesquisa("Alienacao Parental e o Sistema de Justica Brasileiro.", "", repositorioP);
		});
		
	}
	
	@Test
	void testCadastraPesquisaNull() {
		
		assertThrows(NullPointerException.class, () ->{
			cp.cadastraPesquisa(null, "psicologia, sistema juridico, alienacao parental, brasil", repositorioP);
		});
		
		assertThrows(NullPointerException.class, () ->{
			cp.cadastraPesquisa("Alienacao Parental e o Sistema de Justica Brasileiro.", null, repositorioP);
		});
	}
	
	@Test
	void testCadastraPesquisaIlegal() {
		assertThrows(IllegalArgumentException.class, () ->{
			cp.cadastraPesquisa("Alienacao Parental e o Sistema de Justica Brasileiro.", " , , ,", repositorioP);
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			cp.cadastraPesquisa("Alienacao Parental e o Sistema de Justica Brasileiro.", "acessibilidade, , ,automatizacao", repositorioP);
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			cp.cadastraPesquisa("Alienacao Parental e o Sistema de Justica Brasileiro.", "acessibilidade, automatizacao, "
					+ "sistema,condicoes de trabalho, mundo melhor", repositorioP);
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			cp.cadastraPesquisa("Alienacao Parental e o Sistema de Justica Brasileiro.", "ho", repositorioP);
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			cp.cadastraPesquisa("Alienacao Parental e o Sistema de Justica Brasileiro.", "Lorem ipsum dolor sit amet consectetur "
					+ "adipiscing elit Integer euismod leo in consequat efficitur. Proin commodo nisi eget ligula consequat imperdiet "
					+ "ac quis turpis In non fringilla orci Pellentesque pellentesque ipsum vel ipsum ultrices scelerisque Nulla "
					+ "facilisi Morbi ut tellus ante Suspendisse malesuada quis quam eu efficitur Ut mollis turpis magna sit amet auctor "
					+ "nunc pulvinar ultricies. Nam pharetra scelerisque magna ut feugiat.", repositorioP);
		});
	}
	
	@Test
	void testCadastraPesquisa() {
		cp.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", repositorioP);
		
		assertEquals(cp.listaPesquisas("PESQUISA", repositorioP), "COM1 - Homofobia em mensagens online de alunos de computacao do primeiro periodo."
				+ " - computacao, homofobia");
	}

	@Test
	void testExibePesquisaVazio() {
		assertThrows(IllegalArgumentException.class, () ->{
			cp.exibePesquisa("", repositorioP);
		});
	}
	
	@Test
	void testExibePesquisaNull() {
		assertThrows(IllegalArgumentException.class, () ->{
			cp.exibePesquisa(null, repositorioP);
		});
	}
	
	@Test
	void testExibePesquisaIllegal() {
		assertThrows(IllegalArgumentException.class, () ->{
			cp.exibePesquisa("ENE1", repositorioP);
		});
	}
	
	@Test
	void testExibePesquisa() {
		
		cp.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", repositorioP);
		cp.cadastraPesquisa("Aspectos da fermentacao do mosto cervejeiro por leveduras nao-Saccharomyces.", "fermentacao, cerveja",repositorioP);
		cp.cadastraPesquisa("Alienacao Parental e o Sistema de Justica Brasileiro.", "psicologia, sistema juridico, alienacao parental, brasil", repositorioP);
		
		cp.exibePesquisa("COM1", repositorioP);
		cp.exibePesquisa("FER1", repositorioP);
		cp.exibePesquisa("PSI1", repositorioP);
		
		assertEquals(cp.exibePesquisa("COM1", repositorioP), "COM1 - Homofobia em mensagens online de alunos de computacao do primeiro periodo. - computacao, homofobia");
		assertEquals(cp.exibePesquisa("FER1", repositorioP), "FER1 - Aspectos da fermentacao do mosto cervejeiro por leveduras nao-Saccharomyces. - fermentacao, cerveja");
		assertEquals(cp.exibePesquisa("PSI1", repositorioP), "PSI1 - Alienacao Parental e o Sistema de Justica Brasileiro. - psicologia, sistema juridico, alienacao parental, brasil");
	}

	@Test
	void testAlteraPesquisaVazio() {
		assertThrows(IllegalArgumentException.class, () ->{
			cp.alteraPesquisa("", "CAMPO", "eleicao, paraiba", repositorioP);
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			cp.alteraPesquisa("COM1", "", "eleicao, paraiba", repositorioP);
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			cp.alteraPesquisa("COM1", "CAMPO", "", repositorioP);
		});
	}
	
	@Test
	void testAlteraPesquisaNull() {
		cp.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", repositorioP);
		
		assertThrows(NullPointerException.class, () ->{
			cp.alteraPesquisa("COM1", null, "eleicao, paraiba", repositorioP);
		});
		
		assertThrows(NullPointerException.class, () ->{
			cp.alteraPesquisa("COM1", "CAMPO", null, repositorioP);
		});
	}
	
	@Test
	void testAlteraPesquisaIlegal() {
		assertThrows(IllegalArgumentException.class, () ->{
			cp.alteraPesquisa("CO", "CAMPO", "eleicao, paraiba", repositorioP);
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			cp.alteraPesquisa("ENE1", "CAMPO", "eleicao, paraiba", repositorioP);
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			cp.alteraPesquisa("COM1", "QUALQUER", "eleicao, paraiba", repositorioP);
		});
	}
	
	@Test
	void testAlteraPesquisa() {
		
		cp.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", repositorioP);
		
		cp.alteraPesquisa("COM1", "DESCRICAO", "Aumento da evasao no numero de eleitores paraibanos.", repositorioP);
		
		cp.alteraPesquisa("COM1", "CAMPO", "eleicao, paraiba", repositorioP);
		
		assertEquals(cp.exibePesquisa("COM1", repositorioP), "COM1 - Aumento da evasao no numero de eleitores paraibanos. - eleicao, paraiba");
		
	}

	@Test
	void testEncerraPesquisaVazio() {
		assertThrows(IllegalArgumentException.class, () ->{
			cp.encerraPesquisa("", "Pesquisa concliida", repositorioP);
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			cp.encerraPesquisa("COM1", "", repositorioP);
		});
	}
	
	@Test
	void testEncerraPesquisaNull() {
		assertThrows(IllegalArgumentException.class, () ->{
			cp.encerraPesquisa(null, "Pesquisa concliida", repositorioP);
		});
		
		assertThrows(NullPointerException.class, () ->{
			cp.encerraPesquisa("", null, repositorioP);
		});
	}
	
	@Test
	void testEncerraPesquisaIlegal() {
		assertThrows(IllegalArgumentException.class, () ->{
			cp.encerraPesquisa("ENE1", "Pesquisa concliida", repositorioP);
		});
	}
	
	@Test
	void testEncerraPesquisa() {
		
		cp.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", repositorioP);
		
		cp.encerraPesquisa("COM1", "Pesquisa concluida", repositorioP);
		
		assertThrows(IllegalArgumentException.class, () ->{
			cp.encerraPesquisa("COM1", "pesquisa concluida", repositorioP);
		});
		
	}

	@Test
	void testAtivaPesquisaVazio() {
		assertThrows(IllegalArgumentException.class, () ->{
			cp.ehAtiva("", repositorioP);
		});
	}
	
	@Test
	void testAtivaPesquisaNull() {
		assertThrows(NullPointerException.class, () ->{
			cp.ehAtiva(null, repositorioP);
		});
	}
	
	@Test
	void testAtivaPesquisaIlegal() {
		assertThrows(IllegalArgumentException.class, () ->{
			cp.ehAtiva("ENE1", repositorioP);
		});
	}
	
	@Test
	void testAtivaPesquisa() {
		
		cp.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", repositorioP);
		
		assertEquals(cp.ehAtiva("COM1", repositorioP), true);
		
		cp.encerraPesquisa("COM1", "Pesquisa concluida", repositorioP);
		
		assertEquals(cp.ehAtiva("COM1", repositorioP), false);
		
	}

	@Test
	void testEhAtivaVazio() {
		assertThrows(IllegalArgumentException.class, () ->{
			cp.ehAtiva("", repositorioP);
		});
	}
	
	@Test
	void testEhAtivaNull() {
		assertThrows(NullPointerException.class, () ->{
			cp.ehAtiva(null, repositorioP);
		});
	}
	
	@Test
	void testEhAtivaIlegal() {
		assertThrows(IllegalArgumentException.class, () ->{
			cp.ehAtiva("ENE1", repositorioP);
		});
	}
	
	@Test
	void testEhAtiva() {
		
		cp.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", repositorioP);
	
		assertEquals(cp.ehAtiva("COM1", repositorioP), true);
		
		cp.encerraPesquisa("COM1", "Pesquisa concluida", repositorioP);
		
		assertEquals(cp.ehAtiva("COM1", repositorioP), false);
		
	}
	
	@Test
	void testAssociaProblema() {
		cp.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", repositorioP);
		cpb.cadastraProblema("A problematica do aprendizado dos conceitos de programacao orientada a objeto", 5, repositorioProblem);
		assertEquals(cp.associaProblema("COM1", "P1", repositorioP),true);
	}
	
	@Test
	void testDesassociaProblema() {
		cp.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", repositorioP);
		cpb.cadastraProblema("A problematica do aprendizado dos conceitos de programacao orientada a objeto", 5, repositorioProblem);
		cp.associaProblema("COM1", "P1", repositorioP);
		assertEquals(cp.desassociaProblema("COM1", repositorioP), true);
		
	}
	
	@Test
	void testAssociaObjetivo() {
		cp.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", repositorioP);
		co.cadastraObjetivo("GERAL", "descricao1", 2, 5, repositorioObjetivos );
		assertEquals(cp.associaObjetivo("COM1", "O1", repositorioP, repositorioObjetivos), true);
		
	}
	
	@Test
	void testDesassociaObjetivo() {
		cp.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", repositorioP);
		co.cadastraObjetivo("GERAL", "descricao1", 2, 5, repositorioObjetivos);
		cp.associaObjetivo("COM1", "O1", repositorioP, repositorioObjetivos);
		assertEquals(cp.desassociaObjetivo("COM1", "O1", repositorioP, repositorioObjetivos), true);
	}
	
	@Test
	void testAssociaPesquisadorPesquisa() {
		cp.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", repositorioP);
		cdr.cadastraPesquisador("Joel","externo","Interresado em fungos.","thelastofus@2013","https://Cordyceps", repositorioPesquisadores );
		assertTrue(cp.associaPesquisadorPesquisa("COM1", "thelastofus", repositorioP, repositorioPesquisadores));
	}
	
	@Test
	void testDesassociaPesquisadorPesquisa() {
		cp.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", repositorioP);
		cdr.cadastraPesquisador("Joel","externo","Interresado em fungos.","thelastofus@2013","https://Cordyceps", repositorioPesquisadores);
		cp.associaPesquisadorPesquisa("COM1", "thelastofus@2013", repositorioP, repositorioPesquisadores);
		assertEquals(cp.desassociaPesquisadorPesquisa("COM1", "thelastofus@2013", repositorioP, repositorioPesquisadores), true);
	}
	
	@Test
	void testAssociaAtividade() {
		cp.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", repositorioP);
		ca.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.", repositorioAtividades);
		assertTrue(cp.associaAtividade("COM1", "A1", repositorioP, repositorioAtividades));
	}
	
	@Test
	void testDesassociaAtividade() {
		cp.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", repositorioP);
		ca.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.", repositorioAtividades);
		cp.associaAtividade("COM1", "A1", repositorioP, repositorioAtividades);
		assertEquals(cp.desassociaAtividade("COM1", "A1", repositorioP, repositorioAtividades), true);
	}
	
	@Test
	void testConfiguraEstrategia() {
		
		assertThrows(NullPointerException.class, () ->{
			cp.configuraEstrategia(null);
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			cp.configuraEstrategia("");
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			cp.configuraEstrategia("estrategia1");
		});
		
	}
	
	@Test
	void testProximaAtividade() {
		
		cp.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", repositorioP);
		
		ca.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.", repositorioAtividades);
		ca.cadastraAtividade("Monitoramento de chats dos alunos de computacao do segundo periodo.", "MEDIO", "Por se tratar de apenas um monitoramento, o risco nao e muito elevado.", repositorioAtividades);
		ca.cadastraAtividade("Monitoramento de chats dos alunos de computacao do terceiro periodo.", "ALTO", "Por se tratar de um monitoramento, o risco e muito elevado.", repositorioAtividades);
		
		cp.associaAtividade("COM1", "A1", repositorioP, repositorioAtividades);
		cp.associaAtividade("COM1", "A2", repositorioP, repositorioAtividades);
		cp.associaAtividade("COM1", "A3", repositorioP, repositorioAtividades);
		
		ca.cadastraItem("A1", "item1", repositorioAtividades);
		ca.cadastraItem("A1", "item6", repositorioAtividades);
		ca.cadastraItem("A2", "item2", repositorioAtividades);
		ca.cadastraItem("A3", "item3", repositorioAtividades);
		ca.cadastraItem("A3", "item4", repositorioAtividades);
		ca.cadastraItem("A3", "item5", repositorioAtividades);
		
		cp.configuraEstrategia("MAIS_ANTIGA");
		
		assertEquals(cp.proximaAtividade("COM1", repositorioP), "A1");
		
		cp.configuraEstrategia("MAIOR_RISCO");
		
		assertEquals(cp.proximaAtividade("COM1", repositorioP), "A3");
		
		cp.configuraEstrategia("MAIOR_DURACAO");
		
		assertEquals(cp.proximaAtividade("COM1", repositorioP), "A1");
		
		cp.configuraEstrategia("MENOS_PENDENCIAS");
		
		assertEquals(cp.proximaAtividade("COM1", repositorioP), "A2");
		
	}

}
