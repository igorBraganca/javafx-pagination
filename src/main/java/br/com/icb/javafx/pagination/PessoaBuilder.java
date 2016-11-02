package br.com.icb.javafx.pagination;

import java.time.LocalDate;

public class PessoaBuilder {

	private PessoaBuilder() {

	}

	public static Pessoa build(int idade) {
		String nome = "Idade " + idade;
		String genero = (idade % 2 == 0) ? "masculino" : "feminino";
		LocalDate dataNascimento = LocalDate.now().minusYears(idade);

		Pessoa p = new Pessoa(nome, genero, dataNascimento, idade);

		return p;
	}
}
