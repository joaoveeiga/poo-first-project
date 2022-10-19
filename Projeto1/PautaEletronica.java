package Projeto1;

import java.util.*;

public class PautaEletronica {
	Scanner sc = new Scanner(System.in);
	ArrayList<Aluno> alunos = new ArrayList<Aluno>();

	private void exibirMenu() {
		System.out.println("Menu: \n\n" + "1. Inserir aluno\r\n" + "2. Pesquisar aluno por nome\r\n"
				+ "3. Inserir notas\r\n" + "4. Excluir aluno\r\n"
				+ "5. Relat√≥rio de alunos\r\n 	Nome | Nota 1 | Nota 2 | Nota 3 | M√©dia | Situa√ß√£o\r\n"
				+ "6. Informa√ß√µes da disciplina\n" + "0. Sair\n");
	}

	private int getInt(String s) {
		int input;
		while (!sc.hasNextInt()) {
			System.out.println(s);
			sc.next();
		}
		input = sc.nextInt();
		sc.nextLine();
		return input;
	}

	private double getNota(String s) {
		double input;
		do {
			while (!sc.hasNextDouble()) {
				System.out.println(s);
				sc.next();
			}
			input = sc.nextDouble();
			if (input < 0 || input > 10)
				System.out.println("Nota deve ser entre 0 e 10. Digite novamente a nota: ");
		} while (input < 0 || input > 10);
		return input;
	}

	private int getOptionMenu() {
		exibirMenu();
		System.out.print("Digite a op√ß√£o que voc√™ deseja: ");
		int option = getInt("Digite a op√ß√£o que voc√™ deseja: ");
		System.out.println("\n\n");

		return option;
	}

	private void inserirAluno() {
		Aluno aluno = new Aluno();
		String nome = "";
		System.out.print("Digite o nome do(a) aluno(a): ");
		System.out.println();
		nome = sc.nextLine();

		System.out.println("VocÍ deseja inserir a nota deste aluno?\n1- Sim\n2- N„o");
		int option = getInt("");
		if (option == 1) {
			Double nota1, nota2, nota3;
			System.out.println("Digite a primeira nota do(a) aluno(a): ");
			nota1 = getNota("");
			System.out.println("VocÍ quer digitar outra nota?\n1- Sim\n2- N„o");
			option = getInt("");
			if (option == 1) {
				System.out.println("Digite a segunda nota do(a) aluno(a): ");
				nota2 = getNota("");
				System.out.println("VocÍ quer digitar outra nota?\n1- Sim\n2- N„o");
				option = getInt("");
				if (option == 1) {
					System.out.println("Digite a terceira nota do(a) aluno(a): ");
					nota3 = getNota("");
					aluno = new Aluno(nome, nota1, nota2, nota3);
				} else {
					aluno = new Aluno(nome, nota1, nota2);
				}
			} else {
				aluno = new Aluno(nome, nota1);
			}

		} else
			aluno = new Aluno(nome);
		alunos.add(aluno);
	}

	private String pegarNome() {
		System.out.print("Digite o nome do(a) aluno(a) que vocÍ deseja encontrar: ");
		String nome = sc.nextLine();

		return nome;
	}

	private Aluno pesquisarAlunoPorNome() {
		String nome = pegarNome();

		Aluno alunoEncontrado = new Aluno();
		for (Aluno aluno : alunos) {
			if (aluno.getNome().equals(nome)) {
				alunoEncontrado = aluno;
				break;
			}
		}
		return alunoEncontrado;
	}

	private boolean verificarAlteracaoDeNota() {
		int option;
		System.out.println("Essa nota j· foi preenchida. Tem certeza que deseja alterar?\n1- Sim\n2- N„o");
		option = getInt("Essa nota j· foi preenchida. Tem certeza que deseja alterar?\n1- Sim\n2- N„o");
		return option != 1;
	}

	private void inserirNotas() {
		Aluno aluno = pesquisarAlunoPorNome();
		Double nota;
		int option;

		do {
			System.out.println(
					"Digite a nota que ir· ser alterada.\n1- Primeira Nota\n2- Segunda Nota\n3- Terceira Nota");
			option = getInt("Digite a nota que ir· ser alterada.\n1- Primeira Nota\n2- Segunda Nota\n3- Terceira Nota");
			switch (option) {

			case 1:
				if (aluno.getN1() != -1.0 && verificarAlteracaoDeNota()) {
					break;
				}
				System.out.println("Digite a primeira nota do(a) aluno(a): ");
				nota = getNota("Digite a primeira nota do(a) aluno(a): ");
				aluno.setN1(nota);
				break;

			case 2:
				if (aluno.getN2() != -1.0 && verificarAlteracaoDeNota()) {
					break;
				}
				System.out.println("Digite a segunda nota do(a) aluno(a): ");
				nota = getNota("Digite a segunda nota do(a) aluno(a): ");
				aluno.setN2(nota);
				break;

			case 3:
				if (aluno.getN3() != -1.0 && verificarAlteracaoDeNota()) {
					break;
				}
				System.out.println("Digite a terceira nota do(a) aluno(a): ");
				nota = getNota("Digite a terceira nota do(a) aluno(a): ");
				aluno.setN3(nota);
				break;

			default:
				System.out.println("OpÁ„o inv·lida!");
				break;
			}

			System.out.println("VocÍ deseja preencher mais uma nota? \n1- Sim\n2- N„o");
			option = getInt("VocÍ deseja preencher mais uma nota? \n1- Sim\n2- N„o");
		} while (option == 1);
	}

