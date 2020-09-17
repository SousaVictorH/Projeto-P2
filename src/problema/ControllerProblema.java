package problema;

import repositorios.RepositorioProblemas;
import utilidades.Utilidade;

/**
 * @author Placido Henrique - 119110389
 * 
 *         Controller da classe Problema, responsavel criar e
 *         manipular os objetos desta classe dentro do sistema.
 */
public class ControllerProblema {

	private int contador;

	/**
	 * Construtor da classe. Inicializa o mapa e o contador com o valor 0.
	 * 
	 * @param repositorioProblemas
	 */
	public ControllerProblema() {
		this.contador = 0;
	}

	/**
	 * Verifica se o inteiro passado vale de 1 a 5. Caso contrario lanca uma excecao
	 * de argumento.
	 * 
	 * @param i   Inteiro que sera verificado.
	 * @param msg Mensagem da excecao.
	 * @throws IllegalArgumentException
	 */
	private void checkInteiro(int i, String msg) throws IllegalArgumentException {
		if (i <= 0 || i > 5) {
			throw new IllegalArgumentException(msg);
		}

	}

	/**
	 * Metodo de geracao do codigo de um problema. Consiste na concatenacao de "P" +
	 * |numero atual do contador|, e apos o processo o contador eh acrescido em 1
	 * unidade.
	 * 
	 * @return um codigo de identificacao.
	 */
	private String geraCodigo() {
		this.contador++;
		String codigo = "P" + Integer.toString(this.contador);
		return codigo;

	}

	/**
	 * Cria e insere um novo problema no mapa de problemas
	 * 
	 * @param descricao   Descricao livre ,em String, do problema.
	 * @param viabilidade Viabilidade do problema em um valor inteiro de 1 a 5.
	 */
	public void cadastraProblema(String descricao, Integer viabilidade, RepositorioProblemas repositorioProblemas) {
		Utilidade.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");

		checkInteiro(viabilidade, "Valor invalido de viabilidade.");

		String codigo = geraCodigo();

		repositorioProblemas.adicionaProblema(codigo, new Problema(codigo, descricao, viabilidade));
	}

	/**
	 * Remove um problema do sistema.
	 * 
	 * @param codigo Codigo do problema que se deseja apagar.
	 */
	public void apagaProblema(String codigo, RepositorioProblemas repositorioProblemas) {
		Utilidade.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (!repositorioProblemas.existeProblema(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}

		repositorioProblemas.removeProblema(codigo);
	}

	/**
	 * Exibe o problema que corresponde ao codigo passado.
	 * 
	 * @param codigo Codigo do problema a ser exibido.
	 * @return A respresentacao textual de um problema em String.
	 */
	public String exibeProblema(String codigo, RepositorioProblemas repositorioProblemas) {
		Utilidade.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (!repositorioProblemas.existeProblema(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}

		return repositorioProblemas.getProblema(codigo).toString();
	}

}
