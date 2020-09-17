package repositorios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;

import pesquisadores.Pesquisador;

public class RepositorioPesquisadores {
	private HashMap<String, Pesquisador> pesquisadores;

	public RepositorioPesquisadores() {
		pesquisadores = new HashMap<String, Pesquisador>();
	}

	public void adiconaPesquisador(String novoValor, Pesquisador pesquisador) {

		pesquisadores.put(novoValor, pesquisador);

	}

	public void removePesquisador(String email) {
		pesquisadores.remove(email);
	}

	public boolean existePesquisador(String email) {

		if (pesquisadores.containsKey(email)) {
			return true;
		}

		return false;
	}

	public Pesquisador getPesquisador(String email) {

		return pesquisadores.get(email);
	}

	public Collection<Pesquisador> getLista() {
		return this.pesquisadores.values();
	}

	public Collection<String> getKeySet() {
		return this.pesquisadores.keySet();
	}

	// ==============================| PARTE 12 |==============================
	// @author Placido Henrique.

	/**
	 * Metodo para salvar os pesquisadores do sistema.
	 */
	public void salva() {
		try {
			File pesquisadores = new File("./Saves/Pesquisadores/pesquisadores.txt");
			FileOutputStream fos = new FileOutputStream(pesquisadores);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.pesquisadores);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carrega os pesquisadores salvos anteriormente no sistema.
	 */
	public void carrega() {
		try {
			FileInputStream fis = new FileInputStream("./Saves/Pesquisadores/pesquisadores.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.pesquisadores = (HashMap<String, Pesquisador>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
