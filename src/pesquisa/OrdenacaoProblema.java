package pesquisa;

import java.util.Comparator;

/**
 * @author Placido Henrique
 * 
 *         Classe comparator de pesquisas, cujo criterio e o id do problema
 *         associado a pesquisa, em ordem decrescente. O criterio de desempate e
 *         a ordenacao natural das pesquisas.
 *
 */
public class OrdenacaoProblema implements Comparator<Pesquisa> {

	@Override
	public int compare(Pesquisa p1, Pesquisa p2) {
		if (p1.getCodigoProblema().contentEquals("") && p2.getCodigoProblema().contentEquals("")) {
			return p1.compareTo(p2);
		}
		return -p1.getCodigoProblema().compareTo(p2.getCodigoProblema());
	}
}
