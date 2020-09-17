package utilidades;

import java.io.Serializable;
import java.util.Comparator;

public class ComparatorDuracao<Atividade> implements Comparator<Atividade>, Serializable {

	/**
	 * Codigo de serializacao da classe.
	 */
	private static final long serialVersionUID = 7439436324345028346L;

	@Override
	public int compare(Atividade a1, Atividade a2) {
		int retorno = 0;

		if (((atividade.Atividade) a1).getDuracao() > ((atividade.Atividade) a2).getDuracao()) {
			retorno = -1;
		}

		if (((atividade.Atividade) a1).getDuracao() < ((atividade.Atividade) a2).getDuracao()) {
			retorno = 1;
		}

		return retorno;
	}

}
