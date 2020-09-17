package utilidades;

import java.util.ArrayList;

public class Concatenadora {
	public static String concatena(String retorno, String sinal, ArrayList<String> lista) {
	for(int i = 0; i < lista.size(); i++) {
		if(retorno.equals("")) {
			retorno += lista.get(i);
		} else {
			retorno += " | " + lista.get(i);
		}
	}
	return retorno;
	}
}
	


