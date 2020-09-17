package busca;

import java.util.ArrayList;
import java.util.Collections;

import atividade.IDatividade;
import repositorios.RepositorioAtividades;
import repositorios.RepositorioObjetivos;
import repositorios.RepositorioPesquisadores;
import repositorios.RepositorioPesquisas;
import repositorios.RepositorioProblemas;
import utilidades.Ordenadora;

/**
 * Classe responsável por varrer os repositorios de todas as classes em busca de
 * termos especificados pelo usuario
 * 
 * @author Felipe de Souza Siqueira - 119110399
 */

public class Busca {
	/**
	 * Método que percorre os controllers em busca de uma palavra
	 * 
	 * @param termo Palavra que está sendo buscada
	 * @return Retorna uma String contendo os resultados da busca
	 */
	public String buscaTermo(String termo, RepositorioAtividades repositorioAtividades,
			RepositorioObjetivos repositorioObjetivos, RepositorioPesquisadores repositorioPesquisador,
			RepositorioPesquisas pesquisas, RepositorioProblemas repositorioProblema) {
		utilidades.Utilidade.validaString(termo, "Campo termo nao pode ser nulo ou vazio.");

		String string = "";

		string = utilidades.Concatenadora.concatena(string, "|", this.busca(termo, repositorioAtividades,
				repositorioObjetivos, repositorioPesquisador, pesquisas, repositorioProblema));

		return string;

	}

	/**
	 * Contabiliza o total de resultados que contém a palavra buscada
	 * 
	 * @param termo Palavra buscada
	 * @return Retorna um inteiro representando o total de frases com a palavra
	 *         buscada
	 */
	public int contaResultadosBusca(String termo, RepositorioAtividades repositorioAtividades,
			RepositorioObjetivos repositorioObjetivos, RepositorioPesquisadores repositorioPesquisador,
			RepositorioPesquisas pesquisas, RepositorioProblemas repositorioProblema) {

		utilidades.Utilidade.validaString(termo, "Campo termo nao pode ser nulo ou vazio.");

		ArrayList<String> resultados = this.busca(termo, repositorioAtividades, repositorioObjetivos,
				repositorioPesquisador, pesquisas, repositorioProblema);

		if (resultados.size() == 0) {
			throw new IllegalArgumentException("Nenhum resultado encontrado");
		}

		return resultados.size();
	}

	/**
	 * Busca um determinado termo em uma posição especifica do total de resultados
	 * 
	 * @param termo   Termo buscado
	 * @param posicao Posicao da String que será retornada
	 * @return Retorna a frase contida na posição informada
	 */
	public String buscaTermoPosicao(String termo, int posicao, RepositorioAtividades repositorioAtividades,
			RepositorioObjetivos repositorioObjetivos, RepositorioPesquisadores repositorioPesquisador,
			RepositorioPesquisas pesquisas, RepositorioProblemas repositorioProblema) {

		utilidades.Utilidade.validaString(termo, "Campo termo nao pode ser nulo ou vazio.");

		if (posicao < 0) {
			throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
		}

		ArrayList<String> resultados = this.busca(termo, repositorioAtividades, repositorioObjetivos,
				repositorioPesquisador, pesquisas, repositorioProblema);

		if (posicao > resultados.size()) {
			throw new IllegalArgumentException("Entidade nao encontrada.");
		}

		return resultados.get(posicao - 1);
	}

