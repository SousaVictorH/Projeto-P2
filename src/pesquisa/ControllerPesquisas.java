package pesquisa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import atividade.IDatividade;
import pesquisadores.Pesquisador;
import problema.Problema;
import repositorios.RepositorioAtividades;
import repositorios.RepositorioObjetivos;
import repositorios.RepositorioPesquisadores;
import repositorios.RepositorioPesquisas;
import repositorios.RepositorioProblemas;
import utilidades.Utilidade;

/**
 * Controller da classe pesquisa
 * 
 * @author Felipe de Souza Siqueira - 119110399 / Victor Hugo Sousa - 119110395
 *
 */
public class ControllerPesquisas {
	private String estrategia;

	public ControllerPesquisas() {
		this.estrategia = "MAIS_ANTIGA";
	}

	private String geraCodigo(String interesses, RepositorioPesquisas repositorioPesquisas) {
		String retorno = "";
		for (int i = 0; i < 3; i++) {
			retorno += interesses.charAt(i);
		}
		if (!repositorioPesquisas.getMapSenhas().containsKey(retorno.toUpperCase())) {
			repositorioPesquisas.getMapSenhas().put(retorno.toUpperCase(), 1);
			retorno += 1;
		} else {
			Integer valor = repositorioPesquisas.getMapSenhas().get(retorno.toUpperCase()) + 1;
			repositorioPesquisas.getMapSenhas().put(retorno.toUpperCase(), valor);
			retorno += valor;

		}

		return retorno.toUpperCase();
	}

	/**
	 * Realiza o cadastro de novas pesquisas
	 * 
	 * @param descricao       descricao da pesquisa
	 * @param camposInteresse Campos de interesse da pesquisa
	 * @return Retorna o codigo da pesquisa
	 */
	public String cadastraPesquisa(String descricao, String camposInteresse,
			RepositorioPesquisas repositorioPesquisas) {
		Utilidade.validaString(descricao, "Descricao nao pode ser nula ou vazia.");
		Utilidade.validaString(camposInteresse, "Formato do campo de interesse invalido.");

		if (camposInteresse.length() >= 255) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}

