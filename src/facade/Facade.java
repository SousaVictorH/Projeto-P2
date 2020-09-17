package facade;

import atividade.ControllerAtividades;
import busca.Busca;
import easyaccept.EasyAccept;
import objetivo.ControllerObjetivo;
import pesquisa.ControllerPesquisas;
import pesquisadores.ControllerPesquisador;
import problema.ControllerProblema;
import repositorios.RepositorioAtividades;
import repositorios.RepositorioObjetivos;
import repositorios.RepositorioPesquisadores;
import repositorios.RepositorioPesquisas;
import repositorios.RepositorioProblemas;
import utilidades.Utilidade;

public class Facade {
	private ControllerPesquisas controllerPesquisas;
	private ControllerAtividades controllerAtividades;
	private ControllerPesquisador controllerPesquisadores;
	private ControllerObjetivo controllerObjetivo;
	private ControllerProblema controllerProblema;
	
	private RepositorioAtividades repositorioAtividades;
	private RepositorioProblemas repositorioProblema;
	private RepositorioPesquisadores repositorioPesquisador;
	private RepositorioPesquisas repositorioPesquisas;
	private RepositorioObjetivos repositorioObjetivos;
	private Busca busca;


	public Facade() {
		this.repositorioAtividades = new RepositorioAtividades();
		this.repositorioProblema = new RepositorioProblemas();
		this.repositorioPesquisador = new RepositorioPesquisadores();
		this.repositorioPesquisas = new RepositorioPesquisas();
		this.repositorioObjetivos = new RepositorioObjetivos();
		
		this.controllerPesquisas = new ControllerPesquisas();
		this.controllerAtividades = new ControllerAtividades();
		this.controllerPesquisadores = new ControllerPesquisador();
		this.controllerObjetivo = new ControllerObjetivo();
		this.controllerProblema = new ControllerProblema();
		
		this.busca = new Busca();
	}

	public static void main(String[] args) {

		args = new String[] { "facade.Facade", "easyaccept/use_case_1.txt", "easyaccept/use_case_2.txt",
				"easyaccept/use_case_3.txt", "easyaccept/use_case_4.txt", "easyaccept/use_case_5.txt",
				"easyaccept/use_case_6.txt", "easyaccept/use_case_7.txt", "easyaccept/use_case_8.txt",
				"easyaccept/use_case_9.txt", "easyaccept/use_case_10.txt", "easyaccept/use_case_11.txt",
				"easyaccept/use_case_12SALVAR.txt","easyaccept/use_case_12CARREGAR.txt" };
		EasyAccept.main(args);
	}

	// ==============================| PARTE 1 |==============================
	// @author Felipe Siqueira

