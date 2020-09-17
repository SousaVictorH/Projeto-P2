package atividade;

import java.io.Serializable;
import java.util.ArrayList;

import utilidades.Utilidade;

/**
 * Classe atividade, toda atividade possui descricao, nivel de risco, descricao
 * de risco e uma atividade subsequente, toda atividade começa com 0 itens
 * adicionados e sua atividade subsequente começa como null
 * 
 * @author Victor Hugo Sousa - 119110395
 *
 */
public class Atividade implements Serializable {
	/**
	 * Codigo de serializacao da classe.
	 */
	private static final long serialVersionUID = 4341536853148837309L;

	/**
	 * descricao no formato de tipo String é guardada a descricao da atividade.
	 */
	private String descricao;

	/**
	 * nivelRisco no formato de tipo String é guardado o nível de risco da atividade
	 * (ALTO/MEDIO/BAIXO).
	 */
	private String nivelRisco;

	/**
	 * descricaoRisco no formato de tipo String é guardada a descricao do risco da
	 * atividade.
	 */
	private String descricaoRisco;

	/**
	 * Id da atividade
	 */
	private IDatividade id;

	/**
	 * listaDeItens no formato de tipo array list são guardados todos os itens
	 * adicionados à atividade.
	 */
	private ArrayList<Item> listaDeItens;

	/**
	 * 
	 */
	private ArrayList<Resultado> listResultados;

	/**
	 * 
	 */
	private ArrayList<Integer> resultadosRemovidos;

	/**
	 * 
	 */
	private int contResultados;

	/**
	 * 
	 */
	private ArrayList<String> pesquisasAssociadas;

	/**
	 * atividadeSuubsequente, atividade subsequente
	 */
	private Atividade atividadeSubsequente;

