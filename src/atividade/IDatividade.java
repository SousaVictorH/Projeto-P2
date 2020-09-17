package atividade;

import java.io.Serializable;

public class IDatividade implements Serializable {

	/**
	 * Codigo de serializacao da classe.
	 */
	private static final long serialVersionUID = 6127700345599066057L;
	private String ID;
	
	public IDatividade(String ID) {
		this.ID = ID;
	}
	
	public String getID() {
		return this.ID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IDatividade other = (IDatividade) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}
}
