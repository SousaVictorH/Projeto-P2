package atividade;

import java.io.Serializable;

import utilidades.Utilidade;

/**
 * Classe Item, todo item possui nome e status, o último começando sempre como
 * pendente.
 * 
 * @author Victor Hugo Sousa - 119110395
 *
 */
public class Item implements Serializable {
	/**
	 * Constante de controle de versão da classe Serializable
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * nome do item, no formato de tipo String é guardado o nome do item.
	 */
	private String nomeItem;

	/**
	 * status, no formato de tipo String é guardado o status do item.
	 */
	private String status;

	/**
	 * Duracao em horas que o item teve
	 */
	private int duracao;

	/**
	 * Construtor da classe item.
	 * 
	 * @param item, nome do item.
	 */
	public Item(String item) {

		Utilidade.validaString(item, "Item nao pode ser nulo ou vazio.");

		this.nomeItem = item;
		this.status = "PENDENTE";
		this.duracao = 0;

	}

	/**
	 * Esse método retorna o status do item.
	 * 
	 * @return status do item.
	 */
	public String getStatus() {

		return this.status;

	}

	/**
	 * Esse método retorna a representação em string do item
	 * 
	 * @return representação em string do item
	 */
	@Override
	public String toString() {

		return String.format("%s - %s", status, nomeItem);

	}

	/**
	 * 
	 * @param duracao
	 */
	public void executa(int duracao) {
		this.status = "REALIZADO";
		this.duracao = duracao;
	}

	/**
	 * Retorna a duracao do item
	 * @return
	 */
	public int getDuracao() {
		return this.duracao;
	}
	/**
	 * Retorna detalhes dos itens
	 * @return Retorna uma string contendo o nome e a duracao do item
	 */
	public String retornaItensDetalhes() {
		String string = this.nomeItem + "-" + this.duracao;
		return string;
	}
}