	// Pesquisas.
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return this.controllerPesquisas.cadastraPesquisa(descricao, campoDeInteresse, repositorioPesquisas);
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		this.controllerPesquisas.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo, repositorioPesquisas);
	}

	public void encerraPesquisa(String codigo, String motivo) {
		this.controllerPesquisas.encerraPesquisa(codigo, motivo, repositorioPesquisas);
	}

	public void ativaPesquisa(String codigo) {
		this.controllerPesquisas.ativaPesquisa(codigo, repositorioPesquisas);
	}

	public String exibePesquisa(String codigo) {
		return this.controllerPesquisas.exibePesquisa(codigo, repositorioPesquisas);
	}

	public boolean pesquisaEhAtiva(String codigo) {
		return this.controllerPesquisas.ehAtiva(codigo, repositorioPesquisas);
	}

	// ==============================| PARTE 2 |==============================
	// @author Davi Andrade

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		this.controllerPesquisadores.cadastraPesquisador(nome, funcao, biografia, email, fotoURL,
				repositorioPesquisador);	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		this.controllerPesquisadores.alteraPesquisador(email, atributo, novoValor, repositorioPesquisador);
	}

	public void desativaPesquisador(String email) {
		this.controllerPesquisadores.desativaPesquisador(email, repositorioPesquisador);
	}

	public void ativaPesquisador(String email) {
		this.controllerPesquisadores.ativaPesquisador(email, repositorioPesquisador);
	}

	public String exibePesquisador(String email) {
		return this.controllerPesquisadores.exibePesquisador(email, repositorioPesquisador);
	}

	public boolean pesquisadorEhAtivo(String email) {
		return this.controllerPesquisadores.pesquisadorEhAtivo(email, repositorioPesquisador);
	}

	// ==============================| PARTE 3 |==============================
	// @author Placido Henrique.

	// Problemas.
	public void cadastraProblema(String descricao, int viabilidade) {
		this.controllerProblema.cadastraProblema(descricao, viabilidade, repositorioProblema);
	}

	public void apagarProblema(String codigo) {
		this.controllerProblema.apagaProblema(codigo, repositorioProblema);
	}

	public String exibeProblema(String codigo) {
		return this.controllerProblema.exibeProblema(codigo, repositorioProblema);
	}

	// Objetivos.
	public void cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		this.controllerObjetivo.cadastraObjetivo(tipo, descricao, aderencia, viabilidade, repositorioObjetivos);
	}

	public void apagarObjetivo(String codigo) {
		this.controllerObjetivo.apagarObjetivo(codigo, repositorioObjetivos);
	}

	public String exibeObjetivo(String codigo) {
		return this.controllerObjetivo.exibeObjetivo(codigo, repositorioObjetivos);
	}

	// ==============================| PARTE 4 |==============================
	// @author Victor Hugo Sousa

	public void cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		this.controllerAtividades.cadastraAtividade(descricao, nivelRisco, descricaoRisco, repositorioAtividades);
	}

	public void apagaAtividade(String codigo) {
		this.controllerAtividades.apagaAtividade(codigo, repositorioAtividades);
	}

	public String exibeAtividade(String codigo) {
		return this.controllerAtividades.exibeAtividade(codigo, repositorioAtividades);
	}

	public void cadastraItem(String codigo, String item) {
		this.controllerAtividades.cadastraItem(codigo, item, repositorioAtividades);
	}

	public int contaItensPendentes(String codigo) {
		return this.controllerAtividades.contaItensPendentes(codigo, repositorioAtividades);
	}

	public int contaItensRealizados(String codigo) {
		return this.controllerAtividades.contaItensRealizados(codigo, repositorioAtividades);
	}

	// ==============================| PARTE 5 |==============================
	// @author Placido Henrique.

	public boolean associaProblema(String idPesquisa, String idProblema) {
		return this.controllerPesquisas.associaProblema(idPesquisa, idProblema, repositorioPesquisas);
	}

	public boolean desassociaProblema(String idPesquisa) {
		return this.controllerPesquisas.desassociaProblema(idPesquisa, repositorioPesquisas);
	}

	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		return this.controllerPesquisas.associaObjetivo(idPesquisa, idObjetivo, repositorioPesquisas, repositorioObjetivos);
	}

	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {	
		return this.controllerPesquisas.desassociaObjetivo(idPesquisa, idObjetivo, repositorioPesquisas, repositorioObjetivos);
	}

	public String listaPesquisas(String ordem) {
		Utilidade.validaString(ordem, "Valor invalido da ordem");
		return this.controllerPesquisas.listaPesquisas(ordem, repositorioPesquisas);	}

	// ==============================| PARTE 6 |==============================
	// @author Victor Hugo Sousa

	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		this.controllerPesquisadores.cadastraEspecialidadeProfessor(email, formacao, unidade, data,
				repositorioPesquisador);	}

	public void cadastraEspecialidadeAluno(String email, String semestre, String IEA) {
		this.controllerPesquisadores.cadastraEspecialidadeAluno(email, semestre, IEA, repositorioPesquisador);
	}

	public String listaPesquisadores(String tipo) {
		return this.controllerPesquisadores.listaPesquisadores(tipo, repositorioPesquisador);
	}

	public boolean associaPesquisador(String idPesquisa, String email) {
		return controllerPesquisas.associaPesquisadorPesquisa(idPesquisa, email, repositorioPesquisas, repositorioPesquisador);
	}

	public boolean desassociaPesquisador(String idPesquisa, String email) {
		return controllerPesquisas.desassociaPesquisadorPesquisa(idPesquisa, email, repositorioPesquisas,
				repositorioPesquisador);	}

	// ==============================| PARTE 7 |==============================
	// @author Davi Andrade

	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		this.controllerAtividades.associaPesquisa(codigoPesquisa, codigoAtividade, repositorioAtividades);
		return this.controllerPesquisas.associaAtividade(codigoPesquisa, codigoAtividade, repositorioPesquisas,
				repositorioAtividades);	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		return this.controllerPesquisas.desassociaAtividade(codigoPesquisa, codigoAtividade, repositorioPesquisas,
				repositorioAtividades);	}

	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		this.controllerAtividades.executaAtividade(codigoAtividade, item, duracao, repositorioAtividades);
	}

	public int cadastraResultado(String codigoAtividade, String resultado) {
		return this.controllerAtividades.cadastraResultado(codigoAtividade, resultado, repositorioAtividades);
	}

	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return this.controllerAtividades.removeResultado(codigoAtividade, numeroResultado, repositorioAtividades);
	}

	public String listaResultados(String codigoAtividade) {
		return this.controllerAtividades.listaResultados(codigoAtividade, repositorioAtividades);
	}

	public int getDuracao(String codigoAtividade) {
		return this.controllerAtividades.getDuracao(codigoAtividade, repositorioAtividades);
	}

	// ==============================| PARTE 8 |==============================
	// @author Felipe Siqueira

	public String busca(String termo) {
		return busca.buscaTermo(termo, repositorioAtividades, repositorioObjetivos, repositorioPesquisador,
				repositorioPesquisas, repositorioProblema);	}

	public int contaResultadosBusca(String termo) {
		return busca.contaResultadosBusca(termo, repositorioAtividades, repositorioObjetivos, repositorioPesquisador,
				repositorioPesquisas, repositorioProblema);	}

	public String busca(String termo, int posicao) {
		return busca.buscaTermoPosicao(termo, posicao, repositorioAtividades, repositorioObjetivos,
				repositorioPesquisador, repositorioPesquisas, repositorioProblema);	}

	// ==============================| PARTE 9 |==============================
	// @author Victor Hugo Sousa

	public void defineProximaAtividade(String idPrecedente, String idSubsquente) {
		this.controllerAtividades.defineProximaAtividade(idPrecedente, idSubsquente, repositorioAtividades);
	}

	public int contaProximos(String idAtividade) {
		return this.controllerAtividades.contaProximos(idAtividade, repositorioAtividades);
	}

	public void tiraProximaAtividade(String idPrecedente) {
		this.controllerAtividades.tiraProximaAtividade(idPrecedente, repositorioAtividades);
	}

	public String pegaProximo(String idAtividade, int enesimaAtividade) {
		return this.controllerAtividades.pegaProximo(idAtividade, enesimaAtividade, repositorioAtividades);
	}

	public String pegaMaiorRiscoAtividades(String idAtividade) {
		return this.controllerAtividades.pegaMaiorRiscoAtividades(idAtividade, repositorioAtividades);
	}

	// ==============================| PARTE 10 |==============================
	// @author Davi Andrade Pontes

	public void configuraEstrategia(String estrategia) {
		this.controllerPesquisas.configuraEstrategia(estrategia);
	}

	public String proximaAtividade(String codigoPesquisa) {
		return this.controllerPesquisas.proximaAtividade(codigoPesquisa, repositorioPesquisas);
	}

	// ==============================| PARTE 11 |==============================
	// @author Felipe de Souza Siqueira
	
	public void gravarResumo(String codigoPesquisa) {
		this.controllerPesquisas.gravarResumo(codigoPesquisa, repositorioPesquisas, repositorioProblema, repositorioObjetivos);
	}
	
	public void gravarResultados(String codigoPesquisa) {
		this.controllerPesquisas.gravarResultados(codigoPesquisa, repositorioPesquisas);
	}

	// ==============================| PARTE 12 |==============================
	// @author Placido Henrique.
	
	public void salvar() {
		this.repositorioAtividades.salva();
		this.repositorioObjetivos.salva();
		this.repositorioPesquisador.salva();
		this.repositorioPesquisas.salva();
		this.repositorioProblema.salva();
	}
	
	public void carregar() {
		this.repositorioAtividades.carrega();
		this.repositorioObjetivos.carrega();
		this.repositorioPesquisador.carrega();
		this.repositorioPesquisas.carrega();
		this.repositorioProblema.carrega();
	}
}
