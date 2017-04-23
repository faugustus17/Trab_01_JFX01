package com.trab01JFX.modelo;

import java.util.Date;

public class Pessoas {
	private int cod_pessoa;
	private String cpf;
	private String nome_pessoa;
	private Date data_nascimento;
	private int cod_profissao;
	
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
	
	public int getCod_profissao() {
		return cod_profissao;
	}
	public void setCod_profissao(int cod_profissao) {
		this.cod_profissao = cod_profissao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cod_pessoa;
		result = prime * result + cod_profissao;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((data_nascimento == null) ? 0 : data_nascimento.hashCode());
		result = prime * result + ((nome_pessoa == null) ? 0 : nome_pessoa.hashCode());
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
		Pessoas other = (Pessoas) obj;
		if (cod_pessoa != other.cod_pessoa)
			return false;
		if (cod_profissao != other.cod_profissao)
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (data_nascimento == null) {
			if (other.data_nascimento != null)
				return false;
		} else if (!data_nascimento.equals(other.data_nascimento))
			return false;
		if (nome_pessoa == null) {
			if (other.nome_pessoa != null)
				return false;
		} else if (!nome_pessoa.equals(other.nome_pessoa))
			return false;
		return true;
	}
}
