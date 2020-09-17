package utilidades;

/**
 * Classe para tratamento de erros
 * 
 * @author Felipe de Souza Siqueira - 119110399
 */
public class Utilidade {
	/**
	 * Valida parametros do tipo null ou que contenham apenas espaço.
	 * 
	 * @param valor    Valor passado pelo usuário
	 * @param mensagem Mensagem apresentada em caso de erro
	 */
	public static void validaString(String valor, String mensagem) throws NullPointerException {
		if (valor == null) {
			throw new NullPointerException(mensagem);
		}

		if (valor.trim().isEmpty()) {
			throw new IllegalArgumentException(mensagem);
		}
	}
}