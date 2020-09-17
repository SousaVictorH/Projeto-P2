package objetivo;

import java.io.Serializable;

/**
 * @author Placido Henrique - 119110389
 * 
 *         Representacao de um objetivo no psquiza. Detem um codigo de
 *         identificacao e descricao unicos, dois valores inteiros,ambos de 1
 *         ate 5, que medem sua viabilidade e aderencia respectivamente, e um
 *         tipo, que pode ser especifico ou geral. Alem do mais, pode ser
 *         associado a uma pesquisa.
 */
public class Objetivo implements Serializable {
	/**
	 * Codigo de serializacao da classe.
	 */
	private static final long serialVersionUID = -1156557154009378093L;
	/**
	 * Codigo identificador do objetivo, gerado a partir do padrao: "O" + |inteiro
	 * gerado automaticamente|.
	 */
	protected String codigo;
	/**
	 * Tipo do objetivo em questao, pode ser |geral| ou |especifico|.
	 */
	protected String tipo;
	/**
	 * Descricao livre do problema em uma String.
	 */
	protected String descricao;
	/**
	 * Valor inteiro que mede a viabilidade do objetivo.
	 */
	protected int viabilidade;
	/**
	 * Valor inteiro que mede a aderencia ao objetivo.
	 */
	protected int aderencia;
	/**
	 * Define se o objetivo esta associado ou nao a uma pesquisa, com o valor true
	 * se estiver associado, e false se nao estiver.
	 */
	protected String idPesquisa;

	/**
	 * Construtor da classe objetivo. Recebe valores para o codigo, descricao,
	 * viabilidade e aderencia. Por outro lado, o tipo não e definido, pois este
	 * recebera um valor automaticamente nas classes filhas.
	 * 
	 * @param codigo       Codigo de identificação.
	 * @param descriçao    Descricao em String do objetivo.
	 * @param vialbilidade Valor inteiro que mede a viabilidade do objetivo.
	 * @param aderencia    Valor inteiro que mede a aderencia do objetivo.
	 */
	public Objetivo(String codigo, String tipo, String descricao, int vialbilidade, int aderencia) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.descricao = descricao;
		this.viabilidade = vialbilidade;
		this.aderencia = aderencia;
		this.idPesquisa = "";
	}

	/**
	 * Metodo que retorna a representacao textual de um problema.
	 * 
	 * @return Uma String no formato: |CODIGO| - |TIPO| - |DESCRICAO| - |VALOR|" em
	 *         que a aderencia e a soma da viabilidade com a aderencia.
	 */
	@Override
	public String toString() {
		int valor = this.aderencia + this.viabilidade;
		return this.codigo + " - " + this.tipo + " - " + this.descricao + " - " + valor;
	}

	// ==============================| PARTE 5 |==============================
	// @author Placido Henrique.

	/**
	 * Atribui um valor para o ID da pesquisa associada ao objetivo.
	 * 
	 * @param idPesquisa ID da pesquisa associada ao problema.
	 * 
	 * @return O resultado da associacao em boolean.
	 */
	public boolean setIdPesquisa(String idPesquisa) {
		if (!this.idPesquisa.contentEquals("")) {
			if (!this.idPesquisa.contentEquals(idPesquisa)) {
				throw new IllegalArgumentException("Objetivo ja associado a uma pesquisa.");
			} else {
				return false;
			}
		}
		this.idPesquisa = idPesquisa;
		return true;
	}

	/**
	 * Desassocia o objetivo a pesquisa atual.
	 */
	public boolean dessasociaPesquisa() {
		this.idPesquisa = "";
		return true;
	}

	/**
	 * Acessa o ID da pesquisa do objetivo. Caso nao haja nenhuma, o valor retornado
	 * eh uma string vazia.
	 * 
	 * @return O ID da pesquisa associada ao objetivo.
	 */
	public String getIdPesquisa() {
		return this.idPesquisa;
	}

	/**
	 * Metodo de acesso a descricao.
	 * 
	 * @return A descricao do objetivo.
	 */
	public String getDescricao() {
		return this.descricao;
	}

}
