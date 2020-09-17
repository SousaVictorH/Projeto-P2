package repositorios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import problema.Problema;

/**
 * @author Placido Henrique/
 * 
 *         Classe de armazenamento dos Objetivos.
 *
 */
public class RepositorioProblemas {
	/**
	 * Mapa de armazenamento dos problemas, em que as chaves sao as geradas pelo
	 * respectivo controller.
	 */
	private HashMap<String, Problema> problemas;

	/**
	 * Construtor da classe, que inicializa o mapa de problemas.
	 */
	public RepositorioProblemas() {
		problemas = new HashMap<String, Problema>();
	}

	/**
	 * Adiciona um problema no mapa da classe.
	 * 
	 * @param chave    Chave do problema no mapa.
	 * @param objetivo Problema que sera adicionado no mapa.
	 */
	public void adicionaProblema(String codigo, Problema problema) {
		problemas.put(codigo, problema);

	}

	/**
	 * Remove um problema do mapa de problemas.
	 * 
	 * @param chave    Chave do problema no mapa.
	 * @param objetivo Problema que sera removido do mapa.
	 */
	public void removeProblema(String codigo) {
		this.problemas.remove(codigo);
	}

	/**
	 * Verifica a existencia de um problema no mapa. Caso exista, eh retornado o
	 * valor true, do contrario, eh retornado o valor false.
	 * 
	 * @param codigo Codigo do problema.
	 * 
	 * @return Valor booleano conforme a existencia do problema.
	 */
	public boolean existeProblema(String codigo) {
		if (problemas.containsKey(codigo)) {
			return true;
		}
		return false;
	}

	/**
	 * Acessa, atraves do seu codigo, um problema no mapa.
	 * 
	 * @param codigo Codigo do problema a ser acessado.
	 * 
	 * @return O problema ao qual o codigo pertence.
	 */

	public Problema getProblema(String codigo) {
		if (!existeProblema(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}
		return problemas.get(codigo);
	}

	public HashMap<String, Problema> getMapProblemas() {
		return problemas;
	}

	// ==============================| PARTE 12 |==============================
	// @author Placido Henrique.

	/**
	 * Metodo de salvamento dos problemas.
	 */
	public void salva() {

		try {
			File problemas = new File("./Saves/Problemas/problemas.txt");
			FileOutputStream fos = new FileOutputStream(problemas);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.problemas);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carrega os problemas salvos anteriormente.
	 */
	public void carrega() {
		try {
			FileInputStream fis = new FileInputStream("./Saves/Problemas/problemas.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.problemas = (HashMap<String, Problema>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
