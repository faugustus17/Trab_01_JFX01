package com.trab01JFX.modelo;

public class Profissoes {
	
	private int cod_profissao;
	private String descricao;
	
	public int getCod_profissao() {
		return cod_profissao;
	}
	public void setCod_profissao(int cod_profissao) {
		this.cod_profissao = cod_profissao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cod_profissao;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		Profissoes other = (Profissoes) obj;
		if (cod_profissao != other.cod_profissao)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}
}
