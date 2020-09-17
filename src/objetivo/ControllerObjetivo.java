package objetivo;

import repositorios.RepositorioObjetivos;
import utilidades.Utilidade;

/**
 * @author Placido Henrique - 119110389
 * 
 *         Controller da classe Objetivo. Este gera o codigo dos objetivos e os
 *         cria, alem de gerenciar-los.
 * 
 * 
 */
public class ControllerObjetivo {

	private int contador;

	/**
	 * Construtor da classe. Inicializa o HashMap de objetivos e o contador, o
	 * ultimo com valor 0.
	 */
	public ControllerObjetivo() {

		this.contador = 0;

	}

	private String geraCodigo() {
		this.contador++;
		String codigo = "O" + Integer.toString(this.contador);
		return codigo;
	}

	/**
	 * Verifica se o Integer passado vale de 1 a 5. Caso contrario lanca uma excecao
	 * de argumento.
	 * 
	 * @param i   Inteiro que sera verificado.
	 * @param msg Mensagem da excecao.
	 * @throws IllegalArgumentException
	 */
	private void checkInteiro(Integer i, String msg) throws IllegalArgumentException {
		if (i <= 0 || i > 5) {
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * Cria e insere um novo objetivo no mapa de objetivos.
	 * 
	 * @param tipo        Tipo do objetivo. Pode ser "GERAL" ou "ESPECIFICO".
	 * @param descricao   Descricao livre do problema em uma String.
	 * @param aderencia   Valor inteiro que mede a aderencia ao objetivo.
	 * @param viabilidade Valor inteiro que mede a viabilidade do objetivo.
	 */
	public void cadastraObjetivo(String tipo, String descricao, Integer aderencia, Integer viabilidade,
			RepositorioObjetivos repositorioObjetivos) {
		//Excecoes
		Utilidade.validaString(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		Utilidade.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		this.checkInteiro(aderencia, "Valor invalido de aderencia");
		this.checkInteiro(viabilidade, "Valor invalido de viabilidade.");
		
		tipo = tipo.toUpperCase();
		if (!tipo.contentEquals("GERAL") && !tipo.contentEquals("ESPECIFICO")) {
			throw new IllegalArgumentException("Valor invalido de tipo.");
		}
		
		String codigo = this.geraCodigo();
		repositorioObjetivos.adicionaObjetivo(codigo, new Objetivo(codigo, tipo, descricao, aderencia, viabilidade));

		
	}

	/**
	 * Remove um objetivo do sistema.
	 * 
	 * @param codigo Codigo do objetivo que se deseja apagar.
	 */
	public void apagarObjetivo(String codigo, RepositorioObjetivos repositorioObjetivos) {
		Utilidade.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (!repositorioObjetivos.existeObjetivo(codigo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}

		repositorioObjetivos.removeObjetivo(codigo);
	}

	/**
	 * Exibe o objetivo que corresponde ao codigo passado.
	 * 
	 * @param codigo Codigo do objetivo a ser exibido.
	 * 
	 * @return A respresentacao textual de um objetivo em String.
	 */
	public String exibeObjetivo(String codigo, RepositorioObjetivos repositorioObjetivos) {
		Utilidade.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (!repositorioObjetivos.existeObjetivo(codigo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}

		return repositorioObjetivos.getObjetivo(codigo).toString();
	}

	// ==============================| PARTE 5 |==============================
	// @author Placido Henrique.

	/**
	 * Retorna o ID da pesquisa relacionada ao objetivo. Caso nao haja pesquisa
	 * relacionda, o valor passado e uma string vazia.
	 * 
	 * @param idObjetivo ID do objetivo o qual se deseja saber a pesquisa associada.
	 * 
	 * @return ID da pesquisa associada.
	 */
	public String getIdPesquisa(String idObjetivo, RepositorioObjetivos repositorioObjetivos) {
		if (!repositorioObjetivos.existeObjetivo(idObjetivo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}

		return repositorioObjetivos.getObjetivo(idObjetivo).getIdPesquisa();
	}

}
