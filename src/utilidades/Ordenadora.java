package utilidades;


import java.util.Comparator;

import atividade.IDatividade;


public class Ordenadora implements Comparator<Object>{
	
	@Override
	public int compare(Object obj1, Object obj2) {
		
		if(obj1 instanceof IDatividade) {
			IDatividade A1 = (IDatividade) obj1;
			IDatividade A2 = (IDatividade) obj2;
			return -A1.getID().compareTo(A2.getID());
		} else if(obj1 instanceof String) {
			String S1 = (String) obj1;
			String S2 = (String) obj2;
			return -S1.compareTo(S2);
		}
		return 0;
	}
}