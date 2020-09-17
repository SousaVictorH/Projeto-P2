package repositorios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import pesquisa.Pesquisa;

public class RepositorioPesquisas {

	private HashMap<String, Pesquisa> pesquisas;

	private HashMap<String, Integer> senhas;

	public RepositorioPesquisas() {
		pesquisas = new HashMap<String, Pesquisa>();
		senhas = new HashMap<String, Integer>();
	}

	public void adicionaPesquisa(String codigo, Pesquisa pesquisa) {

		pesquisas.put(codigo, pesquisa);

	}

	public boolean existePesquisa(String codigoPesquisa) {
		if (this.pesquisas.containsKey(codigoPesquisa)) {
			return true;
		}

		return false;
	}

	public Pesquisa getPesquisa(String codigo) {
		return this.pesquisas.get(codigo);
	}

	public HashMap<String, Pesquisa> getMapPesquisas() {
		return pesquisas;
	}

	public HashMap<String, Integer> getMapSenhas() {
		return senhas;
	}

	// ==============================| PARTE 12 |==============================
	// @author Placido Henrique.

	/**
	 * Metodo de salvamento das pesquisas.
	 */
	public void salva() {

		try {
			File pesquisas = new File("./Saves/Pesquisas/pesquisas.txt");
			File senhas = new File("./Saves/Pesquisas/senhas.txt");

			FileOutputStream fosPesquisas = new FileOutputStream(pesquisas);
			FileOutputStream fosSenhas = new FileOutputStream(senhas);

			ObjectOutputStream oosPesquisas = new ObjectOutputStream(fosPesquisas);
			ObjectOutputStream oosSenhas = new ObjectOutputStream(fosSenhas);

			oosPesquisas.writeObject(this.pesquisas);
			oosSenhas.writeObject(this.senhas);

			oosPesquisas.close();
			oosSenhas.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carrega as pesquisas salvas anteriormente.
	 */
	public void carrega() {
		try {
			FileInputStream fisPesquisas = new FileInputStream("./Saves/Pesquisas/pesquisas.txt");
			FileInputStream fisSenhas = new FileInputStream("./Saves/Pesquisas/senhas.txt");

			ObjectInputStream oisPesquisas = new ObjectInputStream(fisPesquisas);
			ObjectInputStream oisSenhas = new ObjectInputStream(fisSenhas);

			this.pesquisas = (HashMap<String, Pesquisa>) oisPesquisas.readObject();
			this.senhas = (HashMap<String, Integer>) oisSenhas.readObject();

			oisPesquisas.close();
			oisSenhas.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
