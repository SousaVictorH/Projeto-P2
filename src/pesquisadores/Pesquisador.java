package pesquisadores;

import java.io.Serializable;

/**
 * Classe que representa um pesquisador, que tem um nome, funcao, biografia,
 * email, foto e um statusDeAtividade de atividade
 * 
 * @author Davi Andrade Pontes
 *
 */
public class Pesquisador implements Serializable {
	
	/**
	 * Codigo de serializacao da classe.
	 */
	private static final long serialVersionUID = 4994377236255612124L;
	protected String nome;
	protected String funcao;
	protected String biografia;
	protected String email;
	protected String foto;
	private boolean statusDeAtividade;
	private Tipo tipo;
	
	/**
	 * O contrutor inicia a classe com base no seu nome, funcao, biografia, email e foto
	 * 
	 * @param nome, nome do pesquisador
	 * @param funcao, funcao do pesquisador
	 * @param biografia, biografia do pesqquisador
	 * @param email, email do pesquisador
	 * @param foto, foto do pesquisador
	 */
	public Pesquisador(String nome, String funcao, String biografia, String email, String foto) {
		
		this.nome = nome;
		this.funcao = funcao;
		this.biografia = biografia;
		this.email = email;
		this.foto = foto;
		this.statusDeAtividade = true;
		
		if(funcao.equals("externo")) {
			this.tipo = new Externo();
		}else {
			this.tipo = null;
		}
		
	}
	/**
	 * Esse método retorna o nome do pesquisador
	 * 
	 * @return nome do pesquisador
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Esse método redefine o nome do pesquisador
	 * 
	 * @param nome novo nome do pesquisador
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * Esse método retorna a funcao do pesquisador
	 * 
	 * @return funcao do pesquisador
	 */
	public String getFuncao() {
		return funcao;
	}
	/**
	 * Esse método redefine a funcao do pesquisador
	 * 
	 * @param funcao funcao do pesquisador
	 */
	public void setFuncao(String funcao) {
		
		this.funcao = funcao;
		
		if(funcao.equals("externo")) {
			this.tipo = new Externo();
		}else {
			this.tipo = null;
		}
		
	}
	/**
	 * Esse método retorna a biografia do pesquisador
	 * 
	 * @return biografia do pesquisador
	 */
	public String getBiografia() {
		return biografia;
	}
	/**
	 * Esse método redefine a biografia do pesquisador
	 * 
	 * @param biografia biografia do pesquisaddor
	 */
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	/**
	 * Esse método retorna o email do pesquisador
	 * 
	 * @return email do pesquisador
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Esse método redefine o email do pesquisador
	 * 
	 * @param email novo email do pesquisador
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Esse método retorna a url da foto do pesquisador
	 * 
	 * @return
	 */
	public String getFoto() {
		return foto;
	}
	/**
	 * Esse método redefine a url da foto do pesquisador
	 * 
	 * @param foto
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}
	/**
	 * Esse método troca o status de atividade do pesquisador
	 */
	public void swapStatusDeAtividade() {
		if (statusDeAtividade) {
			statusDeAtividade = false;
		} else {
			statusDeAtividade = true;
		}	
	}
	/**
	 * Esse método retorna o status de atividade do pesquisador
	 * 
	 * @return status de atividade do pesquisador
	 */
	public boolean getStatusDeAtividade() {
		return this.statusDeAtividade;
	}
	/**
	 * Esse método retorna a representaçao em string do pesquisador
	 * 
	 * @return representaçao em string do pesquisador
	 */
	@Override
	public String toString() {
		
		String string = String.format("%s (%s) - %s - %s - %s", this.nome, this.funcao, this.biografia, this.email, this.foto);
		
		if(tipo != null) {
			string += tipo.toString();
		}
		
		return string;
		
	}
	/**
	 * Esse método cadastra o tipo aluno no pesquisador
	 * 
	 * @param semestre, semestre do aluno
	 * @param IEA, IEA do aluno
	 */
	public void setEstudante(int semestre, double IEA) {
		
		this.tipo = new Estudante(semestre, IEA);
		
	}
	/**
	 * Esse método cadastra o tipo professor no pesquisador
	 * 
	 * @param formacao, formacao do pesquisador
	 * @param unidade, unidade do pesquisador
	 * @param data, data de entrada do pesquisador
	 */
	public void setProfessor(String formacao, String unidade, String data) {
		
		this.tipo = new Professor(formacao, unidade, data);
		
	}
	/**
	 * Ese método retorna o tipo do pesquisador
	 * 
	 * @return tipo do pesquisador
	 */
	public Tipo getTipo() {
		
		return this.tipo;
		
	}
	/**
	 * Esse método uma representação inteira do pesquisador
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}
	/**
	 * Esse método compara um objeto ao pesquisador
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pesquisador other = (Pesquisador) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	/**
	 * Esse método retorna o semestre cursado pelo aluno
	 * 
	 * @return semestre cursado pelo aluno
	 */
	public int getSemestre() {
		int inteiro = 0;
		if(this.tipo instanceof Estudante) {
			inteiro = ((Estudante) tipo).getSemestre();
			return inteiro;
		}
		throw new IllegalArgumentException("Tipo nao compativel");
	}
	/**
	 * Esse método retorna a IEA do aluno
	 * 
	 * @return IEA do aluno
	 */
	public double getIEA() {
		double iea = 0;
		if(this.tipo instanceof Estudante) {
			iea = ((Estudante) tipo).getIEA();
			return iea;
		}
		throw new IllegalArgumentException("Tipo nao compativel");
	}
	/**
	 * Esse método altera o semestre cursado pelo aluno
	 * 
	 * @param novoSemestre novo semestre do pesquisador
	 */
	public void setSemestre(int novoSemestre) {
		if(this.tipo instanceof Estudante) {
			((Estudante) tipo).setSemestre(novoSemestre);
		}else {
			throw new IllegalArgumentException("Tipo nao compativel");
		}
	}
	/**
	 * Esse método altera a IEA do aluno
	 * 
	 * @param novaIEA novaIEA do aluno
	 */
	public void setIEA(double novaIEA) {
		if(this.tipo instanceof Estudante) {
			((Estudante) tipo).setIEA(novaIEA);
		}else {
			throw new IllegalArgumentException("Tipo nao compativel");
		}
	}
	/**
	 * Esse método retorna a formacao do professor
	 * @return formacao do professor
	 */
	public String getFormacao() {
		String formacao = "";
		if (this.tipo instanceof Professor) {
			formacao = ((Professor) tipo).getFormacao();
			return formacao;
		}
		throw new IllegalArgumentException("Tipo nao compativel");
	}
	/**
	 * Esse método retorna a unidade do professor
	 * @return unidade do professsor
	 */
	public String getUnidade() {
		String formacao = "";
		if (this.tipo instanceof Professor) {
			formacao = ((Professor) tipo).getUnidade();
			return formacao;
		}
		throw new IllegalArgumentException("Tipo nao compativel");
	}
	/**
	 * Esse método retorna a data do professor
	 * @return
	 */
	public String getData() {
		String formacao = "";
		if (this.tipo instanceof Professor) {
			formacao = ((Professor) tipo).getData();
			return formacao;
		}
		throw new IllegalArgumentException("Tipo nao compativel");
	}
	/**
	 * Esse método altera a formacao do professor
	 * @param formacao nova formacao do professor
	 */
	public void setFormacao(String formacao) {
		if(this.tipo instanceof Professor) {
			((Professor) tipo).setFormacao(formacao);
		}else {
			throw new IllegalArgumentException("Tipo nao compativel");
		}
	}
	/**
	 * Esse método altera a unidade do professor
	 * @param unidade nova unidade do professor
	 */
	public void setUnidade(String unidade) {
		if(this.tipo instanceof Professor) {
			((Professor) tipo).setUnidade(unidade);
		}else {
			throw new IllegalArgumentException("Tipo nao compativel");
		}
	}
	/**
	 * Esse método altera a data de formacao do professor
	 * @param data nova data de formacao do professor
	 */
	public void setData(String data) {
		if(this.tipo instanceof Professor) {
			((Professor) tipo).setData(data);
		}else {
			throw new IllegalArgumentException("Tipo nao compativel");
		}
	}

}
