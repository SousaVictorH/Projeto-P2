package utilidades;

import java.util.Comparator;

public class ComparatorRisco<Atividade> implements Comparator<Atividade> {

	@Override
	public int compare(Atividade a1, Atividade a2) {
		if (((atividade.Atividade) a1).getNivelRisco().equals("BAIXO")) {
			if (!((atividade.Atividade) a2).getNivelRisco().equals("BAIXO")) {
				return 1;
			}
		}

		if (((atividade.Atividade) a1).getNivelRisco().equals("MEDIO")) {
			if (((atividade.Atividade) a2).getNivelRisco().equals("BAIXO")) {
				return -1;
			}

			if (((atividade.Atividade) a2).getNivelRisco().equals("ALTO")) {
				return 1;
			}
		}

		if (((atividade.Atividade) a1).getNivelRisco().equals("ALTO")) {
			if (!((atividade.Atividade) a2).getNivelRisco().equals("ALTO")) {
				return -1;
			}
		}

		return 0;
	}

}
