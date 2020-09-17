package pesquisa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import utilidades.ComparatorDuracao;
import utilidades.ComparatorPendencias;
import utilidades.ComparatorRisco;
import utilidades.Utilidade;
import pesquisadores.Pesquisador;

import java.util.HashSet;
import java.util.LinkedHashMap;

import atividade.Atividade;

/**
 * Classe simulando uma pesquisa científica.
 * 
 * @author Felipe de Souza Siqueira - 119110399 / Victor Hugo Sousa - 119110395
 *
 */
public class Pesquisa implements Comparable<Pesquisa>, Serializable {
	/**
	 * Codigo de identificacao para serializacao da classe.
	 */
	private static final long serialVersionUID = 4358284018179201139L;

	/**
	 * Descrição da pesquisa
	 */
	private String descricao;

	/**
	 * Campos de interesse da pesquisa
	 */
	private String[] campoInteresse;

	/**
	 * codigo de indentificacao da pesquisa
	 */
	private String codigo;

	/**
	 * Status da pesquisa
	 */
	private boolean ativa;

	/**
	 * Motivo de desativacao da pesquisa (caso seja necessario)
	 */
	private String motivo;

	/**
	 * pesquisadores associados a pesquisa
	 */
	private ArrayList<Pesquisador> pesquisadores;

	/**
	 * HashMap com as atividades
	 */
	private LinkedHashMap<String, Atividade> atividades;

	/**
	 * ArrayList com as atividades cadastradas na ordem em que foram inseridas
	 */
	private ArrayList<Atividade> listAtividades;

	/**
	 * Código de identificação do problema da pesquisa.
	 */
	private String codigoProblema;

	/**
	 * Local de armazenamento dos objetivos da pesquisa.
	 */
	private HashSet<String> objetivos;

	/**
	 * Comparator de atividades
	 */
	private Comparator<Atividade> comparator;

	/**
	 * Construtor da classe pesquisa
	 * 
	 * @param descricao      Descricao da pesquisa
	 * @param campoInteresse Campo de interesse da pesquisa
	 * @param codigo         Codigo de identificação da pesquisa
	 */
	public Pesquisa(String descricao, String[] campoInteresse, String codigo) {
		this.descricao = descricao;
		this.campoInteresse = campoInteresse;
		this.codigo = codigo;

		this.ativa = true;
		this.pesquisadores = new ArrayList<>();
		this.atividades = new LinkedHashMap<>();
		this.objetivos = new HashSet<String>();
		this.listAtividades = new ArrayList<>();
		this.codigoProblema = "";
	}

	@Override
	public int compareTo(Pesquisa p2) {
		return -this.codigo.compareTo(p2.codigo);
	}

	/**
	 * toString da classe pesquisa contendo codigo, descricao e campo de interesse.
	 */
	public String toString() {
		String retorno = this.codigo + " - " + this.descricao + " - ";
		for (int i = 0; i < campoInteresse.length; i++) {
			if (i < campoInteresse.length - 1) {
				retorno += campoInteresse[i] + ", ";
			} else {
				retorno += campoInteresse[i];
			}
		}
		return retorno;
	}

	/**
	 * método para alteração da descricao
	 * 
	 * @param newDescricao
	 */
	public void setDescricao(String newDescricao) {
		this.descricao = newDescricao;
	}

	/**
	 * método para alteração do campo de interesse
	 * 
	 * @param newInteresse
	 */
	public void setInteresse(String[] newInteresse) {
		this.campoInteresse = newInteresse;
	}

	/**
	 * método para encerrar uma pesquisa
	 * 
	 * @param motivo Motivo pelo qual a pesquisa está sendo encerrada
	 */
	public void encerraPesquisa(String motivo) {
		this.ativa = false;
		this.motivo = motivo;
	}

	/**
	 * método para ativar pesquisa
	 */
	public void ativaPesquisa() {
		this.ativa = true;
	}

