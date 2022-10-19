package Projeto1;

public class Aluno {
	private String nome;
	private Double n1 = -1.0;
	private Double n2 = -1.0;
	private Double n3 = -1.0;
	private Double media = -1.0;
	public static String nomeDaDisciplina = "Programação Orientada a Objetos";
	private String situacao = "";

	public Aluno() {

	}

	public Aluno(String nome, double n1, double n2, double n3) {
		this.nome = nome;
		this.n1 = n1;
		this.n2 = n2;
		this.n3 = n3;
	}

	public Aluno(String nome, double n1, double n2) {
		this.nome = nome;
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public Aluno(String nome, double n1) {
		this.nome = nome;
		this.n1 = n1;
	}
	
	public Aluno(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getN1() {
		return this.n1;
	}

	public void setN1(Double n1) {
		this.n1 = n1;
	}

	public Double getN2() {
		return n2;
	}

	public void setN2(Double n2) {
		this.n2 = n2;
	}

	public Double getN3() {
		return this.n3;
	}

	public void setN3(Double n3) {
		this.n3 = n3;
	}

	public Double getMedia() {
		return this.media;
	}

	public void setMedia(Double media) {
		this.media = media;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	private Double procurarMenorNota() {
		if (this.getN1() < this.getN2() && this.getN1() < this.getN3()) {
			return this.getN1();
		}

		if (this.getN2() < this.getN1() && this.getN2() < this.getN3()) {
			return this.n2;
		}

		if (this.getN3() < this.getN2() && this.getN3() < this.getN1()) {
			return this.n3;
		}
		return 0.0;
	}

	public Double calcularMedia() {
		double menor = procurarMenorNota();

		if (menor == n1) {
			setMedia((n2 + n3) / 2);
		}
		if (menor == n2) {
			setMedia((n1 + n3) / 2);
		}
		if (menor == n3) {
			setMedia((n1 + n2) / 2);
		}

		return this.media;
	}

	public String verificarSituacao() {
		this.calcularMedia();

		if (media >= 6) {
			setSituacao("Aprovado");
		}
		else if (media >= 4 && n3 == -1) {
			setSituacao("Em recuperação");
		}

		else if ((media < 4) || (media < 6 && n3 != -1)) {
			setSituacao("Reprovado");
		}

		return getSituacao();
	}

}
