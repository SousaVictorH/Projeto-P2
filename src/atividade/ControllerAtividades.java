package atividade;

import repositorios.RepositorioAtividades;
import utilidades.Utilidade;

/**
 * Controller da classe atividade, guardando todas os objetos dessa classe.
 * 
 * @author Victor Hugo Sousa - 119110395
 *
 */
public class ControllerAtividades {
	
	private String geraChave(RepositorioAtividades repositorioAtividades) {
		return "A" + Integer.toString(repositorioAtividades.getListIds().size() + 1);
	}
	/**
	 * Esse método, sem retorno, cadastra uma atividade.
	 * 
	 * @param Descricao      descricao da atividade.
	 * @param nivelRisco     nivel de risco da atividade.
	 * @param descricaoRisco descricao do risco da atividade.
	 */
	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco, RepositorioAtividades repositorioAtividades) {

		Utilidade.validaString(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		Utilidade.validaString(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		Utilidade.validaString(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");

		if (!((nivelRisco.equals("BAIXO")) || (nivelRisco.equals("MEDIO")) || (nivelRisco.equals("ALTO")))) {
			throw new IllegalArgumentException("Valor invalido do nivel do risco.");
		}

		String codigo = geraChave(repositorioAtividades);

		IDatividade id = new IDatividade(codigo);

		repositorioAtividades.adicionaAtividade(id, new Atividade(descricao, nivelRisco, descricaoRisco, id));

		return codigo;
	}

	/**
	 * Esse método, sem retorno, apaga uma atividade.
	 * 
	 * @param codigo código de acesso à atividade.
	 */
	public void apagaAtividade(String codigo, RepositorioAtividades repositorioAtividades) {

		Utilidade.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		IDatividade id = new IDatividade(codigo);
		
		if (!repositorioAtividades.existeAtividade(id)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}

		repositorioAtividades.removeAtividade(id);
	}

	/**
	 * Esse método adiciona um item a uma atividade específica.
	 * 
	 * @param codigo código de acesso à atividade.
	 * @param item   item a ser adicionado à atividade.
	 */
	public void cadastraItem(String codigo, String item, RepositorioAtividades repositorioAtividades) {

		Utilidade.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		IDatividade id = new IDatividade(codigo);
		
		if (!repositorioAtividades.existeAtividade(id)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}

		repositorioAtividades.getAtividade(id).cadastraItem(item);
	}

	/**
	 * Esse método retorna a representação em string de uma determinada atividade.
	 * 
	 * @param codigo código de acesso à atividade.
	 * @return representação em string da atividade relativa ao código (chave)
	 *         passado como parametro.
	 */
	public String exibeAtividade(String codigo, RepositorioAtividades repositorioAtividades) {

		Utilidade.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		IDatividade id = new IDatividade(codigo);
		
		if (!repositorioAtividades.existeAtividade(id)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}

		return repositorioAtividades.getAtividade(id).toString();

	}

	/**
	 * Esse método retorna a quantidade total de itens pendentes de uma determinada
	 * atividade.
	 * 
	 * @param codigo código de acesso à atividade.
	 * @return quantidade total de itens pendentes relativa ao código (chave)
	 *         passado como parametro.
	 */
	public int contaItensPendentes(String codigo, RepositorioAtividades repositorioAtividades) {

		Utilidade.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		IDatividade id = new IDatividade(codigo);
		
		if (!repositorioAtividades.existeAtividade(id)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}

		return repositorioAtividades.getAtividade(id).getItensPendentes();
	}

	/**
	 * Esse método retorna a quantidade total de itens realizados de uma determinada
	 * atividade.
	 * 
	 * @param codigo código de acesso à atividade.
	 * @return quantidade total de itens realizados relativa ao código (chave)
	 *         passado como parametro.
	 */
	public int contaItensRealizados(String codigo, RepositorioAtividades repositorioAtividades) {

		Utilidade.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		IDatividade id = new IDatividade(codigo);
		
		if (!repositorioAtividades.existeAtividade(id)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}

		return repositorioAtividades.getAtividade(id).getItensRealizados();
	}
	/**
	 * 
	 * 
	 * @param codigoAtividade
	 * @param item
	 * @param duracao
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao, RepositorioAtividades repositorioAtividades) {
		utilidades.Utilidade.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		IDatividade id = new IDatividade(codigoAtividade);
		
		if (!repositorioAtividades.getAtividade(id).associadoComPesquisa()) {
			throw new IllegalArgumentException("Atividade sem associacoes com pesquisas.");
		}

		if (item < 1) {
			throw new IllegalArgumentException("Item nao pode ser nulo ou negativo.");
		}

		if (duracao < 1) {
			throw new IllegalArgumentException("Duracao nao pode ser nula ou negativa.");
		}

		if (!repositorioAtividades.existeAtividade(id)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}

		if (!repositorioAtividades.getAtividade(id).contemItem(item)) {
			throw new IllegalArgumentException("Item nao encontrado.");
		}

		repositorioAtividades.getAtividade(id).executaItem(item, duracao);
	}

	public int cadastraResultado(String codigoAtividade, String resultado, RepositorioAtividades repositorioAtividades) {
		utilidades.Utilidade.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		utilidades.Utilidade.validaString(resultado, "Resultado nao pode ser nulo ou vazio.");
		
		IDatividade id = new IDatividade(codigoAtividade);
		
		if (!repositorioAtividades.existeAtividade(id)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}

		return repositorioAtividades.getAtividade(id).cadastraResultado(resultado);
	}

	public boolean removeResultado(String codigoAtividade, int numeroResultado, RepositorioAtividades repositorioAtividades) {
		utilidades.Utilidade.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");

		IDatividade id = new IDatividade(codigoAtividade);
		
		if (!repositorioAtividades.existeAtividade(id)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}

		if (numeroResultado < 1) {
			throw new IllegalArgumentException("numeroResultado nao pode ser nulo ou negativo.");
		}

		return repositorioAtividades.getAtividade(id).removeResultado(numeroResultado);
	}

	public String listaResultados(String codigoAtividade, RepositorioAtividades repositorioAtividades) {
		utilidades.Utilidade.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");

		IDatividade id = new IDatividade(codigoAtividade);
		
		if (!repositorioAtividades.existeAtividade(id)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}

		return repositorioAtividades.getAtividade(id).listaResultados();
	}

	public int getDuracao(String codigoAtividade, RepositorioAtividades repositorioAtividades) {
		utilidades.Utilidade.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");

		IDatividade id = new IDatividade(codigoAtividade);
		
		if (!repositorioAtividades.existeAtividade(id)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}

		return repositorioAtividades.getAtividade(id).getDuracao();
	}

	public void associaPesquisa(String codigoPesquisa, String codigoAtividade, RepositorioAtividades repositorioAtividades) {
		utilidades.Utilidade.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");

		IDatividade id = new IDatividade(codigoAtividade);
		
		if (!repositorioAtividades.existeAtividade(id)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}

		repositorioAtividades.getAtividade(id).associaPesquisa(codigoPesquisa);
	}
	/**
	 * Esse método define a prooxima atividade a ser realizada
	 * 
	 * @param idPrecedente, id da atividade precedente
	 * @param idSubsequente, id da atividade subsequente
	 * @param repositorioAtividades, repositorio de atividades
	 */
	public void defineProximaAtividade(String idPrecedente, String idSubsequente,
			RepositorioAtividades repositorioAtividades) {
		
		Utilidade.validaString(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		Utilidade.validaString(idSubsequente, "Atividade nao pode ser nulo ou vazio.");
		
		IDatividade id1 = new IDatividade(idPrecedente);
		IDatividade id2 = new IDatividade(idSubsequente);
		
		if((!repositorioAtividades.existeAtividade(id1)) || (!repositorioAtividades.existeAtividade(id2))) {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}
		
		Atividade atividadeSubsequente = repositorioAtividades.getAtividade(id2);
		
		repositorioAtividades.getAtividade(id1).setSubsequente(atividadeSubsequente);		
		
	}
	/**
	 * Esse método conta as atividades subsequente à atividade passada como parametro
	 * 
	 * @param idAtividade, id da atividade
	 * @param repositorioAtividades, repositorio de atividades
	 * @return um inteiro que aponta a quantidade de atividades subsequente à atividade passada como parametro
	 */
	public int contaProximos(String idAtividade, RepositorioAtividades repositorioAtividades) {
		
		Utilidade.validaString(idAtividade, "Atividade nao pode ser nulo ou vazio.");
		
		IDatividade id = new IDatividade(idAtividade);
		
		if(!repositorioAtividades.existeAtividade(id)) {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}
		
		return repositorioAtividades.getAtividade(id).contaProximos();
	}
	/**
	 * Esse método retira a atividade subsequente
	 * 
	 * @param idPrecedente, id da atividade precedente
	 * @param repositorioAtividades, repositorio de atividades
	 */
	public void tiraProximaAtividade(String idPrecedente, RepositorioAtividades repositorioAtividades) {
		
		Utilidade.validaString(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		
		IDatividade id = new IDatividade(idPrecedente);
		
		if(!repositorioAtividades.existeAtividade(id)) {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}
		
		repositorioAtividades.getAtividade(id).tiraProximaAtividade();
		
	}
	/**
	 * Esse método retorna a chave da enesima atividade à atividade passada como parametro
	 * 
	 * @param idAtividade, id da atividade
	 * @param enesimaAtividade, inteiro que aponta a posicao da atividade requisitada
	 * @param repositorioAtividades, repositorio de atividades
	 * @return chave da atividade requisitada
	 */
	public String pegaProximo(String idAtividade, int enesimaAtividade, RepositorioAtividades repositorioAtividades) {
		
		Utilidade.validaString(idAtividade, "Atividade nao pode ser nulo ou vazio.");
		
		if(enesimaAtividade < 1) {
			throw new IllegalArgumentException("EnesimaAtividade nao pode ser negativa ou zero.");
		}
		
		IDatividade id = new IDatividade(idAtividade);
		
		if(!repositorioAtividades.existeAtividade(id)) {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}
		
		Atividade atividade = repositorioAtividades.getAtividade(id).getProximo(enesimaAtividade);
		
		IDatividade idAtv = new IDatividade("");
		
		for (IDatividade ids : repositorioAtividades.getMapAtividades().keySet()) {
			
			if (atividade.equals(repositorioAtividades.getAtividade(ids))) {
				idAtv = ids;
			}
			
		}
		
		return idAtv.getID();
		
	}
	/**
	 * Esse método retorna a chave da atividade com maior risco das astividades subsequentes à atividade passada como parametro
	 * 
	 * @param idAtividade, id da atividade
	 * @param repositorioAtividades, repositorio de atividades
	 * @return chave da atividade com maior risco das astividades subsequentes à atividade passada como parametro
	 */
	public String pegaMaiorRiscoAtividades(String idAtividade, RepositorioAtividades repositorioAtividades) {
		
		Utilidade.validaString(idAtividade, "Atividade nao pode ser nulo ou vazio.");
		
		IDatividade id = new IDatividade(idAtividade);
		
		if(!repositorioAtividades.existeAtividade(id)) {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}
		
		if(!repositorioAtividades.getAtividade(id).temSubsequente()) {
			throw new IllegalArgumentException("Nao existe proxima atividade.");
		}
		
		Atividade atividade = repositorioAtividades.getAtividade(id).getMaiorRisco();
		
		IDatividade idAtv = new IDatividade("");
		
		for (IDatividade ids : repositorioAtividades.getMapAtividades().keySet()) {
			
			if (atividade.equals(repositorioAtividades.getAtividade(ids))) {
				idAtv = ids;
			}
			
		}
		
		return idAtv.getID();
	}
	
}