	/**
	 * método de checagem do status de uma pesquisa
	 * 
	 * @return retorna uma booleano contendo o status da pesquisa
	 */
	public boolean ehAtiva() {
		return this.ativa;
	}

	/**
	 * gera representações inteiras para cada objeto
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/**
	 * compara objetos a partir de seus códigos
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pesquisa other = (Pesquisa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * Esse método associa um pesquisador à pesquisa
	 * 
	 * @param pesquisador pesquisador a ser adicionado à pesquisa
	 */
	public void associaPesquisador(Pesquisador pesquisador) {

		pesquisadores.add(pesquisador);

	}

	/**
	 * Esse método desassocia um pesquisador à pesquisa
	 * 
	 * @param pesquisador pesquisador a ser retirado à pesquisa
	 */
	public void desassociaPesquisador(Pesquisador pesquisador) {

		pesquisadores.remove(pesquisador);

	}

	/**
	 * Adiciona uma atividade ao HashMap de atividades
	 * 
	 * @param codigoAtividade Chave da atividade
	 * @param atividade       Atividade que será adicionada ao hashMap de atividades
	 * @return Retorna um booleano informando se a atividade foi ou não associada
	 */
	public boolean associaAtividade(String codigoAtividade, Atividade atividade) {
		if (this.atividades.containsKey(codigoAtividade)) {
			return false;
		}

		this.listAtividades.add(atividade);
		this.atividades.put(codigoAtividade, atividade);

		return true;
	}

	/**
	 * Retira uma atividade do HashMap de atividades
	 * 
	 * @param codigoAtividade Chave da atividade que será removida
	 * @return Retorna um booleano informando se a atividade foi ou não removida
	 */
	public boolean desassociaAtividade(String codigoAtividade) {
		if (!this.atividades.containsKey(codigoAtividade)) {
			return false;
		}

		this.listAtividades.remove(atividades.get(codigoAtividade));
		this.atividades.remove(codigoAtividade);
		return true;
	}

	// ==============================| PARTE 5 |==============================
	// @autor Placido Henrique.

	// Relacao com problema.

	/**
	 * Atribui um valor para o código do problema da pesquisa.
	 * 
	 * @param codigoProblema Valor que o código do problema assumirá.
	 */
	public void setCodigoProblema(String codigoProblema) {
		this.codigoProblema = codigoProblema;
	}

	/**
	 * Metodo de retorno do atributo codigoProblema.
	 * 
	 * @return O valor atual do codigo do problema.
	 */
	public String getCodigoProblema() {
		return this.codigoProblema;
	}

	/**
	 * Retorna o numero de objetivos associados a pesquisa.
	 * 
	 * @return Numero de objetivos associados a pesquisa em int.
	 */
	public int getObjetivosSize() {
		return this.objetivos.size();
	}

	// Relacao com objetivo.

	/**
	 * Associa um objetivo a pesquisa por meio de seu ID.
	 * 
	 * @param idObjetivo ID do objetivo que sera associado a pesquisa.
	 * 
	 * @return Valor booleano que informa o resultado do procedimento.
	 */
	public boolean associaObjetivo(String idObjetivo) {
		this.objetivos.add(idObjetivo);
		return true;
	}

	/**
	 * Desassocia um objetivo a pesquisa por meio de seu ID.
	 * 
	 * @param idObjetivo ID do objetivo que sera desassociado a pesquisa.
	 * 
	 * @return Valor booleano que informa o resultado do procedimento.
	 */
	public boolean desassociaObjetivo(String idObjetivo) {
		if (!this.objetivos.contains(idObjetivo)) {
			return false;
		}
		this.objetivos.remove(idObjetivo);
		return true;
	}

	/**
	 * Este metodo retorna o objetivo de maior ID pertencente a pesquisa.
	 * 
	 * @return ID do maior entre os objetivos.
	 */
	public String getMaiorObjetivo() {
		String maiorObjetivo = "";
		for (String objetivo : this.objetivos) {
			if (objetivo.compareTo(maiorObjetivo) == -1) {
				maiorObjetivo = objetivo;
			}
		}
		return maiorObjetivo;
	}

