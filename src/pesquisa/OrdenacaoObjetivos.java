package pesquisa;

import java.util.Comparator;

/**
 * @author Placido Henrique
 * 
 *         Classe comparator de pesquisas, cujo criterio e o numero de objetivos
 *         associados a pesquisa, em ordem decrescente. O criterio de desempate
 *         e a ordenacao natural das pesquisas.
 *
 */
public class OrdenacaoObjetivos implements Comparator<Pesquisa> {

	@Override
	public int compare(Pesquisa p1, Pesquisa p2) {
		if (p1.getObjetivosSize() == 0 && p2.getObjetivosSize() == 0) {
			return p1.compareTo(p2);
		}
		return -(p1.getObjetivosSize() - p2.getObjetivosSize());
	}

}
