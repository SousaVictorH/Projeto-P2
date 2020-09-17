package repositorios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import atividade.Atividade;
import atividade.IDatividade;
/**
 * @author Victor Hugo Sousa
 * 
 * Classe de armazenamento dos Atividades.
 *
 */
public class RepositorioAtividades {
	/**
	 * ids, array list que guarda todas as chaves de atividades
	 */
	private ArrayList<IDatividade> ids;
	/**
	 * atividades, no formato de hashmap são guardadas todas as atividades, tendo como chave seus ids
	 */
	private HashMap<IDatividade, Atividade> atividades;
	/**
	 * Contrutor da classe, somente inicia o array e o mapa de atividade
	 */
	public RepositorioAtividades() {
		ids = new ArrayList<IDatividade>();

		atividades = new HashMap<IDatividade, Atividade>();
	}
	/**
	 * Esse método adiciona uma atividade ao mapa, com base no id e na propria atividade
	 * 
	 * @param id, id da atividade
	 * @param atividade, atividade em si
	 */
	public void adicionaAtividade(IDatividade id, Atividade atividade) {

		ids.add(id);

		atividades.put(id, atividade);
	}
	/**
	 * Esse método remove uma atividade do mapa, dado o seu id
	 * @param id, id da atividade
	 */
	public void removeAtividade(IDatividade id) {

		atividades.remove(id);

	}
	/**
	 * Esse método retorna true caso exista uma atividade associada ao id passado como parametro e false caso contrário
	 * 
	 * @param id, id da atividade
	 * @return true caso exista uma atividade associada ao id passado como parametro e false caso contrário
	 */
	public boolean existeAtividade(IDatividade id) {

		if (this.atividades.containsKey(id)) {
			return true;
		}

		return false;
	}
	/**
	 * Esse método retorna a atividade associada ao id passado como parametro
	 * 
	 * @param id, id da atividade
	 * @return atividade associada ao id passado como parametro
	 */
	public Atividade getAtividade(IDatividade id) {

		return atividades.get(id);
	}
	/**
	 * Esse método retorna uma cópia da lista de ids
	 * 
	 * @return cópia da lista de ids
	 */
	public ArrayList<IDatividade> getListIds() {
		ArrayList<IDatividade> array = new ArrayList<>(ids);
		return array;
	}
	/**
	 * Esse método retorna uma cópia do mapa de atividades
	 * @return
	 */
	public HashMap<IDatividade, Atividade> getMapAtividades() {
		HashMap<IDatividade, Atividade> hash = new HashMap<>(atividades);
		return hash;
	}

	// ==============================| PARTE 12 |==============================
	// @author Placido Henrique.

	/**
	 * Metodo de salvamento das atividades presentes no sistema.
	 */
	public void salva() {

		try {
			File idAtividades = new File("./Saves/Atividades/idAtividades.txt");
			File atividades = new File("./Saves/Atividades/atividades.txt");

			FileOutputStream fosID = new FileOutputStream(idAtividades);
			FileOutputStream fosAtividades = new FileOutputStream(atividades);

			ObjectOutputStream oosID = new ObjectOutputStream(fosID);
			ObjectOutputStream oosAtividades = new ObjectOutputStream(fosAtividades);

			oosID.writeObject(this.ids);
			oosAtividades.writeObject(this.atividades);

			oosID.close();
			oosAtividades.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo de carregamento das atividades salvas no sistema.
	 */
	public void carrega() {
		try {
			FileInputStream fisID = new FileInputStream("./Saves/Atividades/idAtividades.txt");
			FileInputStream fisAtividades = new FileInputStream("./Saves/Atividades/atividades.txt");

			ObjectInputStream oisID = new ObjectInputStream(fisID);
			ObjectInputStream oisAtividades = new ObjectInputStream(fisAtividades);

			this.ids = (ArrayList<IDatividade>) oisID.readObject();
			this.atividades = (HashMap<IDatividade, Atividade>) oisAtividades.readObject();

			oisID.close();
			oisAtividades.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
