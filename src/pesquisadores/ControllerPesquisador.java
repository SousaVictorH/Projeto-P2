package pesquisadores;

import java.util.ArrayList;
import repositorios.RepositorioPesquisadores;
import utilidades.Utilidade;

/**
 * Classe que representa o controller dos pesquisadores
 * 
 * @author Davi Andrade Pontes - 119110411 / Victor Hugo Sousa - 119110395
 *
 */
public class ControllerPesquisador {
	
	/**
	 * Cadastra um novo pesquisador no controller dado o seu nome, funcao,
	 * biografia, email e uma URL para uma foto
	 * 
	 * @param nome      Noem do pesquisador
	 * @param funcao    Funcao do pesquisador
	 * @param biografia Biografia do pesquisador
	 * @param email     Email do pesquisador
	 * @param fotoURL   URL para a foto do pesquisador
	 */
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL, RepositorioPesquisadores repositorioPesquisadores) {
		Utilidade.validaString(nome, "Campo nome nao pode ser nulo ou vazio.");

		Utilidade.validaString(funcao, "Campo funcao nao pode ser nulo ou vazio.");

		Utilidade.validaString(biografia, "Campo biografia nao pode ser nulo ou vazio.");

		Utilidade.validaString(email, "Campo email nao pode ser nulo ou vazio.");

		Utilidade.validaString(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");

		String[] validacaoEmail = email.split("@");

		if (validacaoEmail.length != 2 || email.startsWith("@") || email.endsWith("@")) {
			throw new IllegalArgumentException("Formato de email invalido.");
		}

		if (!fotoURL.startsWith("http://") && !fotoURL.startsWith("https://")) {
			throw new IllegalArgumentException("Formato de foto invalido.");
		}

		repositorioPesquisadores.adiconaPesquisador(email, new Pesquisador(nome, funcao, biografia, email, fotoURL));
	}

	/**
	 * Altera um pesquisador do controller, dado o seu email para identifica-lo, o
	 * nome do atributo que se deseja alterar e o novo valor que se deseja atribuir
	 * a esse atributo
	 * 
	 * @param email     Email do pesquisador que se deseja alterar
	 * @param atributo  Nome do atributo que se deseja alterar
	 * @param novoValor Novo valor do atributo
	 */
	public void alteraPesquisador(String email, String atributo, String novoValor, RepositorioPesquisadores repositorioPesquisadores) {
		Utilidade.validaString(email, "Email nao pode ser vazio ou nulo.");

		Utilidade.validaString(atributo, "Atributo nao pode ser vazio ou nulo.");

		if (!repositorioPesquisadores.existePesquisador(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}

		switch (atributo) {
		case "FOTO":
			Utilidade.validaString(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
			if (!novoValor.startsWith("http://") && !novoValor.startsWith("https://")) {
				throw new IllegalArgumentException("Formato de foto invalido.");
			}
			repositorioPesquisadores.getPesquisador(email).setFoto(novoValor);
			break;

		case "BIOGRAFIA":
			Utilidade.validaString(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
			repositorioPesquisadores.getPesquisador(email).setBiografia(novoValor);
			break;

		case "FUNCAO":
			Utilidade.validaString(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
			repositorioPesquisadores.getPesquisador(email).setFuncao(novoValor);
			break;

		case "NOME":
			Utilidade.validaString(novoValor, "Campo nome nao pode ser nulo ou vazio.");
			repositorioPesquisadores.getPesquisador(email).setNome(novoValor);
			break;

		case "EMAIL":
			Utilidade.validaString(novoValor, "Campo email nao pode ser nulo ou vazio.");
			String[] verificacaoEmail = novoValor.split("@");
			if (novoValor.startsWith("@") || novoValor.endsWith("@") || verificacaoEmail.length != 2) {
				throw new IllegalArgumentException("Formato de email invalido.");
			}
			Pesquisador pesquisador = repositorioPesquisadores.getPesquisador(email);
			pesquisador.setEmail(novoValor);
			repositorioPesquisadores.removePesquisador(email);
			repositorioPesquisadores.adiconaPesquisador(novoValor, pesquisador);
			break;

		case "SEMESTRE":
			Utilidade.validaString(novoValor, "Campo semestre nao pode ser nulo ou vazio.");
			repositorioPesquisadores.getPesquisador(email).setSemestre(Integer.parseInt(novoValor));
			break;

		case "IEA":
			Utilidade.validaString(novoValor, "Campo IEA nao pode ser nulo ou vazio.");
			repositorioPesquisadores.getPesquisador(email).setIEA(Double.parseDouble(novoValor));
			break;
			
		case "FORMACAO":
			Utilidade.validaString(novoValor, "Campo nome nao pode ser nulo ou vazio.");
			repositorioPesquisadores.getPesquisador(email).setFormacao(novoValor);
			break;

		case "UNIDADE":
			Utilidade.validaString(novoValor, "Campo nome nao pode ser nulo ou vazio.");
			repositorioPesquisadores.getPesquisador(email).setUnidade(novoValor);
			break;
			
		case "DATA":
			Utilidade.validaString(novoValor, "Campo nome nao pode ser nulo ou vazio.");
			String[] validaData = novoValor.split("/");
			if ((validaData.length != 3) || (validaData[0].length() != 2) || (validaData[1].length() != 2)
					|| (validaData[1].length() != 2) || (Integer.parseInt(validaData[0]) > 31)
					|| (Integer.parseInt(validaData[1]) > 12)) {
				throw new IllegalArgumentException("Atributo data com formato invalido.");
			}
			repositorioPesquisadores.getPesquisador(email).setData(novoValor);
			break;

		default:
			throw new IllegalArgumentException("Atributo invalido.");
		}

	}

	/**
	 * Desativa um pesquisador cadastrado no controller desde que o mesmo esteja
	 * ativo
	 * 
	 * @param email Email do pesquisador que se deseja desativar
	 */
	public void desativaPesquisador(String email, RepositorioPesquisadores repositorioPesquisadores) {
		Utilidade.validaString(email, "Email nao pode ser vazio ou nulo.");

		if (!repositorioPesquisadores.existePesquisador(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}

		if (!repositorioPesquisadores.getPesquisador(email).getStatusDeAtividade()) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}

		repositorioPesquisadores.getPesquisador(email).swapStatusDeAtividade();
	}

	/**
	 * Ativa um pesquisador cadastrado no controller desde que o mesmo esteja
	 * inativo
	 * 
	 * @param email Email do pesquisador que se deseja ativar
	 */
	public void ativaPesquisador(String email, RepositorioPesquisadores repositorioPesquisadores) {
		Utilidade.validaString(email, "Email nao pode ser vazio ou nulo.");

		if (!repositorioPesquisadores.existePesquisador(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}

		if (repositorioPesquisadores.getPesquisador(email).getStatusDeAtividade()) {
			throw new IllegalArgumentException("Pesquisador ja ativado.");
		}

		repositorioPesquisadores.getPesquisador(email).swapStatusDeAtividade();
	}

	/**
	 * Retorna a representacao textual de um pesquisador dado o seu email
	 * 
	 * @param email Email do pesquisador que se deseja ver a representacao textual
	 * @return String que é a representacao textual do pesquisador
	 */
	public String exibePesquisador(String email, RepositorioPesquisadores repositorioPesquisadores) {
		Utilidade.validaString(email, "Campo email nao pode ser nulo ou vazio.");

		if (!repositorioPesquisadores.existePesquisador(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}

		return repositorioPesquisadores.getPesquisador(email).toString();
	}

	/**
	 * Verifica se um pesquisador esta ou nao ativo
	 * 
	 * @param email Email do pesquisador que se deseja saber se esta ou nao ativo
	 * @return True caso esteja ativo, False caso contrário
	 */
	public boolean pesquisadorEhAtivo(String email, RepositorioPesquisadores repositorioPesquisadores) {
		Utilidade.validaString(email, "Email nao pode ser vazio ou nulo.");

		if (!repositorioPesquisadores.existePesquisador(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}

		return repositorioPesquisadores.getPesquisador(email).getStatusDeAtividade();
	}
	
	/**
	 * Esse método cadastra a especialidade aluno
	 * 
	 * @param email,    email do pesquisador
	 * @param semestre, semestre do aluno
	 * @param IEA,      IEA do aluno
	 */
	public void cadastraEspecialidadeAluno(String email, String semestre, String IEA, RepositorioPesquisadores repositorioPesquisadores) {

		utilidades.Utilidade.validaString(email, "Campo email nao pode ser nulo ou vazio.");

		Utilidade.validaString(semestre, "Campo semestre nao pode ser nulo ou vazio.");
		Utilidade.validaString(IEA, "Campo IEA nao pode ser nulo ou vazio.");

		if (!repositorioPesquisadores.existePesquisador(email)) {
			throw new IllegalArgumentException("Pesquisadora nao encontrada.");
		}

		int novoSemestre = Integer.parseInt(semestre);
		double novaIEA = Double.parseDouble(IEA);

		if (novoSemestre <= 0) {
			throw new IllegalArgumentException("Atributo semestre com formato invalido.");
		}

		if ((novaIEA < 0) || (novaIEA > 10)) {
			throw new IllegalArgumentException("Atributo IEA com formato invalido.");
		}

		Pesquisador pesquisador = repositorioPesquisadores.getPesquisador(email);

		if (!pesquisador.getFuncao().equals("estudante")) {
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		}

		pesquisador.setEstudante(novoSemestre, novaIEA);
	}

	/**
	 * Esse método cadastra a especialidade professor
	 * 
	 * @param email,    email do pesquisador
	 * @param formacao, formacao do pesquisador
	 * @param unidade,  unidade do pesquisador
	 * @param data,     data de formacao
	 */
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data, RepositorioPesquisadores repositorioPesquisadores) {

		utilidades.Utilidade.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		utilidades.Utilidade.validaString(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		utilidades.Utilidade.validaString(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		utilidades.Utilidade.validaString(data, "Campo data nao pode ser nulo ou vazio.");

		if (!repositorioPesquisadores.existePesquisador(email)) {
			throw new IllegalArgumentException("Pesquisadora nao encontrada.");
		}

		String[] lista = data.split("/");

		if ((lista.length != 3) || (lista[0].length() != 2) || (lista[1].length() != 2) || (lista[2].length() != 4)) {
			throw new IllegalArgumentException("Atributo data com formato invalido.");
		}

		if ((Integer.parseInt(lista[0]) > 31) || (Integer.parseInt(lista[1]) > 12)) {
			throw new IllegalArgumentException("Atributo data com formato invalido.");
		}

		Pesquisador pesquisador = repositorioPesquisadores.getPesquisador(email);

		if (!pesquisador.getFuncao().equals("professor")) {
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		}

		pesquisador.setProfessor(formacao, unidade, data);
	}

	/**
	 * Esse método lista os pesquisadores com o tipo passado como parametro
	 * 
	 * @param tipo, tipo dos pesquisadores a serem listados
	 * @return lista de pesquisadores com o tipo passado como parametro
	 */
	public String listaPesquisadores(String tipo, RepositorioPesquisadores repositorioPesquisadores) {

		utilidades.Utilidade.validaString(tipo, "Campo tipo nao pode ser nulo ou vazio.");

		if (!((tipo.equals("EXTERNO")) || (tipo.equals("ALUNO")) || (tipo.equals("PROFESSOR")))) {
			throw new IllegalArgumentException("Tipo " + tipo + " inexistente.");
		}

		ArrayList<Pesquisador> lista = new ArrayList<>();

		if (tipo.equals("EXTERNO")) {
			for (Pesquisador i : repositorioPesquisadores.getLista()) {
				if (i.getFuncao().equals("externo")) {
					lista.add(i);
				}
			}
		}

		else if (tipo.equals("ALUNO")) {
			for (Pesquisador i : repositorioPesquisadores.getLista()) {
				if (i.getFuncao().equals("estudante")) {
					lista.add(i);
				}
			}
		}

		else if (tipo.equals("PROFESSOR")) {
			for (Pesquisador i : repositorioPesquisadores.getLista()) {
				if (i.getFuncao().equals("professor")) {
					lista.add(i);
				}
			}
		}

		String string = "";

		for (Pesquisador i : lista) {

			if (string.equals("")) {
				string += i.toString();
			} else {
				string += " | " + i.toString();
			}

		}

		return string;

	}
	
}