	/**
	 * Método responsável por varrer todos os repositorios em busca do termo
	 * especificado pelo usuario
	 * 
	 * @param termo                 Termo buscado pello usuario
	 * @param repositorioAtividades Repositorio de Atividades
	 * @param RO                    Repositorio de Objetivos
	 * @param RepoPesquisadores     repositorio de Pesquisadores
	 * @param repositorioPesquisas  Repositorio de Pesquisas
	 * @param repositorioProblemas  Repositorio de Problemas
	 * @return Retorna um ArrayList de Strings contendo todas as representações
	 *         textuais dos campos que contem o termo procurado
	 */
	private ArrayList<String> busca(String termo, RepositorioAtividades repositorioAtividades, RepositorioObjetivos RO,
			RepositorioPesquisadores RepoPesquisadores, RepositorioPesquisas repositorioPesquisas,
			RepositorioProblemas repositorioProblemas) {

		Ordenadora ordenadora = new Ordenadora();
		ArrayList<String> retorno = new ArrayList<>();

		// Busca no repositorio de Pesquisas

		ArrayList<String> chavesPesquisa = new ArrayList<>();

		for (String chave : repositorioPesquisas.getMapPesquisas().keySet()) {
			if (repositorioPesquisas.getPesquisa(chave).getDescricao().toLowerCase().contains(termo)
					|| repositorioPesquisas.getPesquisa(chave).getCamposDeInteresse().toLowerCase().contains(termo)) {
				chavesPesquisa.add(chave);
			}
		}

		Collections.sort(chavesPesquisa, ordenadora);

		for (String id : chavesPesquisa) {
			if (repositorioPesquisas.getPesquisa(id).getDescricao().toLowerCase().contains(termo)) {
				retorno.add(id + ": " + repositorioPesquisas.getPesquisa(id).getDescricao());
			}

			if (repositorioPesquisas.getPesquisa(id).getCamposDeInteresse().toLowerCase().contains(termo)) {
				retorno.add(id + ": " + repositorioPesquisas.getPesquisa(id).getCamposDeInteresse());
			}
		}

		// Busca no repositorio de Pesquisadores

		ArrayList<String> chavesPesquisadores = new ArrayList<>();

		for (String chave : RepoPesquisadores.getKeySet()) {
			if (RepoPesquisadores.getPesquisador(chave).getBiografia().toLowerCase().contains(termo)) {
				chavesPesquisadores.add(chave);
			}

		}

		Collections.sort(chavesPesquisadores, ordenadora);
		for (String id : chavesPesquisadores) {
			retorno.add(id + ": " + RepoPesquisadores.getPesquisador(id).getBiografia());
		}

		// Busca no repositorio de Problemas

		ArrayList<String> chavesProblemas = new ArrayList<>();

		for (String chave : repositorioProblemas.getMapProblemas().keySet()) {
			if (repositorioProblemas.getProblema(chave).getDescricao().toLowerCase().contains(termo)) {
				chavesProblemas.add(chave);
			}

		}

		Collections.sort(chavesProblemas, ordenadora);
		for (String id : chavesProblemas) {
			retorno.add(id + ": " + repositorioProblemas.getProblema(id).getDescricao());
		}

		// Busca no repositorio de Objetivos

		ArrayList<String> chavesObjetivos = new ArrayList<>();

		for (String chave : RO.getMapObjetivos().keySet()) {
			if (RO.getObjetivo(chave).getDescricao().toLowerCase().contains(termo)) {
				chavesObjetivos.add(chave);
			}

		}

		Collections.sort(chavesObjetivos, ordenadora);
		for (String id : chavesObjetivos) {
			retorno.add(id + ": " + RO.getObjetivo(id).getDescricao());
		}

		// Buscas no repositorio de Atividades

		ArrayList<IDatividade> chavesAtividades = new ArrayList<>();

		// Busca no repositorio de Atividades
		for (IDatividade chave : repositorioAtividades.getMapAtividades().keySet()) {
			if (repositorioAtividades.getMapAtividades().get(chave).getDescricao().toLowerCase().contains(termo)) {
				chavesAtividades.add(chave);
			}
			if (repositorioAtividades.getMapAtividades().get(chave).getDescricaoRisco().toLowerCase().contains(termo)) {
				chavesAtividades.add(chave);
			}
		}
		Collections.sort(chavesAtividades, ordenadora);
		for (IDatividade id : chavesAtividades) {
			retorno.add(id.getID() + ": " + repositorioAtividades.getMapAtividades().get(id).getDescricao());
		}

		return retorno;

	}
}