	/**
	 * Retorna a descricao da pesquisa
	 * 
	 * @return Retorna uma string contendo a descricao da pesquisa
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Retorna uma String contendo os campos de interesse
	 * 
	 * @return Retorna uma String contendo os campos de interesse
	 */
	public String getCamposDeInteresse() {
		String string = "";
		for (int i = 0; i < campoInteresse.length; i++) {
			if (i < campoInteresse.length - 1) {
				string += campoInteresse[i] + ", ";
			} else {
				string += campoInteresse[i];
			}
		}
		return string;
	}

	/**
	 * Checa se um pesquisador está cadastrado
	 * 
	 * @param pesquisador Pesquisador que será procurado
	 * @return Retorna um booleano informando se o pesquisador está ou não
	 *         cadastrado
	 */
	public boolean temPesquisador(Pesquisador pesquisador) {

		if (pesquisadores.contains(pesquisador)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return True caso existam atividades com pendencias, False caso contrario
	 */
	public boolean temPendencias() {
		for (String chave : atividades.keySet()) {
			Atividade a = atividades.get(chave);

			if (a.getItensPendentes() > 0) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 
	 * @param estrategia
	 * @return
	 */
	public String proximaAtividade(String estrategia) {
		Utilidade.validaString(estrategia, "Campo estrategia nao pode ser nulo ou vazio");

		if (!this.temPendencias()) {
			throw new IllegalArgumentException("Pesquisa sem atividades pendentes");
		}

		ArrayList<Atividade> aux = new ArrayList<>();

		aux.addAll(this.listAtividades);

		if (estrategia.equals("MAIS_ANTIGA")) {

			for (int i = 0; i < aux.size(); i++) {
				if (aux.get(i).getItensPendentes() > 0) {
					return aux.get(i).getId();
				}
			}

			return null;
		}

		if (estrategia.equals("MENOS_PENDENCIAS")) {
			this.comparator = new ComparatorPendencias<Atividade>();
		}

		if (estrategia.equals("MAIOR_RISCO")) {
			this.comparator = new ComparatorRisco<Atividade>();
		}

		if (estrategia.equals("MAIOR_DURACAO")) {
			this.comparator = new ComparatorDuracao<Atividade>();
		}

		Collections.sort(aux, comparator);

		for (int i = 0; i < aux.size(); i++) {
			if (aux.get(i).getItensPendentes() > 0) {
				return aux.get(i).getId();
			}
		}

		return null;
	}

	/**
	 * Retorna uma String com os detalhes de todos os pesquisadores
	 * 
	 * @return Retorna uma String com os detalhes de todos os pesquisadores
	 */
	public String retornaPesquisadores() {
		String string = "";
		for (int i = 0; i < pesquisadores.size(); i++) {
			string += "\n        - " + pesquisadores.get(i).toString();
		}
		return string;
	}

	/**
	 * Retorna um hashset com todos os objetivos
	 * 
	 * @return Retorna um hashset com todos os objetivos
	 */
	public HashSet<String> retornaObjetivos() {
		return objetivos;
	}

	/**
	 * Retorna uma lista com todas as atividade e com seus itens ao lado de seus
	 * status
	 * 
	 * @return Retorna uma lista com todas as atividade e com seus itens ao lado de
	 *         seus status
	 */
	public String retornaAtividades() {
		String string = "";

		for (Atividade atividade : atividades.values()) {
			string += atividade.retornaAtividadeItens();
		}

		return string;
	}

	/**
	 * Retorna uma String da descrição da atividade e dos itens cadastrados e sua
	 * duração. Caso a duração seja = 0, o Item não é retornado
	 * 
	 * @return Retorna uma String da descrição da atividade e dos itens cadastrados
	 *         e sua duração.
	 */
	public String retornaItensResultados() {
		String string = "";
		for (Atividade atividade : atividades.values()) {
			string += "        - " + atividade.getDescricao() + "\n" + atividade.retornaItensResultados();
		}
		return string;
	}
}