		if (camposInteresse.split(",").length > 4) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}

		String[] interesses = camposInteresse.split(", ");

		if (!this.tamanhoCerto(interesses)) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}

		String codigo = this.geraCodigo(camposInteresse, repositorioPesquisas);

		Pesquisa pesquisa = new Pesquisa(descricao, interesses, codigo);

		repositorioPesquisas.adicionaPesquisa(codigo, pesquisa);

		return codigo;
	}

	private void checkID(String id, String msg, RepositorioPesquisas repositorioPesquisas) {
		if (!repositorioPesquisas.existePesquisa(id)) {
			throw new IllegalArgumentException(msg);
		}
	}

	private void checkAtividade(String codigo, String msg, RepositorioPesquisas repositorioPesquisas) {
		if (!this.ehAtiva(codigo, repositorioPesquisas)) {
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * to String da pesquisa
	 * 
	 * @param codigo codigo da pesquisa
	 * @return retorna um codigo gerado para a pesquisa
	 */
	public String exibePesquisa(String codigo, RepositorioPesquisas repositorioPesquisas) {
		if (!repositorioPesquisas.existePesquisa(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}

		return repositorioPesquisas.getPesquisa(codigo).toString();
	}

	/**
	 * Altera atributos da classe pesquisa
	 * 
	 * @param codigo               codigo da pesquisa
	 * @param conteudoASerAlterado Especifica qual atributo deve ser alterado
	 * @param novoConteudo         Especifica qual o novo valor do atributo
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo,
			RepositorioPesquisas repositorioPesquisas) {

		if (!repositorioPesquisas.existePesquisa(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}

		if (!this.ehAtiva(codigo, repositorioPesquisas)) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}

		Utilidade.validaString(conteudoASerAlterado, "Campo de interesse invalido.");

		if (!repositorioPesquisas.getPesquisa(codigo).ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}

		if (conteudoASerAlterado.equals("DESCRICAO")) {
			Utilidade.validaString(novoConteudo, "Descricao nao pode ser nula ou vazia.");
		}

		Utilidade.validaString(novoConteudo, "Formato do campo de interesse invalido.");

		if (!conteudoASerAlterado.equals("DESCRICAO") && !conteudoASerAlterado.equals("CAMPO")) {
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
		}

		Pesquisa pesquisa = repositorioPesquisas.getPesquisa(codigo);

		if (conteudoASerAlterado.equals("DESCRICAO")) {
			pesquisa.setDescricao(novoConteudo);
		}

		if (conteudoASerAlterado.equals("CAMPO")) {
			String[] novo = novoConteudo.split(", ");

			pesquisa.setInteresse(novo);
		}
	}

	/**
	 * Encerra uma pesquisa
	 * 
	 * @param codigo codigo da pesquisa
	 * @param motivo Motivo pelo qual a pesquisa está sendo encerrada
	 */
	public void encerraPesquisa(String codigo, String motivo, RepositorioPesquisas repositorioPesquisas) {
		Utilidade.validaString(motivo, "Motivo nao pode ser nulo ou vazio.");

		if (!repositorioPesquisas.existePesquisa(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}

		if (!this.ehAtiva(codigo, repositorioPesquisas)) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}

		repositorioPesquisas.getPesquisa(codigo).encerraPesquisa(motivo);
	}

	/**
	 * Muda o status da pesquisa para ativado(true)
	 * 
	 * @param codigo codigo da pesquisa
	 */
	public void ativaPesquisa(String codigo, RepositorioPesquisas repositorioPesquisas) {
		if (!repositorioPesquisas.existePesquisa(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}

		if (this.ehAtiva(codigo, repositorioPesquisas)) {
			throw new IllegalArgumentException("Pesquisa ja ativada.");
		}

		repositorioPesquisas.getPesquisa(codigo).ativaPesquisa();
	}

	/**
	 * Checa se uma pesquisa está ativa
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean ehAtiva(String codigo, RepositorioPesquisas repositorioPesquisas) {
		Utilidade.validaString(codigo, "Codigo nao pode ser nulo ou vazio.");

		if (!repositorioPesquisas.existePesquisa(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}

		return repositorioPesquisas.getPesquisa(codigo).ehAtiva();
	}

	/**
	 * Checa se uma area de interesses possui a quantidade minima de caracteres
	 * necessária
	 * 
	 * @param interesse Areas de interesse da pesquisa
	 * @return retorna um boolean que informa se a area de pesquisa possui ou não a
	 *         quantidade minima
	 */
	private boolean tamanhoCerto(String[] interesse) {
		for (int i = 0; i < interesse.length; i++) {
			if (interesse[i].length() < 3) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Esse método associa um pesquisador a uma pesquisa
	 * 
	 * @param codigo      codigo da pesquisa
	 * @param pesquisador pesquisador a ser adicionado à pesquisa
	 */
	public boolean associaPesquisadorPesquisa(String codigo, String email, RepositorioPesquisas repositorioPesquisas,
			RepositorioPesquisadores repositorioPesquisadores) {

		utilidades.Utilidade.validaString(codigo, "Campo idPesquisa nao pode ser nulo ou vazio.");
		utilidades.Utilidade.validaString(email, "Campo emailPesquisador nao pode ser nulo ou vazio.");

		if (!repositorioPesquisas.existePesquisa(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}

		Pesquisa pesquisa = repositorioPesquisas.getPesquisa(codigo);

		if (!pesquisa.ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");

		}

		if (!repositorioPesquisadores.existePesquisador(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado.");
		}

		Pesquisador pesquisador = repositorioPesquisadores.getPesquisador(email);

		if (repositorioPesquisas.getPesquisa(codigo).temPesquisador(pesquisador)) {
			return false;
		}

		pesquisa.associaPesquisador(pesquisador);
		return true;

	}

	/**
	 * Esse método desassocia um pesquisador a uma pesquisa
	 * 
	 * @param codigo      codigo da pesquisa
	 * @param pesquisador pesquisador a ser desassociado da pesquisa
	 */
	public boolean desassociaPesquisadorPesquisa(String codigo, String email, RepositorioPesquisas repositorioPesquisas,
			RepositorioPesquisadores repositorioPesquisadores) {

		utilidades.Utilidade.validaString(codigo, "Campo idPesquisa nao pode ser nulo ou vazio.");
		utilidades.Utilidade.validaString(email, "Campo emailPesquisador nao pode ser nulo ou vazio.");

		if (!repositorioPesquisas.existePesquisa(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}

		Pesquisa pesquisa = repositorioPesquisas.getPesquisa(codigo);

		if (!pesquisa.ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}

		if (!repositorioPesquisadores.existePesquisador(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado.");
		}

		Pesquisador pesquisador = repositorioPesquisadores.getPesquisador(email);

		if (!repositorioPesquisas.getPesquisa(codigo).temPesquisador(pesquisador)) {
			return false;
		}

		pesquisa.desassociaPesquisador(pesquisador);
		return true;
	}

	/**
	 * Confere se existe uma pesquisa no controller
	 * 
	 * @param codigoPesquisa Codigo da pesquisa que se deseja saber se existe
	 * @return True caso exista, False caso contrario
	 */
	public boolean existePesquisa(String codigoPesquisa, RepositorioPesquisas repositorioPesquisas) {
		if (repositorioPesquisas.existePesquisa(codigoPesquisa)) {
			return true;
		}

		return false;
	}

	/**
	 * Método para associar uma atividade a uma pesquisa
	 * 
	 * @param codigoPesquisa
	 * @param codigoAtividade
	 * @param controllerAtividades
	 * @return retorna um booleano indicando se foi ou não associada
	 */
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade,
			RepositorioPesquisas repositorioPesquisas, RepositorioAtividades repositorioAtividade) {

		utilidades.Utilidade.validaString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		utilidades.Utilidade.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");

		if (!repositorioPesquisas.existePesquisa(codigoPesquisa)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}

		if (!repositorioPesquisas.getPesquisa(codigoPesquisa).ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}

		if (!repositorioAtividade.existeAtividade(new IDatividade(codigoAtividade))) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}

		return repositorioPesquisas.getPesquisa(codigoPesquisa).associaAtividade(codigoAtividade,
				repositorioAtividade.getAtividade(new IDatividade(codigoAtividade)));
	}

	/**
	 * Método para desassociar uma atividade a uma pesquisa
	 * 
	 * @param codigoPesquisa
	 * @param codigoAtividade
	 * @param controllerAtividades
	 * @return retorna um booleano indicando se foi ou não desassociada
	 **/
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade,
			RepositorioPesquisas repositorioPesquisas, RepositorioAtividades repositorioAtividade) {

		utilidades.Utilidade.validaString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		utilidades.Utilidade.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");

		if (!repositorioPesquisas.existePesquisa(codigoPesquisa)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}

		if (!repositorioPesquisas.getPesquisa(codigoPesquisa).ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}

		if (!repositorioAtividade.existeAtividade(new IDatividade(codigoAtividade))) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}

		return repositorioPesquisas.getPesquisa(codigoPesquisa).desassociaAtividade(codigoAtividade);
	}

	// ==============================| PARTE 5 |==============================
	// @autor Placido Henrique.

	// Relacao com problemas.

	/**
	 * Associa um problema a uma pesquisa, ambos correspondentes aos IDs
	 * especificados.
	 * 
	 * @param idPesquisa ID da pesquisa que tera um problema associado.
	 * @param idProblema Problema que sera associado a pesquisa.
	 * 
	 * @return Um boolean indicando o sucesso ou a falha da associacao.
	 */
	public boolean associaProblema(String idPesquisa, String idProblema, RepositorioPesquisas repositorioPesquisas) {
		// Excecoes idPesquisa.
		Utilidade.validaString(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		Utilidade.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		this.checkID(idPesquisa, "Pesquisa nao encontrada.", repositorioPesquisas);
		this.checkAtividade(idPesquisa, "Pesquisa desativada.", repositorioPesquisas);

		// Exececoes codigoProblema.
		if (!repositorioPesquisas.getPesquisa(idPesquisa).getCodigoProblema().contentEquals("")) {
			if (repositorioPesquisas.getPesquisa(idPesquisa).getCodigoProblema().contentEquals(idProblema)) {
				return false;
			}
			throw new IllegalArgumentException("Pesquisa ja associada a um problema.");
		}

		repositorioPesquisas.getPesquisa(idPesquisa).setCodigoProblema(idProblema);
		return true;
	}

	/**
	 * Desassocia um problema a uma pesquisa, ambos correspondentes aos IDs
	 * especificados.
	 * 
	 * @param idPesquisa ID da pesquisa que seu problema desassociado.
	 * @return Um boolean indicando o sucesso ou a falha da associacao.
	 */
	public boolean desassociaProblema(String idPesquisa, RepositorioPesquisas repositorioPesquisas) {
		Utilidade.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Utilidade.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");

		this.checkID(idPesquisa, "Pesquisa nao encontrada.", repositorioPesquisas);
		this.checkAtividade(idPesquisa, "Pesquisa desativada.", repositorioPesquisas);

		if (repositorioPesquisas.getPesquisa(idPesquisa).getCodigoProblema().contentEquals("")) {
			return false;
		}

		repositorioPesquisas.getPesquisa(idPesquisa).setCodigoProblema("");
		return true;
	}

	/**
	 * Associa um objetivo a uma pesquisa, ambos conforme os IDs passados.
	 * 
	 * @param idPesquisa ID da pesquisa que tera um objetivo associado.
	 * @param idObjetivo ID do objetivo que sera associado a pesquisa.
	 * 
	 * @return Valor booleano que informa o resultado do procedimento.
	 */
	public boolean associaObjetivo(String idPesquisa, String idObjetivo, RepositorioPesquisas repositorioPesquisas,
			RepositorioObjetivos repositorioObjetivos) {

		// Excecoes idObjetivo.
		Utilidade.validaString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		if (!repositorioObjetivos.existeObjetivo(idObjetivo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}

		// Exececoes idPesquisa.
		Utilidade.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		this.checkID(idPesquisa, "Pesquisa nao encontrada.", repositorioPesquisas);
		this.checkAtividade(idPesquisa, "Pesquisa desativada.", repositorioPesquisas);

		if (repositorioObjetivos.getObjetivo(idObjetivo).setIdPesquisa(idPesquisa)) {
			return repositorioPesquisas.getPesquisa(idPesquisa).associaObjetivo(idObjetivo);
		} else {
			return false;
		}

	}

	/**
	 * Desassocia um objetivo a uma pesquisa, ambos conforme os IDs passados.
	 * 
	 * @param idPesquisa ID da pesquisa que tera um objetivo desassociado.
	 * @param idObjetivo ID do objetivo que sera desassociado a pesquisa.
	 * 
	 * @return Valor booleano que informa o resultado do procedimento.
	 */
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo, RepositorioPesquisas repositorioPesquisas,
			RepositorioObjetivos repositorioObjetivos) {
		// Exececoes idObjetivo
		Utilidade.validaString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		if (!repositorioObjetivos.existeObjetivo(idObjetivo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}

		// Excecoes idPesquisa
		Utilidade.validaString(idObjetivo, "Campo idPesquisa nao pode ser nulo ou vazio.");
		this.checkID(idPesquisa, "Pesquisa nao encontrada.", repositorioPesquisas);
		this.checkAtividade(idPesquisa, "Pesquisa desativada.", repositorioPesquisas);

		if (repositorioPesquisas.getPesquisa(idPesquisa).desassociaObjetivo(idObjetivo)) {
			return repositorioObjetivos.getObjetivo(idObjetivo).dessasociaPesquisa();
		} else {
			return false;
		}
	}

	/**
	 * Lista as pesquisas presentes no sistema de acordo com a ordem escolhida. As
	 * ordenacoes disponiveis, todas do maior para o menor, sao: Pesquisa (a partir
	 * do ID da propia pesquisa), Problema (pelos IDs dos problemas das pesquisas),
	 * Objetivos (atraves do numero de objetivos que a pesquisa contem, com criterio
	 * de desempate sendo o objetivo de maior ID das pesquisas). Caso as pesquisas
	 * nao tenham problema associado(na listagem por problema), ou nao tenham nenhum
	 * objetivo(quando o criterio for objetivos), elas serao ordenadas pelo ID
	 * propio.
	 * 
	 * @param ordem Criterio da ordenacao dos elementos na listagem.
	 * 
	 * @return Uma Lista das representacoes textuais das pesquisas no formato :
	 *         "pesquisa 1 | pesquisa 2 | ..."
	 */
	public String listaPesquisas(String ordem, RepositorioPesquisas repositorioPesquisas) {
		ordem = ordem.toUpperCase();
		String saida = "";
		ArrayList<Pesquisa> listaPesquisas = new ArrayList<Pesquisa>(repositorioPesquisas.getMapPesquisas().values());

		if (ordem.contentEquals("PESQUISA")) {
			Collections.sort(listaPesquisas);
		}

		else if (ordem.contentEquals("PROBLEMA")) {
			Collections.sort(listaPesquisas, new OrdenacaoProblema());
		}

		else if (ordem.contentEquals("OBJETIVOS")) {
			Collections.sort(listaPesquisas, new OrdenacaoObjetivos());
		}

		else {
			throw new IllegalArgumentException("Valor invalido da ordem");
		}

		for (Pesquisa pesquisa : listaPesquisas) {
			saida += pesquisa.toString() + " | ";
		}

		saida = saida.substring(0, saida.length() - 3);

		return saida;
	}

	public void configuraEstrategia(String estrategia) {
		Utilidade.validaString(estrategia, "Estrategia nao pode ser nula ou vazia.");

		if (!estrategia.equals("MAIS_ANTIGA") && !estrategia.equals("MENOS_PENDENCIAS")
				&& !estrategia.equals("MAIOR_RISCO") && !estrategia.equals("MAIOR_DURACAO")) {
			throw new IllegalArgumentException("Valor invalido da estrategia");
		}

		this.estrategia = estrategia;
	}

	public String proximaAtividade(String codigoPesquisa, RepositorioPesquisas repositorioPesquisas) {
		Utilidade.validaString(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");

		if (!repositorioPesquisas.existePesquisa(codigoPesquisa)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}

		if (!repositorioPesquisas.getPesquisa(codigoPesquisa).ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}

		if (!repositorioPesquisas.getPesquisa(codigoPesquisa).temPendencias()) {
			throw new IllegalArgumentException("Pesquisa sem atividades com pendencias.");
		}

		return repositorioPesquisas.getPesquisa(codigoPesquisa).proximaAtividade(estrategia);
	}

	public void gravarResumo(String codigoPesquisa, RepositorioPesquisas repositorioPesquisa,
			RepositorioProblemas repositorioProblema, RepositorioObjetivos repositorioObjetivo) {

		Utilidade.validaString(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		if (!repositorioPesquisa.getMapPesquisas().containsKey(codigoPesquisa)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}

		Pesquisa pesquisa = repositorioPesquisa.getPesquisa(codigoPesquisa);
		Problema problema = repositorioProblema.getProblema(pesquisa.getCodigoProblema());
		String objetivos = "";
		for (String s : repositorioPesquisa.getPesquisa(codigoPesquisa).retornaObjetivos()) {
			objetivos += "        - " + repositorioObjetivo.getObjetivo(s) + "\n";
		}

		try {

			FileWriter fw = new FileWriter("_" + codigoPesquisa + ".txt");
			PrintWriter pw = new PrintWriter(fw);
			
			String string = "- Pesquisa: " + codigoPesquisa + " - " + pesquisa.getDescricao() + " - "
					+ pesquisa.getCamposDeInteresse() + "\n" + "    - Pesquisadores:" 
					+ pesquisa.retornaPesquisadores() + "\n    - Problema:\n" + "        - "
					+ problema.toString() + "\n" + "    - Objetivos:\n" + objetivos  + "    - Atividades:\n"
					+ pesquisa.retornaAtividades();
			pw.print(string);
			pw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void gravarResultados(String codigoPesquisa, RepositorioPesquisas repositorioPesquisa) {
		Utilidade.validaString(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		if (!repositorioPesquisa.getMapPesquisas().containsKey(codigoPesquisa)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}

		Pesquisa pesquisa = repositorioPesquisa.getPesquisa(codigoPesquisa);
		try {
			File file = new File(codigoPesquisa + "-Resultados.txt");
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			pw.print("- Pesquisa: " + codigoPesquisa + " - " + pesquisa.getDescricao() + " - "
					+ pesquisa.getCamposDeInteresse() + System.lineSeparator() + "    - Resultados:"+ System.lineSeparator() + pesquisa.retornaItensResultados()
			);

			pw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
