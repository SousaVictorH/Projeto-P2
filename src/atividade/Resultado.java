package atividade;

import java.io.Serializable;

/**
 * 
 * @author davi
 *
 */
public class Resultado implements Serializable {
	/**
	 * 
	 */
	private String resultado;

	/**
	 * 
	 */
	private int id;

	public Resultado(String resultado, int id) {
		this.resultado = resultado;

		this.id = id;
	}

	@Override
	public String toString() {
		return resultado;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Resultado) {
			Resultado r = (Resultado) obj;

			if (this.getResultado().equals(r.getResultado())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.getResultado().hashCode();
	}

	public int getId() {
		return id;
	}

	public String getResultado() {
		return resultado;
	}

}
