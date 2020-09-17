package pesquisadores;

import java.io.Serializable;
import java.util.Locale;
/**
 * Essa classe, estudante, implementa a classe tipo, possui semestre e IEA
 * 
 * @author Victor Hugo Sousa - 119110395
 *
 */
public class Estudante implements Tipo,Serializable{
	/**
	 * Codigo de serializacao da classe.
	 */
	private static final long serialVersionUID = 5583014858902745737L;
	/**
	 * semestre do aluno
	 */
	private int semestre;
	/**
	 * IEA do aluno
	 */
	private double IEA;
	
	/**
	 * O construtor inicia a classe com base no nome, funcao, biografia, email, foto, semestre e IEA passados como parametro
	 * 
	 * @param semestre, semestre cursado pelo aluno
	 * @param IEA, IEA do aluno
	 */
	public Estudante(int semestre, double IEA) {
		this.semestre = semestre;
		this.IEA = IEA;
	}
	/**
	 * Esse método retorna o semestre cursado pelo aluno
	 * 
	 * @return semestre cursado pelo aluno
	 */
	public int getSemestre() {
		return this.semestre;
	}
	/**
	 * Esse método retorna a IEA do aluno
	 * 
	 * @return IEA do aluno
	 */
	public double getIEA() {
		return this.IEA;
	}
	/**
	 * Esse método altera o semestre cursado pelo aluno
	 * 
	 * @param novoSemestre novo semestre do pesquisador
	 */
	public void setSemestre(int novoSemestre) {
		this.semestre = novoSemestre;
	}
	/**
	 * Esse método altera a IEA do aluno
	 * 
	 * @param novaIEA novaIEA do aluno
	 */
	public void setIEA(double novaIEA) {
		this.IEA = novaIEA;
	}
	/**
	 * Esse método retorna a representação em string do tipo
	 * 
	 * @return representação em string do tipo
	 */
	public String toString() {
		
		return String.format(Locale.UK, " - %so SEMESTRE - %.1f", Integer.toString(semestre), IEA);
		
	}

}
