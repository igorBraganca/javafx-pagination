package br.com.icb.javafx.pagination;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pessoa {
	private String nome;
	private StringProperty genero;
	private LocalDate dataNascimento;
	private IntegerProperty idade;

	public Pessoa(String nome, String genero, LocalDate dataNascimento, Integer idade) {
		super();
		this.nome = nome;
		this.genero = new SimpleStringProperty(genero);
		this.dataNascimento = dataNascimento;
		this.idade = new SimpleIntegerProperty(idade);
	}

	public String getNome() {
		return nome;
	}

	public StringProperty getGenero() {
		return genero;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public IntegerProperty getIdade() {
		return idade;
	}

}
