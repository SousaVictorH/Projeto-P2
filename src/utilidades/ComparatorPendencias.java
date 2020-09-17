package utilidades;

import java.util.Comparator;

public class ComparatorPendencias<Atividade> implements Comparator<Atividade> {

	@Override
	public int compare(Atividade a1, Atividade a2) {
		if (((atividade.Atividade) a1).getItensPendentes() > ((atividade.Atividade) a2).getItensPendentes()) {
			return 1;
		}

		if (((atividade.Atividade) a1).getItensPendentes() < ((atividade.Atividade) a2).getItensPendentes()) {
			return -1;
		}

		return 0;
	}

}