	private void excluirAluno() {
		String nome = pegarNome();

		alunos.removeIf(aluno -> (aluno.getNome().equals(nome)));
		System.out.println(alunos.toString());
	}

	private String verificarNota(Double nota) {
		if (nota < 0) {
			return "N„o preenchida";
		}
		return nota.toString();
	}

	private void exibirRelatorio() {
		System.out.println(
				"       Nome     |     Nota 1     |     Nota 2     |     Nota 3     |     MÈdia     |     SituaÁ„o");
		int index = 1;
		String nome;
		for (Aluno aluno : alunos) {
			aluno.verificarSituacao();

			if (aluno.getNome().length() > 10) {
				nome = aluno.getNome().substring(0, 10);
			} else {
				nome = aluno.getNome();
				while (nome.length() < 10) {
					nome = nome.concat(" ");
				}
			}

			String s = (index) + ". " + nome + "    " + verificarNota(aluno.getN1()) + "    "
					+ verificarNota(aluno.getN2()) + "    " + verificarNota(aluno.getN3()) + "    "
					+ verificarNota(aluno.getMedia()) + "    " + aluno.getSituacao();
			System.out.println(s);
			index++;
		}
	}

	private void exibirInformacoesDaDisciplina() {
		int quantidadeDeAlunos = alunos.size();
		System.out.println("Nome da disciplina: " + Aluno.nomeDaDisciplina);
		System.out.println("Total de alunos: " + quantidadeDeAlunos);
		Aluno alunoMaiorNota1 = new Aluno();
		Aluno alunoMaiorNota2 = new Aluno();
		Aluno alunoMaiorNota3 = new Aluno();
		Aluno alunoMaiorMedia = new Aluno();

		double mediaGeral = 0;
		int index = 0;

		for (Aluno aluno : alunos) {
			mediaGeral += aluno.calcularMedia();
			if (index == 0) {
				alunoMaiorNota1.setN1(aluno.getN1());
				alunoMaiorNota2.setN2(aluno.getN2());
				alunoMaiorNota3.setN3(aluno.getN3());
				alunoMaiorMedia.setMedia(aluno.getMedia());
				alunoMaiorNota1 = aluno;
				alunoMaiorNota2 = aluno;
				alunoMaiorNota3 = aluno;
				alunoMaiorMedia = aluno;
			}

			if (aluno.getN1() > alunoMaiorNota1.getN1()) {
				alunoMaiorNota1 = aluno;
			}

			if (aluno.getN2() > alunoMaiorNota2.getN2()) {
				alunoMaiorNota2 = aluno;
			}

			if (aluno.getN3() > alunoMaiorNota3.getN3()) {
				alunoMaiorNota3 = aluno;
			}

			if (aluno.getMedia() > alunoMaiorMedia.getMedia()) {
				alunoMaiorMedia = aluno;
			}

			index++;
		}
		mediaGeral /= quantidadeDeAlunos;
		System.out.println("MÈdia geral: " + mediaGeral);
		System.out.println("Maior Nota 1: " + alunoMaiorNota1.getN1() + "\n" + "		Aluno(a): "
				+ alunoMaiorNota1.getNome() + "\n		MÈdia: " + alunoMaiorNota1.getMedia());
		System.out.println("Maior Nota 2: " + alunoMaiorNota2.getN2() + "\n" + "		Aluno(a): "
				+ alunoMaiorNota2.getNome() + "\n		MÈdia: " + alunoMaiorNota2.getMedia());
		System.out.println("Maior Nota 3: " + alunoMaiorNota3.getN3() + "\n" + "		Aluno(a): "
				+ alunoMaiorNota3.getNome() + "\n		MÈdia: " + alunoMaiorNota3.getMedia());
		System.out.println("Maior MÈdia: " + alunoMaiorMedia.getMedia() + "\n" + "		Aluno(a): "
				+ alunoMaiorMedia.getNome() + "\n		MÈdia: " + alunoMaiorMedia.getMedia());
		System.out.println("\n\n");
	}

	public void menu() {
		int option = getOptionMenu();
		Locale.setDefault(Locale.US);

		while (option != 0) {
			switch (option) {
			case 1:
				inserirAluno();
				break;
			case 2:
				pesquisarAlunoPorNome();
				break;
			case 3:
				inserirNotas();
				break;
			case 4:
				excluirAluno();
				break;
			case 5:
				exibirRelatorio();
				break;
			case 6:
				exibirInformacoesDaDisciplina();
				break;
			default:
				System.out.println("VocÍ escolheu uma opÁ„o inv·lida. Por favor, digite novamente.");
				break;
			}
			option = getOptionMenu();
		}
		System.out.println("Obrigado por usar o nosso programa. Valeu, falou!");
	}
}
