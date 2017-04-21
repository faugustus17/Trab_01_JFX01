package com.trab01JFX.modelo;

import java.util.Date;

public class Pessoas {
	private int cod_pessoa;
	private String cpf;
	private String nome_pessoa;
	private Date data_nascimento;
	
	public int getCod_pessoa() {
		return cod_pessoa;
	}
	public void setCod_pessoa(int cod_pessoa) {
		this.cod_pessoa = cod_pessoa;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome_pessoa() {
		return nome_pessoa;
	}
	public void setNome_pessoa(String nome_pessoa) {
		this.nome_pessoa = nome_pessoa;
	}
	public Date getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	
}
