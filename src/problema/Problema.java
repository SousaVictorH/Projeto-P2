package problema;

import java.io.Serializable;

/**
 * @author Placido Henrique - 119110389
 * 
 *         Representaçao de um problema no Psquiza. Este possui um codigo unico
 *         que o identifica dentro do sistema, uma descriçao e um valor de
 *         viabilidade proprios. Ademais, pode ser associado a varias pesquisas.
 */
public class Problema implements Serializable {
	/**
	 * Codigo de serializacao da classe.
	 */
	private static final long serialVersionUID = 6366211740348828669L;
	/**
	 * Codigo do problema.
	 */
	private String codigo;
	/**
	 * Descricao livre do problema.
	 */
	private String descricao;
	/**
	 * Valor inteiro de 1 a 5 que define a viabilidade do problema.
	 */
	private int viabilidade;

	/**
	 * Construtor da classe. Atribui as entradas do usuario ao Problema criado.
	 * 
	 * @param código      Codigo em string do problema a ser criado.
	 * @param descrição   Descricao livre, em String, do problema.
	 * @param viabilidade Viabilidade do problema em um valor inteiro de 1 a 5.
	 */
	public Problema(String codigo, String descricao, int viabilidade) {
		// checa nulo ou vazio.
		this.codigo = codigo;
		this.descricao = descricao;
		this.viabilidade = viabilidade;
	}

	/**
	 * Metodo de exibicao do problema. Retorna uma String contendo as principais
	 * informacoes do proprio.
	 * 
	 * @return Uma representação textual do Problema no formato: " |CODIGO| -
	 *         |DESCRICAO| - |VIABILIDADE|"
	 */
	@Override
	public String toString() {
		return this.codigo + " - " + this.descricao + " - " + this.viabilidade;
	}

	/**
	 * Retorna a descricao do problema.
	 * 
	 * @return A descricao do problema.
	 */
	public String getDescricao() {
		return this.descricao;
	}
}
