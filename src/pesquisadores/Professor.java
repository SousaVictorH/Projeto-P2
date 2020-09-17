package pesquisadores;

import java.io.Serializable;

/**
 * Essa classe, professor, implementa a classe tipo, possui formacao, unidade e data
 * 
 * @author Victor Hugo Sousa - 119110395
 *
 */
public class Professor implements Tipo, Serializable{
	/**
	 * Codigo de serializacao da classe.
	 */
	private static final long serialVersionUID = -4431922745263521757L;
	/**
	 * formacao do professor
	 */
	private String formacao;
	/**
	 * unidade do professor
	 */
	private String unidade;
	/**
	 * data de ingresso do professor
	 */
	private String data;
	/**
	 * O contrutor inicia a classe com base no seu nome, funcao, biografia, email, foto, formacao, unidade e data
	 * 
	 * @param formacao, formacao do professor
	 * @param unidade, unidade do professor
	 * @param data, data de formacao do professor
	 */
	public Professor(String formacao, String unidade, String data) {
		this.formacao = formacao;
		this.unidade = unidade;
		this.data = data;
	}
	/**
	 * Esse método retorna a formacao do professor
	 * @return formacao do professor
	 */
	public String getFormacao() {
		return this.formacao;
	}
	/**
	 * Esse método retorna a unidade do professor
	 * @return unidade do professsor
	 */
	public String getUnidade() {
		return this.unidade;
	}
	/**
	 * Esse método retorna a data do professor
	 * @return
	 */
	public String getData() {
		return this.data;
	}
	/**
	 * Esse método altera a formacao do professor
	 * @param formacao nova formacao do professor
	 */
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	/**
	 * Esse método altera a unidade do professor
	 * @param unidade nova unidade do professor
	 */
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	/**
	 * Esse método altera a data de formacao do professor
	 * @param data nova data de formacao do professor
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * Esse método retorna a representação em string do tipo
	 * 
	 * @return representação em string do tipo
	 */
	public String toString() {
		
		return String.format(" - %s - %s - %s", formacao, unidade, data);
		
	}
	
}
