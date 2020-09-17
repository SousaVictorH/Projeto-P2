package repositorios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import objetivo.Objetivo;

/**
 * @author Placido Henrique/
 * 
 *         Classe de armazenamento dos Objetivos.
 *
 */
public class RepositorioObjetivos {
	/**
	 * Mapa de armazenamento dos objetivos, em que as chaves sao as geradas pelo
	 * controllerObjetivo.
	 */
	private HashMap<String, Objetivo> objetivos;

	/**
	 * Construtor da classe, que inicializa o mapa de objetivos.
	 */
	public RepositorioObjetivos() {
		objetivos = new HashMap<String, Objetivo>();
	}

	/**
	 * Adiciona um objetivo no mapa de objetivos.
	 * 
	 * @param chave    Chave do objetivo no mapa.
	 * @param objetivo Objetivo que sera adicionado no mapa.
	 */
	public void adicionaObjetivo(String chave, Objetivo objetivo) {
		objetivos.put(chave, objetivo);
	}

	/**
	 * Remove um objetivo do mapa de objetivos.
	 * 
	 * @param chave    Chave do objetivo no mapa.
	 * @param objetivo Objetivo que sera removido do mapa.
	 */
	public void removeObjetivo(String codigo) {
		objetivos.remove(codigo);
	}

	/**
	 * Verifica a existencia de um objetivo no mapa. Caso o objetivo exista, eh
	 * retornado o valor true, do contrario, eh retornado o valor false.
	 * 
	 * @param codigo Codigo do objetivo.
	 * 
	 * @return Valor booleano conforme a existencia do objetivo.
	 */
	public boolean existeObjetivo(String codigo) {
		if (this.objetivos.containsKey(codigo)) {
			return true;
		}
		return false;
	}

	/**
	 * Acessa, atraves do seu codigo, um objetivo do mapa.
	 * 
	 * @param codigo Codigo do objetivo que se deseja.
	 * 
	 * @return O objetivo ao qual o codigo pertence.
	 */
	public Objetivo getObjetivo(String codigo) {
		return objetivos.get(codigo);
	}

	/**
	 * Metodo de acesso ao mapa da classe.
	 * 
	 * @return O mapa de objetivos.
	 */
	public HashMap<String, Objetivo> getMapObjetivos() {
		return objetivos;
	}

	// ==============================| PARTE 12 |==============================
	// @author Placido Henrique.

	/**
	 * Salva todos os objetivos presentes no sistema.
	 */
	public void salva() {

		try {
			File objetivos = new File("./Saves/Objetivos/objetivos.txt");
			FileOutputStream fos = new FileOutputStream(objetivos);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.objetivos);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carrega os objetivos salvos anteriormente no sistema.
	 */
	public void carrega() {
		try {
			FileInputStream fis = new FileInputStream("./Saves/Objetivos/objetivos.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.objetivos = (HashMap<String, Objetivo>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