	/**
	 * Contrutor da calsse atividade.
	 * 
	 * @param descricao      descricao da atividade.
	 * @param nivelRisco     nivel de risco da atividade.
	 * @param descricaoRisco descricao do nivel de risco da atividade.
	 */
	public Atividade(String descricao, String nivelRisco, String descricaoRisco, IDatividade id) {

		Utilidade.validaString(descricao, "Campo Descricao nao pode ser nulo ou vazio.");

		Utilidade.validaString(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");

		if (!((nivelRisco.equals("BAIXO")) || (nivelRisco.equals("MEDIO")) || (nivelRisco.equals("ALTO")))) {
			throw new IllegalArgumentException("Valor invalido do nivel do risco.");
		}

		Utilidade.validaString(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");

		this.descricao = descricao;
		this.nivelRisco = nivelRisco;
		this.descricaoRisco = descricaoRisco;
		this.id = id;

		this.listaDeItens = new ArrayList<>();
		this.contResultados = 0;
		this.resultadosRemovidos = new ArrayList<>();
		this.listResultados = new ArrayList<>();
		this.pesquisasAssociadas = new ArrayList<>();

		this.atividadeSubsequente = null;
	}

	/**
	 * Esse método retorna a representacao em string da atividade.
	 * 
	 * @return representação em string da atividade.
	 */
	@Override
	public String toString() {

		String string = String.format("%s (%s - %s)", descricao, nivelRisco, descricaoRisco);

		for (Item i : listaDeItens) {

			string += " | " + i.toString();

		}

		return string;

	}

	/**
	 * Esse método cadastra um item à atividade.
	 * 
	 * @param item item a ser adicionado à atividade.
	 */
	public void cadastraItem(String item) {

		Utilidade.validaString(item, "Item nao pode ser nulo ou vazio.");

		listaDeItens.add(new Item(item));

	}

	public boolean contemItem(int item) {
		if (this.listaDeItens.size() < item) {
			return false;
		}

		return true;
	}

	public void executaItem(int item, int duracao) {
		if (this.listaDeItens.get(item - 1).getStatus().equals("REALIZADO")) {
			throw new IllegalArgumentException("Item ja executado.");
		}

		this.listaDeItens.get(item - 1).executa(duracao);
	}

	/**
	 * Esse método retorna a quantidade de itens pendentes relativos à atividade.
	 * 
	 * @return quantidade de itens pendentes relativos à atividade.
	 */
	public int getItensPendentes() {

		int contador = 0;

		for (int c = 0; c < listaDeItens.size(); c++) {

			if (listaDeItens.get(c).getStatus().equals("PENDENTE")) {
				contador += 1;
			}

		}

		return contador;

	}

	/**
	 * Esse método retorna a quantidade de itens realizados relativos à atividade.
	 * 
	 * @return quantidade de itens realizados relativos à atividade.
	 */
	public int getItensRealizados() {

		int contador = 0;

		for (int c = 0; c < listaDeItens.size(); c++) {

			if (listaDeItens.get(c).getStatus().equals("REALIZADO")) {
				contador += 1;
			}

		}

		return contador;

	}

	public String getId() {
		return id.getID();
	}

	/**
	 * Cadastra um novo resultado
	 * 
	 * @param resultado String indicando qual foi o resultado
	 * @return Retorna um int com a quantidade de resultados
	 */
	public int cadastraResultado(String resultado) {
		this.contResultados++;

		this.listResultados.add(new Resultado(resultado, contResultados));

		return this.contResultados;
	}

	/**
	 * Metodo para a remocao de um resultado
	 * 
	 * @param numeroResultado Id do resultado
	 * @return Retorna um booleano indicando se o resultado foi(true) ou não(false)
	 *         removido da lista de resultados
	 */
	public boolean removeResultado(int numeroResultado) {
		for (int i = 0; i < this.listResultados.size(); i++) {

			if (this.listResultados.get(i).getId() == numeroResultado) {
				this.listResultados.remove(i);

				this.resultadosRemovidos.add(numeroResultado);

				return true;
			}
		}

		if (this.resultadosRemovidos.contains(numeroResultado)) {
			return false;
		}

		throw new IllegalArgumentException("Resultado nao encontrado.");

	}

	/**
	 * Produz uma String em forma de lista com todos os resultados
	 * 
	 * @return Retorna uma String com todos os resultados
	 */
	public String listaResultados() {
		String retorno = "";

		for (int i = 0; i < this.listResultados.size(); i++) {

			if (i == 0) {
				retorno += listResultados.get(i).getResultado();
			} else {
				retorno += " | " + listResultados.get(i).getResultado();
			}
		}

		return retorno;
	}

	/**
	 * Retorna um int com o a duracao total
	 * 
	 * @return Retorna um int com a duracao total
	 */
	public int getDuracao() {
		int retorno = 0;

		for (int i = 0; i < this.listaDeItens.size(); i++) {
			retorno += this.listaDeItens.get(i).getDuracao();
		}

		return retorno;
	}

	/**
	 * Retorna a descricao da Atividades
	 * 
	 * @return Retorna uma String com a descricao da atividade
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Retorna a descricao de risco da Atividades
	 * 
	 * @return Retorna uma String com a descricao de risco da atividade
	 */
	public String getDescricaoRisco() {
		return this.descricaoRisco;
	}

	/**
	 * Esse método gera uma representação inteira à atividade
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		return result;
	}

	/**
	 * Esse método compara a atividade a um dado objeto
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	/**
	 * Associa uma pesquisa a atividade
	 * 
	 * @param codigoPesquisa Id da pesquisa
	 */
	public void associaPesquisa(String codigoPesquisa) {
		this.pesquisasAssociadas.add(codigoPesquisa);
	}

	/**
	 * Checa se uma pesquisa é associada a pesquisa
	 * 
	 * @return Retorna um booleano indicando ese a pesquisa é(true) ou não(false)
	 *         associada a atividade
	 */
	public boolean associadoComPesquisa() {
		if (this.pesquisasAssociadas.size() == 0) {
			return false;
		}

		return true;
	}

	/**
	 * Esse método retorna o nivel do risco da atividade
	 * 
	 * @return nível de risco da atividade
	 */
	public String getNivelRisco() {
		return this.nivelRisco;
	}

	/**
	 * Esse método define a atividade subsequente
	 * 
	 * @param atividadeSubsequente, atividade subsequente
	 */
	public void setSubsequente(Atividade atividadeSub) {

		if (verificaLoop(this, atividadeSub)) {
			throw new IllegalArgumentException("Criacao de loops negada.");
		}

		if (this.atividadeSubsequente != null) {
			throw new IllegalArgumentException("Atividade ja possui uma subsequente.");
		}

		this.atividadeSubsequente = atividadeSub;

	}

	/**
	 * Esse método privado verifica se um loop será gerado
	 * 
	 * @param atividadeOriginal, atividade
	 * @param atividadeSub,      atividade subsequente
	 * @return true caso se verifique será gerada um loop e false caso contrário
	 */
	private boolean verificaLoop(Atividade atividadeOriginal, Atividade atividadeSub) {

		if (atividadeSub == null) {
			return false;
		}

		if (atividadeOriginal.equals(atividadeSub)) {
			return true;
		}

		return verificaLoop(atividadeOriginal, atividadeSub.atividadeSubsequente);

	}

	/**
	 * Esse método conta a quantidade de atividades subsequentes
	 * 
	 * @return quantidade atividades subsequentes
	 */
	public int contaProximos() {

		if (this.atividadeSubsequente == null) {
			return 0;
		}

		return 1 + this.atividadeSubsequente.contaProximos();

	}

	/**
	 * Esse método retira a atividade subsequente
	 */
	public void tiraProximaAtividade() {

		this.atividadeSubsequente = null;

	}

	/**
	 * Esse método retorna a enesima atividade subsequente
	 * 
	 * @param enesimaAtividade, enesima atividade
	 * @return atividade que ocupa a enesima posicao
	 */
	public Atividade getProximo(int enesimaAtividade) {

		if (this.atividadeSubsequente == null) {
			throw new IllegalArgumentException("Atividade inexistente.");
		}

		if (enesimaAtividade == 1) {
			return this.atividadeSubsequente;
		}

		return this.atividadeSubsequente.getProximo(enesimaAtividade - 1);
	}

	/**
	 * Esse método percorre todas as atividades subsequentes e retorna a atividade
	 * de maior risco
	 * 
	 * @return atividade de maior risco entre as subsequentes
	 */
	public Atividade getMaiorRisco() {

		Atividade atvMaiorRisco = this.atividadeSubsequente;
		Atividade atvSub = this.atividadeSubsequente;

		while (atvSub != null) {

			if (atvMaiorRisco.getNivelRisco().equals(atvSub.getNivelRisco())) {
				atvMaiorRisco = atvSub;
			}

			else if (atvSub.getNivelRisco().equals("ALTO")) {
				atvMaiorRisco = atvSub;
			}

			else if (atvMaiorRisco.getNivelRisco().equals("BAIXO") && atvSub.getNivelRisco().equals("MEDIO")) {
				atvMaiorRisco = atvSub;
			}

			atvSub = atvSub.atividadeSubsequente;

		}

		return atvMaiorRisco;

	}

	/**
	 * retorna uma String com a descricao da atividade e os itens com seus status
	 * 
	 * @return retorna uma String com a descricao da atividade e os itens com seus
	 *         status
	 */
	public String retornaAtividadeItens() {
		String string = String.format("        - %s (%s - %s)", descricao, nivelRisco, descricaoRisco) + "\n";
		for (int i = 0; i < this.listaDeItens.size(); i++) {
			string += "            - " + listaDeItens.get(i).getStatus() + " - ITEM" + (i + 1) + "\n";
		}

		return string;
	}

	/**
	 * Retorna uma String dos itens cadastrados e sua duração. Caso a duração seja =
	 * 0, o Item não é retornado
	 * 
	 * @return Retorna uma String dos itens cadastrados e sua duração.
	 */
	public String retornaItensResultados() {
		String string = "";
		for (int i = 0; i < this.listaDeItens.size(); i++) {
			if (listaDeItens.get(i).getDuracao() != 0) {
				string += "            - ITEM" + (i + 1) + " - " + listaDeItens.get(i).getDuracao() + "\n";
			}
		}
		for (Resultado resultado : this.listResultados) {
			string += "            - " + resultado.toString() + "\n";
		}
		return string;
	}

	public boolean temSubsequente() {
		return this.atividadeSubsequente != null;
	}
}
